<template lang="">
    <main class=" center-grid">
        
        <section class=" center-grid article-box">
            <h1 class="d-none">디테일</h1>
                <article>
                    <h1 class="title">{{title}}</h1>
                    <hr>
                    <div class="userInfo">
                        <div class="d-inline like-active"></div>
                        <div class="d-inline">{{like}} / </div>
                        <div class="d-inline">{{image}} / </div>
                        <div class="d-inline" @click="statusz">{{nickname}}</div>
                        
                    </div>
                    <br>
                    <p class="detail-width">
                        {{content}}<br>
                        그것은 천국인 거야 야야야야야야야야야야야야야야야ㅑ야야ㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑㅑzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
                        노란 머리가 젤다죠? 
                        
                    </p>
                </article>
        </section>
        <div class="subscribeBtn" v-show="subscribe" style="position: relative;"><span>구독하기</span></div>
    </main>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue';

const props = defineProps({
    detailPage: {
        type: Object
    }
})
console.log(props)
let data = ref();
let title = ref('');
let content = ref('');
let like = ref();//좋아요
let image = ref('');//이미지
let nickname = ref('');//닉네임
let subscribe = ref(false);

function load() {
    setTimeout(() => {
        data = props.detailPage
        console.log(data)

        title.value = data.title
        content.value = data.content
        like.value = data.like;
        image.value = data.image;
        nickname.value = data.nickname;


    }, 50);
}

onMounted(() => {
    load();
})

function statusz() {
    // console.log("하이룽");
    subscribe.value = !subscribe.value;
    console.log(subscribe.value)
}
</script>

<style scoped>
.article-box {
    width: 39rem;
    height: minmax(1rem, auto);
}

.article-box .title {
    font-size: 2.25rem;
    text-align: center;
}

.article-box .userInfo {
    text-align: right;
}

.d-inline {
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
    padding-left: 2.5rem;
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
    width: 18.5rem;
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
    width: 10em;
    /* 최대 5글자까지만 보이도록 요소의 너비를 지정 */
    white-space: nowrap;
    /* 텍스트를 한 줄로 표시 */
    overflow: hidden;
    /*요소의 영역을 벗어나는 내용은 잘리고 표시되지 않음 */
    text-overflow: ellipsis;
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
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
    /*  */
}

.detail-width {
    width: 100%;
    min-width: 500px;
    max-width: 1100px;
    /* width: minmax(500px, 1280px); */
    text-align: left;
}

.subscribeBtn {
    text-align: center;
    background-color: #989797;
    width: 10rem;
    height: 5rem;
    position: absolute;
  top: 0px;
  left: 630px;
  

}
</style>

