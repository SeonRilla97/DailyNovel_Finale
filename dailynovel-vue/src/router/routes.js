import memberLayout from '../components/member/Layout.vue';
  import DiaryLayout from '../components/member/diary/Layout.vue'


import memberGuestBookLayout from '../components/member/guestbook/Layout.vue';
import Index from '../components/Index.vue';

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
      {path: 'guestbook', component:memberGuestBookLayout},
    ]}

]

export default routes;