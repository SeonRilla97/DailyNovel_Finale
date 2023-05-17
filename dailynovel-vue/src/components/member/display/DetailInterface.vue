<template lang="">
    <detail :detailPage="model[id]" :likeInfo="likeStatus" :segnal="load(memberId)"></detail>
     
</template>

<script setup>
import detail from './Detail.vue'
import { useRoute } from 'vue-router';

import { onMounted, reactive,ref,} from 'vue'

let route = useRoute();
let model = reactive([]);
let indeLikeList = reactive([]);

let id = ref();
let likeStatus = ref();
let memberId = 1; // 지금은 멤버id를 1로 꽂아놨는데 이후에 확인해 봐야 함

async function load() {
    const likeList = await fetch(`http://localhost:8080/display/likeScan?mId=${memberId}`)
    const data = await likeList.json()
    indeLikeList.splice(0, indeLikeList.length, ...data);
    
    const resList = await fetch('http://localhost:8080/display/listall')
    const list = await resList.json()
    model.splice(0, model.length, ...list); //이게 핵심인 거 같다.
    id.value = route.params.id;
    
    likeStatus = indeLikeList.some(item => item.diaryId === model[id.value].id)
    // console.log(likeStatus)  
}


onMounted(()=>{
    load()
})


</script>
<style scoped>
    
</style>