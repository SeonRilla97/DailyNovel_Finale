<script setup>

let message = "오늘 선릴라의 점심은 무엇일까요? 길이 길어지게 되면 두줄로 늘어나나요? 나중에 상태매세지 제한도 걸어야겟죠~";
</script>
<template>
  <main class="m-profile-index-container">
    <div class="m-profile-index-profile shadow-1">
      <div class="m-profile-index-profile-imgBox">
        <div class="m-profile-index-profile-img"></div>
      </div>
      <div class="m-profile-index-profile-messageBox">
        <div class="m-profile-index-profile-message">
          <span class="h3">{{ message }}</span>
        </div>
      </div>
    </div>
    <div class="m-profile-index-info">
      <div class="m-profile-index-info-detail shadow-1">
        <div class="">
          <span>하루를 정리한 수 </span>
          <span>{{ 12 }}</span>
          <span style="color: red;">{{ "그림자 줄이셔야 합니다~" }}</span>
        </div>
        <div class="">
          <span>공유한 일기 수</span>
          <span>{{ 34 }}</span>
        </div>
        <div class="">
          <span>만든 컬렉션의 수</span>
          <span>{{ 56 }}</span>
        </div>
      </div>
      <div class="m-profile-index-info-follow shadow-1">
        div class
      </div>
    </div>
  </main>
</template>
<style scoped>
.m-profile-index-container {
  height: 100%;
  padding: 0.5rem;
  display: grid;
  grid-template-areas:
    "profile info info"
    "profile info info"
    "profile info info";
  grid-template-columns: 1fr 1.2fr 1.2fr;
  grid-template-rows: 1fr 1fr 1fr;

  box-sizing: border-box;
}

.m-profile-index-profile {
  margin: 1rem;
  grid-area: profile;

  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 2fr 1fr;

  background-color: #F9FAFA;

  border: 1px solid #E6E7E7;
  border-radius: 12px;

  box-sizing: border-box;
}

.m-profile-index-profile-imgBox {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.m-profile-index-profile-img {
  width: 16rem;
  height: 16rem;
  border-radius: 120px;

  background-size: cover;
  background-position: center;
  background-color: aqua;
  background-image: url(../../../assets/img/temp/tempProfile.jpg);
  background-repeat: no-repeat;
}

.m-profile-index-profile-messageBox {
  display: flex;
  justify-content: center;
  align-items: center;
}

.m-profile-index-profile-message {
  margin: 1rem;
}

.m-profile-index-info {
  margin: 1rem;
  grid-area: info;
  /* border: 2px solid black; */
  border-radius: 12px;

  display: grid;
  grid-template-areas:
    "detail"
    "follow";
  grid-template-rows: 2fr 1fr;
  gap: 1rem;
}

.m-profile-index-info-detail {
  border: 1px solid #E6E7E7;
  border-radius: 12px;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;

  background-color: #F9FAFA;
}

.m-profile-index-info-follow {
  border: 1px solid #E6E7E7;
  border-radius: 12px;

  background-color: #F9FAFA;
}
</style>
