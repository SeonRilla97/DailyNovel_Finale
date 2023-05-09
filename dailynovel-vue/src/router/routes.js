import memberLayout from '../components/member/Layout.vue';
  import DiaryLayout from '../components/member/diary/Layout.vue'


import memberGuestBookLayout from '../components/member/guestbook/Layout.vue';
import Index from '../components/Index.vue';

import guestbook  from './guestbook.js';

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
      // {path: 'guestbook', component:memberGuestBookLayout},
      guestbook,
    ]}

  // 이런식으로 해야하지 않을까 싶어요 (05.09 재준)
  // { path: '/member', children:[
  //   {path: 'room', component: Layout, children:[
  //     diary,
  //     guestbook
  //   ]}
  // ]}

]

export default routes;