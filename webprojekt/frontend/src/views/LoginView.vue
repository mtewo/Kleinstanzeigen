<script setup lang="ts">
    import { useLogin } from '@/stores/loginstore'
    import{ ref, reactive } from 'vue'
    import{ useInfo } from '@/composables/useInfo'
    import { useRouter } from 'vue-router' 
  
    // Store and Composable einbinden
    const { loginState, login, logout } = useLogin()
    const { info, setzeInfo, loescheInfo} = useInfo()
    const router = useRouter()

    // Lokale reaktive Variablen für die Eingabefelder
    const username = ref<string>('')
    const passwort = ref<string>('')

    logout()

    function handleLogin(){
        login(username.value, passwort.value)
        if(!loginState.loggedIn){
            passwort.value = ''
            setzeInfo("Jammer - der Login-Versuch war zwar nicht erfolgreich, aber erfolglos")

        } else {
            loescheInfo()
            router.push('/anzeige') // Wechsel zu Anzeigeliste
        }
    }
</script>
<template>
    <input type="text"  v-model="username"> <br/>
    <input type="password"  v-model="passwort"> <br/>
    <button @click="handleLogin">Login</button>
</template>