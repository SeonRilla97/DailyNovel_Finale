import memberLayout from '../components/member/Layout.vue';


import Login from '../components/user/Login.vue';
import LoginLayout from '../components/user/Inc/Layout.vue';
import Signup from '../components/user/Signup.vue';

import signupSocial from '../components/user/SignupSocial.vue';
import Achievement from '../components/member/Insights/Achievements.vue';
import Chart from '../components/member/Insights/Chart.vue';

import feeling from '../components/member/Insights/Achievement/feeling.vue';
import honesty from '../components/member/Insights/Achievement/honesty.vue';
import tag from '../components/member/Insights/Achievement/tag.vue';

import guestbook from './guestbook.js';
import collection from './collection.js';
import display from './display.js';
import diary from './diary.js';
import follow from './follow.js';
import profile from './profile.js';
import { createRouter, createWebHistory } from 'vue-router';

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
          { path: 'achievement', component: Achievement, },
          { path: 'chart', component: Chart },
          {path: 'feeling', component: feeling},
          {path: 'honesty', component: honesty},
          {path: 'tag', component: tag},
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


  // 이런식으로 해야하지 않을까 싶어요 (05.09 재준)
  // { path: '/member', children:[
  //   {path: 'room', component: Layout, children:[
  //     diary,
  //     guestbook
  //   ]}
  // ]}




export default routes;