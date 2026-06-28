import { createRouter, createWebHistory } from 'vue-router'
/*import HomeView from '../views/HomeView.vue'*/
import LoginView from '@/views/LoginView.vue'
import AnzeigeListeView from '@/views/AnzeigeListeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
    path : '/login',
    //name: LoginView,
    component: LoginView
    },
    {
      path: '/anzeige',
      //name: AnzeigeListeView,
      component: AnzeigeListeView
    },
    {
      path: '/',
      redirect: '/anzeige'
    }

  ],
})

export default router
