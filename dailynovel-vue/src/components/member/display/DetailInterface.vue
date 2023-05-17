<template lang="">
    <detail :detailPage="model[id]" :likeInfo="like[id]"></detail>

</template>
<script setup>
import detail from './Detail.vue'
import { useRoute } from 'vue-router';

import { onMounted, reactive,ref, } from 'vue'

// 되는 gpt코드 -> 비교해보기
let route = useRoute();
let model = reactive([]);
let like = reactive([]);

let id = ref();

let memberId = 1; // 지금은 멤버id를 1로 꽂아놨는데 이후에 확인해 봐야 함

async function load() {
    const resList = await fetch('http://localhost:8080/display/listall')
    const list = await resList.json()
    model.splice(0, model.length, ...list); //이게 핵심인 거 같다.
    id.value = route.params.id;

    const likeList = await fetch(`http://localhost:8080/display/likeScan?mId=${memberId}`)
    const data = await likeList.json()
    like.splice(0, like.length, ...data);
}


onMounted(()=>{
    load()
})



</script>
<style scoped>
    
</style>