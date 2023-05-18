<template lang="">
    <!-- <detail :detailPage="model[id]" :likeInfo="likeStatus" :segnal="load(memberId)"></detail> -->
    <!-- <detail :detailPage="model[id]" :likeInfo="likeStatus" :pageStatus="pageRefresh" @updatePage="updatePage"></detail> -->
    <detail :detailPage="model[id]" :likeInfo="likeStatus" @updatePage="updatePage"></detail>
     
</template>

<script setup>
import detail from './Detail.vue'
import { useRoute } from 'vue-router';
import {useUserDetailsStore} from '../../store/useUserDetailsStore.js'

import { onMounted, reactive,ref,} from 'vue'

let userDetails = useUserDetailsStore();

let route = useRoute();
let model = reactive([]);
let myFavorites = reactive([]);

let id = ref();
let likeStatus = ref();
let memberId = userDetails.id; // 지금은 멤버id를 1로 꽂아놨는데 이후에 확인해 봐야 함

let pageRefresh = ref(1);

async function load() {
    const likeList = await fetch(`http://localhost:8080/display/likeScan?mId=${memberId}`)
    const data = await likeList.json()
    myFavorites.splice(0, myFavorites.length, ...data);
    
    const resList = await fetch('http://localhost:8080/display/listall')
    const list = await resList.json()
    model.splice(0, model.length, ...list); //이게 핵심인 거 같다.
    id.value = route.params.id;
    
    likeStatus = myFavorites.some(item => item.diaryId === model[id.value].id)
    // console.log(likeStatus)  
}


onMounted(()=>{
    load()
})

function updatePage(){
    console.log("업데이트 완료")
    load()
}

</script>
<style scoped>
    
</style>