
import Layout from '../components/member/collection/Layout.vue';
  import Main from '../components/member/collection/Main.vue';
  import shared from '../components/member/collection/Shared.vue';
import Detail from '../components/member/collection/detail/route.js'
const route = 
  {
    path: 'collection', component:Layout, children:[
      { path: 'main', component:Main},
      { path: 'shared', component:shared},
      Detail
        
      
    ]
  }

export default route;