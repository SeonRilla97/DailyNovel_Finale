<script setup>
import{ref,toRefs,watchEffect,toRef} from 'vue'
import { useRouter,useRoute } from 'vue-router'
let registerMenuController = ref(false);

// 컬렉션 추가 Form 변경 함수
function registerFromHandler(){
    if(registerMenuController.value){
        regCollectionName.value =null;
        errHandler.value = false;
    }
    registerMenuController.value = !registerMenuController.value
    
}


const props  = defineProps({
    collection: {
        type: Object,
        required:true
    },
    isDuplicated: null,
    successAddMenu: null
})



let emit = defineEmits(["registerCollection","initSuccesAddMenu","callgetCollectionList"])
function menuClickHandler(e){
    console.log(e.target);
}

// 컬렉션 생성 폼 에러 메세지 출력
const errMsg = ref();
// errMsg
let errHandler = ref(false);
// 컬렉션 생성시 중복 확인 -> 중복이라면 에러메세지 띄운다
watchEffect(()=>{
    if(!props.isDuplicated){
        console.log("에이 중복 아니잖아")
        return
    }
    // console.log("와 이게 진짜 바로 즉각 실행된다고? " + props.isDuplicated)
    errMsg.value = "똑같은 컬렉션은 만들 수 없어요!";
    errHandler.value=true;
    
})

// 컬렉션 등록 성공시 폼 원래대로 되돌리기
watchEffect(()=>{
    console.log("실행은 되고?")
    console.log(props.successAddMenu)
    if(!props.successAddMenu){
        console.log("등록이 아닌데?")
        return
    }
    //폼 입력창 창 전환
    registerFromHandler();
    emit("initSuccesAddMenu")
})
let regCollectionName = ref("")
// 컬렉션 폼의 생성 버튼 누를 때 동작
function regBtnClickHandler(){
    // 입력을 안했을 때
    if(regCollectionName.value==null ||regCollectionName.value.length == 0 ){
        errMsg.value = "컬렉션 이름을 입력해주세요!";
        errHandler.value=true;
        return
    }
    // 입력 길이가 길 때
    if(regCollectionName.value.length > 12){
        errMsg.value = "이름이 너무 길어요!";
        errHandler.value=true;
        return
    }
    // 컬렉션 이름을 매개변수로 전달, 컬렉션 생성
    // 컬렉션 중복시, 오류 발생해야함
    emit("registerCollection",regCollectionName.value)
}
// 팝업창 오픈
let updateModalOpen=ref(false)
// 어떤 컬렉션의 이름을 수정할지 선택하는 변수
let updateData = ref();
// 컬렉션 수정 창 여는친구
function modifyHandler(colList){
    if(updateModalOpen.value == true)
        return;
    updateModalOpen.value = true;
    updateData.value = colList;
}
// 수정창의 취소버튼을 누를 때
function modifyCancel(){
    updateData.value =null;
    updateModalOpen.value =false;
    updateColName.value = null;
    updateErr.value= null;
    updateErrControl.value = false;
}
// 수정창의 수정 버튼을 누를 때
let updateColName = ref("");
// 에러 메세지 출력
let updateErr = ref("");
// 에러 떴다고 알리는 변수
let updateErrControl = ref(false);

// 수정폼의 수정버튼 클릭
function modifyReg(){
    console.log("수정!")
    // console.log(updateColName.value)
    // console.log(updateData.value.memberId)
    console.log(updateData.value.memberId)
    // console.log(updateData.value.)
    console.log(updateColName.value)

    // console.log(updateData.value.memberId,updateColName.value, updateData.value.id )
    // 입력을 안했을 때
    if(updateColName.value==null ||updateColName.value.length == 0 ){
        console.log("이건 실행되고?")
        updateErr.value = "컬렉션 이름을 입력해주세요!";
        updateErrControl.value=true;
        return
    }
    // 입력 길이가 길 때
    if(updateColName.value.length > 12){
        updateErr.value = "이름이 너무 길어요!";
        updateErrControl.value=true;
        return
    }
    

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw = JSON.stringify({
    "memberId": updateData.value.memberId,
    "name": updateColName.value,
    "id": updateData.value.id
    });

    let requestOptions = {
    method: 'PUT',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
    };

    fetch("http://localhost:8080/collection", requestOptions)
    .then(response => response.json())
    .then(result => {
        // 1.true(수정완료) => 창닫기
        if(result ==true){
            modifyCancel();
            // 컬렉션 불러와달라고 Layout에 명령해야함
            emit('callgetCollectionList')
        }else{
        // 2.false(컬렉션명 중복) => 에러메세지
        updateErr.value = "중복된 이름은 만들 수 없어요!";
        updateErrControl.value=true;

        }
    })
    .catch(error => console.log('error', error));

}

