<script setup>

import { ref } from 'vue';
import { onMounted, onUpdated } from 'vue';
import { Quill, QuillEditor } from '@vueup/vue-quill';
// import BlotFormatter from 'quill-blot-formatter'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

const quill = ref(null);
console.log(quill);


onMounted(() => {
    quill.value = new Quill('.editor-quill');
    let contents = quill.value.getContents();
    let refValue = quill.value;

    console.dir(typeof(contents));

    refValue.on('text-change', () => {
        console.log(quill.value.getContents());
    });
    
});


</script>

<template>

    <QuillEditor 
        class="editor-quill"
        placeholder="나의 마음을 찬찬히 넣어주세요"
        theme="snow"
        toolbar="minimal"
        :modules="modules"
    />
    
    
</template>


<style scoped>

.editor-quill{
    /* border: none; */

    /* width: 100%;
    height: 500px; */


}



</style>
