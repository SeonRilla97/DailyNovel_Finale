<script setup>
import { reactive } from 'vue';

const collection = reactive({
    List : ["가장 행복했던 여름","영화 모음","여행 모음","집가는 중에","123"]
})
getCollectionList();

// 컬렉션 리스트 받아오기
function getCollectionList(){
fetch("http://localhost:8080/collection")
    .then(response => response.json())
    .then(collections => {
        // 받은 데이터 일단 볼까
        // console.log(collections);
        collection.List = collections;
        console.log(collection.List);
    })
    .catch(error => console.log('error', error));
}
defineProps()

// Collection 데이터 쑤셔넣기
function PostCollection(){
fetch("http://localhost:8080/collection")
    .then(response => response.json())
    .then(collections => {
        // 받은 데이터 일단 볼까
        // console.log(collections);
        collection.List = collections;
        console.log(collection.List);
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
            <router-view :collection="collection"></router-view>
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