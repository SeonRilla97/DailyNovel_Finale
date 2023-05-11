// export default guestbook;
import Layout from '../components/member/display/Layout.vue';
import DetailLayout from '../components/member/guestbook/Detail.vue';

// const guestbook = {
//   path: 'guestbook', component:Layout};

const route = 
  {
    path: 'community', component:Layout, children:[
      {path: 'detail', component:DetailLayout  }
    ]
  }

export default route;