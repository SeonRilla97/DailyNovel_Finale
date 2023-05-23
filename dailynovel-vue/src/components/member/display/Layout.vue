<template lang="">
    <section class="container">
    <main>
        <br>
        <ul class="ulMargin">
            <li class="point" :class="'1'==currentCategory?'active-commu-category':''" @click="categoryClick('1')"><h1>인기</h1></li>
            <li class="point" :class="'2'==currentCategory?'active-commu-category':''" @click="categoryClick('2')"><h1>최신</h1></li>
            <li class="point" :class="'자유'==currentCategory?'active-commu-category':''" @click="categoryClick('자유')"><h1>자유</h1></li> 
            <li class="point" :class="'영화'==currentCategory?'active-commu-category':''" @click="categoryClick('영화')"><h1>영화</h1></li>
            <li class="point" :class="'여행'==currentCategory?'active-commu-category':''" @click="categoryClick('여행')"><h1>여행</h1></li>
            <!-- <li class="point" :class="'1'==currentCategory?'active-commu-category':''" @click="useDisplayCategoryStore().saveCategory('1')"><h1>인기</h1></li>
            <li class="point" :class="'2'==currentCategory?'active-commu-category':''" @click="useDisplayCategoryStore().saveCategory('2')"><h1>최신</h1></li>
            <li class="point" :class="'자유'==currentCategory?'active-commu-category':''" @click="useDisplayCategoryStore().saveCategory('자유')"><h1>자유</h1></li> 
            <li class="point" :class="'영화'==currentCategory?'active-commu-category':''" @click="useDisplayCategoryStore().saveCategory('영화')"><h1>영화</h1></li>
            <li class="point" :class="'여행'==currentCategory?'active-commu-category':''" @click="useDisplayCategoryStore().saveCategory('여행')"><h1>여행</h1></li> -->
        </ul>
        <br>
        <br>
        <section class="container">
        <section class="community">
            <h1 class="d-none">게시판 리스트</h1>
            <ul class="commu-content-grid">
                <!-- <li class="" v-for="m in model" :key="m.id" v-show="m.share==1 && (currentCategory==m.category || currentCategory==2)"> -->
                    <li class=""  v-for="(l, i) in model" :key="i" v-show="currentCategory==l.tag || currentCategory === '1' && l.like >= 3 || currentCategory === '2'">
                    <div class="content-box">
                        <div class="content-title">
                            <p><b>{{l.title}}</b></p>
                        </div>
                        <div class="content-underline">
                            <hr>
                        </div>
                        <router-link :to="'community/'+i">
                            <div class="content-subject"> 
                                {{l.content}}
                            </div>
                        </router-link>
                        <div class="content-like-count">
                            <div @click="likeSwitchHandler(l.id)">
                                <div class="inline" :class="isDiaryIdMatched(l.id)?'like-active':'like-deactive'"></div><span class="mgl-1">{{l.like}}</span>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
            <!-- <div class="center-grid "><div class="more-btn nodouble-drag" @click="goToNextPage()">더보기 + 99</div></div> -->
        </section>
    </section>
    </main>
</section>
</template>
<script setup>

import { onMounted, reactive, ref, } from 'vue'
import {useUserDetailsStore} from '../../store/useUserDetailsStore.js'
import {useDisplayCategoryStore} from '../../store/useDisplayCategoryStore.js'

// 되는 gpt코드 -> 비교해보기
let model = reactive([]);
let count = ref();
let category = reactive([]);

let DisplayCategory = useDisplayCategoryStore()

let currentCategory = DisplayCategory.category;

let indeLikeList = reactive([])

let userDetails = useUserDetailsStore();

let memberId = userDetails.id; // 지금은 멤버id를 1로 꽂아놨는데 이후에 확인해 봐야 함

async function load() {
    const resList = await fetch('http://localhost:8080/display/listall')
    const list = await resList.json()
    model.splice(0, model.length, ...list);
    // console.log(list)

    const likeList = await fetch(`http://localhost:8080/display/likeScan?mId=${memberId}`)
    const data = await likeList.json()
    indeLikeList.splice(0, indeLikeList.length, ...data);
}


onMounted(() => {
    load()
})

function categoryClick(page) {
    DisplayCategory.saveCategory(page)
    currentCategory=page
    load()
}

function isDiaryIdMatched(diaryId){// 좋아요 클릭했는지 확인하는 함수
    return this.indeLikeList.some(item => item.diaryId === diaryId);
}


