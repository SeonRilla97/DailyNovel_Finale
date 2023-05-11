<script setup>
import{ref} from 'vue'
let registerMenuController = ref(false);

function registerFromHandler(){
    registerMenuController.value = !registerMenuController.value
}
</script>


<template>

        <div class="slide-container" >
            <div class="create-collection-form box">

            <transition name="fade">
                <div class="register-form shape" v-if="registerMenuController">
                    <div class="title">컬렉션의 제목은<br/>무엇인가요?</div>
                    <div class="form">
                        <input class="input mgt-6" type="text">
                        <div class="title-err mgt-4">컬렉션 이름은 중복할 수 없어요</div>
                    </div>
                    <div class="submit">
                        <span class="register ib">생성</span>
                        <span class="cancel ib mgl-5" @click="registerFromHandler">취소</span>
                    </div>
                </div>
            </transition>

            <transition name="fade">
                <div class="register-btn" v-if="!registerMenuController" @click="registerFromHandler">
                    <div class="header"></div>
                    <div class="main">
                        <div class="title">나만의 컬렉션을<br/>추가해보세요</div>
                        <div class="icon-plus mgt-5"></div>
                    </div>
                </div>
            </transition>
            </div>
            <router-link to="./shared">
                <div class="box my-diary-btn mgl-5">
                    <div class="header"></div>
                    <h1 class="title">공유되었던 일기들</h1>
                    <div></div>
                </div>
            </router-link>

            <router-link to="./diary">
                <div class="box content">
                    <div class="header"></div>
                    <div class="icon-tack icon"></div>
                    <h1 class="title">가장 행복했던 순간들</h1>
                    <div class="lc-center"><div class="icon-chat ib"></div><span class="ib mgl-2">12</span></div>
                </div>
            </router-link>

        </div>
</template>
<style scoped>


.collection-container > .main{
    display:flex;
    /* align-items: center; */
    overflow-x:scroll;
    width:100%;
}

.collection-container .main .slide-container{
    display:flex;
    align-items: center;
    /* transform: translateY(-10%); */
    padding:0 58px ;
    /* box-sizing: border-box; */
} 

.collection-container .main .box{
    width:240px;
    height: 360px;
    margin-left: 36px;
    border: 1px solid #00000020;
    border-radius: 12px;
    display:grid;
    grid-template-rows: 1.35fr 4.24fr 1fr;
    text-align: center;
    position:relative;
}
.collection-container .main .box .shape,
.collection-container .main .box .register-btn{
    width:240px;
    height: 360px;
    display:grid;
    grid-template-rows: 1.35fr 4.24fr 1fr;
    text-align: center;
    position:relative;
}
.collection-container .main .box:nth-child(1){
    margin-left:0
}
.collection-container .main .box:nth-child(2){
    /* margin-left:0 */
}



.collection-container .main .box .title{

    font-size: 20px;
    font-weight: bold;
}
.collection-container .main .box .register-form{
    
}
.collection-container .main .box .register-form >.title{
    align-self: end;
    transform: translateY(30px);
}
.collection-container .main .box .register-form .form >input{
    width:180px;
    height:40px;
    border-radius: 10px;
    border:1px solid #9f9f9f;
    background-color: transparent;
    text-align:center;
    font-size:16px;
}
.collection-container .main .box .register-form .form >.title-err{
    color:red;
    font-weight: 550;
    font-size:14px
}
.collection-container .main .box .register-form .submit{
    font-size:20px;
    font-weight: bold;
    display:flex;
    justify-content: center;
    transform: translateY(-30px);
    height:fit-content;
}

.collection-container .main .box.register-form .submit > .register:hover{
    color:#8ADA7D;
    cursor:pointer;
}
.collection-container .main .box.register-form .submit > .cancel:hover{
    color:red;
    cursor:pointer;
}

.collection-container .main .box.register-btn .main{
    display:flex;
    flex-direction: column;
    align-items: center;
}
.collection-container .main .register-btn{
    position:absolute;
}
.collection-container .main .register-btn,
.collection-container .main .my-diary-btn,
.collection-container .main .content{
    cursor:pointer;
}

.collection-container .main .box.content .icon{
    position:absolute;
    right: 0;
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