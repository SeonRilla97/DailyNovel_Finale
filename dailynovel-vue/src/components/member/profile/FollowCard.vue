<script setup>
import { useRouter } from 'vue-router';
let router = useRouter();

function gotofollowpage(id){
    router.go("/member/room/follow");
}

</script>

<template>
    <router-link to="/member/room/follow">
        <section class="m-flwcard-container">
        <div class="m-follow-img">이미지</div>
        <div class="m-follow-name">이름</div>
        <div class="m-follow-checkbox">구독중</div>
    </section>    
    </router-link>
    
</template>

<style scoped>
.m-flwcard-container{
    width:100%;
    height: 100%;
    border-radius: 8px;

    justify-self: center;
    align-self: center;

    background-color: blueviolet;

    display: grid;
    grid-template-rows: 4fr 1fr 1fr;
    
    box-sizing: border-box;
}

.m-follow-img{
    width:100px;
    height:100px;
    justify-self: center;
    background-color: red;
    border-radius: 30px;
}
</style>
