import Layout from './Layout.vue';
  import Diary from './Diary.vue';
  import Comment from './Comment.vue';

const route = 
  {
    path: 'detail/:collectionId',name:"detail", component:Layout, children:[
      { path: 'diary',name:"detailDiary", component:Diary},
      { path: 'Comment', component:Comment}
    ]
  }

export default route;