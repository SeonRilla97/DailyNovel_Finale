<script setup>
import { onBeforeMount, onMounted, onUpdated, reactive, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';

let userDetails = useUserDetailsStore();
let route = useRoute();
let router = useRouter();

// 방명록 주인 ID
const hostId = userDetails.id;
let guestId = 2;

let mode = null;

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



onMounted(() => {
  console.log(hostId);

  getGuestbookList();

  for (n in guestbook.list) {
    n.isModify = false;
  }

  for (i in guestbook.list) {
    console.log(i);
  }

  // 현재 들고온 주소에 따라서 모드 확인
  if (route.fullPath.match("profile"))
    mode = "profile";
  else
    mode = "guest";

  console.log(mode);
})

let isModify = ref(false);

function cmtModifyHandler() {
  isModify.value = !isModify.value;
  console.log(isModify);
}

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
    .then((data) => guestbooks.list = data)   
}

</script>
<template>
  <ul class="m-guestbook-content-list">
    <li class="lc-center" v-if="mode == 'guest'">
      <div class="m-guestbook-content-writeForm">
        <textarea class="m-guestbook-content-writeBox" v-model="guestbook.content"></textarea>
        <button @click.prevent="writeGuestBookHandler" type="submit" value="">작성</button>
      </div>
    </li>
    <li class="lc-center" v-for="item in guestbooks.list" :key="item.id">

      <div class="m-guestbook-content-item">
        <div class="m-guestbook-item-header">
          <span>From.</span>
          <div class="m-guestbook-content-writer"><span>{{ item.writerName }}</span></div>
        </div>

        <div class="m-guestbook-content-text"><span>{{ item.content }}</span></div>
        

        <!-- 기능 구현 우선으로 할거라 일단 마구잡이로 적고 추후 리팩토링 필수. -->

        <!-- 답글이 존재할 때 -->
        <form class="m-guestbook-comment-form" v-if="mode == 'profile' && item.comment">
            
              <textarea class="m-guestbook-comment-text-host" readonly cols="30" rows="10" :value="item.comment"></textarea>
              <div style="width:fit-content">
                <div class="m-guestbook-icon-box pencil-icon" @click="rewriteGuestBookCommentHandler"></div>
                <div class="m-guestbook-icon-box trash-icon" @click="deleteGuestBookCommentHandler" ></div>
              </div>
        </form>
        
        <!-- <div v-show = isModify>
              <textarea class="m-guestbook-comment-text-host" cols="30" rows="10" :value="item.comment"></textarea>
              <div @click="cmtModifyHandler">수정</div>
            </div>  
        // 수정을 눌렀을 때
        <form class="m-guestbook-comment-form" v-if="mode == 'profile'">
          
        </form> -->

        <!-- 답글이 존재하지 않을 때 -->
        <form class="m-guestbook-comment-form" v-if="mode == 'profile' && !item.comment">
          <textarea class="m-guestbook-comment-text-host" cols="30" rows="10" placeholder="답글을 보내보세요."
            v-model="guestbookComment.content"></textarea>
          <button type="submit" @click.prevent="writeGuestBookCommentHandler(item.id)">저장</button>
        </form>


        <!-- 방문자 모드 -->
        <form class="m-guestbook-comment-form" v-if="mode == 'guest'">
          <textarea class="m-guestbook-comment-text-guest" @click.prevent="" readonly name="" id="" cols="30" rows="10"
            :value=item.comment></textarea>
        </form>
      </div>
    </li>
    <!-- <li v-show=false>
      <span>아직 방명록이 남겨져 있지 않아요. 좋은 일기를 공유해보면서 소통해보면 어떨까요?</span>
    </li> -->
  </ul>
</template>

<style scoped>
.m-guestbook-icon-box{
  width:24px;
  height:24px;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

.m-guestbook-icon-box:hover{
  background-color: rgba(0,0,0,0.2);
}
.m-guestbook-comment-text-host {
  font-family: 'Nanum Gothic', sans-serif;
  resize: none;
  width: 80%;
  height: 80%;
  border: none;
  /* readonly : "readonly"; */
  background-color: #fafafa;
}

.m-guestbook-comment-text-guest {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: 1rem;
  resize: none;
  width: 80%;
  height: 80%;
  border: none;
  text-align: center;
  background-color: #fafafa;

}

.m-guestbook-comment-text-guest:focus {
  outline: none;
}

.m-guestbook-comment-form {
  width: 100%;
  height: 20%;
  display: flex;
  justify-content: center;
  align-items: center;
}

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