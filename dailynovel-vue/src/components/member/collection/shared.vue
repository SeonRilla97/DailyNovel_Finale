<script setup>
import {ref, watchEffect,toRef,onMounted,onUpdated} from 'vue'
// 추가버튼 팝업메뉴 Open
let menuOpen = ref(null);
// 다이어리가 속한 컬렉션
let curDiaryInCollection = ref([1,2,3]);

// 추가 버튼 누를시 동작하는 이벤트 핸들러
function mOpenHandler(diaryId){
    // 메뉴 버튼 한번 더 클릭시 닫힘 기능
    if(menuOpen.value == diaryId){
        menuOpen.value=null;
        return;
    }
    // 해당 다이어리가 담긴 컬렉션 목록
    let containCollection=[];
    
    // Diary가 담긴 Colletion Id로 찾기
    fetch(`http://localhost:8080/collection/items?diaryId=${diaryId}`)
    .then(response => response.json())
    .then(result => {
    //1.해당 다이어리가 속한 컬렉션 목록을 들고온다.(result)
        //2.collection_items(DB Table)에서 collectionId 값만 추출 하여 배열에 저장
        let modifiedArr = result.map(function(result){
            return result.collectionId;
        });
        //3.배열을 reactive한 변수에 저장
        curDiaryInCollection.value = modifiedArr
    })
    .catch(error => console.log('error', error));
    // 현재 누른 추가버튼 드롭다운 활성화
    menuOpen.value = diaryId;
}

// 컬렉션에 일기를 등록하는 핸들러
function collectionAddHandler(diaryId, collectionId) {
    // 1. diaryId, collection id를 서버로 넘겨서 등록 or 삭제 한다.
    //   세부사항 : Fetch
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw = JSON.stringify({
    "diaryId": diaryId,
    "collectionId": collectionId
    });

    var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
    };
    fetch("http://localhost:8080/collection/items", requestOptions)
    .then(response => response.json())
    .then(result => {
        // DIaryId CollectionId 들어있는 객체 배열 받아서 
        // CollectionId만 들어있는 배열로 Mapping
        let modifiedArr = result.map(function(result){
            return result.collectionId;
        });
        curDiaryInCollection.value = modifiedArr

    })
    .catch(error => console.log('error', error));
    // 2. 하나의 일기가 어느 컬렉션에 속해 있는지 데이터를 받아서, 화면단에 동기화 시킨다.

}

// Collection Layout으로부터 불러온 데이터들
let props = defineProps({
    displayedDiary: {
        type: Object,
        required:true
    },
    collection: {
        type: Object,
        required:true
    }
})
console.log(props.displayedDiary)

const emit=defineEmits(["callDisplayed"]);
let clickMenu = ref(1)
function sortMenuClickHandler(menu,curClick) {
    // console.log(`${menu} 찍었다!`)
    // 누른거 색깔 바꾸고
    clickMenu.value = curClick;
    // 정렬 기준 알려주고
    emit("callDisplayed",menu)
}



</script>
<template>
    <div class="shared-container">
        <header class="header">
            <div class="pdl-5 h2 font-bold">
                <div class="ib standard" @click="sortMenuClickHandler('newest',1)" :class="{active : clickMenu==1}">최신</div>
                <div class="ib standard mgl-3" @click="sortMenuClickHandler('likes',2)" :class="{active : clickMenu==2}">인기</div>
            </div>
            <!-- <div class="icon-back"></div> -->
        </header>
        <section class="main">
            <div class="slide-container" >
              <section class="box" v-for="(value, key) in displayedDiary.list" v-bind:data-id="value.diaryId">
                        <div class="title">{{value.title}}</div>
                        <div class="main">
                            <div class="content mgt-4">{{displayedDiary.diaryCntText[key]}}</div>
                        </div>
                        <div class="lc-center"><div class="icon-like ib"></div><span class="ib mgl-2">{{value.likes}}</span></div>   
                        <div class="dropdown collection-list">
                            <div class="btn" @click="mOpenHandler(value.diaryId)"><span>추가</span><span class="icon-clamp"></span></div>
                           <TransitionGroup>
                            <Transition name="bounce">
                                <div class="content" @click="optionClickHandler" v-show="menuOpen== value.diaryId">
                                    <a href="#"  class="item"  v-for="(collection, key) in collection.List" :class="{shared: curDiaryInCollection.includes(collection.id) }" @click="collectionAddHandler(value.diaryId,collection.id)">{{collection.name}}</a>
                                </div>
                            </Transition>
                            </TransitionGroup>
                        </div>
                </section>
            </div>
        </section>
    </div>
