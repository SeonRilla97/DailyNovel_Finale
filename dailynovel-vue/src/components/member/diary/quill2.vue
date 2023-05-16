<script setup>

import { ref,onMounted, onUpdated } from 'vue';
import { Quill, QuillEditor } from '@vueup/vue-quill';
// import BlotFormatter from 'quill-blot-formatter'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import ImageUploader from 'quill-image-uploader';
// import axios from '../lib/axios';


import 'quill-image-uploader/dist/quill.imageUploader.min.css';

const quill = ref(null);


let contents ;
let refValue ;



    //현재 위치 받아오기 API
    function geoFindMe() {
        const status = document.querySelector("#status");
        const mapLink = document.querySelector("#map-link");

        mapLink.href = "";
        mapLink.textContent = "";

        function success(position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;

            status.textContent = "";
            mapLink.href = `https://www.openstreetmap.org/#map=18/${latitude}/${longitude}`;
            mapLink.textContent = `Latitude: ${latitude} °, Longitude: ${longitude} °`;

            console.log(mapLink.textContent);
        }

        function error() {
            status.textContent = "Unable to retrieve your location";
        }

        if (!navigator.geolocation) {
            status.textContent = "Geolocation is not supported by your browser";
        } else {
            status.textContent = "Locating…";
            navigator.geolocation.getCurrentPosition(success, error);
        }
    }
    refValue.on('text-change',geoFindMe);

onMounted(() => {

    quill.value = new Quill('.editor-quill');
    console.log(quill.value);

    contents = quill.value.getContents();
    refValue = quill.value;


    refValue.on('text-change', function(delta, oldDelta, source) {
        if (source == 'api') {
            console.log("An API call triggered this change.");
        } else if (source == 'user') {
            console.log("A user action triggered this change.");
        }
    });


    // quill.value.on('text-change', function(delta, oldDelta, source) {
    //     if (source == 'api') {
    //         console.log("An API call triggered this change.");
    //     } else if (source == 'user') {
    //         console.log("A user action triggered this change.");
    //     }
    // });

});


</script>

<template>

<div class="editor-quill">
    <QuillEditor :toolbar="['bold', 'italic', 'underline', 'image']" />
</div>
    <!-- id="editor-quill" -->


    
    
</template>


<style scoped>

.editor-quill{
    /* border: none; */

    /* width: 100%;
    height: 500px; */


}



</style>
