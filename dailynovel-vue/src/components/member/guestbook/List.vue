<script setup>
import { onBeforeMount, onMounted, onUpdated, reactive, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';
import gbCard from './Card.vue';

let userDetails = useUserDetailsStore();
let route = useRoute();
let router = useRouter();

// 방명록 주인 ID
const hostId = userDetails.id;
let guestId = 2;

let mode = ref("");

// 방명록 리스트 불러오기
let guestbooks = reactive({
  list: null
});

let guestbook = reactive({
  memberId: hostId,
  writerId: guestId,
  content: null
});

let guestbookComment = reactive({
  guestbookId: null,
  memberId: hostId,
  content: null
})

// 프로필로부터 해당 프로필의 유저 ID 받아온것 -- 선유진 추가 (이거 이용해서 해당 유저의 방명록 불러와야합니다)
let props = defineProps({
  userId: {
        type: Object,
        required:true
    }
});



onMounted(() => {
  console.log(hostId);
  console.log(props.userId)
  getGuestbookList();



  // 현재 들고온 주소에 따라서 모드 확인
  if (route.fullPath.match("profile"))
    mode.value = "profile";
  else
    mode.value = "guest";

  console.log(mode);
})

function deleteGuestBookCommentHandler(){

}

function rewriteGuestBookCommentHandler(){

}

function initComment(){
  guestbookComment.guestbookId = null;
  guestbookComment.content = null;
}

async function writeGuestBookCommentHandler(guestbookId) {

  guestbookComment.guestbookId = guestbookId;

  await fetch("http://localhost:8080/members/guestbooks/comment",
    {
      method: "POST",
      headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
      },
      body: JSON.stringify(guestbookComment)
    })
    .then(response => response.json())
    .then((data) => { if (data == 1) console.log("완료") })
    .catch(error => console.log(error));

  initComment();
}

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
  
  // getGuestbookList();

}

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
    .then((data) => {
      guestbooks.list = data;
      console.log(data);
    })   

    
    // console.log(data);
}

function getFollowGuestbookList() {
  fetch(`http://localhost:8080/members/guestbooks?id=${FollowId}`,
    {
      method: "GET",
      headers: {
        "Accept": "application/json",
        "Content-type": "application/x-www-form-urlencoded"
      },
    })
    .then(response => response.json())
    .then((data) => guestbooks.list = data)   
}

</script>
<template>
  <ul class="m-guestbook-content-list">
    <li class="lc-center" v-if="mode == 'guest'">
      <div class="m-guestbook-content-writeForm">
        <textarea class="m-guestbook-content-writeBox" v-model="guestbook.content"></textarea>
        <button @click.prevent="writeGuestBookHandler" class="m-gb-writeBtn" type="submit" value="">작성</button>
      </div>
    </li>
    <li class="m-gb-list" v-if="mode == 'guest'" v-for="item in 10">
      <div class="m-gb-header lc-center">
        <div><span>From.</span></div>
        <div>{{ "글쓴이" }}</div>
      </div>
      <div class="m-gb-main">
        <textarea class="txta-rsnone" readonly></textarea>
        <div class="m-gb-cmt">
          <div><span>답글</span></div>
          <textarea class="txta-rsnone" readonly></textarea>
        </div>
      </div>

    </li>
    <gbCard v-if="mode == 'profile'" v-for="item in guestbooks.list"  :guestbookId = "item.id" @init-guestbook-list="getGuestbookList"></gbCard>
  
    <!-- <li v-show=false>
      <span>아직 방명록이 남겨져 있지 않아요. 좋은 일기를 공유해보면서 소통해보면 어떨까요?</span>
    </li> -->
  </ul>
</template>

<style scoped>
.m-gb-list{
  width:80%;
  height:80%;

  background-color: #fafafa;
  border: 2px solid #FCD602;
  border-radius: 8px;

  justify-self: center;
  align-self: center;

  display: grid;
  grid-template-rows: 1fr 5fr;
    box-sizing: border-box;


}
.m-gb-main{
  height:100%;

  display:grid;
  grid-template-rows: 3fr 1fr;
}
.m-gb-header{
  display: flex;
  align-items: center;
}
.m-gb-cmt{
  display: flex;
  align-items: center;
}
.txta-rsnone{
  resize: none;
  border: none;
}

.txta-rsnone:focus{
  resize: none;
  outline: none;
}
.m-gb-writeBtn{
  width:80px;
  height:40px;
  border-radius: 8px;;
  background-color: #FCD602;
  justify-self: center;
}

.m-gb-writeBtn:hover{
  opacity: 0.5;
} 

.m-guestbook-content-list {
  width: 100%;
  /* height: 100%; */
  min-height: 70%;
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
  
  /* overflow:scroll; */
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
  height: 80%;

  background-color: #fafafa;
  border: 2px solid #FCD602;
  border-radius: 8px;

  box-sizing: border-box;

  display: grid;
  grid-template-rows: 8fr 1fr;
  padding:1rem;
  

}

.m-guestbook-content-writeBox {
  width: 90%;
  height: 90%;
  /* background-color: #FCD602; */
  border: 1px solid #FCD602;

  justify-self: center;

  resize: none;
}

.m-guestbook-content-comment {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 1rem;
}


</style>