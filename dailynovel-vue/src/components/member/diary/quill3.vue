<template>
    <div>
      <!-- Create the editor container -->
      <div 
      @blur="focusOutHandler"
      id="editor"
      @click="quillBolck"
      @keydown="enterHandler"></div>

      <!-- <div
      id="editor1">
    </div> -->

    </div>
  </template>

<script setup>
import Quill from 'quill';
import {ref , onMounted, onUpdated, defineEmits,defineProps } from 'vue';

let JosnRef = ref("");
const emit = defineEmits(
    ["quillOutput"]
);

const props = defineProps({
    'loadDiaryContent' : '',
});

var toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike', 'image'],        // toggled buttons
//   ['blockquote', 'code-block'],
//   [],

  [{ 'header': 1 }, { 'header': 2 }],               // custom button values
  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
//   [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
//   [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
//   [{ 'direction': 'rtl' }],                         // text direction

//   [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
//   [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

  [{ 'color': [] }, { 'font': [] }],
//   [{ 'align': [] }],

//   ['clean']                                         // remove formatting button
];

let quill = "";
let deltaJson = null;
let convertDeltaJson = null;
let putToggle = false;
let isInput = false;

let isFirst = true;
let isAfter = false;
let BeforeJson = null;
let ToJson = "";

let ReadyToChange = 0;

const quillBolck = (e) => {
    console.log(e.target);
    ReadyToChange ++;
    isInput = true;
    console.log("스위치");
};

const focusOutHandler = (e) => {
    // console.log(e.target);
}

const enterHandler = (e) => {
    console.log(e.code);


    if(e.code == 'ControlLeft'){
        
        emitReady();
    };
    if(e.code == 'F1'){
        console.log(quill.getText())
    }

}
// let quill2;
onMounted(() => {

    quill = new Quill('#editor', {
        theme: 'snow',
        modules: {
            toolbar: toolbarOptions
        },
    });

    // quill2 = new Quill('#editor1',{
    //     readOnly: true
    // });

    quill.on('text-change', function(delta, oldDelta, source) {
        // console.log( quill.getLength());

        let contents = quill.getContents();
        deltaJson = contents.ops;

        // if(quill.getLength() % 2 == 0 && isInput == true){
        //     console.log("스위치2");
        //     putToggle = !(putToggle);
        //     emitReady();
        // }
    });
});

function emitReady(){

    // if(putToggle == true){
        convertDeltaJson = JSON.stringify(deltaJson);
        emit("quillOutput", convertDeltaJson);
        // putToggle = !(putToggle);
    // }

}; 



onUpdated(() => {
    //처음에 props 담는 용
    // console.log(props.loadDiaryContent);
    // if(BeforeJson == null){
        
    console.log(BeforeJson);
//db 에 있는 데이터 quill 에 뿌려주기
    BeforeJson = props.loadDiaryContent;

    editTriger(BeforeJson);
    // editTriger2(BeforeJson);
    // console.log( quill2.getText());
        // isFirst = false;
    // }
    //이후 용
    // else if(BeforeJson != null){
    //     console.log(BeforeJson);
    //     console.log("계속 들어옴?");

    //     BeforeJson = props.loadDiaryContent;
    //     ToJson = JSON.parse(BeforeJson);
    //     quill.setContents(ToJson);
    // }

});

//원본 row한 스트링을 받음
function editTriger(Json1){

    // let a = Json1.charAt(1);
    // console.log(a);

    // console.log(typeof(Json1));
    console.log(Json1);
    // if(typeof(Json1) == 'string'){
        // quill.setText(Json1);
    // }
    // else{
        ToJson = JSON.parse(Json1);
        quill.setContents(ToJson);
    // }
}

// function editTriger2(Json1){
//     ToJson = JSON.parse(Json1);
//     quill2.setContents(ToJson);
// }

function editortrigger (delta, oldDelta, source) {
    // console.log(quill.value.getContents().ops[0].insert);
    console.log(quill.value.getContents().ops);
    
    
    if (source == 'api') {
        console.log("An API call triggered this change.");
    } else if (source == 'user') {
        console.log("A user action triggered this change.");
    }
};


  </script>

<style scoped>

#editor{
    height: 300px;
}


</style>