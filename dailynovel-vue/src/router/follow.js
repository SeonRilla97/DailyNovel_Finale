import Layout from '../components/member/follow/Layout.vue';
import GuestbookListLayout from '../components/member/guestbook/List.vue';

const route = {path: 'follow', component:Layout ,children:[
  {path: 'collection'},
  {path: 'guestbook', component: GuestbookListLayout }
]};

export default route;