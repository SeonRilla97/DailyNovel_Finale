<script setup>
import {onBeforeMount,reactive,ref} from 'vue';
import { useUserDetailsStore } from '../../../../store/useUserDetailsStore';

let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법

const props = defineProps({
    data: {
        type: Object,
        required:true
    }
})
let emit = defineEmits(['callComments'])
console.log(props.data.collectionId)
onBeforeMount (()=> {
    console.log(props.data)
    // console.log(new Date(props.data.comments[0].regDate))
})

// 날짜 알아낼때
function pfgetDate(date) {
    return `${date.getFullYear()}.${date.getMonth()+1}.${date.getDate()}.`;
}
// 시간 알아낼때
function pfgetTime(date) {
    let hour =  date.getHours();
    let minute = date.getMinutes();
    if(hour < 10) hour = `0${hour}`;
    if(minute < 10)minute= `0${minute}`;
    // date.getMinutes();
    return `${hour}:${minute}`;
}

// 수정중인 Comment ID
let curModify = ref(null);
// 수정 누를때
function pfupdateHandler(id){
    if(curReg.value != null){curReg.value=null;console.log(`작성꺼라${curReg.value}`)} 
    if(curModify.value == id) {curModify.value=null;return}
    curModify.value = id;
}

// 작성중인 대댓글 ID
let curReg = ref(null);
// 답글쓰기 누를때
function pfregHandler(id){
    if(curModify.value != null){curModify.value=null; console.log(`수정꺼라${curModify.value}`)} 
    if(curReg.value == id) {curReg.value=null;return}
    curReg.value = id;
}

// 펼쳐진 대댓글
let openReply = ref(null)
// 답글 불러오기 (나는 하나의 댓글에 대한 답글만 불러오게 할거야! 그외는 안불러와)
let reply = reactive({
    list:null
})
function pfgetReplyListFromDB(cmt,update){
    if(cmt.comments == 0 ) return; //답글이 들어오는게 아니라 댓글이 들어와야함을 의미
    if(openReply.value == cmt.id && update !=true) {
        openReply.value =null
        reply.list =null
        return;
    }
    //선택된 댓글 (답글을 펼칠 댓글)
    openReply.value =cmt.id;
    fetch(`http://localhost:8080/collection/comment?collectionId=${cmt.collectionId}&depth=1&refId=${cmt.id}`)
    .then(response => response.json())
    .then(result => {
        reply.list = result;
        console.log(result);
    })
    .catch(error => console.log('error', error));
}


// (댓글/답글)의 수정폼에서 수정을 눌렀을때 (Fetch에서 분기됨)
function pfupdateClickHandler(e,cmt,upperCmt){
    console.log("수정합니다");
    console.log(cmt);
    // 2단계 위로가서 reg-input 찾기
    // html 구조 바뀌면 오류 발생할 지도 모름
    let content =e.target.parentNode.parentNode.querySelector(".reg-input").innerText;
    console.log(content);
    if(content=="") return;
    // 서버에게 수정요청
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
    "id": cmt.id,
    "content": content
    });

    var requestOptions = {
    method: 'PUT',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
    };

    fetch("http://localhost:8080/collection/comment", requestOptions)
    .then(response => response.text())
    .then(result => {
        if(cmt.depth == 0){// 댓글인경우
        emit("callComments",cmt.collectionId);
        console.log("이건 댓글!")
        }
        else{// 답글인경우
            console.log(cmt)
        pfgetReplyListFromDB(upperCmt,true);
        console.log("이건 답글!")
        }
        
        
        curModify.value=null;
    })
    .catch(error => console.log('error', error));
}


// 등록을 눌렀을때 (댓글/ 답글)
function pfregClickHandler(e,memberId, collectionId,depth,refId,cmt){
    let textdom = e.target.parentNode.parentNode.querySelector(".reg-input");
    console.log(textdom.innerText)
    let content =e.target.parentNode.parentNode.querySelector(".reg-input").innerText;
    if(content =="") {return}
    console.log(`${memberId} ${collectionId} ${depth} ${refId} ${content}` )
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw =null
    if(refId)
    raw = JSON.stringify({
    "memberId": memberId,
    "content": content,
    "collectionId": collectionId,
    "depth": depth,
    "refId": refId
    });
    else
    raw = JSON.stringify({
    "memberId": memberId,
    "content": content,
    "collectionId": collectionId,
    "depth": 0
    });
    var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
    };

    fetch("http://localhost:8080/collection/comment", requestOptions)
    .then(response => response.text())
    .then(result => {
        // if(refId) //답글쓰기

        emit("callComments",collectionId);
        if(cmt)
            pfgetReplyListFromDB(cmt,true);

        curReg.value=null;

        textdom.innerText="";
    })
    .catch(error => console.log('error', error));
}
console.log(props.data.comments)

