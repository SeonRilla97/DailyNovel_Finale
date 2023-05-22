<template lang="">
    <section class="grid">
        <h1 class="d-none">신고페이지</h1>
        <form method="POST" data-email="S2dailynovelS2@gmail.com"
            action="https://script.google.com/macros/s/AKfycbzJhpwG2sb8vN0okuI8i4DRcCILqthRQQJvf5Dy1spmGBQSxs0q_7CRNzxb83Y1j-6i/exec" 
            target = iframe1
            class="grid">
                <!-- change the form action to your script url -->
                <section>
                    <h1 class="d-none">기본 신고 정보</h1>
                    <div class="d-none">
                        <label for="신고자Id">MemberId</label>
                        <input type="text" name="reporterId" placeholder="신고자" maxlength='30' :value="memberId" readonly>
                    </div>
                    <div class="d-none">
                        <label for="신고게시글Id">DiaryId</label>
                        <input type="text" name="DiaryId" placeholder="일기번호" maxlength='30' :value="id" readonly>
                    </div>
                </section>
                <section>
                    <h1 class="d-none">신고 세부사항</h1>
                    <fieldset class="name grid">
                        <legend>
                            <h1>신고 이유를 선택해주세요</h1>
                        </legend>
                        <select name="reason" id="" v-model="reason">
                            <option value="">신고 이유를 선택해주세요.</option>
                            <option value="욕설">욕설</option>
                            <option value="자살암시">자살암시</option>
                            <option value="외설">외설</option>
                            <option value="기타">기타</option>
                        </select>
                        <textarea name="message" id="" cols="100" rows="10" placeholder="신고 내용을 입력해주세요." class="resize"></textarea>
                    </fieldset>
                </section>
                <div>
                    <button @click="goBackHandler">취소하기</button>
                    <button @click="sendHandler" class="reg">등록하기</button>
                </div>

        </form>
        <iframe id="iframe1" name="iframe1" class="d-none"></iframe>
    </section>


</template>
<script setup>
import { ref, defineProps, onUpdated, onMounted, } from 'vue'
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js'

const props = defineProps({
    id: {
        type: Number
    }
})

function goBackHandler() {
    history.back()
}
function sendHandler() {
    alert("신고를 완료했습니다.")
}

// onMounted(() => {
//      console.log(memberId);
// }),

// onUpdated(() => {
//     console.log(reason.value);
// })

const reason = ref('');
// console.log(reason.value);
let memberId = useUserDetailsStore().id;

</script>
<style scoped>
.grid {
    display: grid;
    justify-items: center;
    align-items: center;
    grid-auto-flow: row;
}

.img {
    width: 473.76px;
    height: 442.08px;
}
.reg{
    background-color: #F2C6C2;
}
.reg:hover{
    background-color: #f0a099;
}
.resize{
    resize: none;
}
</style>