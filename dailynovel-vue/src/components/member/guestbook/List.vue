<script setup>
import { onBeforeMount, reactive } from 'vue';

let guestbooks = reactive({
  list: null
});

// let json = reactive("");

onBeforeMount(async () => {
  let response = await fetch("http://localhost:8080/members/guestbooks/all",
    {
      method: "POST",
      headers: {
        "Accept": "application/json",
        "Content-type": "application/x-www-form-urlencoded"
      },
    });

  guestbooks.list = await response.json();
  console.log(guestbooks.list);
  // console.log(guestbooks[0].content);


});

// function loadGuestBooks() {
//   fetch("http://localhost:8080/members/guestbooks/all");
// }


</script>
<template>
  <main>
    <ul class="m-guestbook-content-list">
      <!-- <li class="lc-center" v-for="n in 20"> -->
      <li class="lc-center" v-for="item in guestbooks.list">
        <div class="m-guestbook-content-item shadow-1">
          <div class="m-guestbook-item-header">
            <div class="m-guestbook-content-title"><span>방명록 제목</span></div>
            <div class="m-guestbook-content-writer"><span>{{ item.writerId }}</span></div>
          </div>
          <div class="m-guestbook-content-text"><span>{{ item.content }}</span></div>
          <div class="m-guestbook-content-comment"><span>{{ '방명록 답글' }}</span></div>
        </div>
      </li>
    </ul>

  </main>
</template>

<style scoped>
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

@media (max-width: 768px) {
  .m-guestbook-content-list {
    grid-template-columns: repeat(2, 1fr);
    gap: 0.5rem;
  }
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