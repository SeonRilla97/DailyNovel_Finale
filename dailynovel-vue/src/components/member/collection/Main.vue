<script setup>
import{ref,toRefs,watchEffect} from 'vue'
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
    isDuplicated: null
})



let emit = defineEmits(["registerCollection"])
function menuClickHandler(e){
    console.log(e.target);
}

// 컬렉션 생성 폼 에러 메세지 출력
const errMsg = ref();

// 
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
// errMsg
let errHandler = ref(false);
// 컬렉션 이름은 중복할 수 없어요

</script>


<template>

        <div class="collection-container" >
            <div class="register-form box mgl-5">

            <transition name="fade">
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

            <transition name="fade">
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
            
            <router-link to="./detail/comment" class="mgl-5" v-for="colList in collection.List"  >
                <div class="box" v-bind:data-id="colList.id">
                    <div class="header"></div>
                    <h1 class="title">{{colList.name}}</h1>
                    <div class="icon-tack"></div>
                </div>
            </router-link>

        </div>
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
.collection-container > a:last-child{
    margin-right:3rem;
}
.collection-container .box .title{
    font-size:20px;
    font-weight: bolder;
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
}
.icon-chat{
    background-image: url("../../../assets/img/chat.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;
    width:16px;
    height: 16px;
}


/* vue Transition */


.fade-enter-active {
  transition: opacity 2s ease;
}

.fade-enter-from
 {
    opacity: 0;
}
</style>