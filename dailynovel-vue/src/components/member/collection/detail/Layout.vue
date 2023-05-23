<script setup>
import { ref , onBeforeMount, reactive} from 'vue';
import { useRoute,useRouter } from 'vue-router'
import { useUserDetailsStore } from '../../../store/useUserDetailsStore';

let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
let pageIndex = ref(1)

const route = useRoute()
console.log(route.path)
// 현재 컬렉션 ID값 추출
const collectionId = ref('');
collectionId.value = route.params.collectionId
// 사용자의 ID값 추출
const memberId = userDetails.id;
const data = reactive({
    diarys:{},
    comments:{}
})

// getListInCollection(memberId,collectionId.value);

// router.push("detailDiary")
// 댓글불러와야함

// 해당 컬렉션에 포함된 모든 일기 불러오기
function getListInCollection(memberId,collectionId) {
    let member = memberId;
    let collection = collectionId;
    var requestOptions = {
    method: 'GET',
    redirect: 'follow'
    };

    fetch(`http://localhost:8080/diary/incollection?memberId=${member}&collectionId=${collection}`, requestOptions)
    .then(response => response.json())
    .then(result => {
        data.diarys= result;
        
    })
    .catch(error => console.log('error', error));
}

onBeforeMount (()=> {
    console.log("하하하하하")
    getListInCollection(memberId,collectionId.value);

    const router = useRouter()

    router.push({name:'detailDiary'})
})

// 해당 컬렉션의 댓글 대댓글 모두 불러오기


let menuControl= ref(1);

function menuClickHandler(menuIdx){
    menuControl.value = menuIdx
}
</script>

<template>
    <div class="detail-container">
        <header class="header">
            <div class="pdl-5 h2 font-bold">
                <router-link to="/member/room/collection/detail/s/diary"><div class="ib" @click="menuClickHandler(1)" :class="{active : menuControl==1}">일기</div></router-link>
                <router-link class="mgl-2" to="/member/room/collection/detail/s/comment"><div class="ib" @click="menuClickHandler(2)" :class="{active : menuControl==2}">댓글</div></router-link>
            </div>
            <div class="icon-back"></div>
        </header>
        
        <router-view
        :data = "data"></router-view>
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