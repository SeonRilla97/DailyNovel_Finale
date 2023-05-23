<script setup>
import {onBeforeMount,ref} from 'vue';
import { useUserDetailsStore } from '../../../store/useUserDetailsStore';

let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법

const props = defineProps({
    data: {
        type: Object,
        required:true
    }
})


onBeforeMount (()=> {
    console.log(props.data)
    console.log(new Date(props.data.comments[0].regDate))
})

// 날짜 알아낼때
function getDate(date) {
    return `${date.getFullYear()}.${date.getMonth()+1}.${date.getDate()}.`;
}
// 시간 알아낼때
function getTime(date) {
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
function updateHandler(id){
    if(curReg.value != null){curReg.value==null} 
    if(curModify.value == id) {curModify.value=null;return}
    curModify.value = id;
}

// 작성중인 대댓글 ID
let curReg = ref(null);
function regHandler(id){
    if(curModify.value != null){curModify.value==null} 
    if(curReg.value == id) {curReg.value=null;return}
    curReg.value = id;
}
</script>


<template>
    <!-- 페이지 레이아웃 -->
    <div class="comment-layout">
        <section class="main">
            <ul class="comment-list">
                <li class="comment-item" v-for="(cmt, key) in data.comments">
                    <!-- 댓글 UI -->
                    <div class="comment-area">
                        <div v-if="curModify!= cmt.id">
                            <div class="title">
                                <div class="name ib">{{cmt.memberName}}</div>
                                <div class="ib mgl-3 writer">작성자</div>
                            </div>
                            <div class="mgt-2 content">{{ cmt.content }}</div>
                            <div class="ib mgt-3 date-container">
                                <span class="date">{{getDate(new Date(cmt.regDate))}}</span>
                                <span class="mgl-1 time">{{getTime(new Date(cmt.regDate))}}</span>
                            </div>
                            <div class="ib mgl-4 menu-box">
                                <span class="menu-box" v-if="cmt.depth ==0">답글보기</span>
                                <span class="menu-box mgl-3" @click="regHandler(cmt.id)">답글쓰기</span>
                                <span class="mgl-3 menu-box" @click="updateHandler(cmt.id)" v-if ="userDetails.id== cmt.memberId" >수정</span>
                                <span class="mgl-3 menu-box"  v-if ="userDetails.id== cmt.memberId" >삭제</span>
                            </div>
                        </div>
                        <!-- 댓글 수정 폼 -->
                        <div class="comment-reg" v-if="curModify== cmt.id">
                            <div class="form">
                                <div class="name">{{cmt.memberName}}</div>
                                <div class="reg-input mgt-3" contenteditable="true">{{ cmt.content }}</div>
                                <div class="reg-btn">
                                    <div class="mgt-3 ib cursor-pt">수정</div>
                                    <div class="mgt-3 ib mgl-3 cursor-pt" @click="curModify=null">취소</div>
                                </div>
                            </div>
                        </div>

                        <!-- 대댓글 -->
                        <div class="comment-reg" v-if="curReg == cmt.id">
                            <div class="form">
                                <div class="name">{{cmt.memberName}}</div>
                                <div class="reg-input mgt-3" contenteditable="true"></div>
                                <div class="reg-btn">
                                    <div class="mgt-3 ib cursor-pt">등록</div>
                                    <div class="mgt-3 ib mgl-3 cursor-pt" @click="curReg=null">취소</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="comment-item comment-reply">
                    <div class="comment-area">
                        <div class="name ib">서울빻수리</div>
                        <div class="ib mgl-3 writer">작성자</div>
                        <div class="mgt-2 content">이것은 댓글인데 길게 써도 이쁘게 나와야 하거든요? 근데 길게 쓰는 방법이 뭔가요...<br>저는 짧게밖에 못써서요 근데 적어도 줄바꿈은 돼야하니 조금만 더 길게 써 볼까요? 근데 글이 다닥다닥 붙어있으니 영 보기 그러네요 조금만 띄어볼까요 그래도 대학교수님이 들여쓰기 정도는 알아야 한다고 했으니 들여쓰기도 슬쩍 넣어볼까요. 조금 있다가 해야할거긴 한데 만약 사람이 엔터를 누르면 띄어쓰기가 되도록 함수도 한번 써볼까요 엔터를 감지하면 br을 추가시켜주면 되겠네요 근데 그게 가능하겠어요 예..? 확 그냥 들여쓰기 빼버릴까요? br태그 넣는다 생각하니 별로 안이쁘네요 조금 들여쓰는게</div>
                        <div class="ib mgt-3 date-container">
                            <span class="date">2023.05.22.</span>
                            <span class="mgl-1 time"> 08:47</span>
                        </div>
                        <div class="ib mgl-4 menu-box">
                            <span class="menu-box">답글보기</span>
                            <span class="mgl-3 menu-box">수정</span>
                            <span class="mgl-3 menu-box">삭제</span>
                        </div>

                        <!-- 댓글 수정 폼 -->
                        <div class="comment-reg">
                            <div class="form">
                                <div class="name">서울빻수리</div>
                                <div class="reg-input mgt-3" contenteditable="true"></div>
                                <div class="reg-btn">
                                    <div class="mgt-3 ib cursor-pt">등록</div>
                                    <div class="mgt-3 ib mgl-3 cursor-pt">수정</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li> 
                <li class="comment-item comment-reply">
                    <div class="comment-area">
                        <div class="name ib">서울빻수리</div>
                        <div class="ib mgl-3 writer">작성자</div>
                        <div class="mgt-2 content">이것은 댓글인데 길게 써도 이쁘게 나와야 하거든요? 근데 길게 쓰는 방법이 뭔가요...<br>저는 짧게밖에 못써서요 근데 적어도 줄바꿈은 돼야하니 조금만 더 길게 써 볼까요? 근데 글이 다닥다닥 붙어있으니 영 보기 그러네요 조금만 띄어볼까요 그래도 대학교수님이 들여쓰기 정도는 알아야 한다고 했으니 들여쓰기도 슬쩍 넣어볼까요. 조금 있다가 해야할거긴 한데 만약 사람이 엔터를 누르면 띄어쓰기가 되도록 함수도 한번 써볼까요 엔터를 감지하면 br을 추가시켜주면 되겠네요 근데 그게 가능하겠어요 예..? 확 그냥 들여쓰기 빼버릴까요? br태그 넣는다 생각하니 별로 안이쁘네요 조금 들여쓰는게</div>
                        <div class="ib mgt-3 date-container">
                            <span class="date">2023.05.22.</span>
                            <span class="mgl-1 time"> 08:47</span>
                        </div>
                        <div class="ib mgl-4 menu-box">
                            <span class="menu-box">답글보기</span>
                            <span class="mgl-3 menu-box">수정</span>
                            <span class="mgl-3 menu-box">삭제</span>
                        </div>
                    </div>
                </li>
            </ul>

            <div class="comment-reg">
                <div class="form">
                    <div class="name">서울빻수리</div>
                    <div class="reg-input mgt-3" contenteditable="true"></div>
                    <div class="reg-btn mgt-3 ib cursor-pt">등록</div>
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
}
.title{
    display:flex;
}
.comment-area .writer{
    border:solid 2px red;
    padding:3px 6px;
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