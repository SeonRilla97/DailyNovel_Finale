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


    <div class="achievement" :class="{ 'completed': pinoData >= 5 }">
        <div class="achievement-img2"></div>
        <div class="achievement-data">솔직함(0) : {{ pinoData }}/5</div>
        <div class="achievement-data">콧대의 신!</div>
     </div>

    <div class="achievement" :class="{ 'completed': honestyTotal >= 20 }">
        <div class="achievement-img6"></div>
        <div class="achievement-data">솔직함40미만 : {{ honestyTotal }}/20</div>
        <div class="achievement-data">내가 바로 소설가</div>
    </div>


</div>

</main>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useUserDetailsStore } from "../../../store/useUserDetailsStore.js";


let userDetails = useUserDetailsStore(); // 피니아 사용


// 솔직함
const honesty0Data = ref('');
const honesty2Data = ref('');
const honesty4Data = ref('');
const honesty6Data = ref('');
const honesty8Data = ref('');
const honestyTotal = ref('')
const pinoData = ref('');
const NpinoData = ref('');

const HrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};

var pinorequestOptions = {
  method: 'GET',
  redirect: 'follow'
};

var NpinorequestOptions = {
  method: 'GET',
  redirect: 'follow'
};


async function Npinoload() {
  const response = await fetch(`http://localhost:8080/insights/Npino?Npino&count&memberId=${userDetails.id}`, NpinorequestOptions)
  const data = await response.json();
  console.log(data)

  let Npino = [];
  let count = [];

  for (const key in data) {
    Npino.push(key)
    count.push(data[key])
  }

  NpinoData.value = count[1];
  console.log("NpinoData.value:", NpinoData.value)


}

async function pinoload() {
  const response = await fetch(`http://localhost:8080/insights/pino?memberId=${userDetails.id}`, pinorequestOptions)
  const data = await response.json();

  let pino = [];
  let count = [];

  for (const key in data) {
    pino.push(key)
    count.push(data[key])
  }

  pinoData.value = count[1];

}

async function Hload() {
  const response = await fetch(`http://localhost:8080/insights/Honesty?memberId=${userDetails.id}`, HrequestOptions);
  const data = await response.json();
  console.log(data)
  let honesty = [];
  let counts = [];

  for (const key in data) {
    honesty.push(key);
    counts.push(data[key]);
  }

  honesty0Data.value = counts[0];
  honesty2Data.value = counts[1];
  honesty4Data.value = counts[2];
  honesty6Data.value = counts[3];
  honesty8Data.value = counts[4];

  honestyTotal.value = honesty0Data.value + honesty2Data.value;

}

onMounted(() => {
  Hload();
  pinoload();
  Npinoload();
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