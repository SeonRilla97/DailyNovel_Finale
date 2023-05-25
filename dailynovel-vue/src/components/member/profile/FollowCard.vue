<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
let router = useRouter();

const props = defineProps({
  data: {
    type: Object,
    required: true
  }
});

const followMImage = ref("default.jpg");

function gotofollowpage(id){
    router.go("/member/room/follow");
}

// async function getFollowMemberInfo(id){
//     fetch(`http://localhost:8080/members/info?id=${id}`,{
        
//     })
// }

async function getFollowMemberInfoById(id) {
    await fetch(`http://localhost:8080/members/mInfo?id=${id}`,
    {
      method: "GET",
      headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
      },      
    })
    .then(response => response.json())
    .then((data) => 
    {
    followMImage.value = data.image;
    // console.log(image.value);
    
    })    
    .catch(error => console.log(error));
}
console.log( props.data.nickName );

onMounted(()=>{
    getFollowMemberInfoById(props.data.id);
})
</script>

<template>
  <router-link :to="`/member/room/follow/${props.data.id}`">
        <section class="m-flwcard-container">
            <div class="m-follow-img" :style="`background-image: url(http://localhost:8080/image/profile/${followMImage})`">이미지</div>
            <div class="m-follow-name lc-center"><span>{{ props.data.nickName }}</span></div>
            <div class="m-follow-checkbox lc-center"><div> </div></div>
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
    width:180px;
    height:180px;
    justify-self: center;
    background-color: red;
    background-image: url(http://localhost:8080/image/profile/8.png);
    background-repeat: no-repeat;
    background-size: cover;
    border-radius: 50%;
    text-indent: -999rem;
}
</style>
