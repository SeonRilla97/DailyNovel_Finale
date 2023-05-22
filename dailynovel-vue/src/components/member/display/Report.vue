<template lang="">
    <section>
        <h1>신고페이지</h1>
        <input type="text" v-value="">
        <select name="report" id="">
            <option value="">신고를 선택해주세요.</option>
            <option value="욕설">욕설</option>
            <option value="자살암시">자살암시</option>
            <option value="외설">외설</option>
            <option value="기타">기타</option>
        </select>
        <button @click="transmitReport">전송</button>
    </section>


</template>
<script setup>
    async function transmitReport(){
        try {
        const response = await fetch(`http://localhost:8080/display/${this.indeLikeList.some(item => item.diaryId === diaryId) ? "deletelike" : "addlike"}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                memberId: memberId,         // 멤버 정보 가지고 오기
                writerId: writerId,
                diaryId: diaryId,
                reason:
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

</script>
<style scoped>
    
</style>