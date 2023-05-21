<script setup>
import { objectToString } from '@vue/shared';
import { ref, reactive, onUpdated, onMounted } from 'vue';

// let guestbook = reactive({});

let props = defineProps({
    guestbook: Object,

}) ;

let cmtOshow = ref(false);
let cmtRshow = false;

function showHandler(){
    cmtOshow.value = !cmtOshow.value;
}

onUpdated(()=>{
    
})

onMounted(() => {

});


</script>

<template>
<li class="lc-center">
    <section class="m-gbcard-container">

        <!-- 방명록 카드 헤더 -->
        <header class="m-gbcard-header">
            <span>From.</span>
            <div class="m-gbcard-writer"><span>{{ props.guestbook.writerName }}</span></div>
        </header>

        <!-- 방명록 카드 메인 -->
        <main class="m-gbcard-main">
            
            <!-- 방명록 카드 내용 -->
            <div class="m-gbcard-content">
                <span>{{ props.guestbook.content }}</span>
            </div>

            <!-- 방명록 카드 답글 -->
            <section class="m-gbcard-cmnt">
                

                <!-- 답글할 때 -->
                <form class="m-gbcard-cmnt-form" >
                    
                    <textarea class="m-gbcard-cmnt-writebox" placeholder="답글을 적어보세요." :value="props.guestbook.comment"></textarea>
                    <div class="m-gbcard-cmnt-updateiconbox">
                        <div class="m-guestbook-icon-box pencil-icon" @click="showHandler"></div>
                        <div class="m-guestbook-icon-box trash-icon" ></div>
                    </div>
                </form>   
                <form class="m-gbcard-cmnt-form" v-show="cmtOshow">
                    
                    <textarea class="m-gbcard-cmnt-writebox" placeholder="확인용 답글을 적어보세요." ></textarea>
                    <div class="m-gbcard-cmnt-updateiconbox">
                        <div class="m-guestbook-icon-box pencil-icon" ></div>
                        <div class="m-guestbook-icon-box trash-icon" ></div>
                    </div>
                </form>       

                <!-- 답글이 존재하지 않을 때 -->
                

                <!-- 방문자 모드 -->              
            </section>
        
        </main>   
    </section>
</li>
</template>

<style scoped>
.m-guestbook-icon-box{
  width:24px;
  height:24px;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

.m-guestbook-icon-box:hover{
  background-color: rgba(0,0,0,0.2);
}

/* 방명록 카드 박스 */
.m-gbcard-container{
    display: grid;
    grid-template-rows: 1fr 3.5fr ;

    width: 100%;
    height: 100%;

    background-color: #fafafa;
    border: 2px solid #FCD602;
    border-radius: 8px;



    box-sizing: border-box;
}

.m-gbcard-header{
    display:flex;
    justify-content: center;
    padding:8px;
    box-sizing: border-box;
}

.m-gbcard-main{
    width:100%;
    height:100%;

    display: grid;
    grid-template-rows: 4fr 1fr;

    box-sizing: border-box;
}
.m-gbcard-content{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
}

.m-gbcard-cmnt{
    display: flex;
}
.m-gbcard-cmnt-form{
    display: flex;
    align-items: center;
    width: 100%;
    height: 100%;
}
.m-gbcard-cmnt-updateiconbox{
    display: flex;
    flex-direction: column;
}
.m-gbcard-cmnt-writebox{
    font-family: 'Nanum Gothic', sans-serif;
    resize: none;
  
    width: 80%;
    height: 80%;
  
    outline: 1px #FCD602;

    border: none;
  
    background-color: #fafafa;
}
</style>