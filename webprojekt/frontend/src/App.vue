<script setup lang="ts">
import { ref } from 'vue';
import AnzeigeListeView from './views/AnzeigeListeView.vue';

const info = ref('Beachten Sie unsere aktuellen Anzeigen mit Sonderangeboten zur Ball-WM')


</script>

<template>


  <nav>
      <h1>Kleinstanzeigen</h1>
      <a href="/admin/benutzer">Benutzer</a>
      <a href="/admin/anzeige">Anzeigen</a>
      <div class="sprache">
            <a href="?sprache=de">DE</a>
            <a href="?sprache=en">EN</a>
            <a href="?sprache=nl">NL</a>
      </div>
  </nav>

  <div v-if="info" class="info" > <!--div ausgeben wenn info nicht leer-->
      {{info}}
      <button @click="info = ''">x</button>
  </div>

    <main>
      <!-- Hier kommt später RouterView oder AnzeigeListeView -->
       <!--AnzeigeListeView einbetten-->
       <AnzeigeListeView />

    </main>
  <footer>
      <p> 2026 Kleinstanzeigen</p>
  </footer>
        
    
  
</template>

<style scoped>

</style>
