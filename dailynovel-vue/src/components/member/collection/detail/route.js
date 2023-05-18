import Layout from './Layout.vue';
  import Diary from './Diary.vue';
  import Comment from './Comment.vue';

const route = 
  {
    path: 'detail', component:Layout, children:[
      { path: 'diary', component:Diary},
      { path: 'Comment', component:Comment}
    ]
  }

export default route;