import Layout from '../components/member/profile/Layout.vue';
import Index from  '../components/member/profile/Index.vue';
import GuestbookListLayout from '../components/member/guestbook/List.vue';

const route = {path: 'profile', redirect:"room/profile/index" ,component:Layout ,children:[
  {path: 'index', component:Index},
  {path: 'collection'},
  {path: 'guestbook', component: GuestbookListLayout }
]};

export default route;