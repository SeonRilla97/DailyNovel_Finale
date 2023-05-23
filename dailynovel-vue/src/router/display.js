import Layout from '../components/member/display/Layout.vue';
import Detail from '../components/member/display/Detail.vue';
import DetailInterface from '../components/member/display/DetailInterface.vue';
import Report from '../components/member/display/Report.vue';


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
    path: 'community/Report/:id',
    name: 'Report',
    props: true,
    component:Report
  },


];
