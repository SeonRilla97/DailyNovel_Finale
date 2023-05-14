<script setup>
import { ref } from "vue";
import Modal from "./LoginModal.vue";

let email = ref("");
let emailBtn = ref(false);
let password = ref("");
let passwordBtn = ref(false);
let passwordCheck = ref("");
let phoneNumber = ref();
let phoneNumberBtn = ref(false);
let emailcheck = ref("");
let mailcheckBtn = ref(false);
let nickName = ref("");
let nickNameBtn = ref(false);
let content = ref("");

let emailVerification = /^[a-zA-Z0-9+-_.]+@[a-zA-Z-]+\.[a-zA-Z-.]+$/;
let passwordVerificationStrong = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
let passwordVerificationMiddle = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
let nickNameVerification = /^.{1,8}$/;
let phoneNumberVerification = /d{11}/;
let showModal = ref(false);

let emailVerificationResult = ref(false);
let passwordVerificationStrongResult = ref(false);
let passwordVerificationMiddleResult = ref(false);
let nickNameVerificationResult = ref(false);
let phoneNumberVerificationResult = ref(false);

async function showHandler(event) {
  if (event == "닉네임") {
    console.log(nickName.value);
    let response = await fetch(
      "http://localhost:8080/users/nicknameCheck?nickname=" + nickName.value
    );
    let data = await response.text();
    console.log(data);
    if (data === "false") {
      content.value = "사용할 수 있는 닉네임입니다.";
    } else if (data === "true") {
      content.value = "중복된 닉네임입니다.";
      nickNameBtn.value = true;
    } else {
      // 예외 처리: 예상하지 못한 응답 형식이나 오류 처리
      content.value = "응답을 처리할 수 없습니다.";
    }
  } else if (event == "이메일") {
    let response = await fetch(
      "http://localhost:8080/users/emailCheck?email=" + email.value
    );
    let data = await response.text();

    if (data === "true") {
      content.value = "발송된 인증번호를 확인해주세요";
      emailBtn.value = true;
    } else if (data === "false") {
      content.value = "이미 가입된 이메일입니다.";
    } else {
      // 예외 처리: 예상하지 못한 응답 형식이나 오류 처리
      content.value = "이메일을 다시 확인해주세요";
    }
  } else if (event == "이메일인증번호확인") {
    let response = await fetch(
      "http://localhost:8080/users/emailCheckNum?email=" +
        email.value +
        "&emailCheckNum=" +
        emailcheck.value
    );
    let data = await response.text();

    if (data === "true") {
      content.value = "인증에 성공했습니다.";
      mailcheckBtn = true;
    } else if (data === "false") {
      content.value = "인증에 실패했습니다.";
      emailBtn.value = true;
    } else {
      // 예외 처리: 예상하지 못한 응답 형식이나 오류 처리
      content.value = "응답을 처리할 수 없습니다.";
    }
  } else {
    if (!emailBtn) {
      content.value = "이메일을 다시 입력하세요";
    } else if (!passwordBtn) {
      content.value = "패스워드를 다시입력해주세요.";
    } else if (!phoneNumberBtn) {
      content.value = "올바른 전화번호가 아닙니다.";
    } else if (!mailcheckBtn) {
      content.value = "이메일 인증을 시도해주세요";
    } else if (!nickNameBtn) {
      content.value = "닉네임을 다시 입력해주세요";
    } else content.value = "회원가입에 성공했습니다.";
  }

  showModal.value = true;
}
function dlgHandler(a) {
  showModal.value = false;
}

//비밀번호 정규식
function passwordVerification() {
  if (password.value.match(passwordVerificationStrong)) {
    passwordVerificationStrongResult = true;
    console.log("정규식 통과");
  } else if (password.value.match(passwordVerificationMiddle)) {
    passwordVerificationMiddleResult = true;
    console.log("약한정규식 통과");
  } else {
    console.log("실패");
  }
}
//비밀번호 일치
function passwordverify() {
  if (password.value == passwordCheck.value) {
    console.log("같다!");
  } else console.log("다르다");
}

