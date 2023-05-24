<script setup>
import FollowCard from './FollowCard.vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';
import { onMounted, reactive } from 'vue';

const user = useUserDetailsStore();
const userId = user.id;
const userFollow = reactive({

})

async function getFollowIdList(id) {
  try {
    const response = await fetch(`http://localhost:8080/members/follow?id=${id}`);
    if (!response.ok) {
      throw new Error('Failed to fetch data');
    }
    const data = await response.json();
    userFollow.value = data;
    console.log(userFollow);
  } catch (error) {
    console.log(error);
  }
}

onMounted(()=> {
     getFollowIdList(user.id);
})

</script>
<template>
    <section class="m-follow-list-container">
           <FollowCard v-for="follow in userFollow.value" :key="follow.value" :data="follow"></FollowCard>
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