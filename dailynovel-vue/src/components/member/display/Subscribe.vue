<template lang="">
    <section class="grid columns">
        <h1 class="d-none">프로필 안내 창</h1>
            <div class="profileImage" :style="`background-image: url(http://localhost:8080/image/profile/${image});`">
            프로필 이미지{{image}}
        </div> 
        <div class="grid row">
            <span class="btn" @click="subscribeHandler(writerId)">{{ isSubscribed=='true' ? '구독취소' : '구독하기' }}</span>
            <router-link to="/member/room/collection/main" class="btn"  v-show="isSubscribed=='true'">구경가기</router-link>
                    <!-- 구독을 눌러야 v-show가 될 수 있도록 만든다. -->
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

    // console.log(memberId.value);
    // console.log(writerId.value);

    SubscriptionStatus();
}

async function SubscriptionStatus() {
    const response = await fetch(`http://localhost:8080/display/subscribeScan?mId=${memberId.value}&fId=${writerId.value}`);
    isSubscribed.value = await response.text()
}

async function subscribeHandler(writerId) {
    console.log("구독했습니다.")
    // console.log("구독 " + (likeStatus? "delete" : "insert"));
    if (memberId == writerId)
        alert("5252, 본인은 구독할 수 없다구~")
    else {
        console.log(memberId)
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
    /* background-color: blue; */
    border: 1px;
    height: 30px;
    border-radius: 5px;
}

.btn:hover{
    background-color: #f0a59e;
    transition: 1s;
}
</style>