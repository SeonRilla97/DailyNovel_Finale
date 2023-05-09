<script setup>
import Header from './Header.vue'
import List from './List.vue'
import Editor from './editor.vue'

</script>
<template>
    <div class="diary-container">
        <Header />


        <section class="diary-main">
            <List/>

            <Editor />
        </section>
    </div>
</template>


<style scpoed>
/* 페이지 컨테이너 */
.diary-container{
    display:flex;
    flex-direction: column;
}
/* 다이어리 컨텐츠 부분 레이아웃 잡기*/
.diary-main{
display:flex;
flex-grow: 1;
}

</style>