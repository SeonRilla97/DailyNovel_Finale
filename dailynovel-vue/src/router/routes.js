import memberLayout from '../components/member/Layout.vue';

import Index from '../components/Index.vue';

import memberModalLayout from '../components/member/modal/Layout.vue';
  import DiaryLayout from '../components/member/diary/Layout.vue'

import memberGuestBookLayout from '../components/member/guestbook/Layout.vue';
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
    {path: 'modal', component: memberModalLayout, children:[
      {path: 'diary', component:DiaryLayout}
    ]} 
  ]}


]

export default routes;