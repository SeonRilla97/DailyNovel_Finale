<template lang="">
    <main class=" center-grid">
        <div class="goBack-btn back-arrow" @click="goBackHandler"></div>
        <transition name="bounce">
            <div class="subscribeBtn nodouble-drag" v-show="subscribeBox">
                <Subscribe :memberId="memberId" :writerId="writerId" :image="image" v-if="writerId!=''"></Subscribe>
            </div>
        </transition>
        <!-- <div class="content-center"> -->
        <div>
            <section class=" content-grid article-box ">
                <h1 class="d-none">디테일</h1>
                    <article class="" > 
                        <div class="sticky">
                            <h1 class="title nodouble-drag">{{title}}</h1>
                            <hr>
                            <div class="userInfo">
                                <div class="d-inline" :class="likeStatus?'like-active':'like-deactive'" @click="likeSwitchHandler(diaryId)"></div>
                                <div class="d-inline nodouble-drag mgl-1">{{like}}</div>
                                <div class="d-inline nodouble-drag">
                                    <div class="profileImage" :style="`background-image: url(http://localhost:8080/image/profile/${image});`"></div>
                                </div>
                                <div class="d-inline nodouble-drag cursor" @click="openSubscribeBoxHandler">{{nickname}}</div>
                            </div>
                        </div>
                        <br>
                        <div class="detail-width nodouble-drag scroll" >
                            <div>
                                <div>
                                    <div id="editor">
                                    </div>
                                </div>
                            </div>
                            
                            <br>
                        </div>
                        <div class="center-grid padding-bottom sticky">
                            <div class="btn-box">
                                <div class="more-btn nodouble-drag cursor" @click="likeSwitchHandler(diaryId)">
                                    <div class="d-inline" :class="likeStatus?'like-active':'like-deactive'" > </div>
                                        좋아요 {{like}}
                                    </div>
                                <div class="mgl-3 more-btn nodouble-drag btn-box btn-grid cursor" @click="unimplemented">
                                    <router-link :to="'/member/room/community/report/'+diaryId">
                                        <span class="alert-btn"></span>
                                        <span>신고하기</span>
                                    </router-link>
                                </div>
                            </div>
                        </div>
                    </article>
                </section>
        </div>
    </main>
</template>

<script setup>

import Subscribe from './Subscribe.vue'
import { reactive, ref, onMounted, defineProps, defineEmits, onBeforeMount, onUpdated } from 'vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js'
import Quill from 'quill';

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

        editTriger(content.value);
    }, 200);
    // })
}
let quill;
onMounted(async () => {
    await load();
    quill = new Quill('#editor',{
        readOnly: true
    });
    console.log(quill);
})

onUpdated(()=>{
    let text = quill.getText();
    // console.log(data.content);

})

function editTriger(Json1){
    let ToJson = JSON.parse(Json1);
    quill.setContents(ToJson);
}

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

function unimplemented() {
    console.log("신고하는 중")
};

function goBackHandler() {
    history.back()
}

</script>

<style scoped>
.back-arrow {
    width: 3rem;
    height: 3rem;
    background-image: url('@/assets/img/display/backArrow-inactive.svg');
    ;
    background-repeat: no-repeat;
    background-position: center bottom;
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
}

.back-arrow:hover {
    background-image: url('@/assets/img/display/backArrow-active.svg');
    ;
}

.article-box {
    width: 39rem;
    /* 체크, 너비 */
    height: minmax(500px, auto);
}

.article-box .title {
    font-size: 1.5rem;
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
.goBack-btn {
    position: fixed;
    /* padding-top: 1rem;
    padding-bottom: 1rem;
    top: 0px;
    background-color: #FAFFF9; */
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

.alert-btn {
    width: 1rem;
    height: 1rem;
    background-image: url('@/assets/img/display/alert-btn.svg');
    background-repeat: no-repeat;
    background-position: center bottom;
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
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
    min-width: 600px;
    max-width: 600px;
    min-height: 420px;
    text-align: left;
}

/* .content-center {
    position: absolute;
    top: 74px;
    left: 50%;
    transform: translateX(-50%);
} */

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
    /* z-index: 1; */
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

}

.cursor {
    cursor: pointer;
}

.btn-box {
    display: grid;
    grid-auto-flow: column;
}

.btn-grid {
    justify-content: center;
    align-items: center;
    grid-gap: 0.2rem;
}</style>

