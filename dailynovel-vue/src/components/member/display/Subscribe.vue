<template lang="">
    <span @click="subscribeHandler(writerId)">{{ ssibal=='true' ? '구독취소' : '구독하기' }}</span>
    <router-link to="/member/room/collection/main" v-show="">구경가기</router-link>
            <!-- 구독을 눌러야 v-show가 될 수 있도록 만든다. -->
            <!-- 해당 member_id에 맞는 컬렉션으로 접속되도록 바꿔야 한다. -->
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
})

let ssibal = ref();
let memberId = ref();
let writerId = ref();

function load() {
    ssibal.value = props['ssibal'];
    memberId.value=props['memberId'];
    writerId.value=props['writerId'];

    console.log(memberId.value);
    console.log(writerId.value);

    fetchSubscriptionStatus();
}

async function fetchSubscriptionStatus() {
    const response = await fetch(`http://localhost:8080/display/subscribeScan?mId=${memberId.value}&fId=${writerId.value}`);
    ssibal.value = await response.text()
    // console.log(ssibal.value);
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
        alert("배달의 민족 주문")
    }
}

onMounted(() => {
    load();
})


</script>

<style scoped></style>