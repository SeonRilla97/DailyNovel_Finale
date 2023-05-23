<script setup>
import { ref, watchEffect , reactive} from "vue";
import Modal from "./LoginModal.vue";
import { useRoute, useRouter } from "vue-router";

let router = useRouter();
let route =  reactive(useRoute()); //라우팅의 정보를 가져다 주는애


let phoneNumber = ref("");

let content = ref("");

let phoneNumberVerification = /^(\d{3})(\d{3,4})(\d{4})$/;
let showModal = ref(false);


async function showHandler(event) {
  if (event == "이메일찾기") {
    let response = await fetch(
      "http://localhost:8080/users/PhoneNumberCheck?phoneNumber=" + phoneNumber.value
    );
    let data = await response.text();

    if (data === null) {
      content.value = "가입되어 있지 않는 전화번호 입니다.";

    } else 
      content.value ="닉네임:"+data;

  }  
  else{

  }
  showModal.value = true;
}

function dlgHandler() {
  showModal.value = false;
}

/**let checkphone = phoneNumber.value.replace(/\d/g, "");
let formattedPhone = checkphone.replace(phoneNumberVerification, "$1-$2-$3");*/
function phoneNumberChange() {
  let phoneNumberCheck = phoneNumber.value.replace(/\D/g, "");
  let formattedPhone = phoneNumberCheck.replace(phoneNumberVerification, "$1-$2-$3");

  return formattedPhone;
}
function phoneNumberInput() {
  let formattedPhoneNumber = phoneNumberChange();
  phoneNumber.value = formattedPhoneNumber;
}

function limitInput(inputValue, maxLength) {
  if (inputValue) {
    return inputValue.toString().slice(0, maxLength);
  }
  return '';
}

</script>
<template>
  <div class="container-1-nmg container-s1">
    <div class="main lc-vertical-alignment">
      <div class="text-1">계정찾기</div>

      <div class="form-1">
        <div class="text-2">
          <span class="essential-color">*</span>
          필수입력사항
        </div>
        <form
          class="mgt-1 lc-vertical-alignment"
          enctype="application/x-www-form-urlencoded">
          <!--전화 번호-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="phone-number">
                전화번호
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input
                id="phone-number"
                class="input-1"
                @input="phoneNumberInput()"
                type="text"
                tabindex="0"
                placeholder="숫자만 입력해주세요"
                required
                v-model="phoneNumber"
                maxlength='13'
              />
            </div>
            <div class="mgl-3">
              <button
                type="button"
                :class="[!mailcheckBtn ? 'btn-1' : 'btn-1-off']"
                @click="showHandler('이메일찾기')"
                :disabled="mailcheckBtn == true"
              >
              이메일 찾기
              </button>
            </div>
          </div>
        </form>
      </div>
    <div class="div-btn">
        <router-link class="btn-2" to="/login">
          <span class="text-5" > 로그인</span>
        </router-link> 
        </div>
    </div>
  </div>
  <Modal :content="content" :show="showModal" @ok="dlgHandler"> </Modal>
</template>

<style scoped>
@import url("/css/reset.css");
@import url("/css/style.css");
@import url("/css/layout.css");
@import url("/css/box.css");
@import url("/css/button.css");

.container-s1 {
  min-width: 1050px;
  margin-top: 50px;
  margin-bottom: 60px;
  background-color: #fff;
}

.form-1 {
  width: 640px;
  margin: 0px auto;
  box-sizing: border-box;
}

.display-flex {
  width: 100%;
  display: flex;
  flex-direction: row;
}

.text-1 {
  margin-bottom: 50px;
  font-size: 32px;
  line-height: 35px;
  font-weight: 500;
  text-align: center;
  letter-spacing: -1px;
  color: #333;
}

.text-2 {
  padding-bottom: 10px;
  border-bottom: 2px solid rgb(51, 51, 51);
  font-size: 12px;
  color: rgb(102, 102, 102);
  line-height: 17px;
  text-align: right;
}

.text-5 {
  margin-top: 18px;
  display: inline-block;
  font-size: 20px;
  font-weight: 500;
}

.btn-1 {
  width: 120px;
  font-weight: 500;
  font-size: 14px;
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  height: 52px;
  border-radius: 3px;
  color: rgb(235, 155, 56);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(235, 155, 56);
  margin: 0;
}

