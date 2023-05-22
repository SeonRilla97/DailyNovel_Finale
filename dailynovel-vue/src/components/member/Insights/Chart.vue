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


    <div class="chart-box">

      <div class="chart">
        <div>
          <canvas id="feeling"></canvas>
        </div>
        <div>기분</div>
      </div>

      <div class="chart">
        <div>
          <canvas id="honesty"></canvas>
        </div>

        <div>솔직함</div>
      </div>

      <div class="chart">
        <div>
          <canvas id="Tag" height="300px"></canvas>
        </div>
        <div>태그</div>

      </div>

    </div>
  </main>
</template>


  

<script setup>
import { onMounted } from 'vue';
import Chart from 'chart.js/auto';
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { useUserDetailsStore } from "../../store/useUserDetailsStore.js";

// 사용할 변수를 먼저 정의합니다.
let userDetails = useUserDetailsStore();
Chart.register(ChartDataLabels);

// Tag 차트를 생성하는 함수입니다.
function TcreateChart(map) {
  const data = {
    labels: map.tags,
    datasets: [{
      data: map.counts,
      fill: true,
      backgroundColor: 'rgba(54, 162, 235, 0.2)',
      borderColor: 'rgb(54, 162, 235)',
      pointBackgroundColor: 'rgb(54, 162, 235)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgb(54, 162, 235)'
    }]
  };
  const Tconfig = {
    type: 'bar',
    data,
    options: {
      maintainAspectRatio: false,
      responsive: false,
      plugins: {
        legend: {
          display: false,
          fontSize: 32,
          position: 'bottom'
        }
      }
    },
  };

  const myChart = new Chart(
    document.getElementById('Tag'),
    Tconfig
  );
}

// Feeling 차트를 생성하는 함수입니다.
function FcreateChart(map) {
  const data = {
    labels: map.feelings,
    datasets: [{
      data: map.counts,
      backgroundColor: [
        '#fecd50',
        '#a0d4cd',
        '#ef7d00',
        '#e42313',
        '#dca8ad',
        '#b482c2',
        '#68739b',
        '#6495ed',
        '#d45087'
      ],
      borderColor: [
        '#fecd50',
        '#a0d4cd',
        '#ef7d00',
        '#e42313',
        '#dca8ad',
        '#b482c2',
        '#68739b',
        '#6495ed',
        '#d45087'
      ],
      borderWidth: 1,
    }]
  };

  const Fconfig = {
    type: 'doughnut',
    data,
    options: {
      responsive: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: {
            maxWidth: 300,
            padding: 15,
            fontSize: 32,

          }
        }
      }
    }
  };

  const myChart = new Chart(
    document.getElementById('feeling'),
    Fconfig
  );
}

// Honesty 차트를 생성하는 함수입니다.
function HcreateChart(map) {
  const data = {
    labels: map.honestyRanges,
    datasets: [{
      data: map.counts,
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(75, 192, 192)',
        'rgb(255, 205, 86)',
        'rgb(159, 162, 235)',
        'rgb(54, 162, 235)'
      ],
      borderColor: [
        'rgb(255, 99, 132)',
        'rgb(75, 192, 192)',
        'rgb(255, 205, 86)',
        'rgb(159, 162, 235)',
        'rgb(54, 162, 235)'
      ],
      borderWidth: 1,
    }]
  };

  const Hconfig = {
    type: 'polarArea',
    data,
    options: {
      responsive: false,
      plugins: {
        legend: {
          position: 'bottom',
          labels: {
            fontSize: 32,
            padding: 15
          }
        }
      }
    },
  };

  const myChart = new Chart(
    document.getElementById('honesty'),
    Hconfig
  );
}

// 데이터를 가져와 차트를 생성하는 함수입니다.
function loadData() {
  // Tag 데이터 가져오기
  var TrequestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  fetch(`http://localhost:8080/insights/Tag?memberId=${userDetails.id}`, TrequestOptions)
    .then(response => response.json())
    .then(data => {
      let tags = [];
      let counts = [];

      for (const key in data) {
        tags.push(key)
        counts.push(data[key]);
      }

      const map = {
        tags,
        counts
      };

      console.log("tags[0]:", tags[0]);
      TcreateChart(map);
    });

  // Feeling 데이터 가져오기
  var FrequestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  fetch(`http://localhost:8080/insights/Feeling?memberId=${userDetails.id}`, FrequestOptions)
    .then(response => response.json())
    .then(data => {
      let feelings = [];
      let counts = [];

      for (const key in data) {
        feelings.push(key);
        counts.push(data[key]);
      }

      const map = {
        feelings,
        counts
      };

      FcreateChart(map);
    });

  // Honesty 데이터 가져오기
  var HrequestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  fetch(`http://localhost:8080/insights/Honesty?memberId=${userDetails.id}`, HrequestOptions)
    .then(response => response.json())
    .then(data => {
      let honestyRanges = [];
      let counts = [];

      for (const key in data) {
        honestyRanges.push(key);
        counts.push(data[key]);
      }

      const map = {
        honestyRanges,
        counts
      };

      HcreateChart(map);
    });
}

// 컴포넌트가 마운트되었을 때 데이터를 가져오고 차트를 생성합니다.
onMounted(() => {
  loadData();
});
</script>

<style>
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

.select-box>* {
  flex: 0.1 0.1 auto;
  font-size: 30px;
  font-weight: bold;

}

.Move:hover {
  color: rgb(226, 226, 99);
  text-decoration: underline;
}


.chart-box {
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: wrap;
  gap: 15px;
  width: 100%;
  height: 100%;
  overflow: scroll;


}

.chart-box .chart {

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;


  max-width: 320px;
  max-height: 451px;
  gap: 30px;

  width: 50%;
  height: 50%;

  background-color: #FFF;

}

.chart-box .chart #feeling,
.chart-box .chart #honesty,
.chart-box .chart #Tag {


  max-width: 100%;
  max-height: 100%;
  border: 1px solid #999999;
  padding: 10px;
  border-radius: 15px;

}
</style>