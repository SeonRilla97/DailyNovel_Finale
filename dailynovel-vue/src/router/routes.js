import memberLayout from '../components/member/Layout.vue';

import Login from '../components/user/Login.vue';
import LoginLayout from '../components/user/Inc/Layout.vue';
import Signup from '../components/user/Signup.vue';
import memberModalLayout from '../components/member/modal/Layout.vue';
import DiaryLayout from '../components/member/diary/Layout.vue'

const routes =  [
  { path: '/user', component: LoginLayout, children:[
      {path: 'login', component:Login},
      {path: 'signup', component:Signup}
    ]
  },

  { path: '/member', component: memberLayout, children :[
    {path: 'modal', component: memberModalLayout, children:[
      {path: 'diary', component:DiaryLayout}
    ]} 
  ]}


]

export default routes;