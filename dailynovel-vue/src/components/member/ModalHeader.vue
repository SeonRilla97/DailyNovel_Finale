<script setup>

defineEmits('modalCloseEventHandler')
function modalCloseHandler() {
    console.log("모달이 닫힙니다.");
}
</script>
    

<template>
    <header class="modal-header">
        <router-link to="/member/room" class="exit"><button @click="$emit('modalCloseEventHandler')"
                class="close-btn"></button></router-link>
        <!-- <div class="title ">모아 보기</div> -->
    </header>
</template>


<style>
.modal-header {
    width: 100%;
    height: 100%;

    background-color: #F2C6C2;

    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
}

.modal .title {
    font-size: 40px;
    font-weight: bold;
}


.modal-header .exit {
    position: absolute;
    right: 20px;
}

.modal-header .exit button {
    background-size: 40px;
    width: 40px;
    height: 40px;
    background-color: transparent;
    border: none;

}
</style>