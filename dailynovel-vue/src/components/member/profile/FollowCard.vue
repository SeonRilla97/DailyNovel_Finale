<script setup>
import { useRouter } from 'vue-router';
let router = useRouter();

const props = defineProps({
  data: {
    type: Object,
    required: true
  }
});

function gotofollowpage(id){
    router.go("/member/room/follow");
}

async function getFollowMemberInfo(id){
    fetch(`http://localhost:8080/members/info?id=${id}`,{
        
    })
}
console.log( props.data.nickName );

</script>

<template>
  <router-link :to="`/member/room/follow/${props.data.id}`">
        <section class="m-flwcard-container">
            <div class="m-follow-img">이미지</div>
            <div class="m-follow-name lc-center"><span>{{ props.data.nickName }}</span></div>
            <div class="m-follow-checkbox lc-center"><div>구독중</div></div>
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

    background-color: #FFF8F3;

    display: grid;
    grid-template-rows: 4fr 1fr 1fr;
    
    box-sizing: border-box;
}

.m-follow-img{
    width:100px;
    height:100px;
    justify-self: center;
    background-color: red;
    background-image: url(http://localhost:8080/image/profile/8.png);
    border-radius: 30px;
}
</style>
