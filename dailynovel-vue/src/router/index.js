import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../components/index.vue'
import About from '../components/about.vue'

import MainLayout from '../components/inc/Layout.vue'

import ModalLayout from '../components/inc/Modal.vue'
  import Diary from '../components/member/diary/Layout.vue'
  import Collection from '../components/member/collection/Layout.vue'
  import Setting from '../components/member/settings/Layout.vue'
  import Community from '../components/member/Community/Layout.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: About
    },
    {
      path: '/main',
      name: 'main',
      component : MainLayout,
      children: [
        {
          path: 'modal',
          name: 'modal',
          component : ModalLayout,
          children :[
            {
              path: 'diary',
              name: 'diary',
              component : Diary
            },
            {
              path: 'collection',
              name: 'Collection',
              component : Collection
            },
            {
              path: 'setting',
              name: 'setting',
              component : Setting
            },
            {
              path: 'Community',
              name: 'Community',
              component : Community
            }
          ]

        }
      ]

  }
  ]
})

export default router
