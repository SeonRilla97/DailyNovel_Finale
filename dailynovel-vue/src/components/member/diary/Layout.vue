<script setup>
import Header from './Header.vue'
import List from './List.vue'
import Editor from './editor.vue'
import { reactive } from 'vue';

import Filter from './filter.js'
import filter from './filter.js';


const diaryFilter = reactive({
    diary:{
        feeling:new Filter(0,["기분","좋음","기쁨","나쁨"]),
        weather:new Filter(0,["날씨","비","맑음","눈"]),
        date:null,
        sort:new Filter(0,["정렬기준","최신순","오래된순"]),
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
        console.log(diaryFilter.diary.date)  
        getListwithFiltering()
}

// console.log(diaryFilter.diary.date);
function getListwithFiltering() {
    // console.log("실험")
    const filterList =diaryFilter.diary

    const feeling = diaryFilter.diary.feeling;
    const weather = diaryFilter.diary.weather;
    const date = diaryFilter.diary.date;
    const sort = diaryFilter.diary.sort;
    const keyword = diaryFilter.diary.keyword;
    let query = "?";

    if(feeling.idx != 0){
        query += `&feeling=${feeling.menu[feeling.idx]}`;
    }
    if(weather.idx != 0){
        query += `&weather=${weather.menu[weather.idx]}`;
    }
    if(date!= null){
        query += `&date=${date.toISOString()}`;
    }
    if(sort.idx != 0){
        query += `&order=${sort.menu[sort.idx]}`;
    }
    if(keyword != ""){
        query += `&query=${keyword}`;
    }
    console.log(query);
}
console.log(new Date().toISOString())
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