import Layout from '../components/member/display/Layout.vue';
import Detail from '../components/member/display/Detail.vue';
import test from '../components/member/display/test.vue';

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
    name: 'detail',
    component:Detail 
  },
  {
    path: 'test',
    component: test,
  },


];
