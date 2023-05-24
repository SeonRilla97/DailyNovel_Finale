<script setup>
import{ref,toRefs,watchEffect,toRef} from 'vue'
import { useRouter,useRoute } from 'vue-router'
let registerMenuController = ref(false);

const props  = defineProps({
    collection: {
        type: Object,
        required:true
    },
    isDuplicated: null,
    successAddMenu: null
})
function menuClickHandler(e){
    console.log(e.target);
}
const router = useRouter()
function colClickHandler(collectionId){
    router.push({
         name: 'detail', 
         params: { "collectionId": collectionId } 
    })
}
</script>


<template>
    <div class="collection-container undrag" >
        <!-- <router-link to="./detail/comment" class="mgl-5" v-for="colList in collection.List"  > -->
            <div class="box mgl-5" v-bind:data-id="colList.id"  v-for="colList in collection.List" @click.stop="colClickHandler(colList.id)">
                <div class="header">
                    <!-- <div class="icon-tack">\
                    </div> -->
                    <div class="dropdown">
                        <div class="icon-tack"></div>
                    </div>
                </div>
                <h1 class="title">{{colList.name}}</h1>
            </div>
        <!-- </router-link> -->
    </div>
</template>
<style scoped>
.collection-container{
    display:flex;
    align-items: center;
    overflow-x: scroll; 
    width:100%;
    height: 100%;

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

.icon-tack{
    background-image: url("../../../../assets/img/pin.svg");
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
    background-image: url("../../../../assets/img/chat.svg");
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