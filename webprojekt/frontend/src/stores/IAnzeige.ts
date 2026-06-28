import { ref } from 'vue'
import AnzeigeListe from '@/components/anzeige/AnzeigeListe.vue'

export interface IAnzeigeDTD {
  id: number
  titel: string
  beschreibung: string
  preis: number
  anzahl: number
  verfuegbar: number
  ablaufdatum: string
  anbieterName: string
  anbieterAdresse: string
}