// 삭제 버튼 클릭
function delHandler(colList){
    console.log("삭제!")
    console.log(colList)
    // 컬렉션 ID 추출 (삭제하기위해)
    console.log(colList.id)

    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/collection/${colList.id}`, requestOptions)
    .then(response => response.text())
    .then(result => {
        if(result)
            emit('callgetCollectionList');
    })
    .catch(error => console.log('error', error));
    
}
const router = useRouter()
function colClickHandler(collectionId){
    // console.log("컬렉션 눌렀네?" +collectionId)
    
    // console.log(router);
    // router.push(`./detail`)
    router.push({
         name: 'detail', 
         params: { "collectionId": collectionId } 
    })
}
</script>


<template>

        <div class="collection-container undrag" >
            <div class="register-form box mgl-5">

            <transition name="fades">
                <div class="create box" v-if="registerMenuController">
                    
                    <div class="title">컬렉션의 제목은<br/>무엇인가요?</div>
                        <input class="input" type="text" v-model="regCollectionName">
                            <div class="err" v-show="errHandler">{{errMsg}}</div>
                    <div class="submit">
                        <span class="btn-reg ib" @click = "regBtnClickHandler">생성</span>
                        <span class="btn-cancel ib mgl-5" @click="registerFromHandler">취소</span>
                    </div>
                </div>
            </transition>

            <transition name="fades">
                <div class="reg-btn box" v-if="!registerMenuController" @click="registerFromHandler">
                    <div class="header"></div>
                    <div class="title">나만의 컬렉션을<br/>추가해보세요</div>
                    <div class="icon-plus icon"></div>
                </div>
            </transition>
            </div>
            <router-link to="./shared" class="mgl-5" @click="menuClickHandler">
                <div class="box">
                    <div class="header"></div>
                    <h1 class="title">공유되었던 일기들</h1>
                    <div></div>
                </div>
            </router-link>
            
            <!-- <router-link to="./detail/comment" class="mgl-5" v-for="colList in collection.List"  > -->
                <div class="box mgl-5" v-bind:data-id="colList.id"  v-for="colList in collection.List" @click.stop="colClickHandler(colList.id)">
                    <div class="header">
                        <!-- <div class="icon-tack">\
                        </div> -->
                        <div class="dropdown">
                            <div class="icon-tack"></div>
                            <transition name="bounce">
                                <div class="content" @click.stop>
                                    <a href="#"  class="item" @click.stop="modifyHandler(colList)">수정</a>
                                    <a href="#"  class="item" @click.stop="delHandler(colList)">삭제</a>
                                </div>
                            </transition>
                        </div>
                    </div>
                    <h1 class="title">{{colList.name}}</h1>
                </div>
            <!-- </router-link> -->
        </div>
        <transition name="slide">
            <form class="container-update" v-if=updateModalOpen>
                <div class="title"><span>새로운 이름을 적어주세요</span></div>
                <div class="cur-name"><span>현재 이름 : </span><span>{{ updateData.name }}</span></div>
                <input type="text" class="input" v-model="updateColName">
                <div class="btn">
                    <div class="reg" @click="modifyReg">수정</div>
                    <div class="cancel" @click="modifyCancel">취소</div>
                </div>
                <div class="err" v-show="updateErrControl">{{updateErr}}</div>
            </form>
        </transition>
</template>
<style scoped>
.collection-container{
    display:flex;
    align-items: center;
    overflow-x: scroll;

}

/* Box 관련 CSS */
.collection-container .box{
    /* background-color: aqua; */
    width:240px;
    height: 360px;

    min-width:240px;
    min-height:360px;

    display:grid;
    grid-template-rows: repeat(5,1fr);

    border: 1px solid #00000020;
    border-radius: 12px;
    
    text-align:center;
    font-size: 20px;
    align-items: center;
    /* justify-content: center; */
}
.collection-container> .box:last-child{
    margin-right:2.5rem;
}
.collection-container > a:last-child{
    margin-right:3rem;
}
.collection-container .box .title{
    font-size:20px;
    font-weight: bolder;
}
.collection-container .box .header{
    position:relative;
    width:100%;
    height:100%;
}

/* 컬렉션 등록 박스 */
.collection-container .register-form{
    position:relative;
    
}
.box.create{
    position:absolute;
    margin-left: 0 !important;
    border:none;
}
.box.create .title{
    align-self: start;
    grid-row-start: 2;
    grid-row-end:3;
}
.box.create .input{
    border-radius: 10px;
    border: 1px solid #9f9f9f;
    width:184px;
    height:39px;
    justify-self: center;
    align-self: center;
    background-color: transparent;
    text-align: center;
    font-size:20px;
    grid-row-start: 3;
    grid-row-end:4;
}
.box.create .err{
    font-size:16px;
    color:red;
    grid-row-start: 4;
    grid-row-end:5;
    align-self: start;
}
.box.create .submit{
    align-self: start;
    grid-row-start: 5;
}
    .box.create .submit .btn-reg:hover{
        color:#6ce04f;
        cursor: pointer;
    }
    .box.create .submit .btn-cancel:hover{
        color:red;
        cursor: pointer;
    }

/* dropDown(필터 메뉴) */
.collection-container .dropdown .btn {
font-size: 16px;
border: none;
cursor: pointer;
box-sizing: border-box;
}
.collection-container .dropdown:hover .content {
  display: block;
}
.collection-container .dropdown {
position: absolute;
display: inline-block;
right:0
}

.collection-container .dropdown .content {
display: none; 
position: absolute;
background-color: #f9f9f9;
min-width: 60px;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
z-index: 1;
text-align: center;
top: -45px;
/* left:-30px; */
transform:translateX(-70%);
font-size: 12px;
border-radius: 5px;
/* height: 120px; */
}
.collection-container .dropdown .content .item {
color: black;
padding: 6px 10px;
text-decoration: none;
display: block;
}

.collection-container .dropdown .content a:hover {background-color: #f1f1f1}




/* 컬렉션 등록 버튼 */
.box.reg-btn{
    position:absolute;
    margin-left: 0;
    border:none;
    cursor: pointer;
}
.box.reg-btn .icon{
    justify-self: center;
    align-self: end;
}
.icon-plus{
    background-image: url("../../../assets/img/plus.svg");
    background-repeat: no-repeat;
    background-size: 32px 32px;
    background-position: center center;
    width:32px;
    height: 32px;
    
}
.icon-tack{
    background-image: url("../../../assets/img/pin.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;
    width:16px;
    height:16px;
    position:absolute;
    right: 10px;
    top: 3px;
    display: inline-block;
    
}
.icon-chat{
    background-image: url("../../../assets/img/chat.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;
    width:16px;
    height: 16px;
    grid-column-end: 1;
    grid-column-end: 2;
}
/* 컬렉션 수정 버튼 */
.container-update{
    width:400px;
    height:200px;
    background-color: rgb(246, 246 , 246);
    display:flex;
    flex-direction:column;
    padding: 10px;
    box-sizing: border-box;
    position:absolute;
    justify-self: center;
    align-self: center;
    box-shadow: 1px 1px 1px 1px #55555560;
    
    
}
    .container-update .title{
        font-size:20px;
        align-self: center;
    }
    .container-update .cur-name{
        font-size:12px;
        color: #00000080;
        margin: 20px 0 0 10% ;
    }
    .container-update .input{
        width:80%;
        height:20%;
        align-self: center;
        border-radius: 5px;
        border: none;
        font-size: large;
        text-indent: 3%;
        margin-top:5px

    }
    .container-update .input:focus{
        outline:none;
    }
    .container-update .err{
        color:red;
        margin:auto;
    }
    .container-update .btn {
        margin-top:10px;
        margin-left:10%;
        user-select: none;
    }
    .container-update .btn > div{
        display:inline-block;
        width:60px;
        height:30px;
        /* background-color: #4560f8; */
        text-align: center;
        line-height: 30px;
        border-radius: 4px;
    }
    .container-update .reg{
        color:#fcfcfc;
        background-color: rgb(20, 134, 207);
    }
    .container-update .reg:hover{
        cursor:pointer;
    }
    .container-update .cancel{
        color:#00000080;
        background-color: #fcfcfc;
        margin-left: 5px;
    }
    .container-update .cancel:hover{
        /* color:red; */
        cursor:pointer;
    }

/* vue Transition */


.fades-enter-active {
  transition: opacity 1s ease;
}

.fades-enter-from
 {
    opacity: 0;
}


.slide-enter-active {
  animation: slide 0.5s;
}
.slide-leave-active {
  animation: slide 0.5s reverse;
}
@keyframes slide {
  0% {
    transform: translateY(200%);
  }
  50% {
    transform: translateY(-20%);
  }
  100% {
    transform: translateY(0);
  }
}
</style>