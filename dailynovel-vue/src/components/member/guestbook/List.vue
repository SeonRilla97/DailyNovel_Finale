<script setup>
import { onBeforeMount, onMounted, onUpdated, reactive, ref } from 'vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';

let userDetails = useUserDetailsStore();
// 방명록 주인 ID
let hostId = userDetails.id;
let guestId = 2;

// 방명록 리스트 불러오기
let guestbooks = reactive({
  list: null
});

let guestbook = reactive({
  memberId: hostId,
  writerId: guestId,
  content: null
});

let gbtojson = JSON.stringify(guestbook);

function writeGuestBookHandler() {
  fetch("http://localhost:8080/members/guestbooks/save",
    {
      method: "POST",
      headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
      },
      body: JSON.stringify(guestbook)
    })
    .then(response => response.json())
    .then((data) => { if (data == 1) console.log("완료") })
    .catch(error => console.log(error));
}

// onUpdated(() => {
//   console.log(guestbooktxt);
// })

onMounted(() => {
  console.log(hostId);
  getGuestbookList();
})


// function getGuestbookList() {
//   fetch("http://localhost:8080/members/guestbooks/list",
//     {
//       method: "POST",
//       headers: {
//         "Accept": "application/json",
//         "Content-type": "application/x-www-form-urlencoded"
//       },
//     })
//     .then(response => response.json())
//     .then((data) => guestbooks.list = data);
// }

function getGuestbookList() {
  fetch(`http://localhost:8080/members/guestbooks?id=${hostId}`,
    {
      method: "GET",
      headers: {
        "Accept": "application/json",
        "Content-type": "application/x-www-form-urlencoded"
      },
    })
    .then(response => response.json())
    .then((data) => guestbooks.list = data);
}

// function saveGuestbook(){
//   fetch("",{
//     method:
//   })
// }

</script>
<template>
  <ul class="m-guestbook-content-list">
    <li class="lc-center" v-show="true">
      <div class="m-guestbook-content-writeForm">
        <textarea class="m-guestbook-content-writeBox" v-model="guestbook.content"></textarea>
        <button @click.prevent="writeGuestBookHandler" type="submit" value="">작성</button>
      </div>
    </li>
    <li class="lc-center" v-for="item in guestbooks.list">
      <div class="m-guestbook-content-item">
        <div class="m-guestbook-item-header">
          <span>From.</span>
          <div class="m-guestbook-content-writer"><span>{{ item.writerName }}</span></div>
        </div>
        <div class="m-guestbook-content-text"><span>{{ item.content }}</span></div>
        <div class="m-guestbook-content-comment"><span>{{ item.comment }}</span></div>
      </div>
    </li>
  </ul>
</template>

<style scoped>
.m-guestbook-content-list {
  width: 100%;
  /* height: 100%; */
  min-height: 100%;
  box-sizing: border-box;
  border-radius: 12px;
  /* border: 3px solid rgba(10, 115, 4, 0.606); */

  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(2, 1fr);
  gap: 1rem;
  /* grid-template-rows: 1fr 1fr; */

  background-color: rgba(10, 115, 4, 0.606);

  background-color: #FFF8F3;

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
  width: 240px;
  height: 346px;
  /* background-color: yellow; */
  background-color: #fafafa;
  border: 2px solid #FCD602;
  border-radius: 8px;

  justify-content: space-between;

  box-sizing: border-box;
}

.m-guestbook-item-header {
  display: flex;
  padding: 1rem;
}

.m-guestbook-content-writer {
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
  padding: 1rem;
}

.m-guestbook-content-writeForm {
  width: 240px;
  height: 346px;

  background-color: #fafafa;
  border: 2px solid #FCD602;
  border-radius: 8px;

  box-sizing: border-box;

  display: grid;
  grid-template-rows: 8fr 1fr;


}

.m-guestbook-content-writeBox {
  width: 90%;
  height: 90%;
  /* background-color: #FCD602; */
  border: 1px solid #FCD602;

  justify-self: center;
}

.m-guestbook-content-comment {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 1rem;
}
</style>