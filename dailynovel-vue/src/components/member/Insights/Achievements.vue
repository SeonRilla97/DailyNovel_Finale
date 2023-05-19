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

    <div class="achievement-box">

      <div class="achievement" :class="{ 'completed': feelingData >= 6 }">
        <div class="achievement-img1"></div>
        <div class="achievement-data">{{ feelingData }}/6</div>
        <div class="achievement-data">행복사</div>
      </div>

      <div class="achievement" :class="{ 'completed': honestyData >= 5 }">
        <div class="achievement-img2"></div>
        <div class="achievement-data">{{ honestyData }}/5</div>

        <div class="achievement-data">피노키오 코 500m 달성!</div>
      </div>

      <div class="achievement">
        <div class="achievement-img3"></div>
        <div class="achievement-data">3/10</div>
        <div class="achievement-data">역마살 on</div>
      </div>

      <div class="achievement">
        <div class="achievement-img4"></div>
        <div class="achievement-data">3/24</div>
        <div class="achievement-data">이동진씨의 친구</div>
      </div>

      <div class="achievement">
        <div class="achievement-img5"></div>
        <div class="achievement-data">3/25</div>
        <div class="achievement-data">내가 바로 작성왕</div>
      </div>
    </div>

  </main>
</template>


<script setup>
import { onMounted, ref } from 'vue';

let honesty = [];
let feeling = [];
const honestyData = ref('');
const feelingData = ref('');



const HrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};

var FrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};


async function Fload() {
  const response = await fetch("http://localhost:8080/insights/Feeling?feellng",FrequestOptions)
  const data = await response.json();
  
  let feeling = [];
  let counts = [];

  for(const key in data){
    feeling.push(key);
    counts.push(data[key]);
  }

  feelingData.value = counts[7];


  
  console.log("feelingData.value:", feelingData.value)

}

async function Hload() {
  const response = await fetch("http://localhost:8080/insights/Honesty?honestyRange", HrequestOptions);
  const data = await response.json();
  console.log(data)
  let honesty = [];
  let counts = [];

  for(const key in data){
    honesty.push(key);
    counts.push(data[key]);
  }

  honestyData.value = counts[0];
  console.log("honestyData.value:", counts[0]);


}

onMounted(() => {
  Hload();
  Fload();
});

</script>



<style scoped>
.screen{
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
}

.select-box{
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  padding-top: 30px;
  text-align: center;


}

.select-box > *{
  flex: 0.1 0.1 auto;
  font-size: 30px;
  font-weight: bold;

}

.Move:hover{
  color: rgb(226, 226, 99);
  text-decoration: underline;
}
.achievement-box {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;


  width: 100%;
  height: 100%;

}

.achievement-box .achievement {

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;


  max-width: 200px;
  min-width: 150px;
  max-height: 450px;

  width: 100%;
  height: 100%;

  background-color: #D9D9D9;
  border-bottom: 1px solid #999999;
  border-radius: 15px;


}

.achievement-data{
  font-weight: bold;
  font-size:  16px;
}

.achievement.completed {
  background-color: rgb(127, 127, 204);
}


</style>