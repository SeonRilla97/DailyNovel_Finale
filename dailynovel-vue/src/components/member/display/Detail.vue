<template lang="">
    <main class=" center-grid">
        <div class="subscribeBtn nodouble-drag" v-show="subscribeBox">
            <Subscribe :memberId="memberId" :writerId="writerId" v-if="writerId!=''"></Subscribe>
        </div>
        <div class="content-center">
            <section class=" center-grid article-box scroll">
                <h1 class="d-none">디테일</h1>
                    <article> 
                        <div class="sticky">
                            <h1 class="title nodouble-drag">{{title}}</h1>
                            <hr>
                            <div class="userInfo">
                                <div class="d-inline" :class="likeStatus?'like-active':'like-deactive'" @click="likeSwitchHandler(diaryId)"></div>
                                <div class="d-inline nodouble-drag">{{like}} / </div>
                                <div class="d-inline nodouble-drag">{{image}} / </div>
                                <div class="d-inline nodouble-drag" @click="openSubscribeBoxHandler">{{nickname}}</div>
                            </div>
                        </div>
                        <br>
                        <p class="detail-width nodouble-drag">
                            {{content}}<br>
                            도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득                            득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득도완득
                            <br>
                            도
                            <br>
                            완
                            <br>
                            득
                        </p>
                    </article>
                    <div class="center-grid padding-bottom sticky">
                        <div style="display:inline-block">
                            <div class="more-btn nodouble-drag d-inline">좋아요</div>
                            <div class="more-btn nodouble-drag d-inline">신고하기</div>
                        </div>
                    </div>
            </section>
        </div>
    </main>
</template>

<script setup>

import Subscribe from './Subscribe.vue'
import { reactive, ref, onMounted, defineProps, defineEmits, onBeforeMount, } from 'vue';
import {useUserDetailsStore} from '../../store/useUserDetailsStore.js'

const emit = defineEmits([
    'updatePage'
]);


const props = defineProps({
    detailPage: {
        type: Object
    },
    likeInfo: {
        type : Boolean
    },
})

let userDetails = useUserDetailsStore();

let data = ref();
let diaryId = ref();
let title = ref('');
let content = ref('');
let like = ref();//게시글의 총 좋아요 수
let image = ref('');//이미지
let nickname = ref('');//닉네임
let subscribeBox = ref(false);
let writerId = ref('');// 게시글 작성자 아이디

let likeStatus = ref('');

let memberId = userDetails.id; // 멤버 아이디 받아오는 걱 수정해야 함

let SubscriptionStatus = ref();


onBeforeMount(async () => {
    await load();
});


async function load() {
    
    setTimeout(() => {
    // await zz(()=>{
        // console.log(props)
        data = props.detailPage

        diaryId.value = data.id;
        title.value = data.title
        content.value = data.content
        like.value = data.like;
        image.value = data.image;
        nickname.value = data.nickname;
        writerId.value = props.detailPage['memberId'];

        likeStatus = props.likeInfo

    }, 150);
    // })
}

onMounted(async() => {
    await load();
})


async function likeSwitchHandler(diaryId) {
    console.log("좋아요 " + (likeStatus? "delete" : "insert"));

    try {
        const response = await fetch(`http://localhost:8080/display/${likeStatus? "deletelike" : "addlike"}`, {
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
    emit('updatePage');
    setTimeout(load, 50);
    
}

function openSubscribeBoxHandler() {
    subscribeBox.value = !subscribeBox.value;
    console.log(subscribeBox.value)
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
    background-position: center bottom;
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
    background-color: #FAFFF9;
    /* #F2C6C2; */
    border: 3px solid black;
    border-radius: 0.5rem;
    width: 20rem;
    height: 9.375rem;
    position: absolute;
    top: 170px;
    left: 615px;
    z-index: 1;
}

.content-center {
    position: absolute;
    top: 74px;
    left: 50%;
    transform: translateX(-50%);
}

.nodouble-drag {
    user-select: none;
}

.scroll {
    overflow: auto;
    height: 648px;
}

.sticky{
    position: sticky;
    padding-top: 1rem;
    padding-bottom: 1rem;
    top: 0px;
    background-color: #FAFFF9;
}
.padding-bottom{
    padding-top: 1rem;
    padding-bottom: 1rem;
}

</style>

