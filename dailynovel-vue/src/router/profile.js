import Layout from '../components/member/profile/Layout.vue';
import Index from  '../components/member/profile/Index.vue';
import Follow from '../components/member/profile/Follow.vue';
import Export from '../components/member/profile/Export.vue';
import Info from '../components/member/profile/Info.vue';
import GuestbookListLayout from '../components/member/guestbook/List.vue';
import Export from  '../components/member/profile/Export.vue';


const route = {path: 'profile', redirect:"room/profile/index" ,component:Layout ,children:[
  {path: 'index', component:Index},
  {path: 'collection'},
  {path: 'guestbook', component: GuestbookListLayout },
  {path: 'follow', component: Follow },
  {path: 'Export', component: Export },
  {path: 'Info', component: Info },
]};

export default route;