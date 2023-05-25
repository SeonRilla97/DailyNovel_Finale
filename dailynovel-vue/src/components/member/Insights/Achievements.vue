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

      <div class="achievement" :class="{ 'completed': write >= 5 }">
        <div class="achievement-img7"></div>
        <div class="achievement-data">작성수 {{ write }}/5</div>
        <div class="achievement-data">일기 걸음마</div>
      </div>

      <div class="achievement" :class="{ 'completed': write >= 15 }">
        <div class="achievement-img8"></div>
        <div class="achievement-data">작성수 {{ write }}/15</div>
        <div class="achievement-data">일기쓰기 좋아요</div>
      </div>

      <div class="achievement" :class="{ 'completed': write >= 30 }">
        <div class="achievement-img9"></div>
        <div class="achievement-data">작성수 {{ write }}/30</div>
        <div class="achievement-data">일기 장수</div>
      </div>

      <div class="achievement" :class="{ 'completed': write >= 50 }">
        <div class="achievement-img10"></div>
        <div class="achievement-data">작성수 {{ write }}/50</div>
        <div class="achievement-data">생각계의 홍석현</div>
      </div>


      <div class="achievement" :class="{ 'completed': write >= 100 }">
        <div class="achievement-img11"></div>
        <div class="achievement-data" >작성수{{ write }}/100</div>
        <div class="achievement-data">내가 바로 작성왕</div>
      </div>

   




    </div>
  </main>
</template>


<script setup>
import { onMounted, ref } from 'vue';
import { useUserDetailsStore } from "../../store/useUserDetailsStore";


let userDetails = useUserDetailsStore(); // 피니아 사용



// 태그
const tripTag = ref('');
const movieTag = ref('');
const freeTag = ref('');
const write = ref('');



var TrequestOptions = {
  method: 'GET',
  redirect: 'follow'
};



async function Tload() {
  const response = await fetch(`http://localhost:8080/insights/Tag?memberId=${userDetails.id}`, TrequestOptions);
  const data = await response.json();
  console.log(data)
  let tags = [];
  let counts = [];

  for (const key in data) {
    tags.push(key);
    counts.push(data[key]);

    for (let i = 0; i < 3; i++) {
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
     write.value = tripTag.value + movieTag.value + freeTag.value;

  }

  console.log("작성:",write.value);
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
  color: rgb(255, 208, 0);
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
  background-color: #F2C6C2;
}
</style>