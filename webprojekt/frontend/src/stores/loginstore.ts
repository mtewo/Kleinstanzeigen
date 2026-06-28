import { reactive, readonly, computed } from 'vue';
import { defineStore } from 'pinia';

export const useLogin = defineStore ('loginstore',() => {
    const loginState = reactive<{ username: String, loggedIn: boolean}>({
        username: '',
        loggedIn: false
    });

    function logout(): void {
        loginState.username = " "
        loginState.loggedIn = false
    }

    function login(username: string, passwort: string): void {
          if (username !== '' && passwort !== '') {
            loginState.username = username
            loginState.loggedIn = true
        } else {
            logout()
        }
    }

    return {
        loginState, 
        login,
        logout
    }
})