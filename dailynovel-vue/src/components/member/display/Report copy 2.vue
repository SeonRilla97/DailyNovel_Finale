<template lang="">
    <section class="grid">
        <h1 class="d-none">신고페이지</h1>
        <img src="@/assets/img/display/igotyou.png" alt="img" class="img d-none">
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

        <form class="gform pure-form pure-form-stacked" method="POST" data-email="S2dailynovelS2@gmail.com"
        action="https://script.google.com/macros/s/AKfycbzJhpwG2sb8vN0okuI8i4DRcCILqthRQQJvf5Dy1spmGBQSxs0q_7CRNzxb83Y1j-6i/exec" target = iframe1>
            <!-- change the form action to your script url -->
            <textarea name="message" id="" cols="30" rows="10" placeholder="문의사항을 적어주세요" class="box-large input-boder-none mgt-5 feedback-box-size"></textarea>
            <div class="d-none">
                <label for="id">id</label>
                <input type="text" name="id" placeholder="아이디." maxlength='30' :value="memberId" readonly>
            </div>
            <div class="l-box-short mgt-5">
                <fieldset class="name"><h1>문의종류</h1></fieldset>
                    <div class="content">
                        <label for="kind" class="mg-2">버그</label>
                        <input type="radio" name="kind" id="" value="버그">
                        <label for="kind" class="mg-2">기능</label>
                        <input type="radio" name="kind" id="" value="기능">
                        <label for="kind" class="mg-2">그외</label>
                        <input type="radio" name="kind" id="" value="그외" checked>
                    </div>
            </div>
                <div class="flex-jfy-center mgt-5">
                    <input class="btn3" type="submit" value="보내기" style="cursor: pointer;">
                </div>
        </form>
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