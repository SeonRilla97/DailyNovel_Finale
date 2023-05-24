<template lang="">
    <section class="grid justify-i-center">
        <h1 class="d-none">신고페이지</h1>
        <form method="POST" data-email="S2dailynovelS2@gmail.com"
            action="https://script.google.com/macros/s/AKfycbzJhpwG2sb8vN0okuI8i4DRcCILqthRQQJvf5Dy1spmGBQSxs0q_7CRNzxb83Y1j-6i/exec" 
            target = iframe1
            class="grid justify-i-center">
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
                    <!-- <hr class="hr-content"> -->
                    <fieldset class="name grid ">
                        <legend>
                            <h1 class="h1-font">신고유형 및 문의내용</h1>
                        </legend>
                        <hr class="hr-content">
                        <section class="grid grid-column ">
                            <h1>신고유형</h1>
                            <select name="reason" id="" v-model="reason" class="mgr-50">
                                <option value="">신고유형 선택</option>
                                <option value="욕설">욕설</option>
                                <option value="자살암시">자살암시</option>
                                <option value="외설">외설</option>
                                <option value="기타">도배</option>
                                <option value="기타">기타</option>
                            </select>
                        </section>
                        <hr>
                        <section class="grid grid-column align-i-start justify-c-sb">
                            <h1>신고내용</h1>
                            <textarea name="message" id="" cols="75" rows="10" placeholder="신고 내용을 입력해주세요." class="resize"></textarea>
                        </section>
                    </fieldset>
                </section>
                <div class="grid grid-column mgt-3">
                    <button @click="goBackHandler" v-text="changeBtn==1?'취소하기':'뒤로가기'"></button>
                    <button @click="sendHandler" class="reg mgl-3">등록하기</button>
                </div>
        </form>
        <iframe id="iframe1" name="iframe1" class="d-none"></iframe>
    </section>


</template>
<script setup>
import { ref, defineProps, onUpdated, onMounted, } from 'vue'
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js'

let changeBtn = ref(1);

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
    changeBtn.value++
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
.mgr-50{
    margin-right: 19.5rem;
}
.h1-font{
    font-size: 30px;
}
.grid {
    display: grid;
    align-items: center;
}
.justify-i-center{
    justify-items: center;
}
.justify-i-start{
    justify-items: start;
}
.justify-i-end{
    justify-items: end;
}
.justify-c-sb{
    justify-content: space-between;
}
.align-i-start{
    align-items: start;
}
.grid-row{
    grid-auto-flow: row;
    
}
.grid-column{
    grid-auto-flow: column;
    justify-items: start;
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

.hr-content{
    width: 723px;
    border-color: rgb(0, 0, 0);
}
hr{
    width: 723px;
    border-color: rgba(179, 179, 179, 0.171);
}
</style>