.btn-1-off {
  width: 120px;
  font-weight: 500;
  font-size: 14px;
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  height: 52px;
  border-radius: 3px;
  color: rgb(94, 94, 94);
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(94, 94, 94);
  margin: 0;
}

.div-btn {
  display: flex;
  -moz-box-pack: center;
  justify-content: center;
  border-top: 1px solid rgb(247, 247, 247);
  padding: 40px 0px;
}

.btn-2 {
  display: block;
  padding: 0px 10px;
  text-align: center;
  overflow: hidden;
  width: 240px;
  height: 56px;
  border-radius: 3px;
  color: rgb(255, 255, 255);
  background-color: rgb(235, 155, 56);
  border: 0px none;
}

.div-input {
  position: relative;
  height: 52px;
}

.div-input-2 {
  position: relative;
  height: 52px;
  border-radius: 4px;
  border: 1px solid rgb(221, 221, 221);
}

.div-label {
  width: 139px;
  padding-top: 20px;
}

.label-1 {
  font-weight: 500;
  color: rgb(51, 51, 51);
  line-height: 20px;
}

.input-1 {
  width: 360px;
  height: 52px;
  padding: 0px 11px 1px 15px;
  border-radius: 4px;
  border: 1px solid rgb(221, 221, 221);
  font-weight: 400;
  font-size: 16px;
  line-height: 1.5;
  color: rgb(51, 51, 51);
  outline: none;
  box-sizing: border-box;
}

.input-1-noborder {
  width: 360px;
  height: 52px;
  padding: 0px 11px 1px 15px;
  font-weight: 400;
  font-size: 16px;
  line-height: 1.5;
  outline: none;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center; /* 체크박스와 라벨의 수직 정렬을 위해 추가 */
}

[type="radio"] {
  vertical-align: middle;
  appearance: none;
}

.input-1-noborder input[type="radio"] {
  margin: 0 5px; /* 원하는 여백을 설정하세요 */
  transform: scale(1.5); /* 원하는 크기를 설정하세요 */
  vertical-align: middle;
  appearance: none;
  border: max(1px, 0.1em) solid rgb(235, 155, 56);
  border-radius: 50%;
  width: 1em;
  height: 1em;
}

.input-1-noborder input[type="radio"]:checked {
  background: rgb(235, 155, 56); /* 선택 시 원하는 배경색을 지정하세요 */
}

.input-1-noborder input[type="radio"]:checked + label {
  color: rgb(235, 155, 56); /* 선택 시 원하는 텍스트 색상을 지정하세요 */
}

.input-1-ok {
  width: 360px;
  height: 52px;
  padding: 0px 11px 1px 15px;
  border-radius: 4px;
  border: 1px solid rgb(176, 246, 177);
  font-weight: 400;
  font-size: 16px;
  line-height: 1.5;
  color: rgb(51, 51, 51);
  outline: none;
  box-sizing: border-box;
}

.input-1-no {
  width: 360px;
  height: 52px;
  padding: 0px 11px 1px 15px;
  border-radius: 4px;
  border: 1px solid rgb(235, 143, 143);
  font-weight: 400;
  font-size: 16px;
  line-height: 1.5;
  color: rgb(51, 51, 51);
  outline: none;
  box-sizing: border-box;
}

.input-2 {
  width: 116px;
  height: 52px;
  padding: 0px 11px 1px 15px;
  font-weight: 400;
  font-size: 16px;
  line-height: 1.5;
  color: rgb(51, 51, 51);
  outline: none;
  box-sizing: border-box;
  text-align: center;
}

.essential-color {
  color: rgb(238, 106, 123);
}

/*  추가한자: 이동형 사유:location 수직 정렬이 필요했기 때문에 정렬정중앙 정렬이 아님 */
.lc-vertical-alignment {
  display: flex;
  flex-direction: column;
  align-items: center;
}

/*  추가한자: 이동형 사유:location 수평 정렬이 필요했기 때문에 정렬정중앙 정렬이 아님 */
.lc-horizontal-alignment {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.birth-deco::after {
  content: "/";
  font-size: 14px;
  color: rgb(204, 204, 204);
  text-align: center;
  line-height: 40px;
}

.color-red {
  color: red;
}

.color-green {
  color: greenyellow;
}

.color-yellow {
  color: yellow;
}
</style>
