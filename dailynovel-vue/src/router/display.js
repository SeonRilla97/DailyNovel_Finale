import Layout from '../components/member/display/Layout.vue';
import Detail from '../components/member/display/Detail.vue';
import DetailInterface from '../components/member/display/DetailInterface.vue';
import InfinityScroll from '../components/member/display/InfinityScroll.vue';


// export default [
//   {path : 'community', component:Layout, children:[
//     {path: ':id', component:DetailLayout  },
//   ]},
// ]
export default [
  {
    path: 'community', 
    component:Layout, 
    props: true,
    children:[
    ]
  },
  {
    path: 'community/:id',
    name: 'detailInterface',
    props: true,
    component:DetailInterface
  },
  {
    path: 'InfinityScroll',
    component:InfinityScroll
  },
  // {
  //   path: 'community/:id', 
  //   // path: '',
  //   name: 'detail',
  //   component:Detail 
  // },


];
