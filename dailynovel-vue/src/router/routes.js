import memberLayout from '../components/member/Layout.vue';


import Login from '../components/user/Login.vue';
import LoginLayout from '../components/user/Inc/Layout.vue';
import Signup from '../components/user/Signup.vue';

import signupSocial from '../components/user/SignupSocial.vue';
import Achievement from '../components/member/Insights/Achievements.vue';
import Chart from '../components/member/Insights/Chart.vue';

import guestbook from './guestbook.js';
import collection from './collection.js';
import display from './display.js';
import diary from './diary.js';
import follow from './follow.js';
import profile from './profile.js';
import { createRouter, createWebHistory } from 'vue-router';

import InfinityScroll from '../components/member/display/InfinityScroll.vue';
import { useUserDetailsStore } from "../components/store/useUserDetailsStore.js";

const routes = [

  {
    path: '/', redirect: "login", component: LoginLayout, children: [
      { path: 'signup', component: Signup },
      { path: 'signupsocial', component: signupSocial },
      { path: 'login', component: Login },
      {
        path: 'error', children: [
          { path: '403', component: () => import("./error/403.vue") }
        ]
      },
      {
        path: "/:pathMatch(.*)*",
        component: () => import("./error/404.vue")
      }
    ]
    //pathMatch,동적인  import-> 미리로드하지 않고 쓸지 않쓸지 모르는 걸 위한 Lazy로딩법
  },
  {
    path: '/member', children: [
      {
        path: 'room', component: memberLayout, children: [
          diary,
          { path: 'achievement', component: Achievement },
          { path: 'chart', component: Chart },
          collection,
          guestbook,
          ...display,
          profile,
          follow,
        ]
      },

      
    ]
  }
];



const router = createRouter({
  history: createWebHistory(),
  routes,
});


router.beforeEach((to, from, next) => {
  // 현재 라우트가 '/member/room'인 경우에만 캔버스 요소에 포커스 설정
  if (to.path === '/member/room') {
    setTimeout(() => {
      const canvasElement = document.getElementById('canvas');
      if (canvasElement) {
        canvasElement.focus();
      }
      next();
    }, 0);
  } else {
    next();
  }
});

  // 이런식으로 해야하지 않을까 싶어요 (05.09 재준)
  // { path: '/member', children:[
  //   {path: 'room', component: Layout, children:[
  //     diary,
  //     guestbook
  //   ]}
  // ]}




export default routes;