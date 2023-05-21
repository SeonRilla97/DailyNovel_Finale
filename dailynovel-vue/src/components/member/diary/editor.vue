<script setup>
import quill from './quill.vue';
import quill2 from './quill2.vue';
import quillCopy from './quill copy.vue';

import { ref,onMounted, onUpdated, defineProps } from 'vue';
import MapBox from './MapBox.vue';

const props = defineProps({
    'isAdd' : '',
    'isLoad' : '',
    // 'current' : ''
});



let weatherDone = false;
let ControllerAdd = props.isAdd;

onMounted(() => {


});


// loadDiary() 무한 반복 막기위한 선언
let previousLoad = -1;

onUpdated(() => {
  // console.log(current);

  ControllerAdd = props.isAdd
  // console.log(ControllerAdd);

    //update 이중 방지용
  if( ControllerAdd == true && weatherDone == false){
    let promise = geoFindMe();

    promise
    .then(response => weather(response[0],response[1]))
    .then(result => addDiary(result))
    .then(success => console.log(success));
  }

  // console.log(previousLoad, props.isLoad);
  if(previousLoad != props.isLoad){
    loadDiary(props.isLoad);
    previousLoad = props.isLoad
  }

})

function getDate(gotdate){

  //프로토 타입
  Date.prototype.amPm = function() {
      let h = this.getHours() < 12 ? "오전" : "오후";
      return h;
  }
  Date.prototype.getHoursAmPm = function() {
      let h = this.getHours() < 12 ? this.getHours() : this.getHours() - 12;
      return h;
  }

  console.log(gotdate);
  // let date = new Date();
  let date = gotdate;
  console.log(date.getFullYear());
  titleDate = `${date.getMonth()+1}월 ${date.getDate()}일`;
  return `${date.getFullYear()}년 ${date.getMonth()+1}월 ${date.getDate()}일 ${date.amPm()} ${date.getHoursAmPm()}시 ${date.getMinutes()}분`;
}

  let titleDate = "";
  const mapToggle = ref(false);
  const imageToggle = ref(false);


//현재시간 받아오기
  const time = getDate(new Date);
  

//날씨관련 객체
  const weatherData = ref("");

  weatherData.value = {
      'city' : '평양',
      'weatherCode': '0',
      'weatherDes': '날씨'
  };

const diaryRef = ref("");

let diaryObj = {
  "id": null,
  "member_id": 4,
  "title": titleDate+'의 일기',
  "content": "",
  "weather": weatherData.value.weatherDes,
  "feeling": "",
  "honesty": "",
  "tag": "",
  "regDate": getDate(new Date),
  "lat": 38,
  "lng": 128
};
diaryRef.value = diaryObj;
console.log(diaryRef.value);

 //현재 위치 받아오기 API

// 지도 설정한 좌표값 얻어오기
function coor(coor) {

  console.log(coor)
}

 function geoFindMe() {

  return new Promise(function (resolve){
    function success(position) {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;
        //날씨 호출
        // weather(latitude, longitude);
        resolve([latitude,longitude]);
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
  });
  }

  function weather(latitude, longitude){

    return new Promise(function (resolve){

    let requestOptions = {
      method: 'GET',
      redirect: 'follow'
      };

      fetch(`http://api.openweathermap.org/data/2.5/forecast?lat=${latitude}&lon=${longitude}&exclude=current&appid=fdbbc96af009ea8344414a270ad5386e&units=metric&lang=kr`, requestOptions)
      .then(response => response.json())
      .then(result => {
          weatherData.value.city = result.city.name;
          weatherData.value.weatherCode = result.list[1].weather[0].id;

          // console.log( result.list[1].weather[0].id);
          weatherDone = true;
          return result.list[1].weather[0].id;
          // weatherCodeToDes(result.list[1].weather[0].id);
      })

        //날씨 코드에서 명칭으로 변경 7가지 경우의 수
      .then(code =>{
        let weatherCodeConvert = parseInt(code /= 100);

        if(weatherCodeConvert == 2)
          weatherData.value.weatherDes = "천둥번개";
        else if(weatherCodeConvert == 3)
          weatherData.value.weatherDes = "천둥번개";
        else if(weatherCodeConvert == 5)
        weatherData.value.weatherDes = "비";
        else if(weatherCodeConvert == 6)
        weatherData.value.weatherDes = "눈";
        else if(weatherCodeConvert == 7)
        weatherData.value.weatherDes = "미세먼지";
        else if(weatherCodeConvert == 8){
          if(code / 800 == 1)
            weatherData.value.weatherDes = "맑음";
          else 
            weatherData.value.weatherDes = "흐림";
        }
        resolve(weatherData.value.weatherDes);
        return true;
      })
      .catch(error => console.log('error', error));
    })
  };


console.log(diaryObj);

const addDiary = function(isAdd){
  return new Promise(function(resolve, reject){

  // if(isAdd == true){
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    diaryObj.weather = weatherData.value.weatherDes;
    let raw = JSON.stringify(diaryObj);

    let requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };

    fetch("http://localhost:8080/diary", requestOptions)
      .then(response => response.text())
      .then(result => console.log(result))
      .catch(error => console.log('error', error));
    console.log("완료");
  // }
    resolve("seucess");
})};

