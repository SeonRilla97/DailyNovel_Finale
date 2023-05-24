import Layout from '../components/member/follow/Layout.vue';
import GuestbookListLayout from '../components/member/guestbook/List.vue';
import IndexLayout from '../components/member/follow/Index.vue';
// import CollectionLayout from '../components/member/follow/collection/Layout.vue'
import CollectionLayout from'../components/member/follow/collection/Layout.vue'
import ColMain from '../components/member/follow/collection/main.vue'
import colDetailLayout from '../components/member/follow/collection/detail/Layout.vue'
import Diary from '../components/member/follow/collection/detail/Diary.vue'
import Comment from '../components/member/follow/collection/detail/Comment.vue'
const route = {path: 'follow/:memberId', component:Layout,children:[
  {path: 'index', component:IndexLayout},
  {path: 'guestbook', component: GuestbookListLayout},
  {path: 'col', component: CollectionLayout, children:[
    {path:'main', component: ColMain,},
  ]},
  {
    path: 'detail/:collectionId',name:"pfdetail", component:colDetailLayout, children:[
      { path: 'diary',name:"pfdetailDiary", component:Diary},
      { path: 'comment', component:Comment}
    ]
  }
]};

export default route;