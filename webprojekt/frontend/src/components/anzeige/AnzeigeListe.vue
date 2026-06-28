<script setup lang="ts"> /*<!--sonst import type nut in typescript files-->*/
    import type {IAnzeigeDTD} from '@/stores/IAnzeige.ts'
    import AnzeigeListeZeile from './AnzeigeListeZeile.vue'

    defineProps<{
    anzeigen:IAnzeigeDTD[]
    }>() /*Tabellen-Komponente empfängt ihre Props: ARRAY!*/ 
</script>
<template> 
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Titel</th>
                <th>Preis</th>
                <th>Verfügbar</th>
                <th>Ablaufdatum</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <!--iteriert übers Array und gibt für jedes AnzeigeID eine Zeile aus-->
            <AnzeigeListeZeile 
                v-for="anzeige in anzeigen"
                :key="anzeige.id"
                :anzeige="anzeige"
            />

        </tbody>
    </table>
</template>