function pfpfdeleteHandler(cmt,upper){
    var requestOptions = {
  method: 'DELETE',
  redirect: 'follow'
};

fetch(`http://localhost:8080/collection/comment/${cmt.id}`, requestOptions)
  .then(response => response.json())
  .then(result => {
    emit("callComments",cmt.collectionId);
    if(upper) pfgetReplyListFromDB(upper,true);
    else pfgetReplyListFromDB(cmt,true);
  })
  .catch(error => console.log('error', error));
}
</script>
<template>
    <!-- 페이지 레이아웃 -->
    <div class="comment-layout">
        <section class="main">
            <ul class="comment-list">
                <li class="comment-item" v-for="(cmt, key) in data.comments">
                    <!-- 댓글 컨테이너(하나의 댓글에 대해) -->
                    <div class="comment-area">
                        <!-- 댓글 UI -->
                        <div v-if="curModify!= cmt.id">
                            <div class="title">
                                <div class="name ib">{{cmt.memberName}}</div>
                                <div class="ib mgl-3 writer undrag" v-if="userDetails.id == cmt.memberId">나</div>
                            </div>
                            <div class="mgt-2 content">{{ cmt.content }}</div>
                            <div class="ib mgt-3 date-container">
                                <span class="date">{{pfgetDate(new Date(cmt.regDate))}}</span>
                                <span class="mgl-1 time">{{pfgetTime(new Date(cmt.regDate))}}</span>
                            </div>
                            <div class="ib mgl-4 menu-box">
                                <span class="menu-box undrag" v-if="cmt.depth ==0" @click="pfgetReplyListFromDB(cmt)">답글보기</span><span class="undrag">({{ cmt.comments }})</span>
                                <span class="menu-box mgl-3 undrag" @click="pfregHandler(cmt.id)">답글쓰기</span>
                                <span class="mgl-3 menu-box undrag" @click="pfupdateHandler(cmt.id)" v-if ="userDetails.id== cmt.memberId" >수정</span>
                                <span class="mgl-3 menu-box undrag"  v-if ="userDetails.id== cmt.memberId" @click="pfpfdeleteHandler(cmt)">삭제</span>
                            </div>
                        </div>
                        <!-- 댓글 수정 폼 -->
                        <div class="comment-reg" v-if="curModify== cmt.id">
                            <div class="form">
                                <div class="name">{{userDetails.nickname}}</div>
                                <div class="reg-input mgt-3" contenteditable="true">{{ cmt.content }}</div>
                                <div class="reg-btn">
                                    <div class="mgt-3 ib cursor-pt undrag" @click="pfupdateClickHandler($event,cmt)">수정</div>
                                    <div class="mgt-3 ib mgl-3 cursor-pt undrag" @click="curModify=null">취소</div>
                                </div>
                            </div>
                        </div>

                        <!-- 대댓글 입력폼 -->
                        <div class="comment-reg" v-if="curReg == cmt.id">
                            <div class="form">
                                <div class="name">{{ userDetails.nickname}}</div>
                                <div class="reg-input mgt-3" contenteditable="true"></div>
                                <div class="reg-btn">
                                    <div class="mgt-3 ib cursor-pt undrag" @click="pfregClickHandler($event, userDetails.id, cmt.collectionId,1,cmt.id,cmt)">등록</div>
                                    <div class="mgt-3 ib mgl-3 cursor-pt undrag" @click="curReg=null">취소</div>
                                </div>
                            </div>
                        </div>

                        <!-- 대댓글 -->
                        <ul class="comment-list comment-reply" v-if="cmt.id == openReply">
                            <li class="comment-item" v-for="(reply, key) in reply.list">
                                <!-- 대댓글 컨테이너(하나의 댓글에 대해) -->
                                <div class="comment-area">
                                    <!-- 대댓글 UI -->
                                    <div v-if="curModify!= reply.id">
                                        <div class="title">
                                            <div class="name ib">{{reply.memberName}}</div>
                                            <div class="ib mgl-3 writer undrag" v-if="userDetails.id == reply.memberId">나</div>
                                        </div>
                                        <div class="mgt-2 content">{{ reply.content }}</div>
                                        <div class="ib mgt-3 date-container">
                                            <span class="date">{{pfgetDate(new Date(reply.regDate))}}</span>
                                            <span class="mgl-1 time">{{pfgetTime(new Date(reply.regDate))}}</span>
                                        </div>
                                        <div class="ib mgl-2 menu-box">
                                            <span class="menu-box mgl-3 undrag" @click="pfregHandler(reply.id)">답글쓰기</span>
                                            <span class="mgl-3 menu-box undrag" @click="pfupdateHandler(reply.id)" v-if ="userDetails.id== reply.memberId" >수정</span>
                                            <span class="mgl-3 menu-box undrag"  v-if ="userDetails.id== reply.memberId" @click="pfpfdeleteHandler(reply,cmt)">삭제</span>
                                        </div>
                                    </div>
                                    <!-- 대댓글 수정 폼 -->
                                    <div class="comment-reg" v-if="curModify== reply.id">
                                        <div class="form">
                                            <div class="name">{{userDetails.nickname}}</div>
                                            <div class="reg-input mgt-3" contenteditable="true">{{ reply.content }}</div>
                                            <div class="reg-btn">
                                                <div class="mgt-3 ib cursor-pt undrag" @click="pfupdateClickHandler($event,reply,cmt)">수정</div>
                                                <div class="mgt-3 ib mgl-3 cursor-pt undrag" @click="curModify=null">취소</div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 대댓글 입력폼 -->
                                    <div class="comment-reg" v-if="curReg == reply.id">
                                        <div class="form">
                                            <div class="name">{{userDetails.nickname}}</div>
                                            <div class="reg-input mgt-3" contenteditable="true"></div>
                                            <div class="reg-btn">
                                                <div 
                                                class="mgt-3 ib cursor-pt undrag" 
                                                @click="pfregClickHandler($event, userDetails.id, cmt.collectionId,1,cmt.id,cmt)">등록</div>
                                                <div class="mgt-3 ib mgl-3 cursor-pt undrag" @click="curReg=null">취소</div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </li>
                        </ul>



                    </div>
                </li>
            </ul>

            <div class="comment-reg">
                <div class="form">
                    <div class="name">{{userDetails.nickname}}</div>
                    <div class="reg-input mgt-3" contenteditable="true"></div>
                    <div class="reg-btn"><div class="mgt-3 ib cursor-pt" @click="pfregClickHandler($event,userDetails.id,data.collectionId)">등록</div></div>
                </div>
            </div>

        </section>
    </div>

