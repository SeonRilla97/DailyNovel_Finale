<template>
  
  <main class="screen">

    <div class="select-box">
      <router-link to="/member/achievement"><p class="Move">업적</p></router-link>
      <router-link to="/member/chart"><p class="Move">차트</p></router-link>
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
        <canvas id="Template"></canvas>
      </div>
      <div>템플릿</div>

    </div>

    </div>
  </main>


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

    moveController(){

    },

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
          responsive: false,
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
          responsive: false,
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
          responsive: false,
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


.chart-box {
  display: flex;
  justify-content: space-around;
  align-items: center;
  flex-wrap: wrap;
  gap: 15px;
  width: 100%;
  height: 100%;

  
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
.chart-box .chart #Template {


  max-width: 100%;
  max-height: 100%;
  border: 1px solid #999999;
  padding: 10px;
  border-radius: 15px;

}


</style>