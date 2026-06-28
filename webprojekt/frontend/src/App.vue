<script setup lang="ts">
import { ref } from 'vue';
import { RouterView, RouterLink } from 'vue-router';
import AnzeigeListeView from './views/AnzeigeListeView.vue';
import { useInfo } from '@/composables/useInfo'
import LoginView from './views/LoginView.vue';
import { useLogin } from '@/stores/loginstore.ts'

const{ info, loescheInfo, setzeInfo } = useInfo()
const  loginStore  = useLogin()



</script>

<template>


  <nav>
      <h1>Kleinstanzeigen</h1>
      <!--<a href="/admin/benutzer">Benutzer</a>
      <a href="/admin/anzeige">Anzeigen</a>-->
      <RouterLink v-if="loginStore.loginState.loggedIn" to="/anzeige">Anzeigen</RouterLink>
      <div class="sprache">
            <!--<a href="?sprache=de">DE</a>
            <a href="?sprache=en">EN</a>
            <a href="?sprache=nl">NL</a>-->
            <RouterLink to="/login">Login</RouterLink>

      </div>
  </nav>

  <div v-if="info" class="info" > <!--div ausgeben wenn info nicht leer-->
      {{info}}
      <button @click="info = ''">x</button>
  </div>

    <main>
      <!-- Hier kommt später RouterView oder AnzeigeListeView -->
       <!--AnzeigeListeView einbetten <AnzeigeListeView />-->
       <RouterView/>
       
       <!--LoginView einbetten <LoginView/>-->
       

    </main>
  <footer>
      {{loginStore.loginState.username}}
  </footer>
        
    
  
</template>

<style scoped>

</style>
