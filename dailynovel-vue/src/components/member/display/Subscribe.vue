<template lang="">
    <section class="grid columns">
        <h1 class="d-none">프로필 안내 창</h1>
            <div class="profileImage" :style="`background-image: url(http://localhost:8080/image/profile/${image});`">
        </div> 
        <div class="grid row">
            <div class="btn cursor btn-box" @click="subscribeHandler(writerId)" >
                    <span :class="isSubscribed=='true' ? 'subscribe-inactive' : 'subscribe-active'"></span>
                    <span>{{ isSubscribed=='true' ? '구독취소' : '구독하기' }}</span>
            </div>
            <router-link :to="{path:'../follow/'+writerId }" class="btn cursor btn-box"  v-show="isSubscribed=='true'">
                <span class="go-to-page"></span>
                <span>구경가기</span> 
            </router-link>
                <!-- 해당 member_id에 맞는 컬렉션으로 접속되도록 바꿔야 한다. -->
        </div>
    </section>
</template>

<script setup>
import { reactive, ref, onMounted, defineProps, defineEmits, } from 'vue';

const props = defineProps({
    memberId: {
        type: Number
    },
    writerId: {
        type: Number
    },
    image:{
        type: String
    }
})

let isSubscribed = ref();
let memberId = ref();
let writerId = ref();
let image = ref();

function load() {
    isSubscribed.value = props['isSubscribed'];
    memberId.value = props['memberId'];
    writerId.value = props['writerId'];
    image.value = props['image'];

    SubscriptionStatus();
}

async function SubscriptionStatus() {
    const response = await fetch(`http://localhost:8080/display/subscribeScan?mId=${memberId.value}&fId=${writerId.value}`);  //구독 했는지 안 했는지 확인
    isSubscribed.value = await response.text()
}

async function subscribeHandler(writerId) {
    console.log("구독했습니다.")
    if (memberId.value === writerId){
        alert("자신의 일기 입니다.")
    }
    else {
        console.log(memberId.value)
        console.log(writerId)

        try {
            const response = await fetch('http://localhost:8080/display/subscribeRequest', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    followedId: writerId,         // 멤버 정보 가지고 오기
                    followId: memberId.value,
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
        // alert("구독했습니다.")
    }
}

onMounted(() => {
    load();
})


</script>

<style scoped>
.grid {
    display: grid;
}
.columns{
    grid-template-columns: 1fr 1fr;
}
.row{
    grid-template-rows: 1fr 1fr;
    align-items: center;
}

.profileImage {
    width: 52px;
    height: 52px;

    border-radius: 100px;
    /* border: 1px solid #f0a59e; */
    width: 150px;
    height: 150px;
    /* background-image: url(../../../assets/img/ProfileImage/png1.jpg); */
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
}

.btn {
    display: grid;
    /* grid-column: row; */
    align-items: center;
    border: 1px solid black;
    height: 50px;
    border-radius: 5px;
}

.btn:hover{
    background-color: #f0a59e;
    transition: 1s;
}

.cursor{
    cursor: pointer;
}


.subscribe-active {
    /*좋아요 활성화*/
    width: 1.5rem;
    height: 1.5rem;
    background-image: url('@/assets/img/display/subscribe-active.svg');
    background-repeat: no-repeat;
    background-position: center bottom;
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
    /*  */
}

.subscribe-inactive {
    /*좋아요 활성화*/
    width: 1.5rem;
    height: 1.5rem;
    background-image: url('@/assets/img/display/subscribe-inactive.svg');
    background-repeat: no-repeat;
    background-position: center bottom;
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
    /*  */
}

.go-to-page {
    /*좋아요 활성화*/
    width: 1.5rem;
    height: 1.5rem;
    background-image: url('@/assets/img/display/go-to-page.svg');
    background-repeat: no-repeat;
    background-position: center bottom;
    background-size: contain;
    border: none;
    outline: none;
    cursor: pointer;
    /*  */
}
.btn-box{
    grid-auto-flow:column ; 
    justify-content:center ; 
    grid-gap:1rem
}
</style>