const loadDiary = function(diaryId){
  return new Promise(function(resolve){

  let requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  fetch(`http://localhost:8080/diary/${diaryId}`, requestOptions)
    .then(response => response.json())
    .then(result => {
      diaryRef.value = result;
      diaryRef.value.regDate = getDate(new Date(result.regDate));
    })
    .catch(error => console.log('error', error));
  })
}



</script>

<template>

  <div class="editor-wrap"
      @click="onclickedHandler"
      >

    <div class="editor-attribue-box">
      <!-- <div class="editor-data">2023년 4월 10일 오후 02시 01분 </div> -->
      <div class="editor-data">{{diaryRef.regDate}}</div>
      <div class="editor-attribue">{{diaryRef.weather}}</div>
      <div class="editor-attribue">{{'#'+diaryRef.tag}}</div>
      <div class="editor-attribue">{{diaryRef.feeling}}</div>
    </div>

    <div class="editor-title"> 
        <header contenteditable="true"  
                class="editor-title-content editor-header"
                @click="editHandler"
                >
          <!-- {{titleDate+'의 일기'}} -->
          {{ diaryRef.title }}
        </header>
        <div class="editor-share">공유 중</div>
    </div>

    <hr class="editor-title-hr">

    <div class="editor-main-quill">
      <main 
      class="editor-main"
      @click="editHandler"  
      >
        <!-- <quill2/> -->
        <quill
          class="editor-quill"
          :content = "diaryRef.content"
        />
          <!-- <quillCopy/> -->

      </main>

      <div 
        class="editor-sub-button editor-sub">
        
        <!-- @click.prevent="mapToggleHandler"> -->
        <button
        @click="imageToggle = !imageToggle"
        >
        사진추가
      </button>
      <button
          @click="mapToggle = !mapToggle"
          >
        지도추가
      </button>

      </div>

      <div class="img-map-container">
        <div
          v-if="imageToggle"
          class="editor-image editor-sub">
          이미지를 넣어넣어 놀자놀자
        </div>


        <!-- 맵 삽입칸 -->
        <div
          v-if="mapToggle"
          class="mapToggle-map editor-sub">
          <MapBox :coor="coor" @coor="coor"/>
        </div>
      </div>

    </div>  
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


.editor-main-quill{
    height: 80%;
    overflow-y: scroll;

    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 40px 1fr 1fr;
}

.editor-main{

  box-sizing: border-box;

  width: 100%;
  height: 95%;
  /* overflow-y: scroll; */

  /* height: auto; */

  /* display: grid; */
  grid-row: 1/4; 
  grid-column: 1/2;

  margin: 10px 10px 0 0;
}

.editor-sub{
  margin: 10px 0 0 10px;
  border: 1px solid #E3E6E2;
  border-radius: 5px;
}

.editor-sub-button{
  height: 40px;

  grid-row: 1/2;
  grid-column: 2/3;

  display: flex;
  justify-content: start;
  align-items: center;

  border: none;
}

  .editor-sub-button button{
    /* border: 1px solid black; */
    border-radius: 5px;
    /* padding: 5px 20px; */
    background-color: #F9F4F4;
    font-size: 17px;

    width: 100px;
    height: 30px;

    margin-left: 10px;
    
  }

.editor-image{
  /* grid-row: 2/3; */
  grid-column: 2/3;

  /* margin-top: 10px; */
  height: 200px;
}
.editor-map{
  /* grid-row: 3 / 4; */
  grid-column: 2/3;

  /* margin-top: 10px; */

  height: 200px;
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


.img-map-container{
  overflow-y: scroll;
}

</style>