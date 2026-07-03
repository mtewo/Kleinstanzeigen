import { reactive, readonly, computed } from 'vue';
import { defineStore } from 'pinia';
import type { IAnzeigeDTD } from './IAnzeige';
import { useInfo } from '@/composables/useInfo'
import { Client } from '@stomp/stompjs'
import type { IFrontendNachrichtEvent } from '@/services/IFrontendNachrichtEvent.ts'


export const useAnzeigeStore = defineStore("anzeigestore", () => {
    const anzeigedata = reactive<{ok : boolean; anzeigeliste: IAnzeigeDTD[]}>({
        ok: false, 
        anzeigeliste: []
    });

    let stompClient: Client | null = null;

    async function updateAnzeigeListe (): Promise<void> {
        const { setzeInfo } = useInfo()
        try{
            const response = await fetch('api/anzeige')   // gesamte Liste wird vom Server geholt

            if(response.ok){
                const daten = await response.json()
                anzeigedata.anzeigeliste = daten
                anzeigedata.ok = true
                startAnzeigeLiveUpdate()
            } else {
                anzeigedata.anzeigeliste = []
                anzeigedata.ok = false
                setzeInfo(response.statusText)
            }
        
        }
        catch(error){ // Netzwerkfehler
            anzeigedata.anzeigeliste = []
            anzeigedata.ok = false
            setzeInfo(String(error))
        }
        
    }

    function startAnzeigeLiveUpdate(): void {

        const { setzeInfo } = useInfo()

        if (stompClient) {
            // Es existiert schon ein Client, nichts weiter zu tun
            return
        }

            stompClient = new Client({
                brokerURL: `ws://${window.location.host}/stompbroker`,   // Frontend redet nur mit dem ladenden Server, Vite-proxy leitet /stompbroker ans Backend
                onConnect: () => {
                    console.log('STOMP verbunden')
                    stompClient!.subscribe('/topic/anzeige', async (message) => {
                        const event: IFrontendNachrichtEvent = JSON.parse(message.body)
                        console.log(JSON.stringify(event))

                        /*if (event.typ === 'ANZEIGE') { // bei jedem Event mit typ ANZEIGE schmeisßt ganze Anzeigenliste weg und holt die ganze aktualisierte
                            updateAnzeigeListe()
                        }*/
                         if (event.typ !== 'ANZEIGE') return

                        if (event.operation === 'DELETE') {
                            // lokal entfernen, kein Server-Roundtrip nötig
                            anzeigedata.anzeigeliste = anzeigedata.anzeigeliste.filter(
                            a => a.id !== event.id
                            )
                            return
                        }
                         // CREATE oder UPDATE: einzelnen Datensatz nachladen
                        try {
                            const response = await fetch(`/api/anzeige/${event.id}`)
                            if (!response.ok) {
                            setzeInfo(response.statusText)
                            return
                            }
                            const aktualisiert: IAnzeigeDTD = await response.json()

                            const index = anzeigedata.anzeigeliste.findIndex(a => a.id === event.id)
                            if (index !== -1) {
                            // UPDATE: bestehenden Eintrag ersetzen
                            anzeigedata.anzeigeliste[index] = aktualisiert
                            } else {
                            // CREATE: neuen Eintrag hinzufügen
                            anzeigedata.anzeigeliste.push(aktualisiert)
                            }
                        } catch (e) {
                            setzeInfo('Fehler beim Nachladen der Anzeige')
                            console.log(e)
                        }
                    })
                },
                onStompError: (frame) => {
                    console.log('STOMP Fehler', frame)
                    setzeInfo('Fehlery bei Live-Update-Verbindung: ' + frame.headers['message'])
                },
                onWebSocketError: (event) => {
                    console.log('WebSocket Fehler', event)
                    setzeInfo('Fehler bei Live-Update-Verbindung')
                }
        })

        stompClient.activate()

    }

    return {
        anzeigedata,
        updateAnzeigeListe
    }
 
})
