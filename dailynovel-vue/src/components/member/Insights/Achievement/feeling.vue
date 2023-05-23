<template>
    <main class="screen">


<div class="select-box">
  <router-link to="/member/room/achievement">
    <p class="Move">업적</p>
  </router-link>
  <router-link to="/member/room/chart">
    <p class="Move">차트</p>
  </router-link>
</div>

<div class="composition-box">
    <router-link to="/member/room/feeling">
    <p class="Move">기분업적</p>
  </router-link>
  <router-link to="/member/room/honesty">
    <p class="Move">솔직함업적</p>
  </router-link>
  <router-link to="/member/room/tag">
    <p class="Move">태그업적</p>
  </router-link>
</div>

<div class="achievement-box">

  <div class="achievement" :class="{ 'completed': happyData >= 6 }">
    <div class="achievement-img1"></div>
    <div class="achievement-data">행복 {{ happyData }}/6</div>
    <div class="achievement-data">행복사</div>
  </div>

  
  <div class="achievement" :class="{ 'completed': feelingData >= 6 }">
    <div class="achievement-img13"></div>
    <div class="achievement-data">신남 {{ exctingData }}/6</div>
    <div class="achievement-data">많이 신남</div>
  </div>




</div>

</main>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useUserDetailsStore } from "../../../store/useUserDetailsStore.js";


let userDetails = useUserDetailsStore(); // 피니아 사용


const happyData = ref('');
const exctingData = ref('');


var FrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};

async function Fload() {
  const response = await fetch(`http://localhost:8080/insights/Feeling?memberId=${userDetails.id}`, FrequestOptions)
  const data = await response.json();

  console.log(data)

  let feeling = [];
  let counts = [];

  for (const key in data) {
    feeling.push(key);
    counts.push(data[key]);

    for (let i = 0; i < 9; i++) {
      if (feeling[i] == "행복") {
        happyData.value = counts[i]
        console.log(happyData)
      }
    }

    for (let i = 0; i < 9; i++) {
      if (feeling[i] == "신남") {
        exctingData.value = counts[i]
        console.log(exctingData)
      }
    }
  }




}

onMounted(() => {
  Fload();
});

</script>

<style scoped>
.screen {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
}

.select-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding-top: 30px;
  text-align: center;
}

.composition-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding-top: 30px;
  text-align: center;
}

.select-box > * {
  flex: 0.1 0.1 auto;
  font-size: 30px;
  font-weight: bold;
}

.composition-box > * {
  flex: 0.1 0.1 auto;
  font-size: 16px;
  font-weight: bold;
}
.Move:hover {
  color: rgb(226, 226, 99);
  text-decoration: underline;
}

.achievement-box {
  display: flex;
  margin-top: 48px;
  overflow-x: scroll;
  width: 100%;
  height: 100%;
}

.achievement-box .achievement {
  flex: 0 0 250px;
  
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  max-height: 450px;
  margin-left: 2rem;
  background-color: #D9D9D9;
  border-bottom: 1px solid #999999;
  border-radius: 15px;
}

.achievement-data {
  font-weight: bold;
  font-size: 16px;
}

.achievement.completed {
  background-color: rgb(127, 127, 204);
}
</style>