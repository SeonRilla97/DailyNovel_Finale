<script setup>
import { onBeforeMount, reactive, ref } from 'vue';

// 방명록 리스트 불러오기
let guestbooks = reactive({
  list: null
});

let guestbook = {

}

let writerId = ref("");

fetch("http://localhost:8080/members/guestbooks/all",
  {
    method: "POST",
    headers: {
      "Accept": "application/json",
      "Content-type": "application/x-www-form-urlencoded"
    },
  })
  .then(response => response.json())
  .then((data) => guestbooks.list = data);


fetch(`http://localhost:8080/members/info?id=2`,
  {
    method: "GET",
  })
  .then((response) => response.json())
  .then((data) => console.log(data));


// 방명록 답글 불러오기
let comment = ref("");
// let json = reactive("");
fetch("http://localhost:8080/members/guestbooks/comment", {
  method: "POST",
  headers: {
    "Accept": "application/json",
    "Content-type": "application/x-www-form-urlencoded"
  },
  // body: JSON.stringify(data),
})
  .then((response) => response.json())
  .then((data) => comment = data.content);

// function getComments(callback, errorHandling) {
//   fetch("!!")
//     .then((res) => res.json())
//     .then(callback)
//     .catch(errorHandling)
// }

// function displayComment(data) {
//   comment.value = data.content
// }

// function 샬라샬라() {

// }

// getComments(displayComment);


  // .then(console.log(guestbooks));

// guestbooks.list = await response.json();
// let json = await response.json();
// console.log(json);

// guestbooks = json;

// console.log(guestbooks);




</script>
<template>
  <main>
    <ul class="m-guestbook-content-list">
      <!-- <li class="lc-center" v-for="n in 20"> -->
      <!-- <li class="lc-center" v-for="item in guestbooks.list"> -->
      <li class="lc-center" v-for="item in guestbooks.list">
        <div class="m-guestbook-content-item">
          <div class="m-guestbook-item-header">
            <span>From.</span>
            <div class="m-guestbook-content-writer"><span>{{ item.writerId }}</span></div>
          </div>
          <div class="m-guestbook-content-text"><span>{{ item.content }}</span></div>
          <div class="m-guestbook-content-comment"><span>{{ comment }}</span></div>
        </div>
      </li>
    </ul>

  </main>
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
  width: 230px;
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

.m-guestbook-content-comment {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 1rem;
}
</style>