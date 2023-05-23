<script setup>
import FollowCard from './FollowCard.vue';
</script>
<template>
    <section class="m-follow-list-container">
        <FollowCard v-for="i in 20"></FollowCard>
    </section>
</template>

<style scoped>
.m-follow-list-container{
    width: 100%;
    height: 100%;
    display: grid;
    grid-template-columns: repeat(5, 1fr);
    gap: 1rem;
    grid-template-rows: repeat(2, 1fr);

    box-sizing: border-box;

    overflow: scroll;

}
</style>