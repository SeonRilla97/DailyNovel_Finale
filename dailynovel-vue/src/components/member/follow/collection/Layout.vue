<script setup>
import { reactive, ref, onBeforeMount } from 'vue';
import { useUserDetailsStore } from "../../../store/useUserDetailsStore.js";
// import Main from "./Main.vue"
// import Main from './Main.vue';

let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
// 사용자의 컬렉션 리스트
const collection = reactive({
    List : ["가장 행복했던 여름","영화 모음","여행 모음","집가는 중에","123"]
})
const props  = defineProps({
    userId: {
        type: Object,
        required:true
    }
});

onBeforeMount(() => {
    pfgetCollectionList(); 
    console.log(props.userId)   
})


// 컬렉션 리스트 받아오기
function pfgetCollectionList(){
fetch(`http://localhost:8080/collection?memberId=${props.userId}`)
    .then(response => response.json())
    .then(collections => {
        // 받은 데이터 일단 볼까
        collection.List = collections;
        console.log(collection.List);
    })
    .catch(error => console.log('error', error));
}
</script>


<template>
    <section class="collection-container">
        <transition name="fade">
            <router-view
                :userId="userId"
                class="main"
                :collection="collection" 
                :isDuplicated ="isColNameDuplicated" 
                :displayedDiary = "diaryDisplayed"
                @registerCollection = "regBtnClickHandler"
                @initSuccesAddMenu = "successInit"
                @callDisplayed="getSharedDiary"
            >
            </router-view>
        </transition>
    </section>
</template>


<style scoped>
.collection-container{
    display: grid; 
    /* grid-template-rows: 72px 505px; */
    grid-template-rows: 1fr;
    grid-template-areas: 1fr;
    /* overflow:hidden; */
    max-width: 1280px;
}
.collection-container .main{
    grid-row-start:1;
    grid-row-end:2;
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