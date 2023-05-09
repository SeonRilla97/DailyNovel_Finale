import memberLayout from '../components/member/Layout.vue';


import Login from '../components/user/Login.vue';
import LoginLayout from '../components/user/Inc/Layout.vue';
import Signup from '../components/user/Signup.vue';

import DiaryLayout from '../components/member/diary/Layout.vue'
import memberGuestBookLayout from '../components/member/guestbook/Layout.vue';

import Achievement from '../components/member/Achievements/Achievements.vue';
import Chart from '../components/member/chart/Chart.vue';


const routes =  [
  { path: '/user', component: LoginLayout, children:[
      {path: 'login', component:Login},
      {path: 'signup', component:Signup}
    ]
  },

  { path: '/member', component: memberLayout, children :[
      {path: 'diary', component:DiaryLayout},
      {path: 'achievement', component:Achievement},
      {path: 'chart', component:Chart}
    ]},
      {path: 'guestbook', component:memberGuestBookLayout}
    ]


export default routes;