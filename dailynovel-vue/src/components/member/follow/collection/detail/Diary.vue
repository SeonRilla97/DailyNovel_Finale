<script setup>
// detail Layout으로부터 data 받기
import {onBeforeMount,onMounted} from 'vue'
import { useRouter,useRoute } from 'vue-router'
let router = useRouter()
const props = defineProps({
    data: {
        type: Object,
        required:true
    }
})
onBeforeMount(()=>{
console.log("이얍!")
console.log(props.data.diarys)
});
console.log(props.data.diarys)

function mvDetailPage(idx){
    console.log(idx);
    router.push({path:`/member/room/follow/s/col/detail/s/one/${idx}`})
}
</script>
<template>
    <div class="container">
        <div class="diary-list" >

            <section class="box" v-for="(value, idx) in data.diarys" @click="mvDetailPage(idx)">
                    <div class="title">{{value.title}}</div>
                    <div class="main">
                        <div class="content mgt-4">{{ data.diaryCntText[idx]}}</div>
                    </div>
                    <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">{{ value.likes }}</span></div>   
            </section>
            
        </div>
        <div class="err-no-diary" v-if="data.diarys.length==0">일기가 없습니다.</div>
    </div>
    
</template>

<style scoped>
.container{

    display:flex;
    align-items: center;
    overflow-x: auto;
    width: 100%;
    height: 504px;
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
}
.container .diary-list{

    display:flex;
    align-items: center;
    transform: translateY(-10%);
    padding:0 58px ;
    /* box-sizing: border-box; */
} 

.container .box{

    width:240px;
    height: 360px;
    margin-left: 36px;
    border: 1px solid #00000020;
    border-radius: 12px;
    display:grid;
    grid-template-rows: 1fr 5fr 0.5fr;
    text-align: center;
    position:relative;
    box-sizing: border-box;
    padding:12px;
}
.box .main .content{
    overflow: hidden;
    line-height: 18px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 12;
}
.container .box .title{

    font-size: 20px;
    font-weight: bold;
    border-bottom: 1px solid #00000020;
    /* justify-self:center; */

}
.container .box >.title{

    display:flex;
    justify-content: center;
    align-items: center;
}

.icon-like{
    background-image: url("../../../../assets/img/like.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;
    width:16px;
    height: 16px;
}
.err-no-diary{
    font-size:30px;
    position:absolute;
    left:50%;
    top:50%;
    transform: translate(-50% ,-50%);
}
</style>