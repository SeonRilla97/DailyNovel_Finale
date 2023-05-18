<script setup>
import { reactive } from 'vue';
import { useUserDetailsStore } from "../../store/useUserDetailsStore.js";
// import Main from "./Main.vue"
// import Main from './Main.vue';

let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
const collection = reactive({
    List : ["가장 행복했던 여름","영화 모음","여행 모음","집가는 중에","123"]
})
getCollectionList();

// 컬렉션 리스트 받아오기
function getCollectionList(){
fetch(`http://localhost:8080/collection?memberId=${userDetails.id}`)
    .then(response => response.json())
    .then(collections => {
        // 받은 데이터 일단 볼까
        // console.log(collections);
        collection.List = null;
        collection.List = collections;
        console.log(collection.List);
    })
    .catch(error => console.log('error', error));
}
defineProps()

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
    console.log(isSuccess)
    if(isSuccess=="false"){ //중복이면
            console.log("중복")
            // 중복이라면 중복입니다를 알리고 아무 기능도 작동하지 않는다.
            return
        }
        // 창은 원래대로 돌아가고 리스트는 추가된다
        getCollectionList()
})
  .catch(error => console.log('error', error));
}
// 이름 중복 유무 확인
let isColNameDuplicated =ref(false);
function regBtnClickHandler(CollectionName) {
    // 컬렉션 등록 (true = 성공 / false = 중복)
    PostCollection(CollectionName);
    
}
</script>


<template>
    <section class="collection-container">
        <header>
            <div class="pdl-5 h2 font-bold"><router-link class="" to="/member/room/collection/main">컬렉션</router-link></div>
        </header>
        <transition name="fade">
            <router-view :collection="collection" :isDuplicated ="isColNameDuplicated" @registerCollection = "regBtnClickHandler"></router-view>
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
  transition: opacity 0.2s ease;
}

.fade-enter-from
 {
    opacity: 0;
}
</style>