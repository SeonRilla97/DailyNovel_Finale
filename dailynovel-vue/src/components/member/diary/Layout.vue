<script setup>
import Header from './Header.vue'
import List from './List.vue'
import Editor from './editor.vue'
import { reactive } from 'vue';

import Filter from './filter.js'


const diaryFilter = reactive({
    diary:{
        feeling:new Filter(0,["기분","좋음","기쁨","나쁨"]),
        weather:new Filter(0,["날씨","비","맑음","눈"]),
        honesty:0,
        date:["날씨"],
        sort:new Filter(0,["정렬기준","인기순","최신순","오래된순"]),
        collection:new Filter(0,["컬렉션","나만의 일기","배부른 일기","더망"]),
        keyword: ""
    },
})


function filterClickHandler(selected){
    switch(selected.menuname){
        case "feeling":
            diaryFilter.diary.feeling.idx = selected.menuvalue;
            break;
        case "weather":
            diaryFilter.diary.weather.idx = selected.menuvalue;
            break;
        case "date":
            diaryFilter.diary.date = selected.menuvalue;
            break;
        case "sort":
            diaryFilter.diary.sort.idx = selected.menuvalue;
            break;
        case "collection":
            diaryFilter.diary.collection.idx = selected.menuvalue;
            break;
        case "keyword":
            diaryFilter.diary.keyword = selected.menuvalue;
            break;
            
    }
    console.log(diaryFilter.diary.feeling.idx)
        
}
</script>
<template>
    <div class="diary-container">
        <Header class="no-scroll"  :filter = "diaryFilter"  @filterClickedHandler="filterClickHandler"/>

        <section class="diary-main">
            <List/>

            <Editor />
        </section>
    </div>
</template>


<style scpoed>
/* 페이지 컨테이너 */
.diary-container{
    display:flex;
    flex-direction: column;
}
/* 다이어리 컨텐츠 부분 레이아웃 잡기*/
.diary-main{
display:flex;
flex-grow: 1;
}

.no-scroll {
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
}
</style>