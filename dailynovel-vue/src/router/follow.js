import Layout from '../components/member/follow/Layout.vue';
import GuestbookListLayout from '../components/member/guestbook/List.vue';
import IndexLayout from '../components/member/follow/Index.vue';

const route = {path: 'follow', component:Layout, redirect:'follow/index', children:[
  {path: 'index', component:IndexLayout},
  {path: 'collection'},
  {path: 'guestbook', component: GuestbookListLayout }
]};

export default route;