</template>

<style scoped>
.comment-layout{
    display:grid;
    grid-template-columns: 2fr 8fr 2fr;
    grid-template-rows: 1fr;
    max-height: 490px;
    width:100%;
    overflow-y: auto;
}
    .main {
        /* background-color: aqua; */
        grid-column-start: 2;
        grid-column-end: 3;

    }

.comment-list{
    width:100%;
    /* height:100%; */
    box-sizing: border-box;
}

.comment-item{
    width:100%;
    height:fit-content;
    padding:12px 23px 10px 0;
    box-sizing: border-box;
    border-bottom: 1px solid #00000010;
    position:relative;

}
.comment-reply{
    padding-left:7%;
    padding:12px 23px 10px 7%;
    border-bottom: 1px solid #00000010;
}
.comment-reply:last-child{
    border:none;
}
.title{
    display:flex;
}
.comment-area .writer{
    border:solid 2px red;
    padding:3px 15px;
    box-sizing: border-box;
    /* position:absolute; */
    top:3%;
    font-size:12px;
    font-weight: bolder;
    color:red;
    border-radius: 10px;
    
}
.comment-area .date-container{
    font-size:14px;
    color:#00000060
}
.comment-area .content{
    line-height:24px;
    text-indent:5px;
    font-size:16px
}
.menu-box{
    color:#00000060
}
.menu-box:hover{
    cursor: pointer;
}
.comment-reg{
    width:100%;
    min-height:150px;
    height: auto;
    /* background-color: antiquewhite; */
    padding:16px 10px 10px 0;
    /* border-bottom:1px solid #00000010; */
    margin-bottom:24px;

}
.comment-reg .form{
    padding:16px 10px 10px 18px;
    height: auto;
    width:100%;
    border: 1px solid #00000060;
    border-radius: 6px;
    position: relative;
    box-sizing: border-box;
}
.comment-reg .form .name{
    font-weight: bold;
    letter-spacing: 1.8px;
}
.comment-reg  .form .reg-input{
    width:100%;
    box-sizing: border-box;
    height:fit-content;
    /* background-color: aliceblue; */
    min-height:40px;
    margin-bottom: 30px;
    line-height:24px;
    text-indent:5px;
    font-size:16px
}
.comment-reg  .form .reg-input:focus{
    outline:none;
}
.comment-reg  .form .reg-btn{
    position:absolute;
    right:0;
    margin: 0 15px 15px 0;
    font-size: 18px;
    font-weight:bold;
    color:#00000060;
    bottom:0;
}

.cursor-pt{
    cursor:pointer;
}
</style>