<script setup>
import { onMounted, reactive, ref } from 'vue';

// let message = "오늘 선릴라의 점심은 무엇일까요? 길이 길어지게 되면 두줄로 늘어나나요? 나중에 상태매세지 제한도 걸어야겟죠~";

const props = defineProps({
  userId: {type: Number,
    required: true}
})
console.log(props.userId);
getMemberInfoById(props.userId);

onMounted(()=>{
  
})

const member = reactive({
  info: null,
});

const message = ref();
const image = ref("default.jpg");

async function getMemberInfoById(id) {
    await fetch(`http://localhost:8080/members/mInfo?id=${id}`,
    {
      method: "GET",
      headers: {
        // "Accept": "application/json",
        "Content-type": "application/json"
      },      
    })
    .then(response => response.json())
    .then((data) => 
    {member.info = data;
    image.value = member.info.image;
    console.log(image.value);
    if(member.info.message == null){
      message.value = "상태메세지가 없습니다."
    }
    else
      message.value = member.info.message;
    })    
    .catch(error => console.log(error));
}
</script>
<template>
  <main class="m-follow-index-container">
    <div class="m-follow-index-profile shadow-1">
      <div class="m-follow-index-profile-imgBox">
        <div class="m-follow-index-profile-img" :style="`background-image: url(http://localhost:8080/image/profile/${image})`"></div>
      </div>
      <div class="m-follow-index-profile-messageBox">
        <div class="m-follow-index-profile-message">
          <span class="h3">{{ message }}</span>
        </div>
      </div>
    </div>
    <div class="m-follow-index-info">
      <div class="m-follow-index-info-detail shadow-1">
        <div class="m-pf-index-count">
          <div class="m-pf-index-count-title lc-center"><span>하루를 정리한 수 </span></div>
          <div class="m-pf-index-count-num lc-center"><span style="font-size: 3rem;">{{ 12 }} </span></div>          
        </div>
        <div class="m-pf-index-count">
          <div class="m-pf-index-count-title lc-center">
            <span>공유한 일기 수</span>
          </div>
          <div class="m-pf-index-count-num lc-center">
            <span style="font-size: 3rem;">{{ 12 }}</span>
          </div>                    
        </div>
        <div class="m-pf-index-count">
          <div class="m-pf-index-count-title lc-center">
            <span>만든 컬렉션의 수</span>
          </div>
          <div class="m-pf-index-count-num lc-center">
            <span style="font-size: 3rem;">{{ 12 }}</span>
          </div>                    
        </div>               
    
      </div>
      <div class="m-follow-index-info-follow shadow-1">
        
      </div>
    </div>
  </main>
</template>
<style scoped>
.m-pf-index-count{
  width:90%;
  height: 90%;
  /* background-color: red; */

  border: 2px solid #FCD602;
  border-radius: 8px;
  box-shadow: 0px 1px 3px #FCD602;

  justify-self: center;
  align-self: center;

  display:flex;
  flex-direction: column;
  justify-content: space-evenly;
  

}
.m-pf-msg-title{
  width:100%;
  height:20%;
  background-color: #FCD602;
  display:flex;
  justify-content: center;
  align-items: center;

  box-shadow: 0px 2px 5px #FCD602;
}

.m-pf-msg-txt{
  width:100%;
  height:80%;
  display:flex;
  justify-content: center;
  align-items: center;
}
.m-follow-index-container {
  height: 100%;
  padding: 0.5rem;
  display: grid;
  grid-template-areas:
    "profile info info"
    "profile info info"
    "profile info info";
  grid-template-columns: 1fr 1.2fr 1.2fr;
  grid-template-rows: 1fr 1fr 1fr;

  box-sizing: border-box;
}

.m-follow-index-profile {
  margin: 1rem;
  grid-area: profile;

  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 2fr 1fr;

  background-color: #F9FAFA;

  border: 1px solid #E6E7E7;
  border-radius: 12px;

  box-sizing: border-box;
}

.m-follow-index-profile-imgBox {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.m-follow-index-profile-img {
  width: 16rem;
  height: 16rem;
  border-radius: 120px;

  background-size: cover;
  background-position: center;
  background-color: white;
  background-image: url(http://localhost:8080/image/profile/default.jpg);
  background-repeat: no-repeat;
}

.m-follow-index-profile-messageBox {
  display: flex;
  justify-content: center;
  align-items: center;
}

.m-follow-index-profile-message {
  margin: 1rem;
}

.m-follow-index-info {
  margin: 1rem;
  grid-area: info;
  /* border: 2px solid black; */
  border-radius: 12px;

  display: grid;
  grid-template-areas:
    "detail"
    "follow";
  grid-template-rows: 2fr 1fr;
  gap: 1rem;
}

.m-follow-index-info-detail {
  border: 1px solid #E6E7E7;
  border-radius: 12px;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;

  background-color: #F9FAFA;
}

.m-follow-index-info-follow {
  border: 1px solid #E6E7E7;
  border-radius: 12px;

  background-color: #F9FAFA;
}
</style>
