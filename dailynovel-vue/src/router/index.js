import { createRouter, createWebHistory } from 'vue-router'
import { useUserDetailsStore } from "../components/store/useUserDetailsStore.js";


import routes from './routes.js'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes

});
router.beforeEach((to, from, next) => {
  const userDetail = useUserDetailsStore(); // 사용자의 권한 정보를 가져옵니다. 이 부분은 프로젝트에 맞게 구현되어야 합니다.
  
  // '/member'로 시작하는 경로에 대해서만 권한 체크를 수행합니다.
  if (to.path.startsWith('/member')) {
    console.log(userDetail.hasRole("MEMBER"));
    if (userDetail.hasRole("MEMBER")) {
      // 권한이 있는 경우에는 다음으로 진행합니다.
      next();
    } else {
      // 권한이 없는 경우에는 리디렉션 또는 접근 차단을 수행할 수 있습니다.
      next('/login'); // 로그인 페이지로 리디렉션
      // 또는
      // next(false); // 접근 차단
    }
  } else {
    // '/member'로 시작하지 않는 경로는 권한 체크를 수행하지 않고 다음으로 진행합니다.
    next();
  }
});


export default router
