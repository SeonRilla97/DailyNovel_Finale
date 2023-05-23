<template lang="">
    <section class="grid">
        <h1 class="d-none">신고페이지</h1>
        <img src="@/assets/img/display/igotyou.png" alt="img" class="img">
        <!-- <img src="../../../assets/img" alt=""></img> -->
        <div>
            <select name="reason" id="" v-model="reason">
                <option value="">신고 이유를 선택해주세요.</option>
                <option value="욕설">욕설</option>
                <option value="자살암시">자살암시</option>
                <option value="외설">외설</option>
                <option value="기타">기타</option>
            </select>
            <button @click="transmitReport">전송</button>
        </div>
    </section>


</template>
<script setup>
import { ref, defineProps, onUpdated,onMounted, } from 'vue'
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js'

const props = defineProps({
    id: {
        type: Number
    }
})

onMounted(() => {
    console.log(memberId);
}),

onUpdated(() => {
    console.log(reason.value);
})

const reason = ref('');
console.log(reason.value);
let memberId = useUserDetailsStore().id;

async function transmitReport() {
    if (reason.value == '')
        alert('값을 입력하고 전송하세요~')
    else {
        try {
            const response = await fetch(`http://localhost:8080/display/report}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    memberId: memberId,         // 멤버 정보 가지고 오기
                    diaryId: diaryId,
                    reason: reason.value,
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
        alert("신고 완료 '~^'")
    }
}
</script>
<style scoped>
.grid {
    display: grid;
    justify-items: center;
    align-items: center;
}

.img {
    width: 473.76px;
    height: 442.08px;
}
</style>