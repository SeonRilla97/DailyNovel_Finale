<template>
    <div>
      <!-- Create the editor container -->
      <div id="editor" ></div>
    </div>
  </template>
  
  <script setup>
  import Quill from 'quill';
  import { onMounted, onUpdated } from 'vue';
  
//   export default {
//     mounted() {
//       // Initialize Quill editor
//       this.quill = new Quill(this.$refs.editor, {
//         theme: 'snow'
//       });
//     },
    
var toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike', 'image'],        // toggled buttons
  ['blockquote', 'code-block'],

  [{ 'header': 1 }, { 'header': 2 }],               // custom button values
  [{ 'list': 'ordered'}, { 'list': 'bullet' }],
  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
  [{ 'direction': 'rtl' }],                         // text direction

  [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],

  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
  [{ 'font': [] }],
  [{ 'align': [] }],

  ['clean']                                         // remove formatting button
];

let quill = "";
let deltaJson = null;
let convertDeltaJson = null;

onMounted(() => {
    quill = new Quill('#editor', {
        theme: 'snow',

        modules: {
            toolbar: toolbarOptions
        },
    });

    let contents = quill.getContents();

    quill.on('text-change', function(delta, oldDelta, source) {

        let contents = quill.getContents();
        console.log(contents.ops);

        setTimeout(function(){
            deltaJson = contents.ops;
            convertDeltaJson = JSON.stringify(deltaJson);
            console.log(convertDeltaJson);
            // emit("quillOutput", convertDeltaJson);

        },3000);

        // if (source == 'api') {
        //     console.log("An API call triggered this change.");
        // } else if (source == 'user') {
        //     console.log("A user action triggered this change.");
        // }
    });


    
});

onUpdated(() => {
    
    quill.on('text-change',editortrigger);
    

}),

function editortrigger (delta, oldDelta, source) {
    // console.log(quill.value.getContents().ops[0].insert);
    console.log(quill.value.getContents().ops);
    

    // setTimeout(function(){
    //     deltaJson = quill.value.getContents().ops;
    //     convertDeltaJson = JSON.stringify(deltaJson);

    //     emit("quillOutput", convertDeltaJson);

    // },3000);

    // console.log(delta);

    // ops[0].insert
    
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