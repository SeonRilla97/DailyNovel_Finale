<script setup>
import quill from './quill.vue';
import quill2 from './quill2.vue';
import MapBox from './MapBox.vue'
import quillCopy from './quill copy.vue';

import { ref,onMounted, onUpdated } from 'vue';


onMounted(() => {
  
  geoFindMe();

});


//날씨관련 객체
  const weatherData = ref("");

  weatherData.value = {
      'city' : '평양',
      'weatherCode': '0',
      'weatherDes': '날씨'
  };

 //현재 위치 받아오기 API
 function geoFindMe() {

    function success(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;
        //날씨 호출
        weather(latitude, longitude);
    }
    function error() {
        console.log("Unable to retrieve your location");
    }

    if (!navigator.geolocation) {
        console.log("Geolocation is not supported by your browser");
    } else {
        console.log("Locating…");
        navigator.geolocation.getCurrentPosition(success, error);
    }
    }

  function weather(latitude, longitude){

    let requestOptions = {
      method: 'GET',
      redirect: 'follow'
      };

      fetch(`http://api.openweathermap.org/data/2.5/forecast?lat=${latitude}&lon=${longitude}&exclude=current&appid=fdbbc96af009ea8344414a270ad5386e&units=metric&lang=kr`, requestOptions)
      .then(response => response.json())
      .then(result => {
          weatherData.value.city = result.city.name;
          weatherData.value.weatherCode = result.list[1].weather[0].id;

          weatherCodeToDesc(result.list[1].weather[0].id);
      })
      .catch(error => console.log('error', error));
  }

    function weatherCodeToDesc(weatherCode){
      console.log(weatherCode);
      console.log(weatherData.value.weatherDes);

      if(weatherCode / 100 == 2)
        weatherData.value.weatherDes = "천둥번개";
      else if(weatherCode / 100 == 3)
        weatherData.value.weatherDes = "천둥번개";
      else if(weatherCode / 100 == 5)
      weatherData.value.weatherDes = "비";
      else if(weatherCode / 100 == 6)
      weatherData.value.weatherDes = "눈";
      else if(weatherCode / 100 == 7)
      weatherData.value.weatherDes = "미세먼지";
      else if(weatherCode / 100 == 8){
        if(weatherCode / 800 == 1)
          weatherData.value.weatherDes = "맑음";
        else 
          weatherData.value.weatherDes = "흐림";
      }
      console.log( weatherData.value.weatherDes);
    }

</script>

<template>

  <div class="editor-wrap"
      @click="onclickedHandler"
      >

    <div class="editor-attribue-box">
      <div class="editor-data">2023년 4월 10일 오후 02시 01분 </div>
      <div class="editor-attribue">#여행</div>
      <div class="editor-attribue">{{weatherData.weatherDes}}</div>
      <div class="editor-attribue">화남</div>
    </div>

    <div class="editor-title"> 
        <header contenteditable="true"  
                class="editor-title-content editor-header"
                @click="editHandler"
                >
          4월 10일의 일기
        </header>
        <div class="editor-share">공유 중</div>
    </div>

    <hr class="editor-title-hr">
    <main 
    class="editor-main"
    @click="editHandler"  
    >
      <!-- <quill2/> -->
      <quill/>
      <MapBox/>
        <!-- <quillCopy/> -->
    </main>
  </div>


</template>

<style scoped>



.editor-wrap{
  width: 70%;
  height: 95%;
  
  /* overflow: scroll; */
  /* overflow: scroll; */
  margin: 15px 40px;
}
.editor-main{

  height: 80%;

  /* height: auto; */
  overflow-y: scroll;
}


.editor-title-content{
    color: #3F3F3F;
    /* font-size: 20px; */
    margin-top: 10px;

    line-height: 1.2;
}

.editor-title-content:focus{
    outline: none;
}

/* placeholader 관련 설정 */

/* .editor.empty:before {
  content: attr(placeholder);
  margin-top: 10px;
  color: #9A9A97;
  font-size: 20px;
} */

/* p#editor::placeholder{
    color: #9A9A97;
    font-size: 15px;
    padding: 0px;
} */



.editor-attribue-box{
    color: #636363;
    font-size: 15px;

    display: flex;
    align-items: center;
}

 .editor-attribue{
    background-color: #F9F4F4;
    color: #3F3F3F;
    margin-left: 20px;
    padding: 6px 16px;
    border-radius: 5px;
 } 




  .editor-template{
    background-color: #F9F4F4;
    
    color: #636363;
    /* font-size: 20px; */
    /* font-weight: bold; */

    display:flex;
    justify-content: center;
    align-items: center;

    margin-left: 30px;
    /* padding: 3px 20px; */
    border-radius: 5px;

    width: 90px;
    height: 30px;

    /* flex-grow: 1; */
    /* grid-row: 1 / 2; */
    /* justify-self: start; */
  }

.editor-title{

    margin-top: 45px;
    color: #252728;
    font-weight: bold;
    display: flex;
    /* grid-template-columns: 1fr 0.5fr 0.5fr; */
}

  .editor-title-content{
        /* flex-grow: 1; */
        
    font-size: 32px;
    flex-grow: 1000;
      /* line-height: 1.2; */
      /* font-size: 32px; */

      /* grid-row: 1 / 2; */
    }
    .editor-title-content:focus{
        outline: none;
    }


  .editor-share{
      /* box-sizing: border-box; */
      /* width: 60px;
      height: 24px; */

      /* background-color: #191F78;
      
      padding: 4.5px 13px;
      border: 1px solid #191F78;
      border-radius: 2px;

      color: #FCFCFC;
      font-size : 12px;
      text-align: center; */

      justify-self: center;
      align-self: center;
      width:60px;
      height:24px;

      display:flex;
      justify-content: center;
      align-items: center;
      color:#fcfcfc;
      font-size:12px;
      border-radius: 5px;
      background-color: #191F78;

      flex-grow: 1;


      /* grid-row: 1 / 2; */
  }

    .editor-share.active{
      background-color: transparent;
        border: 1px solid #8391FC;
        color: #8391FC;
    }


.editor-title-hr{

  background-color: black;
  height: 1px;
}

</style>