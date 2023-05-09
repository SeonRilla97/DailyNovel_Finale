import memberLayout from '../components/member/Layout.vue';

import Index from '../components/Index.vue';

  import DiaryLayout from '../components/member/diary/Layout.vue'

import Achievement from '../components/member/Achievements/Achievements.vue';
import Chart from '../components/member/chart/Chart.vue';
const routes =  [
  { path: '/', component: Index, children:[
      // {path: 'login', component: },
      // {path: 'account-recovery', component: ,children:[
      //   {path: 'id', component: , },
      //   {path: 'password', component: , }
      // ]},
      // {path: 'sign-up', component: , },
    ]
  },

  { path: '/member', component: memberLayout, children :[
      {path: 'diary', component:DiaryLayout},
      {path: 'achievement', component:Achievement},
      {path: 'chart', component:Chart}
    ]},
  


]

export default routes;