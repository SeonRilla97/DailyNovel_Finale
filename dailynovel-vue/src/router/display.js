import Layout from '../components/member/display/Layout.vue';
import Detail from '../components/member/display/Detail.vue';
import DetailInterface from '../components/member/display/DetailInterface.vue';
// import Alert from '../components/member/display/Alert.vue';


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

  // {
  //   path: 'community/:id/alert',
  //   name: 'alert',
  //   props: true,
  //   component:Alert
  // },


];
