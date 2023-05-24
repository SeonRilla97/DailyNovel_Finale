<script setup>
import { objectToString } from '@vue/shared';
import { ref, reactive, onUpdated, onMounted } from 'vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';

// let guestbook = reactive({});

const user = useUserDetailsStore();

defineEmits(["initGuestbookList"]);

let props = defineProps({
    guestbookId: Number,

}) ;

const guestBookId = props.guestbookId;
const guestBookJson = ref("");


const cmtJson = reactive({
    guestbookId: guestBookId,
    memberId: user.id,
    comment: null,
})

const updatecmt = ref("");
const testCmt = ref("");

let cmtShow = ref(true);
let reCmtShow = ref(false);

function showHandler(){    
    cmtShow.value = !cmtShow.value;
    reCmtShow.value = !reCmtShow.value;
}

onUpdated(()=>{
    console.log("업데이트 중");
})

onMounted(() => { 
    getGusetbookbyIdHandler(guestBookId);
});

async function getGusetbookbyIdHandler(id){
    await fetch(`http://localhost:8080/members/guestbooks/all?id=${id}`,
    {
        method: "GET",
        headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
        },        
    })
    .then(response => response.json())
    .then((data) => { guestBookJson.value = data })
    .then(() => {console.log(guestBookJson.value)})
    // .then(cmtJson.comment = guestBookJson.value.comment)
    .then(() => {updatecmt.value = guestBookJson.value.comment})
    .then(()=>{console.log(updatecmt.value)})
    // .then(()=>{console.log(cmtJson.comment)})
    .catch(error => console.log(error));
}

async function writeGuestBookCommentHandler() {
    let cmtJson = {
        guestbookId: guestBookId,
        memberId: user.id,
        comment: testCmt.value,
    };
    
    console.log(cmtJson);

    await fetch("http://localhost:8080/members/guestbooks/comment",
    {
        method: "POST",
        headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
        },
        body: JSON.stringify(cmtJson)
    })
    .then(response => response.json())
    .then((data) => { if (data == 1) console.log("완료") })
    .then(() => {getGusetbookbyIdHandler(guestBookId)})
    .catch(error => console.log(error));

    testCmt.value = null;
}

async function rewriteGuestBookCommentHandler() {   

    let cmtJson = {
        guestbookId: guestBookId,
        memberId: user.id,
        comment: updatecmt.value,
    };
    
    await fetch("http://localhost:8080/members/guestbooks/comment/update",
    {
        method: "POST",
        headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
        },
        body: JSON.stringify(cmtJson)
    })
    .then(response => response.json())
    .then((data) => { if (data == 1) console.log("완료") })
    .then(() => {getGusetbookbyIdHandler(guestBookId)})
    .then(() => {showHandler()})
    .catch(error => console.log(error));
    
    testCmt.value = null;
}

async function deleteGuestBookCommentHandler(id) {   

    await fetch("http://localhost:8080/members/guestbooks/comment/delete",
    {
        method: "POST",
        headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
        },
        body: JSON.stringify({guestbookId : id})
    })
    .then(response => response.json())
    .then((data) => { if (data == 1) console.log("완료") })
    .then(() => {getGusetbookbyIdHandler(guestBookId)})
    .catch(error => console.log(error));

    testCmt.value = null;
}


</script>

<template>
<li class="lc-center">
    <section class="m-gbcard-container">

        <!-- 방명록 카드 헤더 -->
        <header class="m-gbcard-header">
            <span>From.</span>
            <div class="m-gbcard-writer"><span>{{ guestBookJson.writerName }}</span></div>
        </header>

        <!-- 방명록 카드 메인 -->
        <main class="m-gbcard-main">
            
            <!-- 방명록 카드 내용 -->
            <div class="m-gbcard-content">
                <span>{{ guestBookJson.content }}</span>
            </div>

            <!-- 방명록 카드 답글 -->
            <section class="m-gbcard-cmt">
                

                <!-- 답글할 때 -->
                <form class="m-gbcard-cmt-form" v-show="cmtShow">
                    
                    <!-- 답글을 이미 쓴 적이 있을 때 -->
                    <textarea class="m-gbcard-cmt-writebox" v-if="guestBookJson.comment" readonly placeholder="답글을 적어보세요." 
                    :value="guestBookJson.comment"></textarea>
                    <div class="m-gbcard-cmt-updateiconbox" v-if="guestBookJson.comment">
                        <div class="m-guestbook-icon-box pencil-icon" @click="showHandler"></div>
                        <div class="m-guestbook-icon-box trash-icon" @click="deleteGuestBookCommentHandler(cmtJson.guestbookId), $emit('initGuestbookList')"></div>
                    </div>

                    <!-- 답글을 처음 적을 때 -->
                    <textarea class="m-gbcard-cmt-writebox" v-if="!guestBookJson.comment" placeholder="답글을 적어보세요." v-model="testCmt"                     
                    ></textarea>
                    <div class="m-gbcard-cmt-writeBtn" v-if="!guestBookJson.comment">
                        <input type="submit" class="m-gbcard-modifyBtn" value="작성" @click.prevent="writeGuestBookCommentHandler()">
                    </div>                    
                </form>   
                <!-- v-model="cmtJson.comment"  -->
                <!-- 답글을 수정할 때 -->
                <form class="m-gbcard-cmt-form" v-show="reCmtShow">
                    
                    <textarea class="m-gbcard-cmt-writebox"  v-model="updatecmt"></textarea>
                    <div class="m-gbcard-cmt-updateiconbox">
                        <div class="m-gbcard-modifyBtn" @click.prevent="rewriteGuestBookCommentHandler()"><span style="font-size: 14px;">수정</span></div>                        
                    </div>
                </form>       

                <!-- 답글이 존재하지 않을 때 -->
                

                <!-- 방문자 모드 -->              
            </section>
        
        </main>   
    </section>
</li>
</template>

<style scoped>

.m-gbcard-modifyBtn{
    width:44px;
    height: 44px;
    border-radius: 6px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: antiquewhite;
}
.m-guestbook-icon-box{
  width:24px;
  height:24px;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;

  border-radius: 6px;
}

.m-guestbook-icon-box:hover{
  /* background-color: rgba(0,0,0,0.2); */
  background-color: antiquewhite;
}

/* 방명록 카드 박스 */
.m-gbcard-container{
    display: grid;
    grid-template-rows: 1fr 3.5fr ;

    width: 100%;
    height: 100%;

    background-color: #fafafa;
    border: 2px solid #FCD602;
    border-radius: 8px;



    box-sizing: border-box;
}

.m-gbcard-header{
    display:flex;
    justify-content: center;
    padding:8px;
    box-sizing: border-box;
}

.m-gbcard-main{
    width:100%;
    height:100%;

    display: grid;
    grid-template-rows: 4fr 1fr;

    box-sizing: border-box;
}
.m-gbcard-content{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
}

.m-gbcard-cmt{
    display: flex;

    min-height:100%;
}
.m-gbcard-cmt-form{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 48px;
}
.m-gbcard-cmt-updateiconbox{
    display: flex;
    flex-direction: column;
}
.m-gbcard-cmt-writebox{
    font-family: 'Nanum Gothic', sans-serif;
    resize: none;

    width: 80%;
    height: 80%;

    outline: 3px #FCD602;

    border: none;

    background-color: #fafafa;
}

.m-gbcard-cmt-writebox:hover{
    outline: 3px #FCD602;
}
</style>