<script setup>
import FollowCard from './FollowCard.vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';
import { onMounted } from 'vue';

const user = useUserDetailsStore();
const userId = user.id;
const userFollowId = reactive({list,})

async function getFollowIdList(id){    
    await fetch(`http://localhost:8080/member/follow?id=${id}`,
    {
        method: "GET",
        headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
        },
        body: JSON.stringify(cmtJson)
    })
    .then(response => response.json())
    // .then((data) => { if (data == 1) console.log("완료") })
    .catch(error => console.log(error));
}

onMounted(()=> {
    // getFollowIdList(user.id);
})

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