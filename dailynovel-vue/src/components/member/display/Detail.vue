<template lang="">
    <main class=" center-grid">
        <transition name="bounce">
            <div class="subscribeBtn nodouble-drag" v-show="subscribeBox">
                <Subscribe :memberId="memberId" :writerId="writerId" :image="image" v-if="writerId!=''"></Subscribe>
            </div>
        </transition>
        <div class="content-center">
            <section class=" content-grid article-box ">
                <h1 class="d-none">디테일</h1>
                    <article class="scroll"> 
                        <div class="sticky">
                            <h1 class="title nodouble-drag">{{title}}</h1>
                            <hr>
                            <div class="userInfo">
                                <div class="d-inline" :class="likeStatus?'like-active':'like-deactive'" @click="likeSwitchHandler(diaryId)"></div>
                                <div class="d-inline nodouble-drag">{{like}}</div>
                                <div class="d-inline nodouble-drag">
                                    <div class="profileImage" :style="`background-image: url(http://localhost:8080/image/profile/${image});`"></div>
                                </div>
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
                        <div class="center-grid padding-bottom sticky">
                            <div style="display:inline-block">
                                <div class="more-btn nodouble-drag d-inline" @click="likeSwitchHandler(diaryId)">
                                    <div class="d-inline" :class="likeStatus?'like-active':'like-deactive'" > </div>
                                    {{like}}좋아요
                                </div>
                                <div class="more-btn nodouble-drag d-inline">신고하기</div>
                            </div>
                        </div>
                    </article>
                </section>
        </div>
    </main>
</template>

<script setup>

import Subscribe from './Subscribe.vue'
import { reactive, ref, onMounted, defineProps, defineEmits, onBeforeMount, } from 'vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js'

const emit = defineEmits([
    'updatePage'
]);


const props = defineProps({
    detailPage: {
        type: Object
    },
    likeInfo: {
        type: Boolean
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

onMounted(async () => {
    await load();
})


async function likeSwitchHandler(diaryId) {
    console.log("좋아요 " + (likeStatus ? "delete" : "insert"));

    try {
        const response = await fetch(`http://localhost:8080/display/${likeStatus ? "deletelike" : "addlike"}`, {
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
    height: minmax(500px, auto);
}

.article-box .title {
    font-size: 2.25rem;
    text-align: center;
}

/* text-align: right; */
.article-box .userInfo {
    display: grid;
    justify-content: end;
    grid-auto-flow: column;
    align-items: center;
}

/* btn */
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

.subscribeBtn {
    text-align: center;
    background-color: #FAFFF9;
    /* #F2C6C2; */
    border: 3px dashed black;
    border-radius: 0.5rem;
    width: 20rem;
    height: 9.375rem;
    position: absolute;
    top: 170px;
    left: 615px;
    z-index: 1;
}





/* layout */

.d-inline {
    display: inline-block;
}

.center-grid {
    display: grid;
    justify-content: center;
}

.content-grid {
    display: grid;
    /* grid-template-rows: 540px 100px; */
    justify-content: center;
}

.detail-width {
    width: 100%;
    min-width: 500px;
    max-width: 1100px;
    text-align: left;
}

.content-center {
    position: absolute;
    top: 74px;
    left: 50%;
    transform: translateX(-50%);
}

.scroll {
    overflow: auto;
    height: 648px;
}

.sticky {
    position: sticky;
    padding-top: 1rem;
    padding-bottom: 1rem;
    top: 0px;
    background-color: #FAFFF9;
}

.fixed {
    position: fixed;
    padding-top: 1rem;
    padding-bottom: 1rem;
    top: 0px;
    background-color: #FAFFF9;
}


/* util*/
.nodouble-drag {
    user-select: none;
}

.padding-bottom {
    padding-top: 1rem;
    padding-bottom: 1rem;
}

.profileImage {
    border-radius: 30px;
    width: 30px;
    height: 30px;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
}



/* vue 트렌지션 */
.bounce-enter-active {
    animation: bounce-in .2s;
}

.bounce-leave-active {
    animation: bounce-in .2s reverse;
}

@keyframes bounce-in {
    0% {
        transform: scale(0);
    }

    100% {
        transform: scale(1);
    }
}</style>

