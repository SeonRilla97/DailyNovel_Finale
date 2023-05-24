<script setup>
import { onMounted, reactive, ref } from 'vue';
import { useUserDetailsStore } from '../../store/useUserDetailsStore.js';
import { useRoute } from 'vue-router';


const loginuser = useUserDetailsStore();

const member = reactive({
  info: null,
});

const message = ref("");
const image = ref("default.jpg");

let onchanged = ref(false);
let imageInfo = ref(false);
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
      message.value = "현재 입력된 메세지가 없습니다."
    }
    else
      message.value = member.info.message;
    })    
    .catch(error => console.log(error));
}

onMounted(()=>{
  getMemberInfoById(loginuser.id);  
})


function changeImage(e){
  let fileSelector = e.target.nextElementSibling;
  fileSelector.click();
}

async function changed(e){
  console.log("나 발생?")
  let fileSrc = createURL(e.target.files[0]);
  imageInfo.value = fileSrc;
  onchanged.value = true;
  let formData = new FormData();
  formData.append("image", e.target.files[0]);
  formData.append("id", loginuser.id);
  let response = fetch("http://localhost:8080/~~~~~~~~~~~",{
    method : "POST",
    body : formData
  })
}

function createURL(target){
  let objectURL = URL.createObjectURL(target);
  return objectURL;
}

</script>
<template>
  <main class="m-profile-index-container">
    <div class="m-profile-index-profile shadow-1">
      <div class="m-profile-index-profile-imgBox">
        <div class="m-profile-index-profile-img" :style="`background-image: url(http://localhost:8080/image/profile/${image})`" @click="changeImage" v-if="!onchanged"></div>
        <img class="m-profile-index-profile-img" :src="imageInfo" @click="changeImage" v-if="onchanged" />
        <input type="file" class="d-none"  @change="changed"/>
      </div>
      <div class="m-profile-index-profile-messageBox">
        <div class="m-profile-index-profile-message">
          <div class="m-pf-msg-title">상태 메세지</div>
          <div class="m-pf-msg-txt lc-center"><span class="h3">{{ message }}</span></div>
        </div>
      </div>
    </div>
    <div class="m-profile-index-info">
      <div class="m-profile-index-info-detail shadow-1">
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
      <div class="m-profile-index-info-follow shadow-1">
        
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
.m-profile-index-container {
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

.m-profile-index-profile {
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

.m-profile-index-profile-imgBox {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-sizing: border-box;
}

.m-profile-index-profile-img {
  width: 16rem;
  height: 16rem;
  border-radius: 120px;

  background-size: cover;
  background-position: center;
  background-color: aqua;
  background-image: url(http://localhost:8080/image/profile/8.png);
  background-repeat: no-repeat;
}

.m-profile-index-profile-messageBox {
  display: flex;
  justify-content: center;
  align-items: center; 
}

.m-profile-index-profile-message {
  margin: 1rem;
  width:100%;
  height:80%;
  background-color: #F9FAFA;

  border: 1px solid #FCD602;
  border-radius: 8px;

  /* box-shadow: 0px 1px 2px gray; */
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

.m-profile-index-info {
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

.m-profile-index-info-detail {
  border: 1px solid #E6E7E7;
  border-radius: 12px;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;

  background-color: #F9FAFA;
}

.m-profile-index-info-follow {
  border: 1px solid #E6E7E7;
  border-radius: 12px;

  display:grid;
  grid-template-columns: repeat(5, 1fr);

  background-color: #F9FAFA;
}
</style>
