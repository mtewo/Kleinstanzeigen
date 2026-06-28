<template>
  <div>
    <div>
      <h2 class="title">Unsere aktuellen Anzeigen</h2>
      <input 
        v-model="suchtext"
        type="text"
        placeholder="Suche..."
      />
      <button @click="resetSuche"> Reset </button>

      <AnzeigeListe :anzeigen="gefilterteAnzeigen"/>

    </div>
    
    <div>
      <!--<AnzeigeListe :anzeigen="anzeigeStore.anzeigedata.anzeigeliste"></AnzeigeListe> --><!-- :der Wert wird als JS-Ausdruck übergeben (reaktive Bindung)-->
    </div> 
  </div>
</template>



<script setup lang="ts">
  import AnzeigeListe from '@/components/anzeige/AnzeigeListe.vue'
  import { ref, computed, onMounted } from 'vue';
  import { useAnzeigeStore } from '@/stores/anzeigestore';

  const anzeigeStore = useAnzeigeStore()
  const suchtext = ref ('')

  onMounted(() => {
    anzeigeStore.updateAnzeigeListe()
  })

  const gefilterteAnzeigen = computed(() => {
    if (suchtext.value.trim() === ''){
      return anzeigeStore.anzeigedata.anzeigeliste
    }

    const suche = suchtext.value.toLowerCase()

    return anzeigeStore.anzeigedata.anzeigeliste.filter((anzeige) =>
      anzeige.titel.toLowerCase().includes(suche) || anzeige.beschreibung.toLowerCase().includes(suche)
    )
  })

  function resetSuche() {
    suchtext.value = ''
  }

</script>



<style scoped></style>
