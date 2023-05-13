<template lang="">
    <detail :detailPage="model[id]"></detail>

</template>
<script setup>
import detail from './Detail.vue'
import { useRoute } from 'vue-router';

import { onMounted, reactive,ref, } from 'vue'

// 되는 gpt코드 -> 비교해보기
let route = useRoute();
let model = reactive([]);

let id = ref();

async function load() {
    const resList = await fetch('http://localhost:8080/display/listall')
    const data = await resList.json()
    model.splice(0, model.length, ...data); //이게 핵심인 거 같다.
    id.value = route.params.id;
}


onMounted(()=>{
load()
})



</script>
<style scoped>
    
</style>