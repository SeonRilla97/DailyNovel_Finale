<script setup>
import { reactive, ref } from 'vue';
import { useUserDetailsStore } from "../../store/useUserDetailsStore.js";
// import Main from "./Main.vue"
// import Main from './Main.vue';

let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
// 사용자의 컬렉션 리스트
const collection = reactive({
    List : ["가장 행복했던 여름","영화 모음","여행 모음","집가는 중에","123"]
})
const diaryDisplayed= reactive({
    list : null
})
getCollectionList();

// 컬렉션 리스트 받아오기
function getCollectionList(){
fetch(`http://localhost:8080/collection?memberId=${userDetails.id}`)
    .then(response => response.json())
    .then(collections => {
        // 받은 데이터 일단 볼까
        collection.List = collections;
        console.log(collection.List);
    })
    .catch(error => console.log('error', error));
}

// Collection 데이터 쑤셔넣기
function PostCollection(collectionName){

let myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

let raw = JSON.stringify({
  "memberId": userDetails.id,
  "name": collectionName
});

let requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("http://localhost:8080/collection", requestOptions)
  .then(response => response.text())
  .then(isSuccess => {
    // 컬렉션 등록 성공
    if(isSuccess=="false"){ //중복이면 (false라면)
            // 중복이라면 중복입니다를 알리고 아무 기능도 작동하지 않는다.
            // 하위 컴포넌트에 중복임을 알림(isDuplicated의 watchEffect로 동작)
            isColNameDuplicated.value =true; //중복됨
            return;
        }
        // 창(입력폼에서 입력버튼으로돌아가고 리스트는 추가된다
        getCollectionList()
        regSuccess.value = true;
})
  .catch(error => console.log('error', error));
}
// 이름 중복 유무 확인
let isColNameDuplicated =ref(false);
// 등록 성공 여부 확인
let regSuccess = ref(false);
function regBtnClickHandler(CollectionName) {
    // 컬렉션 등록 (true = 성공 / false = 중복)
    PostCollection(CollectionName);
    
}
function successInit(){
    regSuccess.value = false;
}


getSharedDiary();

// 데이터 불러오기 -> 유저가 공유한적있는 모든 다이어리 불러오기
function getSharedDiary(sortMenu) {
let requestOptions = {
method: 'GET',
redirect: 'follow'
};
// 쿼리 만들기 => memberId필수 , sortMenu 선택(좋아요순, 최신순)
let query = `memberId=${userDetails.id}`
if(sortMenu)
    query+= `&sortStandard=${sortMenu}`
    // console.log(query);
    // console.log(sortMenu);
fetch(`http://localhost:8080/diary/displayed?${query}`, requestOptions)
.then(response => response.json())
.then(result => {
    diaryDisplayed.list = result;

})
.catch(error => console.log('error', error));
}
</script>


<template>
    <section class="collection-container">
        <header>
            <div class="pdl-5 h2 font-bold"><router-link class="" to="/member/room/collection/main">컬렉션</router-link></div>
        </header>
        <transition name="fade">
            <router-view
                :collection="collection" 
                :isDuplicated ="isColNameDuplicated" 
                :successAddMenu = "regSuccess"
                :displayedDiary = "diaryDisplayed"
                @registerCollection = "regBtnClickHandler"
                @initSuccesAddMenu = "successInit"
                @callDisplayed="getSharedDiary"
                @callgetCollectionList = "getCollectionList"
            >
            </router-view>
        </transition>
    </section>
</template>


<style scoped>
.collection-container{
    display: grid; 
    grid-template-rows: 72px auto;
    grid-template-areas: 1fr;
    /* overflow:hidden; */
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
    max-width: 1280px;
}

.collection-container > header {
    display:flex;
    align-items: center;
}


.fade-enter-active {
    animation: fade 0.5s;
}
/* .fade-enter-from
{
    animation: fade 0.5s reverse;
} */
@keyframes fade {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}


</style>