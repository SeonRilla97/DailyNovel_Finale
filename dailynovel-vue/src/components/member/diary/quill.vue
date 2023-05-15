<script setup>

import { ref,onMounted, onUpdated } from 'vue';
import { Quill, QuillEditor } from '@vueup/vue-quill';
// import BlotFormatter from 'quill-blot-formatter'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import ImageUploader from 'quill-image-uploader';
// import axios from '../lib/axios';


import 'quill-image-uploader/dist/quill.imageUploader.min.css';




const quill = ref(null);
console.log(quill);



//툴바 옵션들
const toolbarOptions = 
[
            [{ 'header': 1 }, { 'header': 2 }],
            ['bold', 'italic', 'underline','image'],
            [{ 'list': 'ordered'}, { 'list': 'bullet' }],
            [{ 'font': [] }, ''],
            // [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
            // [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
            // [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults 
];


onMounted(() => {

    // const modules0 = ref(modules0);



    quill.value = new Quill('.editor-quill'
    // ,{
    //     modules : {
    //         toolbar: toolbarOptions
    //     }
    // }
    );
    let contents = quill.value.getContents();
    let refValue = quill.value;

    // Quill.value.register("modules/imageUploader", ImageUploader);
    // const imageUploader = new ImageUploader(quill.value);


    // refValue.on('text-change', () => {
    //     console.log(quill.value.getContents());
    // });


    refValue.on('text-change', function(delta, oldDelta, source) {
        if (source == 'api') {
            console.log("An API call triggered this change.");
        } else if (source == 'user') {
            console.log("A user action triggered this change.");
        }


    refValue.on('selection-change', function(range, oldRange, source) {
        if (range) {
            if (range.length == 0) {
            console.log('User cursor is on', range.index);
            } else {
            var text = refValue.getText(range.index, range.length);
            console.log('User has highlighted', text);
            }
        } else {
            console.log('Cursor not in the editor');
        }
    });


});




    // imageUploader.on('imageUpload', (file) => {
    // // Upload the image to your server and return the URL
    //     const url = '/upload-image';
    //     const formData = new FormData();
    //     formData.append('image', file);

    //     fetch(url, {
    //         method: 'POST',
    //         body: formData,
    //     })
    //     .then(response => response.json())
    //     .then(data => {
    //         quill.insertImage(data.url);
    //     });
    //     });

    const modules = {
          upload: file => {
            return new Promise((resolve, reject) => {
              const formData = new FormData();
              formData.append("image", file);

              fetch('/upload-image', formData)
              .then(res => {
                console.log(res)
                resolve(res.data.url);
              })
              .catch(err => {
                reject("Upload failed");
                console.error("Error:", err)
              })
            })}};

    });
</script>

<template>

    <QuillEditor 
        class="editor-quill"
        placeholder="나의 마음을 찬찬히 넣어주세요"
        :toolbar="toolbarOptions"
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
