<script setup>
import { ref,watch,reactive } from 'vue';
import filter from './filter';
const props = defineProps({
    filter: {
        type: Object,
        required:true
    }
})
// 부모의 함수를 호출하기 위한 Emit
let emit = defineEmits('filterClickedHandler')
// 어떤 필터메뉴의 어느 버튼을 눌렀는지 저장을 위한 객체
const selectedMenu=  {
        menuname:null,
        menuvalue:null
    }
    // 필터의 메뉴를 클릭했을때 동작하는 이벤트핸들러
function optionClickHandler(e){
    selectedMenu.menuname = e.target.parentNode.dataset.menuname;
    selectedMenu.menuvalue = e.target.dataset.idx;
    emit('filterClickedHandler',selectedMenu);
}
// 어느 메뉴의 드롭박스가 실행돼야 하는지 결정하는 변수
let menuOpen = ref(null);

//필터 메뉴 클릭하면 해당 드롭박스 실행시키는 함수
function menuOpenHandler(clickedMenu){
    
    if(menuOpen.value == clickedMenu && menuOpen.value != null){
        menuOpen.value = null;
        // console.log("같은거 클릭");
        return;
    }
    menuOpen.value = clickedMenu;
    console.log(clickedMenu)
}
// 날짜 필터메뉴 선택 날짜 초기화버튼 클릭 핸들러
function dateInitialize(){
    attributes.dates=null;
}

// 검색어 입력 바인딩 변수
let searchKeywork = ref("");
// 검색어 입력 버튼 누를시 동작하는 함수
function searchBtnHandler(){
    selectedMenu.menuname = "keyword"
    selectedMenu.menuvalue= searchKeywork.value;
    emit('filterClickedHandler',selectedMenu);
}


// VCalendar의 상태를 결정하는 변수들
let date = ref(null);
const attributes = reactive(
  {
    highlight: true,
    dates:props.filter.date
  }
);

// attributes의 dates 변화시 동작하는 함수
watch(() => attributes.dates, (newDates, oldDates) => {
  console.log("와치 동작!");
    selectedMenu.menuname = "date"
    selectedMenu.menuvalue= attributes.dates;
    console.log("선택된 Date" + selectedMenu.menuvalue);
    emit('filterClickedHandler',selectedMenu);
});
console.log(props.filter)
function feelingTitle(){
    return props.filter.feeling.name[props.filter.feeling.idx];
}
</script>


<template>
    <header class="diary-header">
        <h1>나의일기</h1>
        <!-- 드롭다운 메뉴 -->

        <!-- feeling -->
        <div class="dropdown">
            <div class="btn" @click="menuOpenHandler(1)"><span>{{ props.filter.feeling.menu[props.filter.feeling.idx] }}</span><span class="icon-clamp"></span></div>
            <transition name="bounce">
                <div class="content" @click="optionClickHandler" data-menuname = "feeling" v-show="menuOpen==1">
                    <a href="#"  class="item" v-for="(feeling,idx) in props.filter.feeling.menu" :data-idx=idx @click="menuOpenHandler">{{ feeling }}</a>
                </div>
            </transition>
        </div>

        
        <!-- weather -->
        <div class="dropdown">
            <div class="btn" @click="menuOpenHandler(2)"><span>{{ props.filter.weather.menu[props.filter.weather.idx] }}</span><span class="icon-clamp"></span></div>
            <transition name="bounce">
                <div class="content" @click="optionClickHandler" data-menuname = "weather" v-show="menuOpen==2">
                    <a href="#"  class="item" v-for="(weather,idx) in props.filter.weather.menu" :data-idx=idx @click="menuOpenHandler">{{ weather }}</a>
                </div>
            </transition>
        </div>

        <!-- honesty 정렬 기준 이었으나
        기준범위 나눠서 정렬기준 뜨는것과, 굳이 솔직함을 모아볼 필요가 있나
    싶어서 필터링에서 제외시킵니다.-->
        <!-- <div class="dropdown">
            <div class="btn" @click="menuOpenHandler(3)"><span>솔직함</span><span class="icon-clamp"></span></div>
            <transition name="bounce">
                <div class="content"  v-show="menuOpen==3" data-menuname = "honesty">
                    <a href="#" class="item" v-for="(f,idx) in props.filter.weather.menu" :data-idx=idx @click="menuOpenHandler">{{ w }}</a>
                </div>
            </transition>
        </div> -->

        <!-- date -->
        <div class="dropdown">
            <div class="btn" @click.stop="menuOpenHandler(4)"><span>날짜</span><span class="icon-clamp"></span></div>
            <transition name="bounce">
                <div class="calendar-container" v-show="menuOpen==4" data-menuname = "date">
                    <VDatePicker class="calendar" v-model="attributes.dates" :attributes="attributes" mode="date" @dayclick="menuOpenHandler">
                        <template #footer>
                            <div class="lc-center pdb-4" style="box-sizing=border-box">
                                <button  class="btn-init" @click="dateInitialize">초기화</button>
                            </div>
                        </template>   
                    </VDatePicker> 
                    <!-- <div class="btn initialize lc-center" @click.stop="dateInitialize"><span>초기화</span><span class="icon-clamp"></span></div> -->
                </div>
            </transition>
        </div>

        <!-- sort -->
        <div class="dropdown">
            <div class="btn" @click="menuOpenHandler(5)"><span>{{ props.filter.sort.menu[props.filter.sort.idx] }}</span><span class="icon-clamp"></span></div>
            <transition name="bounce">
                <div class="content" @click="optionClickHandler"   v-show="menuOpen==5" data-menuname = "sort">
                    <a href="#"  class="item" v-for="(sort,idx) in props.filter.sort.menu" :data-idx=idx @click="menuOpenHandler">{{ sort }}</a>
                </div>
            </transition>
        </div>

        <!-- collection-->
        <div class="dropdown">
            <div class="btn" @click="menuOpenHandler(6)"><span>{{ props.filter.collection.menu[props.filter.collection.idx] }}</span><span class="icon-clamp"></span></div>
            <transition name="bounce">
                <div class="content" @click="optionClickHandler" v-show="menuOpen==6" data-menuname = "collection">
                    <a href="#"  class="item" v-for="(collection,idx) in props.filter.collection.menu" :data-idx=idx @click="menuOpenHandler">{{ collection }}</a>
                </div>
            </transition>
        </div>

        <!-- search box -->
        <div class="search-container">
            <div action="#" class="form">
                <input class="box-search" type="text" placeholder="검색어를 입력하세요..." name="search" v-model="searchKeywork">
                <div class="btn-search cursor-pt" @click="searchBtnHandler">검색</div>
            </div>
        </div>
    </header>
        