</template>
<style scoped>

.shared-container{
    display:grid;
    grid-template-rows: 72px auto;
}

.shared-container{
    display: grid; 
    grid-template-rows: 72px auto;
    grid-template-areas: 1fr;
    /* overflow:hidden; */
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
    max-width: 1280px;
}

.shared-container > header {
    display:flex;
    align-items: center;
    position:relative;
}
    .header .standard{
        cursor:pointer;
    }
.shared-container > .main{
    display:flex;
    align-items: center;
    overflow-x:scroll;
    width:100%;
}
.shared-container .main .slide-container{
    display:flex;
    align-items: center;
    transform: translateY(-10%);
    padding-right:2.5rem
    /* box-sizing: border-box; */
} 

.shared-container .main .box{
    width:240px;
    height: 360px;
    margin-left: 36px;
    border: 1px solid #00000020;
    border-radius: 12px;
    display:grid;
    grid-template-rows: 1fr 5fr 0.5fr;
    text-align: center;
    position:relative;
    padding:12px;
    box-sizing: border-box;
}
/* .shared-container .main .box:nth-child(1){
    margin-left:0
} */
.box .content {
    overflow: hidden;
    line-height: 18px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 12;
}

.shared-container .main .box .title{
    font-size: 20px;
    font-weight: bold;
    border-bottom: 1px solid #00000020;
    /* justify-self:center; */

}
.shared-container .main .box{
    
}
.shared-container .main .box >.title{
    display:flex;
    justify-content: center;
    align-items: center;
}

.icon-like{
    background-image: url("../../../assets/img/like.svg");
    background-repeat: no-repeat;
    background-size: 16px 16px;
    background-position: center center;
    width:16px;
    height: 16px;
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


.shared-container .header .active{
    text-decoration: underline;
    color: #FCD602;
    text-underline-position : under;
}


/* DropDown */

/* dropDown(필터 메뉴) */
.collection-list.dropdown .btn {
    
    bottom:0;
    align-self:center;
    position:absolute;
    bottom: -50px;
    left: 50%;
    transform: translate(-50%);

    width:100px;
    height: 30px;
    background-color: #7594e960;
    color: #fcfcfc;
    display:flex;
    justify-content: center;
    align-items: center;
    border-radius: 20px;
    transition: all 0.2s linear;
}
.collection-list.dropdown .btn:hover{
    background-color: #7594e9;
    cursor: pointer;
}


.collection-list.dropdown {
position: relative;
display: inline-block;
/* margin-left: 40px; */
}

.collection-list.dropdown .content {
/* display: none;  */
position: absolute;
background-color: #fcfcfc;
min-width: 220px;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
z-index: 1;
text-align: center;
/* transform:translate(-50% ,-100%); */
border-radius: 12px;
bottom:0;
height:auto;
max-height:120px;
overflow-y:auto;
}
.collection-list.dropdown .content::-webkit-scrollbar {
    width: 8px;  /* 스크롤바의 너비 */
}

.collection-list.dropdown .content::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: #217af4; /* 스크롤바의 색상 */
    
    border-radius: 10px;
}

.collection-list.dropdown .content::-webkit-scrollbar-track {
    background: rgba(33, 122, 244, .1);  /*스크롤바 뒷 배경 색상*/
}

.collection-list.dropdown .content .item {
color: black;
padding: 12px 16px;
text-decoration: none;
display: block;
transition: 0.5s all linear;
}

.collection-list.dropdown .content .item.shared {
color: #00000040;
}

.collection-list.dropdown .content a:hover {
    background-color: #f1f1f1;
    
    
}



/* vue Transition */
.bounce-enter-active {
  animation: bounce-in 0.5s;
}
.bounce-leave-active {
  animation: bounce-in 0.5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: translateY(+40%);
    opacity: 0;
  }
  50% {
    transform: translateY(-10%);
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}

</style>