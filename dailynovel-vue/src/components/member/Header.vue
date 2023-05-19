<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserDetailsStore } from '../store/useUserDetailsStore.js';


let router = useRouter();
let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
// let nickname = "동동이107"
let nickname = userDetails.nickname;

let alramCount = 15;

defineEmits(["modalOpenHandler"]);

function openPageHandler() {


}

</script>
<template>
  <header class="member-header-container">
    <div class="member-main-header">
      <div class="member-header-logo">
        <div class="member-header-imglogo"></div>
        <div class="member-header-wordlogo"></div>
      </div>
      <div></div>
      <div class="member-header-info" @click="$emit('modalOpenHandler')">
        <div class="member-header-alram header-alram-img">
          <div class="member-header-alram-count">{{ alramCount }}</div>
        </div>
        <div class="member-header-profile">
          <div class="member-header-profile-img">프로필이미지</div>
          <div class="member-header-profile-nickname">{{ nickname }}</div>
        </div>
      </div>
    </div>
  </header>
</template>

<style scoped>
.member-header-container {
  width: 1920px;
  height: 72px;

  background-color: #fcfcfc;

  display: flex;
  justify-content: center;
}

.member-main-header {
  width: 1280px;
  height: 72px;

  display: grid;
  grid-template-columns: 2fr 7fr 2.5fr;

  background-color: #fcfcfc;
}

.member-header-logo {
  display: grid;
  grid-template-columns: 1fr 2fr;

}

.member-header-imglogo {
  width: 100%;
  height: 100%;
  background-image: url(../../assets/img/logo/tempimageLogo.png);
  background-size: 90%;
  background-repeat: no-repeat;
}

.member-header-wordlogo {
  width: 100%;
  height: 100%;
  background-image: url(../../assets/img/logo/wordlogo.png);
  background-size: 120%;
  background-position: center;
  background-repeat: no-repeat;
}

.member-header-info {
  display: grid;
  grid-template-columns: 1fr 3fr;
  gap: 1rem;
}


.member-header-alram {
  width: 72px;
  height: 72px;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr;

}

.member-header-alram-count {
  width: 22px;
  height: 22px;

  grid-column-start: 2;
  grid-column-end: 4;
  grid-row-start: 1;
  grid-row-end: 3;

  display: flex;
  justify-content: center;
  align-items: center;

  justify-self: center;
  align-self: center;

  border-radius: 50px;
  background-color: red;

  color: #fcfcfc;
}

.header-alram-img {
  background-repeat: no-repeat;
  background-position: center;
  background-size: 40%;
  background-image: url("data:image/svg+xml,%3Csvg width='30' height='36' viewBox='0 0 30 36' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M28.443 24.4338C27.8149 22.5197 27.4859 20.5158 27.4859 18.482V13.4873C27.5159 10.317 26.4092 7.23644 24.3156 4.87366C22.222 2.48098 19.3508 0.955645 16.2104 0.566834C14.4757 0.387383 12.7111 0.566834 11.0362 1.1351C9.36137 1.67345 7.83604 2.57071 6.54997 3.76705C5.2639 4.93348 4.2171 6.36909 3.52921 7.95424C2.84131 9.53939 2.4525 11.2442 2.4525 13.0088V18.512C2.4525 20.5158 2.12351 22.5197 1.49543 24.4338L0 28.8603L1.19634 30.5053H9.95954C9.95954 31.8212 10.4979 33.1073 11.4251 34.0345C12.3522 34.9616 13.6383 35.5 14.9543 35.5C16.2702 35.5 17.5563 34.9616 18.4835 34.0345C19.4106 33.1073 19.949 31.8212 19.949 30.5053H28.7122L29.9085 28.8603L28.443 24.4338ZM16.7189 32.2998C16.2403 32.7783 15.6123 33.0475 14.9543 33.0475C14.2963 33.0475 13.6682 32.7783 13.1897 32.2998C12.7111 31.8212 12.4419 31.1932 12.4419 30.5352H17.4367C17.4666 31.1932 17.1974 31.8212 16.7189 32.2998ZM2.93104 28.0229L3.8582 25.2414C4.57601 23.058 4.96482 20.785 4.96482 18.512V13.0088C4.96482 11.6031 5.2639 10.2273 5.83216 8.97113C6.40043 7.68506 7.20796 6.54854 8.25476 5.62138C9.30155 4.6643 10.5278 3.9465 11.8438 3.52778C13.1897 3.07915 14.5954 2.92961 15.9712 3.07915C18.4835 3.40814 20.8163 4.6643 22.4613 6.57845C24.1362 8.49259 25.0035 10.975 24.9736 13.5172V18.5419C24.9736 20.8149 25.3325 23.088 26.0802 25.2713L27.0074 28.0528H2.93104V28.0229Z' fill='%23333333'/%3E%3C/svg%3E%0A");
}

.member-header-profile {
  width: fit-content;

  display: grid;
  grid-template-columns: 1fr 2fr;
}

.member-header-profile-img {
  width: 52px;
  height: 52px;

  border-radius: 30px;
  background-size: cover;
  background-position: center;
  background-color: aqua;
  background-image: url(../../assets/img/temp/tempProfile.jpg);
  background-repeat: no-repeat;

  align-self: center;

  text-indent: -999rem;
}

.member-header-profile-nickname {
  display: flex;
  justify-content: flex-end;
  align-items: center;



  font-size: 18px;
  font-weight: 600;
}
</style>
