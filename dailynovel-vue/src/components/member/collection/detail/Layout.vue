<script setup>
import { ref , onBeforeMount, reactive, onMounted} from 'vue';
import { useRoute,useRouter } from 'vue-router'
import { useUserDetailsStore } from '../../../store/useUserDetailsStore';
import Quill from 'quill';
let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
console.log("Detail의 Layout입니다.")
const route = useRoute()
console.log(route.path)
// 현재 컬렉션 ID값 추출
const collectionId = ref('');
// 사용자의 ID값 추출
const memberId = userDetails.id;
const data = reactive({
    diarys:{},
    comments:{},
    collectionId: collectionId.value,
    diaryCntText:{}, //TEXT
    diaryCntHtml:{} // HTML
})
console.log(collectionId.value);
// getListInCollection(memberId,collectionId.value);

// router.push("detailDiary")
// 댓글불러와야함

// 해당 컬렉션에 포함된 모든 일기 불러오기
function getListInCollection(memberId,colId) {
    console.log("호출은 하고??")
    let member = memberId;
    let collection = colId;
    var requestOptions = {
    method: 'GET',
    redirect: 'follow'
    };

    fetch(`http://localhost:8080/diary/incollection?memberId=${member}&collectionId=${collection}`, requestOptions)
    .then(response => response.json())
    .then(result => {
        data.diarys= result;

        let contentText = [];  //text만 뽑는거
        let contentHtml = [];  //html 형식까지 다 똑같이 가져오는 친구
        for(let diary of result){
            // content -> Json변환 -> quill형식 변환
            contentHtml.push(quill.setContents(JSON.parse(diary.content)));
            //변환된 Quill Content contentText에 삽입
            contentText.push(quill.getText(diary.content));
        }
        data.diaryCntHtml = contentHtml;
        data.diaryCntText = contentText;
        
    })
    .catch(error => console.log('error', error));
}
// 해당 컬렉션의 댓글 불러오기 ( memberId 빠져도 상관없음 -> collectionId 가 애초에 멤버로 불러오기때문 (그리고 나중에 확장을 고려하여 CollectionId 만 필요함))
function getComment(colId, depth, refId){  //처음 부를때 -> colId만 || 답글 부를때 -> colId depth refId
    console.log(colId, depth, refId)
    let query = `?collectionId=${colId}`
    if(depth) query+= `&depth=${depth}`
    if(refId) query+= `&refId=${refId}`
    
    console.log("쿼리값!"+query)

    // ================ Get Method============
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
    };

    fetch(`http://localhost:8080/collection/comment${query}`, requestOptions)
    .then(response => response.json())
    .then(result => {
        data.comments = result
        console.log(result)
    })
    .catch(error => console.log('error', error));
}

// 페이지 마운트전 동작할 함수
onBeforeMount (()=> {
    collectionId.value = route.params.collectionId
    data.collectionId= route.params.collectionId;
    getListInCollection(memberId,collectionId.value);
    getComment(collectionId.value)
    const router = useRouter()
    router.push({name:'detailDiary'})
})

// 어떤 메뉴를 눌렀지? (일기 / 댓글)
let menuControl= ref(1);
function menuClickHandler(menuIdx){
    menuControl.value = menuIdx
}

// 해당 컬렉션의 댓글 대댓글 모두 불러오기



let quill;
// 퀼 정보 모으자
onMounted(() => {
    quill = new Quill('#editor', {
        readOnly: true
    });
})
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
        :data = "data"
        @callComments ="getComment">
        </router-view>
    </div>

    <div id="editor" style="width:0 ; height: 0; display:none; position: absolute;"></div>


</template>
<style scoped>

.detail-container{
    display: grid; 
    grid-template-rows: 72px auto;
    /* grid-template-areas: 1fr; */
    /* overflow:hidden; */

    max-width: 1280px;
}

.detail-container > header {
    display:flex;
    align-items: center;
    position:relative;
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
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