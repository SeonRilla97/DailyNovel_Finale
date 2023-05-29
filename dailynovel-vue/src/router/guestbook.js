// export default guestbook;
import Layout from '../components/member/guestbook/Layout.vue';
import DetailLayout from '../components/member/guestbook/Detail.vue';
import List from '../components/member/guestbook/List.vue';

// const guestbook = {
//   path: 'guestbook', component:Layout};

const route = 
  {
    path: 'guestbook', component:Layout, children:[
      {path: 'list', component:List},
      {path: 'detail', component:DetailLayout  }
    ]
  }

export default route;