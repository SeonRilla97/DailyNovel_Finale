<script setup>
// import Index from './Index.vue';
let name = "선릴라424";
import { useRoute,useRouter  } from 'vue-router'
import {ref, onBeforeMount,onMounted} from 'vue'
// let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
const route = useRoute()
const router = useRouter()

// 현재 누구의 페이지인지 알아야함(userId)
let userId = ref();
let member = ref();

//onBeforeMount(()=>{
  userId.value = route.params.memberId;
  pfgetUserInfo(userId.value);
//});


function pfgetUserInfo(userId){
  console.log("설마 이거 누가 불러?")
  console.log("식빵")
  fetch(`http://localhost:8080/members/info?id=${userId}`)
  .then(response => response.json())
  .then(result => {
    member.value = result;
    router.push(`/member/room/follow/s/index`)
  })
  .catch(error => console.log('error', error));
}
</script>

<template>
  <section class="m-follow-container">

    <header class="m-follow-header">
      <div class="m-follow-header-info">
        <div class="m-follow-header-profile">
          <!-- <div class="m-follow-header-profile-img">프로필이미지</div> -->
          <div class="m-follow-header-profile-name">
            <span class="font-bold h2">{{ member.nickName}} 님의 페이지</span>
          </div>
        </div>
        <hr style="height: 50%; margin:0;">

        <router-link to="/member/room/follow/s/index">
          <span class="font-bold h2">메인</span>
        </router-link>

        <router-link to="/member/room/follow/s/col/main">
          <span class="font-bold h2">컬렉션</span>
        </router-link>

        <router-link to="/member/room/follow/s/guestbook">
          <span class="font-bold h2">방명록</span>
        </router-link>
      </div>

      <div class="m-follow-header-btn">
        <div class="">
          <span>구독하기</span>
        </div>
      </div>
    </header>

    <main class="m-follow-main">
      <router-view
      :userId = "userId"></router-view>
    </main>


  </section>
</template>
<style scoped>
.m-follow-container {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 1fr 8fr;

  box-sizing: border-box;
}

.m-follow-header {
  display: grid;
  grid-template-columns: 1fr 1fr;
  min-height: 72px;
  /* display: flex;
  align-items: center;
  gap: 1rem;

  padding-left: 1rem;
  padding-right: 1rem;
  box-sizing: border-box; */

}

.m-follow-header-info {
  display: flex;
  align-items: center;
  gap: 2rem;

  padding-left: 1rem;
  padding-right: 1rem;
  box-sizing: border-box;
}

.m-follow-header-profile {
  padding-left: 1rem;
  display: flex;
  gap: 1.2rem;
}

.m-follow-header-profile-img {
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

.m-follow-header-profile-name {
  display: flex;
  justify-content: center;
  align-items: center;
}

.m-follow-header-btn {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-right: 3rem;
}

.m-follow-main {
  overflow: auto;
  box-sizing: border-box;

}
</style>