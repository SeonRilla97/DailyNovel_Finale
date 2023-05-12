import Layout from '../components/member/display/Layout.vue';
import Detail from '../components/member/display/Detail.vue';
import DetailInterface from '../components/member/display/DetailInterface.vue';

// export default [
//   {path : 'community', component:Layout, children:[
//     {path: ':id', component:DetailLayout  },
//   ]},
// ]
export default [
  {
    path: 'community', 
    component:Layout, 
    children:[
    ]
  },
  {
    path: 'community/:id',
    name: 'detailInterface',
    component:DetailInterface
  },
  // {
  //   path: 'community/:id', 
  //   // path: '',
  //   name: 'detail',
  //   component:Detail 
  // },


];
