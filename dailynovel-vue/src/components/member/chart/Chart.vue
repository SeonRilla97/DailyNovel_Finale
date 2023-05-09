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
        <h1 class="Achievement-screen-header-title">돌아보기</h1>
        <a href="#" class="Achievement-screen-header-closeBtn"><span class="material-symbols-outlined">close</span></a>
      </header>



      <main class="chart-box">

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
            <canvas id="Template"></canvas>
          </div>
          <div>템플릿</div>

        </div>




      </main>




    </section>




  </div>
</template>


  

<script>
import Chart from 'chart.js/auto';
import ChartDataLabels from 'chartjs-plugin-datalabels';
Chart.register(ChartDataLabels);



export default {
  name: 'FeelingChart',

  data() {
    return {
      TopPercent: '',
      Percent: [], // 소문자로 변경
      extractedData: [],
      Value: [],
    };
  },
  methods: {


    Tload() {
      Chart.register(ChartDataLabels);
      var myHeaders = new Headers();
      myHeaders.append("Cookie", "JSESSIONID=01C4810D9CC26D4924510C7E51C16F69");


      var TrequestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
      };

      fetch("http://localhost:8080/analysis/how?count", TrequestOptions)
        .then(response => response.json())
        .then(data => {
          const proxyArray = data;
          console.log(data);
          const name = proxyArray.map(item => item.name);
          const count = proxyArray.map(item => item.count);

          this.TcreateChart(name, count);
        });
    },

    TcreateChart(name, count) {
      const data = {
        labels: name,
        datasets: [{
          data: count,
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
        type: 'radar',
        data,
        options: {
          maintainAspectRatio: false,
          responsive: true,
          plugins: {
            legend: {
              display: false,
              position: 'bottom'
            }
          }
        },
      };

      // render init block
      const myChart = new Chart(
        document.getElementById('Template'),
        Tconfig,
      );
    },

    Hload() {
      Chart.register(ChartDataLabels);
      var myHeaders = new Headers();
      myHeaders.append("Cookie", "JSESSIONID=00FBFDB24A1BDD060C55F8F3B78E8B1F");

      var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
      };

      fetch("http://localhost:8080/analysis/honesty?honestyRange=&count", requestOptions)
        .then(response => response.json())
        .then(data => {
          const proxyArray = data;
          const range = proxyArray.map(item => item.honestyRange);
          const count = proxyArray.map(item => item.count);


          this.HcreateChart(range, count);
        });
    },


    Fload() {
      Chart.register(ChartDataLabels);
      var myHeaders = new Headers();
      myHeaders.append("Cookie", "JSESSIONID=045AC4FE93F9AA0C9B08C0ECEAA1986F");

      var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
      };

      fetch("http://localhost:8080/analysis/feeling-chart?name&frequency", requestOptions)
        .then(response => response.json())
        .then(data => {
          const proxyArray = data;
          const name = proxyArray.map(item => item.name);
          const frequency = proxyArray.map(item => item.frequency);

          this.FcreateChart(name, frequency);
        });
    },

    FcreateChart(name, frequency) {
      // setup
      const data = {
        labels: name,
        datasets: [{
          data: frequency,
          backgroundColor: [
            '#fecd50', //(Navy blue)
            '#a0d4cd', //(Medium purple)
            '#ef7d00', //(Magenta)
            '#e42313', //(Vivid red)
            '#dca8ad', //(Bright yellow)
            '#b482c2', //(Salmon)
            '#68739b', //(Light green)
            '#6495ed', //(Cornflower blue)
            '#d45087' //(Pinkish red)
          ],
          borderColor: [
            '#fecd50', //(Navy blue)
            '#a0d4cd', //(Medium purple)
            '#ef7d00', //(Magenta)
            '#e42313', //(Vivid red)
            '#dca8ad', //(Bright yellow)
            '#b482c2', //(Salmon)
            '#68739b', //(Light green)
            '#6495ed', //(Cornflower blue)
            '#d45087' //(Pinkish red)
          ],
          borderWidth: 1,
        }]
      };


      // config 
      const Fconfig = {
        type: 'doughnut',
        data,
        options: {
          plugins: {
            legend: {
              position: 'bottom',
              labels: {
                maxWidth: 200,
                padding: 15
              }
            }
          }
        },
      };


      // render init block
      const myChart = new Chart(
        document.getElementById('feeling'),
        Fconfig,
      );
    },

    HcreateChart(range, count) {
      // setup
      const data = {
        labels: range,
        datasets: [{
          data: count,
          backgroundColor: [
            'rgb(255, 99, 132)', // 0 ~ 20
            'rgb(75, 192, 192)', // 20 ~ 40%
            'rgb(255, 205, 86)', // 40 ~ 60%
            'rgb(159, 162, 235)', // 60 ~ 80
            'rgb(54, 162, 235)' // 80 ~ 100
          ],
          borderColor: [
            'rgb(255, 99, 132)', // 0 ~ 20
            'rgb(75, 192, 192)', // 20 ~ 40%
            'rgb(255, 205, 86)', // 40 ~ 60%
            'rgb(159, 162, 235)', // 60 ~ 80
            'rgb(54, 162, 235)' // 80 ~ 100
          ],
          borderWidth: 1,
        }]
      };
      // config 
      const Hconfig = {
        type: 'polarArea',
        data,
        options: {
          plugins: {
            legend: {
              position: 'bottom',
              labels: {
                maxWidth: 200,
                padding: 15
              }
            }
          }
        },
      };
      // render init block
      const myChart = new Chart(
        document.getElementById('honesty'),
        Hconfig

      )
    }

  },
  mounted() {
    this.Fload();
    this.Hload();
    this.Tload();

  },

  components: {
    
  },

}
</script>

<style>
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

.chart-box {
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

.chart-box .chart {

  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;


  max-width: 320px;
  min-width: 300px;
  max-height: 451px;
  gap: 15px;

  width: 80%;
  height: 80%;

  background-color: #FFF;

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
}
</style>