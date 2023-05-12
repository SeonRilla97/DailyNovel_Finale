<template lang="">
    <detail :detailPage="model"></detail>

</template>
<script setup>
import detail from './detail.vue'

import { onMounted, reactive,ref, } from 'vue'

// 되는 gpt코드 -> 비교해보기
let model = reactive([]);
let count = ref();
let category = reactive([]);
let currentCategory = ref(1);


async function load() {
    const resList = await fetch('http://localhost:8080/display/list')
    const data = await resList.json()
    model.splice(0, model.length, ...data); //이게 핵심인 거 같다.

// const resLikeCount = await fetch('http://localhost:8080/community/count2')
// const data2 = await resLikeCount.json()
// console.log(data2);
// count.value = data2;

// const resCategory = await fetch('http://localhost:8080/community/category')
// const data3 = await resCategory.json()
// console.log(data3);
// category.splice(0, model.length, ...data3);

// // const resDetail = await fetch('http://localhost:8080/community/detail2?id='+model.id)
// // const resDetail = await fetch('http://localhost:8080/community/detail2?id=3')
// // const resDetail = await fetch(`http://localhost:8080/community/detail2?id=${this.$route.query.id}`);
// // const data4 = await resDetail.json()
// // console.log(data4);
}


onMounted(()=>{
load()
})



</script>
<style scoped>
    
</style>