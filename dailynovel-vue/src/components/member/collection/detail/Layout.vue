<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router'
import { useUserDetailsStore } from '../../../store/useUserDetailsStore';

let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
let pageIndex = ref(1)

const route = useRoute()
// url정보의 id값 추출
const collectionId = route.params.collectionId;
// 사용자의 ID값 추출
const memberId = userDetails.id;

console.log(collectionId,memberId)



// 댓글불러와야함
</script>


<template>
    <div class="detail-container">
        <header class="header">
            <div class="pdl-5 h2 font-bold">
                <router-link to="/member/room/collection/detail/s/diary"><div class="ib active">일기</div></router-link>
                <router-link class="mgl-2" to="/member/room/collection/detail/s/comment"><div class="ib">댓글</div></router-link>
            </div>
            <div class="icon-back"></div>
        </header>
        
        <router-view></router-view>
    </div>
</template>
<style scoped>

.detail-container{
    display: grid; 
    grid-template-rows: 72px auto;
    /* grid-template-areas: 1fr; */
    /* overflow:hidden; */
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
    max-width: 1280px;
}

.detail-container > header {
    display:flex;
    align-items: center;
    position:relative;
}
.icon-back{
    background-image: url("../../../assets/img/backArrow.svg");
    background-repeat: no-repeat;
    background-size: 24px 24px;
    background-position: center center;
    width:24px;
    height: 24px;
    position:absolute;
    right:10%;
}


.detail-container .header .active{
    text-decoration: underline;
    color: #FCD602;
    text-underline-position : under;
}

/* vue Transition */


.fade-enter-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from
 {
    opacity: 0;
}


</style>