</template>

<style scoped>
/* 다이어리 페이지 헤더 */
.diary-header {
    display:flex;
    align-items: center;
    height:42px;
    position:relative;
}
    .diary-header > h1{
        margin-left:15px;
        font-weight:normal;
        font-size: 20px;
    }
    .diary-header .search-container{
        position:absolute;
        right: 0;
        margin: 2%;
    }


/* dropDown(필터 메뉴) */
.diary-header .dropdown .btn {
font-size: 16px;
border: none;
cursor: pointer;
box-sizing: border-box;
}

.diary-header .dropdown {
position: relative;
display: inline-block;
margin-left: 40px;
}

.diary-header .dropdown .content {
/* display: none;  */
position: absolute;
background-color: #f9f9f9;
min-width: 160px;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
z-index: 1;
text-align: center;
top: 20px;
height: 120px;
overflow-y:scroll;
left:-60px;
}

    /* 스크롤바 커스터마이징 */
    .diary-header .dropdown .content::-webkit-scrollbar {
    width: 1px;  /* 스크롤바의 너비 */
    }
    .diary-header .dropdown .content::-webkit-scrollbar-thumb {
    height: 30%; /* 스크롤바의 길이 */
    background: #848484; /* 스크롤바의 색상 */
    border-radius: 10px;
    }

    .diary-header .dropdown .content::-webkit-scrollbar-track {
    /* background: #84848447;  스크롤바 뒷 배경 색상 */
    background-color: transparent;
    }
.diary-header .dropdown .calendar-container {
    position: absolute;
    z-index: 1;
    top:20px;
    left:-100px;
    width:300px;
    height: 300px;
    /* top: 20vh;
    left: 20vh; */
}
.diary-header .dropdown .calendar-container .btn-init{
    width:80%;
    height:30px;
    border:none;
    background-color:  #5cb3e6;
    border-radius: 10px;
    color:white;
    font-weight: bolder;
    letter-spacing: 1px;
    font-size: 15px;
}

.diary-header .dropdown .content .item {
color: black;
padding: 12px 16px;
text-decoration: none;
display: block;
}

.diary-header .dropdown .content a:hover {background-color: #f1f1f1}

.bounce-enter-active {
  animation: bounce-in 0.2s;
}
.bounce-leave-active {
  animation: bounce-in 0.4s reverse;
}
@keyframes bounce-in {
    0% {
            opacity: 0;
            transform: translate3d(0, 20%, 0);
        }
        to {
            opacity: 1;
            transform: translate(0);
        }
}


.search-container .form{
    display:flex;
    align-items: center;
    background-color: #d8d8d8;
    padding: 2px;
    box-sizing: border-box;
}
.search-container .box-search{
    height: 30px;
    /* background-color: #d8d8d8; */
    text-indent: 10px;
}

.search-container .btn-search{
    height: 30px;
    width:40px;
    margin-left:1px;
    box-sizing: border-box;
    border:none;
    display:inline-block;
    line-height: 30px;
    text-align: center;
    /* border-radius: 5px; */
    
}

</style>