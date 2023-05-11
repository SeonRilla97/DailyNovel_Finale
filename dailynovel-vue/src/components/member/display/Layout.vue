<template lang="">
    <section>
    <main>
        <br>
        <ul>
            <li class="point" :class="1==currentCategory?'active-commu-category':''" @click="categoryClick(1)"><h1>인기</h1></li>
            <li class="point" :class="2==currentCategory?'active-commu-category':''" @click="categoryClick(2)"><h1>최신</h1></li>
            <li class="point" :class="3==currentCategory?'active-commu-category':''" @click="categoryClick(3)"><h1>자유</h1></li> 
            <li class="point" :class="4==currentCategory?'active-commu-category':''" @click="categoryClick(4)"><h1>영화</h1></li>
            <li class="point" :class="5==currentCategory?'active-commu-category':''" @click="categoryClick(5)"><h1>여행</h1></li>
        </ul>
        <br>
        <br>
        <section style="display:grid; justify-content:center ; overflow:hidden; grid-gap:1rem">
            <h1 class="">게시판 리스트</h1>
            <ul class="commu-content-grid">
                <!-- <li class="" v-for="m in model" :key="m.id" v-show="m.share==1 && (currentCategory==m.category || currentCategory==2)"> -->
                <li class=""  v-for="(l, i) in model" :key="i">
                    <div class="content-box">
                        <div class="content-title">
                            <p><b>{{l.title}}</b></p>
                        </div>
                        <div class="content-underline">
                            <hr>
                        </div>
                        <router-link to="community/detail">
                            <div class="content-subject"> 
                                {{l.content}}
                            </div>
                        </router-link>
                        <div class="content-like-count">
                            <div class="like-active inline"></div><span>50</span>
                        </div>
                    </div>
                </li>
            </ul>
            <div class="center-grid"><div class="more-btn">더보기 + 99</div></div>
        </section>
    </main>
</section>
</template>
<script setup>

import { onMounted, reactive,ref, } from 'vue'

// 되는 gpt코드 -> 비교해보기
let model = reactive([]);
let count = ref();
let category = reactive([]);
let currentCategory = ref(1);



async function load() {
const resList = await fetch('http://localhost:8080/display/list')
const data = await resList.json()
model.splice(0, model.length, ...data); //이게 핵심인 거 같다.

// const resLikeCount = await fetch('http://localhost:8080/community/count2')
// const data2 = await resLikeCount.json()
// console.log(data2);
// count.value = data2;

// const resCategory = await fetch('http://localhost:8080/community/category')
// const data3 = await resCategory.json()
// console.log(data3);
// category.splice(0, model.length, ...data3);

// // const resDetail = await fetch('http://localhost:8080/community/detail2?id='+model.id)
// // const resDetail = await fetch('http://localhost:8080/community/detail2?id=3')
// // const resDetail = await fetch(`http://localhost:8080/community/detail2?id=${this.$route.query.id}`);
// // const data4 = await resDetail.json()
// // console.log(data4);
}


onMounted(()=>{
load()
})

function categoryClick(page){
currentCategory.value=page;
}

</script>
<style scoped>
.point{
cursor: pointer;
}
.inline{
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
background-position: center bottom ;
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
background-size: contain;
border: none;
outline: none;
cursor: pointer;
/*  */
}



</style>