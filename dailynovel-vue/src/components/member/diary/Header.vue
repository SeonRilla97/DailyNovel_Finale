<script setup>
import { onMounted,ref} from 'vue';
const props = defineProps({
    filter: {
        type: Object,
        required:true
    }
})
let emit = defineEmits('filterClickedHandler')
const selectedMenu=  {
 menuname:null,
 menuIndex:null
}
function optionClickHandler(e){
    console.log(e);
//    console.log(e.target.innerText);  //value값 뽑기
//    console.log(e.target.dataset.fidx);  // dataset 뽑기
//    console.log(e.target.parentNode.dataset.menuname); //부모의 dataset 뽑기
    selectedMenu.menuname = e.target.parentNode.dataset.menuname;
    selectedMenu.menuIndex = e.target.dataset.idx;
    console.log(selectedMenu);  
    emit('filterClickedHandler',selectedMenu);
    console.log(e.target.parentNode.style);
}

</script>


<template>
    <header class="diary-header">
        <h1></h1>
        <h1>나의일기</h1>
            <!-- 드롭다운 메뉴 -->
            <div class="dropdown">
                <div class="btn"><span>{{ filter.diary.feeling.menu[filter.diary.feeling.idx] }}</span><span class="icon-clamp"></span></div>
                <div class="content" @click="optionClickHandler" data-menuname = "feeling">
                    <a href="#" v-for="(f,idx) in filter.diary.feeling.menu" :data-idx=idx>{{ f }}</a>
                </div>
            </div>

            <div class="dropdown">
                <div class="btn"><span>{{ filter.diary.weather.menu[filter.diary.weather.idx] }}</span><span class="icon-clamp"></span></div>
                <div class="content" @click="optionClickHandler" data-menuname = "weather">
                    <a href="#" v-for="(f,idx) in filter.diary.weather.menu" :data-idx=idx>{{ f }}</a>
                </div>
            </div>

            <div class="dropdown">
                <div class="btn"><span>솔직함</span><span class="icon-clamp"></span></div>
                <div class="content">
                    <a href="#" v-for="w in filter.diary.weather.menu">{{ w }}</a>
                </div>
            </div>

            <div class="dropdown">
                <div class="btn"><span>날짜</span><span class="icon-clamp"></span></div>
                <div class="content">
                    <a href="#" v-for="w in filter.diary.weather.menu">{{ w }}</a>
                </div>
            </div>

            <div class="dropdown">
                <div class="btn"><span>정렬방식</span><span class="icon-clamp"></span></div>
                <div class="content">
                    <a href="#" v-for="w in filter.diary.weather.menu">{{ w }}</a>
                </div>
            </div>

            <div class="dropdown">
                <div class="btn"><span>컬렉션</span><span class="icon-clamp"></span></div>
                <div class="content">
                    <a href="#" v-for="w in filter.diary.weather.menu">{{ w }}</a>
                </div>
            </div>

            <div class="search-container">
                <form action="#">
                <input type="text" placeholder="Search.." name="search">
                <button>검색</button>
                </form>
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
display: none;
position: absolute;
background-color: #f9f9f9;
min-width: 160px;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
z-index: 1;
}

.diary-header .dropdown .content a {
color: black;
padding: 12px 16px;
text-decoration: none;
display: block;
}

.diary-header .dropdown .content a:hover {background-color: #f1f1f1}

.diary-header .dropdown:hover .content{
display: block;
}
.diary-header .dropdown:hover .dropbtn {
background-color: #3e8e41;
}
</style>