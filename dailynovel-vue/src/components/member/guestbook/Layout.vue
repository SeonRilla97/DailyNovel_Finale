<script setup>
import { useRouter } from 'vue-router';
let router = useRouter();

function showDetailHandler() {
  router.push("/member/room/guestbook/detail");
}
</script>

<template>
  <section class="m-guestbook-container">

    <div class="m-guestbook-header">
      <div class="pdl-5">
        <span class="font-bold h2 ">방명록</span>
      </div>
    </div>
    <div class="m-guestbook-main">
      <ul class="m-guestbook-content-list">
        <li class="lc-center" v-for="n in 20" @click="showDetailHandler">
          <div class="m-guestbook-content-item shadow-1">
            <div class="m-guestbook-item-header">
              <div class="m-guestbook-content-title"><span>방명록 제목</span></div>
              <div class="m-guestbook-content-writer"><span>글쓴이</span></div>
            </div>
            <div class="m-guestbook-content-text"><span>방명록 내용</span></div>
            <div class="m-guestbook-content-comment"><span>방명록 답글</span></div>
          </div>
        </li>
      </ul>

    </div>

  </section>
</template>
<style scoped>
.m-guestbook-container {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 1fr 8fr;

  box-sizing: border-box;
}

.m-guestbook-header {
  display: flex;
  justify-content: start;
  align-items: center;

  /* padding-left: 2rem; */

  /* border-bottom: 1px solid gray; */
}

.m-guestbook-title-font {
  font-weight: bold;
  font-size: 1.3rem;
}

.m-guestbook-main {
  overflow: scroll;
  box-sizing: border-box;
  padding: 1rem;
}

.m-guestbook-content-list {
  width: 100%;
  /* height: 100%; */
  box-sizing: border-box;
  border-radius: 16px;

  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
  /* grid-template-rows: 1fr 1fr; */

  background-color: rgba(10, 115, 4, 0.606);

  /* border-radius: 16px; */
  /* overflow: scroll; */

  padding: 1rem;

}

.m-guestbook-content-item {
  display: grid;
  grid-template-rows: 1.5fr 3fr 1fr;

  display: flex;
  flex-direction: column;
  align-items: center;
  /* gap: 16px; */
  width: 80%;
  height: 200px;
  background-color: yellow;
  border-radius: 4px;

  justify-content: space-between;

  box-sizing: border-box;
}

.m-guestbook-item-header {
  display: flex;
}

.m-guestbook-content-title {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;

}

.m-guestbook-content-text {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}

.m-guestbook-content-comment {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
}
</style>