async function updateInput(event) {
  console.log("이거 뜨긴해?");
  if (event == "이메일") {
    if (email.value.match(emailVerification)) {
      emailVerificationResult.value = true;
      emailBtn.value = false;
    } else {
      content.value = "올바른 이메일을 입력해주세요";
      emailVerificationResult.value = false;
      emailBtn.value = false;
    }
  } else if (event == "닉네임") {
    // ...
  } else if (event == "이메일인증번호") {
    // ...
  } else if (event == "패스워드") {
    // ...
  } else if (event == "패스워드확인") {
    // ...
  } else {
    // ...
  }
}
</script>
<template>
  <div class="container-1-nmg container-s1">
    <div class="main lc-vertical-alignment">
      <div class="text-1">회원가입</div>

      <div class="form-1">
        <div class="text-2">
          <span class="essential-color">*</span>
          필수입력사항
        </div>
        <form class="mgt-1 lc-vertical-alignment">
          <!--이메일-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="email">
                이메일
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input
                id="email"
                :class="[
                  email === ''
                    ? 'input-1'
                    : emailVerificationResult
                    ? 'input-1-ok'
                    : 'input-1-no',
                ]"
                type="text"
                tabindex="0"
                placeholder="이메일을 입력해주세요"
                required
                @input="updateInput('이메일')"
                v-model="email"
              />
            </div>
            <div class="mgl-3">
              <button
                type="button"
                :class="[emailBtn ? 'btn-1-off' : 'btn-1']"
                @click="showHandler('이메일')"
                :disabled="emailBtn == true"
              >
                인증번호 받기
              </button>
            </div>
          </div>
          <div
            class="mgt-1"
            style="color: red"
            :class="[emailVerificationResult ? 'd-none' : '']"
          >
            이메일 형식을 지켜주세요
          </div>

          <!--이메일 확인 -->
          <div class="mgt-3 display-flex" :class="[!emailBtn ? 'd-none' : '']">
            <div class="div-label">
              <label for="email">
                이메일 인증하기
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input
                id="email"
                class="input-1"
                type="text"
                tabindex="0"
                placeholder="이메일을 입력해주세요"
                required
                @change="updateInput(이메일인증번호)"
                v-model="emailcheck"
              />
            </div>
            <div class="mgl-3">
              <button
                type="button"
                :class="[!mailcheckBtn ? 'btn-1' : 'btn-1-off']"
                @click="showHandler('이메일인증번호확인')"
                :disabled="mailcheckBtn == true"
              >
                인증번호 확인
              </button>
            </div>
          </div>

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
                class="input-1"
                type="password"
                tabindex="0"
                placeholder="패스워드를 입력해주세요"
                v-model="password"
                @input="passwordVerification()"
                @change="passwordverify()"
              />
            </div>
          </div>
          <div class="mgt-1"       
          :class="[passwordVerificationStrongResult ? 'color-green' :passwordVerificationMiddleResult?'color-yellow' : 'color-red']"
          >비밀번호 형식(8~25문자및숫자포함)
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
                class="input-1"
                type="password"
                tabindex="0"
                placeholder="패스워드를 한번 더 입력해주세요"
                required
                v-model="passwordCheck"
                @input="passwordverify()"
              />
            </div>
          </div>
          <div class="mgt-1" style="color: red">비밀번호와 일치하지 않습니다.</div>

          <!--닉네임-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="nickname">
                닉네임
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input
                id="nickname"
                class="input-1"
                type="text"
                tabindex="0"
                placeholder="닉네임을 입력해주세요"
                required
                v-model="nickName"
              />
            </div>
            <div class="mgl-3">
              <button
                type="button"
                :class="[nickNameBtn ? 'btn-1-off' : 'btn-1']"
                @click="showHandler('닉네임')"
                :disabled="nickNameBtn"
              >
                증복확인
              </button>
            </div>
          </div>
          <div class="mgt-1" style="color: red">닉네임:2~8글자(특수문자제외)</div>

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
                type="text"
                tabindex="0"
                placeholder="숫자만 입력해주세요"
                required
                v-model="phoneNumber"
              />
            </div>
          </div>

          <!--생년 월일-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="birth"> 생년월일 </label>
            </div>
            <div class="div-input-2">
              <input
                id="birth-year"
                class="input-2"
                type="text"
                tabindex="0"
                placeholder="YYYY"
                required
              />
              <span class="birth-deco"> </span>
              <input
                id="birth-month"
                class="input-2"
                type="text"
                tabindex="0"
                placeholder="MM"
                required
              />
              <span class="birth-deco"> </span>
              <input
                id="birth-day"
                class="input-2"
                type="text"
                tabindex="0"
                placeholder="DD"
                required
              />
            </div>
          </div>
        </form>
      </div>
      <div class="div-btn">
        <button class="btn-2" @click="showHandler" v-bind:disabled="emailBtn == false">
          <span class="text-5"> 가입하기 </span>
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

.color-red{
  color: red;
}
.color-green{
  color:greenyellow
}
.color-yellow{
  color: yellow;
}
</style>
