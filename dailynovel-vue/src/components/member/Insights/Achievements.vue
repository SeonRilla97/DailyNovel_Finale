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

      <div class="achievement" :class="{ 'completed': pinoData >= 5 }">
        <div class="achievement-img2"></div>
        <div class="achievement-data">{{ pinoData }}/5</div>
        <div class="achievement-data">콧대의 신!</div>
      </div>

      <div class="achievement" :class="{ 'completed': tripTag >= 10 }">
        <div class="achievement-img3"></div>
        <div class="achievement-data">{{ tripTag }}/10</div>
        <div class="achievement-data">역마살 on</div>
      </div>

      <div class="achievement" :class="{ 'completed': movieTag >= 24 }">
        <div class="achievement-img4"></div>
        <div class="achievement-data">{{ movieTag }}/24</div>
        <div class="achievement-data">이동진씨의 베프</div>
      </div>

      <div class="achievement" :class="{ 'completed': write >= 20 }">
        <div class="achievement-img5"></div>
        <div class="achievement-data">{{ write }}/20</div>
        <div class="achievement-data">내가 바로 작성왕</div>
      </div>

      <div class="achievement" :class="{ 'completed': NpinoData >= 3 }">
        <div class="achievement-img6"></div>
        <div class="achievement-data">{{ NpinoData }}/3</div>
        <div class="achievement-data">내가 바로 작성왕</div>
      </div>
      



    </div>

  </main>
</template>


<script setup>
import { onMounted, ref } from 'vue';

// 솔직함
const honesty0Data = ref('');
const honesty2Data = ref('');
const honesty4Data = ref('');
const honesty6Data = ref('');
const honesty8Data = ref('');
const pinoData = ref('');
const NpinoData = ref('');

// 기분
const feelingData = ref('');

// 태그
const tripTag = ref('');
const movieTag = ref('');
const freeTag = ref('');
const write = ref('');


const HrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};

var FrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};

var TrequestOptions = {
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



async function Npinoload(){
  const response = await fetch("http://localhost:8080/insights/Npino?Npino&count", NpinorequestOptions)
  const data = await response.json();
  console.log(data)

  let Npino = [];
  let count = [];

  for(const key in data){
    Npino.push(key)
    count.push(data[key])
  }

  NpinoData.value = count[1];
  console.log("NpinoData.value:",NpinoData.value)


}

async function pinoload(){
  const response = await fetch("http://localhost:8080/insights/pino?pino&count", pinorequestOptions)
  const data = await response.json();

  let pino = [];
  let count = [];

  for(const key in data){
    pino.push(key)
    count.push(data[key])
  }

  pinoData.value = count[1];



}


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

  honesty0Data.value = counts[0];
  honesty2Data.value = counts[1];
  honesty4Data.value = counts[2];
  honesty6Data.value = counts[3];
  honesty8Data.value = counts[4];



}

async function Tload(){
  const response = await fetch("http://localhost:8080/insights/Tag?tag", TrequestOptions);
  const data = await response.json();
  console.log(data)
  let tags = [];
  let counts = [];

  for(const key in data){
    tags.push(key);
    counts.push(data[key]);
  }

  tripTag.value = counts[0];
  movieTag.value = counts[1];
  freeTag.value = counts[2];
  write.value = counts[0]+counts[1]+counts[2];
  console.log(write);
}


onMounted(() => {
  Hload();
  Fload();
  Tload();
  pinoload();
  Npinoload();
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
  display: grid;
  grid-template-rows: repeat(1, 1fr); 
  grid-template-columns: repeat(6, 1fr); 

  gap: 32px;

  justify-content: space-around;
  align-items: center;
  overflow-x: scroll;


  width: 100%;
  height: 100%;

}

.achievement-box .achievement {

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;


  width: 200px;
  max-height: 450px;
  margin-left: 2rem;


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