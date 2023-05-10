import memberLayout from '../components/member/Layout.vue';


import Login from '../components/user/Login.vue';
import LoginLayout from '../components/user/Inc/Layout.vue';
import Signup from '../components/user/Signup.vue';

import DiaryLayout from '../components/member/diary/Layout.vue'
import memberGuestBookLayout from '../components/member/guestbook/Layout.vue';

import Achievement from '../components/member/Achievements/Achievements.vue';
import Chart from '../components/member/chart/Chart.vue';

import guestbook  from './guestbook.js';

const routes =  [
  { path: '/user', component: LoginLayout, children:[
      {path: 'login', component:Login},
      {path: 'signup', component:Signup}
    ]
  },

  { path: '/member', component: memberLayout, children :[    
      {path: 'diary', component:DiaryLayout},
      {path: 'achievement', component:Achievement},
      {path: 'chart', component:Chart},
      guestbook,
    ]}      
 ]


  // 이런식으로 해야하지 않을까 싶어요 (05.09 재준)
  // { path: '/member', children:[
  //   {path: 'room', component: Layout, children:[
  //     diary,
  //     guestbook
  //   ]}
  // ]}



export default routes;