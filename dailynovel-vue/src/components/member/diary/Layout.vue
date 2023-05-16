<script setup>
import Header from './Header.vue'
import List from './List.vue'
import Editor from './editor.vue'
import { reactive, onBeforeMount } from 'vue';

import Filter from './filter.js'
import filter from './filter.js';

getCollectionList();
const diaryFilter = reactive({
        feeling:new Filter(0,["기분","좋음","기쁨","나쁨"]),
        weather:new Filter(0,["날씨","천둥번개", "이슬비",  "비",  "눈", "미세먼지", "맑음", "흐림"]),
        date:null,
        sort:new Filter(0,["정렬기준","최신순","오래된순"]),
        collection:new Filter(0,["컬렉션","나만의 일기","배부른 일기","더망"]),
        keyword: ""
})
// onBeforeMount(() => {
//     getCollectionList();
// }),

function filterClickHandler(selected){
    console.log(selected);
    switch(selected.menuname){
        case "feeling":
            diaryFilter.feeling.idx = selected.menuvalue;
            break;
        case "weather":
            diaryFilter.weather.idx = selected.menuvalue;
            break;
        case "date":
            diaryFilter.date = selected.menuvalue;
            break;
        case "sort":
            diaryFilter.sort.idx = selected.menuvalue;
            break;
        case "collection":
            diaryFilter.collection.idx = selected.menuvalue;
            break;
        case "keyword":
            diaryFilter.keyword = selected.menuvalue;
            break;
        }

        console.log(diaryFilter.date)
        getListwithFiltering()

}

// console.log(diaryFilter.diary.date);
function getListwithFiltering() {
    // console.log("실험")

    const feeling = diaryFilter.feeling;
    const weather = diaryFilter.weather;
    const date = diaryFilter.date;
    const sort = diaryFilter.sort;
    const collection = diaryFilter.collection;
    const keyword = diaryFilter.keyword;
    console.log("이거나옴???")
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
    if(collection != "" && collection.idx !=0){
        query += `&collection=${collection.id[collection.idx]}`;
    }
    console.log(query);
}
// 컬렉션 메뉴 넣기
function getCollectionList() {
    let requestOptions = {
    method: 'GET',
    redirect: 'follow'
    };

fetch("http://localhost:8080/collection/all", requestOptions)
  .then(response => response.json())
  .then(collections => {
    //배열 생성
    let tmpnameList = [];
    let tmpIdList = [];
    tmpnameList.push("컬렉션")
    tmpIdList.push(null)
    for(let index in collections){
        let col=collections[index];
        tmpnameList.push(collections[index].name);
        tmpIdList.push(collections[index].id)
        // tmpIdList
    }
    diaryFilter.collection.menu = tmpnameList;
    diaryFilter.collection.id = tmpIdList;
  })
  .catch(error => console.log('error', error));
}

console.log(diaryFilter.collection);
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