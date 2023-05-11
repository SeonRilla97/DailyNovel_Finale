
import Layout from '../components/member/collection/Layout.vue';
  import Main from '../components/member/collection/Main.vue';
  import shared from '../components/member/collection/Shared.vue';
  import DetailLayout from '../components/member/collection/DetailLayout.vue';
    import Diary from '../components/member/collection/Diary.vue';
    import Comment from '../components/member/collection/Comment.vue';

const route = 
  {
    path: 'collection', component:Layout, children:[
      { path: 'main', component:Main},
      { path: 'shared', component:shared},
      {path: 'detail', compoent: DetailLayout, children :[
        {path: 'diary', component: Diary},
        {path: 'comment', component: Comment}
        
      ]}
    ]
  }

export default route;