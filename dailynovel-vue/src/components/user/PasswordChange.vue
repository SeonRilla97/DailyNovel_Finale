<script setup>
import { ref } from "vue";
import Modal from "./LoginModal.vue";
import { useRoute, useRouter } from "vue-router";
import { useUserDetailsStore } from "../store/useUserDetailsStore";

let password = ref("");
let passwordCheck = ref("");
let content = ref("");
let passwordVerificationStrong = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{10,25}$/;
let passwordVerificationMiddle = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
let showModal = ref(false);
let passwordVerificationStrongResult = ref(false);
let passwordVerificationMiddleResult = ref(false);
let passwordVerifyResult = ref(false);
let userDetail = useUserDetailsStore();
let router = useRouter();
async function showHandler(event) {
  let email = userDetail.email
  if(event == "비밀번호변경하기") {
     console.log(email);
        await fetch("http://localhost:8080/members/passwordChange", {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
        email: email,
        password: password.value,
      }),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data)
        if(data ==="true"){
        // 응답 처리
        content.value = "비밀번호 변경하기에 성공했습니다.";
        }
        else if(data ==="false")
        content.value = "비밀번호 변경하기에 실패했습니다.";
        else
        content.value = "서버에 문제가 있습니다 잠시후 시도해주세요";

      })
  }

  showModal.value = true;
}
function dlgHandler() {
  showModal.value = false;
}

//비밀번호 정규식
function passwordVerification() {
  if (password.value.match(passwordVerificationStrong)) {
    passwordVerificationStrongResult.value = true;
  } else if (password.value.match(passwordVerificationMiddle)) {
    passwordVerificationMiddleResult.value = true;
  } else {
    passwordVerificationMiddleResult.value = false;
    passwordVerificationStrongResult.value = false;
  }
}

//비밀번호 일치
function passwordverify() {
  if (password.value === passwordCheck.value) {
    passwordVerifyResult.value = true;
  } else passwordVerifyResult.value = false;
}


</script>
<template>
  <div class="container-1-nmg container-s1">
    <div class="main lc-vertical-alignment">
      <div class="text-1">비밀번호 재설정<br><br> 
      </div>
      
      <div>
      <span class="text-2-no-magin">

        「&nbsp;
         회원님의 개인정보를 안전하게 보호하고,<br>&nbsp; 개인정보 도용으로 인한 피해를 막기 위해<br>
        &nbsp; 90일 이상 비밀번호를 변경하지 않은 경우<br>  &nbsp;&nbsp;비밀번호 변경을 권장하고 있습니다. &nbsp; &nbsp; &nbsp; &nbsp;
                                           」
</span>
</div>
      <div class="form-1">
        <div class="text-2">
          <span class="essential-color">*</span>
          필수입력사항
        </div>
        <form
          class="mgt-1 lc-vertical-alignment"
          enctype="application/x-www-form-urlencoded"
        >
          <!--비밀번호-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="password">
                비밀번호
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input
                id="password"
                :class="[
                  password === ''
                    ? 'input-1'
                    : (passwordVerificationMiddleResult ||
                        passwordVerificationStrongResult) == true
                    ? 'input-1-ok'
                    : 'input-1-no',
                ]"
                type="password"
                tabindex="0"
                placeholder="패스워드를 입력해주세요"
                v-model="password"
                v-on:input="passwordVerification(), passwordverify()"
              />
            </div>
          </div>
          <div
            class="mgt-2"
            :class="[
              password === ''
                ? 'd-none'
                : passwordVerificationStrongResult
                ? 'color-green'
                : passwordVerificationMiddleResult
                ? 'color-yellow'
                : 'color-red',
            ]"
          >
            비밀번호 형식(8~25 문자 및 숫자포함)
          </div>

          <!--비밀번호 확인-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="password-check">
                비밀번호 확인
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input
                id="password-check"
                :class="[
                  passwordCheck === ''
                    ? 'input-1'
                    : passwordVerifyResult == true
                    ? 'input-1-ok'
                    : 'input-1-no',
                ]"
                type="password"
                tabindex="0"
                placeholder="패스워드를 한번 더 입력해주세요"
                required
                v-model="passwordCheck"
                @input="passwordverify()"
              />
            </div>
          </div>
          <div
            class="mgt-2"
            :class="[
              passwordCheck === ''
                ? 'd-none'
                : passwordVerifyResult == true
                ? 'd-none'
                : 'color-red',
            ]"
          >
            비밀번호와 일치하지 않습니다.
          </div>

        </form>
      </div>
      <div class="div-btn">
        <button class="btn-2" @click="showHandler('비밀번호변경하기')">
          <span class="text-5"> 비밀번호 변경하기 </span>
        </button>
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

  font-size: 32px;
  line-height: 35px;
  font-weight: 500;
  text-align: center;
  letter-spacing: -1px;
  color: #333;
}


.text-2-no-magin {
  font-size: 16px;
  color: rgb(235, 143, 143);
  line-height: 17px;
  text-align: right;
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
  display: inline-block;
  font-size: 16px;
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

/* .input-1-noborder input[type="radio"]:checked + label {
  color: rgb(235, 155, 56); /* 선택 시 원하는 텍스트 색상을 지정하세요 
} */

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
  color:rgb(4, 211, 38);

}

.color-yellow {
  color:rgb(225, 144, 45);
}
</style>
