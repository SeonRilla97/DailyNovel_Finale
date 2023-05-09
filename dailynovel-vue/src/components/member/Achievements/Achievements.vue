<template>
  <div class="screen">
    <header class="app-header">
      <a href="">
        <div class="dailyNovel icon-logo"></div>
      </a>
      <ul>
        <li>
          <div class="icon-bell"></div>
        </li>
        <li>
          <div class="icon-community"></div>
        </li>
        <li class="profile">
          <div class="img"></div><span class="nickname"></span>
        </li>
      </ul>
    </header>

    <!-- 업적 본문 -->

    <section class="Achievement-screen">


      <header class="Achievement-screen-header">
        <h1 class="Achievement-screen-header-title">업적</h1>
        <a href="#" class="Achievement-screen-header-closeBtn"><span class="material-symbols-outlined">close</span></a>
      </header>



      <main class="Achievement-box">

        <div class="Achievement" :class="{ 'completed': feelingData >= 6 }">
          <div class="first"></div>
          <div>{{ feelingData }}/6</div>
          <div>일주일에 6번 행복하기</div>
        </div>

        <div class="Achievement" :class="{ 'completed':honestyData >= 5  }">
          <div class="second"></div>
          <div>{{ honestyData }}/5</div>

          <div>피노키오 코 500m 달성!</div>
        </div>

        <div class="Achievement">
          <div class="third"></div>
          <div>3/25</div>
          <div>내가 바로 작성왕</div>
        </div>

        <div class="Achievement">
          <div class="fourth"></div>
          <div>3/25</div>
          <div>내가 바로 작성왕</div>
        </div>

        <div class="Achievement">
          <div class="fifth"></div>
          <div>3/25</div>
          <div>내가 바로 작성왕</div>
        </div>





      </main>




    </section>




  </div>
</template>


<script setup>
import { onMounted, ref } from 'vue';

let honesty = [];
let feeling = [];
const honestyData = ref('');
const feelingData = ref('');

var myHeaders = new Headers();
myHeaders.append("Cookie", "JSESSIONID=00FBFDB24A1BDD060C55F8F3B78E8B1F");

const HrequestOptions = {
  method: 'GET',
  headers: myHeaders,
  redirect: 'follow'
};

var FrequestOptions = {
  method: 'GET',
  headers: myHeaders,
  redirect: 'follow'
};


async function Fload() {
  const response = await fetch("http://localhost:8080/analysis/feeling-chart?name&frequency", FrequestOptions);
  const data = await response.json();
  feeling = data;
  console.log("feeling:",feeling);

  feelingData.value = feeling.length ? feeling[4].frequency : '';
  console.log("feelingData.value:",feelingData.value)


}

async function Hload() {
  const response = await fetch("http://localhost:8080/analysis/honesty?honestyRange=&count", HrequestOptions);
  const data = await response.json();
  honesty = data;
  console.log("honesty:",honesty)
  honestyData.value = honesty.length ? honesty[0].count : '';
  console.log("honestyData.value:",honestyData.value);

  
}

onMounted(() => {
  Hload();
  Fload();
});

</script>



<style scoped>
.screen {
  position: fixed;
  top: 0;


  max-width: 1980px;
  max-height: 1080px;
  width: 100vw;
  height: 100vh;


  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #FFF
}

.app-header {
  display: flex;
  max-width: 1980px;
  width: 100vh;
  justify-content: space-between;
}

.app-header ul {
  display: flex;
  gap: 10px;
  margin-right: 10px;
  align-items: center;

}

.screen>.Achievement-screen {
  background-color: #FFF;
  display: flex;
  flex-direction: column;

  position: fixed;
  max-width: 1280px;
  max-height: 720;
  width: 90vw;
  height: 80vh;

  justify-content: space-between;
  align-items: center;


}

.Achievement-screen-header {
  background-color: #F2C6C2;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  min-width: 1000px;

  max-width: 1280px;
  width: 90vw;
  height: 100px;

}

.Achievement-screen-header-title {

  font-size: 50px;
  text-align: center;
  margin: 0 auto;
  /* 중앙정렬 */
  flex: 1;
  /* 확장 */

}

.Achievement-screen-header-closeBtn {
  display: flex;
  justify-content: flex-end;
  /* 우측정렬 */
  
  margin-right: 10px;

  height: 100px;
  align-items: center;
  /* 고정크기 */

}

.Achievement-box {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;


  gap: 15px;

  max-width: 1280px;
  min-width: 1000px;
  width: 90vw;

  max-height: 580px;
  height: 80vh;

  

}

.Achievement-box .Achievement {

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;


  max-width: 229px;
  min-width: 180px;
  max-height: 451px;
  gap: 15px;

  width: 80%;
  height: 80%;

  background-color: #D9D9D9;

}

.Achievement .first {
  background-image: url('../../../src/assets/img/DiaryMainPage/jojang.jpg');
  max-width: 179px;
  width: 80%;
  height: 90%;
  max-height: 181px;
}

.Achievement .second {
  background-image: url('../../../src/assets/img/DiaryMainPage/jojang.jpg');
  max-width: 179px;
  width: 80%;
  height: 90%;
  max-height: 181px;
}

.Achievement .third {
  background-image: url('../../../src/assets/img/DiaryMainPage/jojang.jpg');
  max-width: 179px;
  width: 80%;
  height: 90%;
  max-height: 181px;
}

.Achievement .fourth {
  background-image: url('../../../src/assets/img/DiaryMainPage/jojang.jpg');
  max-width: 179px;
  width: 80%;
  height: 90%;
  max-height: 181px;
}

.Achievement .fifth {
  background-image: url('../../../src/assets/img/DiaryMainPage/jojang.jpg');
  max-width: 179px;
  width: 80%;
  height: 90%;
  max-height: 181px;
}


.Achievement.completed {
  background-color: yellow;
}</style>