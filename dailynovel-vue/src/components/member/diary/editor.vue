<script setup>
import quill from './quill.vue';
import quillCopy from './quill copy.vue';

</script>

<template>

<div class="editor-wrap"
    @click="onclickedHandler"
    >

    <div class="editor-date">2023년 4월 10일 오후 02시 01분 </div>

    <div class="editor-title"> 
        <!-- <span class="editor-title-content">자연에서 즐기는 커피 </span> -->
        <header contenteditable="true"  class="editor-title-content editor-header">
          자연에서 즐기는 커피 
        </header>
        <span class="editor-share">공유 중</span>
    </div>

    <hr class="editor-title-hr">

 
  <main class="editor-main">

    <quill
          />

          <!-- <quillCopy/> -->

  </main>

</div>


</template>

<style scoped>



.editor-wrap{
  width: 95%;
  height: 95%;
  
  /* overflow: scroll; */
  /* overflow: scroll; */
  margin: 15px 40px;
}
.editor-main{
  height: 80%;

  /* height: auto; */
  overflow-y: scroll;

}

.editor-title-content{
    color: #3F3F3F;
    /* font-size: 20px; */
    margin-top: 10px;

    line-height: 1.2;
}

.editor-title-content:focus{
    outline: none;
}

/* placeholader 관련 설정 */

/* .editor.empty:before {
  content: attr(placeholder);
  margin-top: 10px;
  color: #9A9A97;
  font-size: 20px;
} */

/* p#editor::placeholder{
    color: #9A9A97;
    font-size: 15px;
    padding: 0px;
} */

.editor-title{
    display: flex;

    justify-content: space-between;
    align-items: center;
}

    .editor-title-content{
        flex-grow: 1;
    }

    .editor-share{
        /* box-sizing: border-box; */
        /* width: 60px;
        height: 24px; */

        /* background-color: #191F78;
        
        padding: 4.5px 13px;
        border: 1px solid #191F78;
        border-radius: 2px;

        color: #FCFCFC;
        font-size : 12px;
        text-align: center; */

        justify-self: center;
        align-self: center;
        width:60px;
        height:24px;

        display:flex;
        justify-content: center;
        align-items: center;
        color:#fcfcfc;
        font-size:12px;
        border-radius: 5px;
        background-color: #191F78;


    }


    .editor-share.active{
      background-color: transparent;
        border: 1px solid #8391FC;
        color: #8391FC;
    }


.editor-date{
    color: #636363;
    font-size: 15px;
}

.editor-title{

    margin-top: 45px;

    color: #252728;
    font-size: 32px;
    font-weight: bold;

}

.editor-title-hr{

background-color: black;
height: 1px;
}

</style>