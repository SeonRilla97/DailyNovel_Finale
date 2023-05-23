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
    

  <div class="achievement" :class="{ 'completed': tripTag >= 5 }">
        <div class="achievement-img14"></div>
        <div class="achievement-data">여행 : {{ tripTag }}/5</div>
        <div class="achievement-data">초보 여행가</div>
      </div>

    <div class="achievement" :class="{ 'completed': tripTag >= 10 }">
        <div class="achievement-img3"></div>
        <div class="achievement-data">여행 : {{ tripTag }}/10</div>
        <div class="achievement-data">역마살 on</div>
      </div>

      <div class="achievement" :class="{ 'completed': freeTag >= 10 }">
        <div class="achievement-img12"></div>
        <div class="achievement-data">자유 : {{ freeTag }}/10</div>
        <div class="achievement-data">자유의 일기상</div>
      </div>

      <div class="achievement" :class="{ 'completed': movieTag >= 24 }">
        <div class="achievement-img4"></div>
        <div class="achievement-data">영화 : {{ movieTag }}/24</div>
        <div class="achievement-data">이동진씨의 베프</div>
      </div>

     

      



</div>

</main>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useUserDetailsStore } from "../../../store/useUserDetailsStore.js";


let userDetails = useUserDetailsStore(); // 피니아 사용


const tripTag = ref('');
const movieTag = ref('');
const freeTag = ref('');

var TrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};



async function Tload() {
  const response = await fetch(`http://localhost:8080/insights/Tag?memberId=${userDetails.id}`, TrequestOptions);
  const data = await response.json();
  console.log("Tagdata:",data)
  let tags = [];
  let counts = [];

  

  for (const key in data) {
    tags.push(key);
    counts.push(data[key]);

    for (let i = 0; i < 5; i++) {
      if (tags[i] == "여행") {
        tripTag.value = counts[i]
        console.log("여행은 ", tripTag.value)
      }
      else if (tags[i] == "영화") {
        movieTag.value = counts[i]
        console.log("영화는 ", movieTag.value)
    
      }
      else if(tags[i] == "자유") {
        freeTag.value = counts[i]
        console.log("자유는 ", freeTag.value)
     }

     else {
        console.log("태그는 필요없어!");
     }
  }

}
}


onMounted(() => {
  Tload();
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