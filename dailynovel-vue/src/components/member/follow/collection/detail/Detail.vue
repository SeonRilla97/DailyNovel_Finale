<script setup>
import Quill from 'quill';
import { onMounted ,onUpdated} from 'vue'
import { useRoute} from 'vue-router'


const props = defineProps({
    data: {
        type: Object,
        required:true
    }
})

const route = useRoute()

// console.log(idx);
// console.log(props.data.diaryCntHtml[idx]);

let idx;
let quiText;
let quill;
onMounted(() => {
    quill = new Quill('#editors', {
        readOnly: true
    });
    idx = route.params.idx
    quiText = props.data.diaryCntHtml[idx];
    console.log("시발!!시발!!시발!!시발!!시발!!시발!!시발!!" +quiText)
    quill.setContents(quiText)
})
onUpdated(() => {
    
})
</script>

<template>
<div>일단 나오는거 확인</div>
<div>
    <div id="editors" style="width:300px;height: 100px;"></div>
</div>
</template>