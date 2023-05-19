<script setup>
// import Index from './Index.vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';

let userDetails = useUserDetailsStore();

let name = userDetails.nickname;
</script>

<template>
  <section class="m-profile-container">

    <header class="m-profile-header">
      <div class="m-profile-header-info">
        <div class="m-profile-header-profile">
          <!-- <div class="m-profile-header-profile-img">프로필이미지</div> -->
          <div class="m-profile-header-profile-name">
            <span class="font-bold h2">{{ name }}님의 페이지</span>
          </div>
        </div>
        <hr style="height: 50%; margin:0;">

        <router-link to="/member/room/profile/index">
          <span class="font-bold h2">메인</span>
        </router-link>

        <router-link to="/member/room/profile/guestbook">
          <span class="font-bold h2">방명록</span>
        </router-link>

        <router-link to="/member/room/profile/follow">
          <span class="font-bold h2">구독목록</span>
        </router-link>

        <router-link to="/member/room/profile/export">
          <span class="font-bold h2">내보내기</span>
        </router-link>
      </div>

    </header>

    <main class="m-profile-main">
      <router-view></router-view>
    </main>


  </section>
</template>
<style scoped>
.m-profile-container {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 1fr 8fr;

  box-sizing: border-box;
}

.m-profile-header {
  /* display: grid;
  grid-template-columns: 1fr 1fr; */
  min-height: 72px;
  display: flex;
  align-items: center;
  gap: 1rem;

  padding-left: 1rem;
  padding-right: 1rem;
  box-sizing: border-box;

}

.m-profile-header-info {
  display: flex;
  height: 100%;
  align-items: center;
  gap: 2rem;

  padding-left: 1rem;
  padding-right: 1rem;
  box-sizing: border-box;
}

.m-profile-header-profile {
  padding-left: 1rem;
  display: flex;
  gap: 1.2rem;
}

.m-profile-header-profile-img {
  width: 52px;
  height: 52px;

  border-radius: 30px;
  background-size: cover;
  background-position: center;
  background-color: aqua;
  background-image: url(../../../assets/img/temp/tempProfile.jpg);
  background-repeat: no-repeat;

  align-self: center;

  text-indent: -999rem;
}

.m-profile-header-profile-name {
  display: flex;
  justify-content: center;
  align-items: center;
}

.m-profile-header-btn {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-right: 3rem;
}

.m-profile-main {
  overflow: hidden;
  box-sizing: border-box;

}
</style>