import Layout from '../components/member/follow/Layout.vue';
import GuestbookListLayout from '../components/member/guestbook/List.vue';
import IndexLayout from '../components/member/follow/Index.vue';
// import CollectionLayout from '../components/member/follow/collection/Layout.vue'
import CollectionLayout from'../components/member/follow/collection/Layout.vue'
import ColMain from '../components/member/follow/collection/main.vue'
import ColDetail from '../components/member/follow/collection/detail/route.js'
const route = {path: 'follow/:memberId', component:Layout, children:[
  {path: 'index', component:IndexLayout},
  {path: 'guestbook', component: GuestbookListLayout},
  {path: 'collection', component: CollectionLayout, children:[
    {path:'', component: ColMain,},
    ColDetail
  ]}
]};

export default route;

// , redirect:'/member/room/follow/index/'
// , alias:''
// , alias:'guestbook'