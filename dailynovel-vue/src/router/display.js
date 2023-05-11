import Layout from '../components/member/display/Layout.vue';
import Detail from '../components/member/display/Detail.vue';

// export default [
//   {path : 'community', component:Layout, children:[
//     {path: ':id', component:DetailLayout  },
//   ]},
// ]
const route = {
    path: 'community', 
    component:Layout, 
    children:[
      {
        path: 'detail', 
        component:Detail 
      }
    ]
  }

export default route;