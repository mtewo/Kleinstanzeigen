<script setup lang="ts">
import type {IAnzeigeDTD} from '@/views/AnzeigeListeView.vue'
import { ref } from 'vue';

defineProps<{
    anzeige: IAnzeigeDTD 
}>() /* jedes AnzeigeListe Element als Prop*/

const offen = ref(false)

function umschalten(){
    offen.value = !offen.value

}
</script>

<template>
    <tr>
        <td>{{anzeige.id}}</td>
        <td>{{ anzeige.titel }}</td>
        <td>{{ anzeige.preis }}</td>
        <td>{{ anzeige.verfuegbar }} von {{ anzeige.anzahl }}</td>
        <td>{{ anzeige.ablaufdatum }}</td>
        <td><button @click="umschalten">+</button></td>

    </tr>
    <tr v-if="offen"> <!--2. tr nur wenn offen-->
        <td colspan="6"> <!--Detailzeile über alle Splaten strecken-->
            <div>{{ anzeige.anbieterName }}</div>
            <div>{{ anzeige.anbieterAdresse }}
                <a :href="'https://nominatim.openstreetmap.org/ui/search.html?q=' 
                    + encodeURI(anzeige.anbieterAdresse)" target="_blank">[Karte]</a>
                    <!--target="_blank" = Karte in einem neuen Tab öffnen-->
            </div>
            <div>{{ anzeige.beschreibung }}</div>
        </td>

    </tr>

</template>