// 이거 if문을 앞쪽으로 옮겨서 fetch만 바꾸면 집중화 할 수 있을 거 같다.)
async function likeSwitchHandler(diaryId) {
    console.log("좋아요 " + (this.indeLikeList.some(item => item.diaryId === diaryId) ? "delete" : "insert"));
    console.log("멤버아이디: "+memberId);

    try {
        const response = await fetch(`http://localhost:8080/display/${this.indeLikeList.some(item => item.diaryId === diaryId) ? "deletelike" : "addlike"}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberId: memberId,         // 멤버 정보 가지고 오기
                diaryId: diaryId,
            }),
        });

        if (!response.ok) {
            throw new Error('요청에 실패했습니다.');
        }

        const data = response;
        console.log(data); // 응답 데이터 처리
    } catch (error) {
        console.error(error); // 에러 처리
    }

    setTimeout(load, 50);
}

function goToNextPage(){ //스크롤 페이징
    console.log("추가페이지")
}

</script>




<style scoped>
.container {
    width: 80rem;
    height: 544px;
    position: relative;
}

.community {
    display: grid;
    justify-content: center;
    grid-gap: 1rem;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    overflow-y: auto;
}

.ulMargin {
    margin-left: 2.0rem;
}

.point {
    cursor: pointer;
    margin-left: 1rem;
    font-size: 1.875rem;
    user-select: none;
}

.inline {
    display: inline-block;
}

.header-band {
    background-color: #F2C6C2;
    display: grid;
    justify-content: space-between;
    grid-auto-flow: column;
}

.header-band .content {
    display: grid;
    justify-content: space-between;
}

.center-grid {
    display: grid;
    justify-content: center;
}

li {
    display: inline-block;
    list-style: none;
    /* padding-left: 0.5rem; */
}

.active-commu-category {
    /* 선택된 커뮤니티 카테고리 */
    color: rgb(255, 208, 0);
    text-decoration: underline;
}

.commu-content-grid {
    /* 콘텐츠박스의 부모 그리드*/
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    max-width: 1280px;
    grid-gap: 20px;

}

.content-box {
    /* 게시판 콘탠츠박스 디자인 */
    /* border: 1px solid fff8f3; */
    border-radius: 1rem;
    background-color: #fff8f3;
    width: 17.5rem;
    height: 28.375rem;
    display: grid;
    grid-template-rows: 3rem 1rem 20rem 1fr;
    align-content: space-between;

}

/* 콘탠츠박스 내용의 배치에 대한 그리드*/
.content-box .content-title {
    /* 콘텐츠박스 제목의 중앙*/
    justify-self: center;
    align-self: center;
    user-select: none;
}

.content-title p {
    /* 콘텐츠박스 제목의 중앙*/
    font-size: 1.5rem;
    width: 10rem;
    /* 최대 5글자까지만 보이도록 요소의 너비를 지정 */
    white-space: nowrap;
    /* 텍스트를 한 줄로 표시 */
    overflow: hidden;
    /*요소의 영역을 벗어나는 내용은 잘리고 표시되지 않음 */
    text-overflow: ellipsis;
    text-align: center;
    /* 잘린 텍스트를 ...으로 표시 */
}

.content-box .content-underline {
    grid-auto-flow: row;
}

/*https://webdir.tistory.com/483*/
.content-box .content-subject {
    padding: 1rem;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: normal;
    line-height: 1.2;
    height: 17rem;
    text-align: left;
    word-wrap: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 15;
    -webkit-box-orient: vertical;
    user-select: none;
}

.content-box .content-like-count {
    text-align: center;
}

.more-btn {
    /* 더보기 버튼 */

    text-align: center;
    vertical-align: middle;
    background-color: #F2C6C2;
    color: #000;
    width: 8rem;
    height: 4rem;
    border-radius: 5px;
    text-decoration: none;
    font-weight: bold;
    height: 4rem;
    line-height: 4rem;
    /*-webkit-text-stroke: 0.5px black; /* font에 테두리 주는 */
}

.like-active {
    /*좋아요 활성화*/
    width: 1rem;
    height: 1rem;
    background-image: url('@/assets/img/display/heart-red.svg');
    background-repeat: no-repeat;
    background-position: center bottom;
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
    /*  */
}

.like-deactive {
    /*좋아요 비활성화*/
    width: 1rem;
    height: 1rem;
    background-image: url('@/assets/img/display/heart-gray.svg');
    background-repeat: no-repeat;
    background-position: center bottom;
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
    /*  */
}

.nodouble-drag {
    user-select: none;
    cursor: pointer;
}
</style>