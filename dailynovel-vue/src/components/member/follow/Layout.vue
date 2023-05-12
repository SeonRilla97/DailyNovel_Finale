<script setup>
let name = "선릴라424";
</script>

<template>
  <section class="m-follow-container">

    <header class="m-follow-header">
      <div class="m-follow-header-profile">

        <span class="font-bold h2">{{ name }}님의 페이지</span>
      </div>
      <hr style="height: 50%; margin:0;">

      <router-link to="./collection">
        <span class="font-bold h2 ">컬렉션</span>
      </router-link>

      <router-link to="follow/guestbook">
        <span class="font-bold h2 ">방명록</span>
      </router-link>

      <div>
        <span>구독하기</span>
      </div>
    </header>

    <router-view class="m-follow-main"></router-view>


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
  display: flex;
  align-items: center;
  gap: 1rem;

  padding-left: 1rem;
  padding-right: 1rem;
  box-sizing: border-box;

  /* padding-left: 2rem; */

  /* border-bottom: 1px solid gray; */
}

.m-follow-main {
  overflow: scroll;
  box-sizing: border-box;
  padding: 1rem;
}
</style>