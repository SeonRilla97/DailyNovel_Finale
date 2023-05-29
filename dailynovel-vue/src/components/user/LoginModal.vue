<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from "vue-router";
let router = useRouter();
let props = defineProps({
    content: "",
    show: false,
    type: "conform"
});

function navigateToMemberRoom(){
    router.push('/login');
}
</script>

<template>
    <div class="screen" :class="{ 'd-none': !show } " @click="$event => $emit('ok', 'Appvue로 전달이 되지롱')">
        <section>
            <div class="content">
                {{ content }}
            </div>
            <button v-if="content === '회원가입에 성공했습니다.'" @click="navigateToMemberRoom">로그인 하러가기</button>
            <button v-else @click="navigateToDefaultRoute">
        ok
      </button>
        </section>
    </div>
</template>

<style scoped>
.screen {
    background-color: rgba(00, 0, 0, 0.7);
    /* opacity: 0.8;; */
    left: 0;
    top: 0;
    width: 100vw;
    height: 100vh;
    position: fixed;
    display: flex;
    justify-content: center;
    align-items: center;
}



section {
    width: 30vw;
    height: 20vh;
    border-radius: 15px;
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(235, 155, 56);

    display: inline-block;
    position: fixed;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

}


section>.content {
    display: flex;
    font-size: 16px;
    line-height: 35px;
    font-weight: 700;
    text-align: center;
    justify-content: center;
    align-items: center;

}


section>div {
    width: 20vw;
    height: 20vh;
    align-items: center;
    text-align: center;
}

button {
    font-size: 16px;
    line-height: 35px;
    font-weight: 700;

    border: 0;
    background-color: rgb(255, 255, 255);
    color: rgb(235, 155, 56);
    margin-bottom: 15px;
}

.screen>* {
    opacity: 1;
}



.d-none {
    display: none !important;
}
</style>