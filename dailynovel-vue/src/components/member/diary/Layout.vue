<script setup>
import Header from './Header.vue'
import List from './List.vue'
import Editor from './editor.vue'
import { reactive, onBeforeMount } from 'vue';

import Filter from './filter.js'
import filter from './filter.js';


//메뉴 관리
const diaryFilter = reactive({
        feeling:new Filter(0,["기분","화남","불편","평온","실망","불안","행복","슬픔","감동","신남"]),
        weather:new Filter(0,["날씨","천둥번개", "이슬비",  "비",  "눈", "미세먼지", "맑음", "흐림"]),
        date:null,
        sort:new Filter(0,["정렬기준","최신순","오래된순"]),
        collection:new Filter(0,["컬렉션","나만의 일기","배부른 일기","더망"]),
        keyword: ""
})
getCollectionList();
getListwithFiltering();
const diary = reactive({
    list : null
})
// onBeforeMount(() => {
//     getCollectionList();
// }),

// 필터 목록 클릭시 동작 이벤트핸들러
function filterClickHandler(selected){
    // console.log(selected);
    let backupMenu = {
        name:null,
        value : null
    }
    switch(selected.menuname){
        case "feeling":
            backupMenu.name = "feeling";
            backupMenu.value=diaryFilter.feeling.idx;
            diaryFilter.feeling.idx = selected.menuvalue;
            break;
        case "weather":
            backupMenu.name = "feeling";
            backupMenu.value=diaryFilter.weather.idx;
            diaryFilter.weather.idx = selected.menuvalue;
            break;
        case "date":
            backupMenu.name = "date";
            backupMenu.value=diaryFilter.date;
            diaryFilter.date = selected.menuvalue;
            break;
        case "sort":
            backupMenu.name = "sort";
            backupMenu.value=diaryFilter.sort.idx;
            diaryFilter.sort.idx = selected.menuvalue;
            break;
        case "collection":
            backupMenu.name = "collection";
            backupMenu.value=diaryFilter.collection.idx;
            diaryFilter.collection.idx = selected.menuvalue;
            break;
        case "keyword":
            backupMenu.name = "keyword";
            backupMenu.value=diaryFilter.keyword;
            diaryFilter.keyword = selected.menuvalue;
            break;
        }


        // 정렬 값의 변화가 있기 때문에 리스트를 다시 불러야함
        getListwithFiltering(backupMenu)
}

// 필터 목록 클릭시 Diary List 불러오는 함수
function getListwithFiltering(backup) {
    //backup ==> 만약 리스트가 없다면 이전 필터상태를 유지시킨다.
    // 필터링 리스트에 따라 쿼리 뽑기
    const feeling = diaryFilter.feeling;
    const weather = diaryFilter.weather;
    const date = diaryFilter.date;
    const sort = diaryFilter.sort;
    const collection = diaryFilter.collection;
    const keyword = diaryFilter.keyword;
    // console.log("이거나옴???")
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
    // 뽑힌 쿼리에 따라 Fetch 실행
    // 쿼리를 뽑는 기능과 같이 있는 이유 : 여기 말고 쓰는곳이 따로 없어서
    //  굳이 함수로 분리할 필요성을 느끼지 못함
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/diary/filter${query}`, requestOptions)
        .then(response => response.json())
        .then(result => { 
            if(Object.keys(result).length == 0){
                prompt("데이터 으으으읍따");
                
                if(backup){
                    switch(backup.name){
                    case "feeling":
                        diaryFilter.feeling.idx = backup.value;
                        console.log(diaryFIlter);
                        break;
                    case "weather":
                        diaryFilter.weather.idx = backup.value;
                        break;
                    case "date":
                        diaryFilter.date = backup.value;
                        break;
                    case "sort":
                        diaryFilter.sort.idx = backup.value;
                        break;
                    case "collection":
                        diaryFilter.collection.idx = backup.value;
                        break;
                    case "keyword":
                        diaryFilter.keyword = "";
                        break;
                    }
                }


                return;
            }

            // 값 넣어주고
            diary.list=result
            // console.log(`객체상태 :${Object.keys(result) }`)
            // console.log(result)
            //Date 넘어온것들 Date객체로 생성후 삽입
            for(let key in diary.list){
                // console.log(diary.list[key])
                for(let value in diary.list[key]){
                    // console.log(diary.list[key][value].sharedEndDate);
                    // console.log(diary.list[key][value].regDate);
                    if(diary.list[key][value].sharedEndDate)
                        diary.list[key][value].sharedEndDate = new Date(diary.list[key][value].sharedEndDate);
                    diary.list[key][value].regDate = new Date(diary.list[key][value].regDate);
                }
                // console.log(diary.list)
                    // .regDate
                    // console.log(index);
            }
            // 데이터가 아무것도 없다면?
            console.log(result);
        })
        .catch(error => console.log('error', error));
    }
// 컬렉션 메뉴 불러와서 데이터 삽입
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
</script>
<template>
    <div class="diary-container">
        <Header class="no-scroll"  :filter = "diaryFilter"  @filterClickedHandler="filterClickHandler"/>

        <section class="diary-main">
            <List :diary = "diary"/>

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