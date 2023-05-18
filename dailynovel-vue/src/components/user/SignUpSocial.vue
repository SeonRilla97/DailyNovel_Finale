<script setup>
import { ref, watchEffect , reactive} from "vue";
import Modal from "./LoginModal.vue";
import { useRoute, useRouter } from "vue-router";

let router = useRouter();
let route =  reactive(useRoute()); //라우팅의 정보를 가져다 주는애
let email = ref("");
let emailBtn = ref(false);
let password = ref("");
let passwordBtn = ref(false);
let passwordCheck = ref("");
let phoneNumber = ref("");
let phoneNumberBtn = ref(false);
let emailcheck = ref("");
let mailcheckBtn = ref(false);
let nickName = ref("");
let nickNameBtn = ref(false);
let content = ref("");
let birthYear = ref();
let birthMonth = ref();
let birthDay = ref();
//1은남자 2는여자
let gender = ref();
let emailVerification = /^[a-zA-Z0-9+-_.]+@[a-zA-Z-]+\.[a-zA-Z-.]+$/;
let passwordVerificationStrong = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{10,25}$/;
let passwordVerificationMiddle = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
let phoneNumberVerification = /^(\d{3})(\d{3,4})(\d{4})$/;
let showModal = ref(false);
let birthYearVerification = /^(\d{4})$/;
let birthMonthVerification = /^(\d{2})$/;
let birthDayVerification = /^(\d{2})$/;

let emailVerificationResult = ref(false);
let passwordVerificationStrongResult = ref(false);
let passwordVerificationMiddleResult = ref(false);
let passwordVerifyResult = ref(false);
let phoneNumberVerificationResult = ref(false);


//받아온 값 활용해보기

watchEffect(() => {
  if (route) {
    email.value = route.query.AuthEmail || "";
    console.log(email.value);
    emailVerificationResult.value =true;

  }
});

async function updateInput(event) {
  if (event === "이메일") {
    if (email.value.match(emailVerification)) {
      emailVerificationResult.value = true;
      emailBtn.value = false;
    } else {
      content.value = "올바른 이메일을 입력해주세요";
      emailVerificationResult.value = false;
      emailBtn.value = false;
    }
  }
}

async function showHandler(event) {
  if (event == "닉네임") {
    let response = await fetch(
      "http://localhost:8080/users/nicknameCheck?nickname=" + nickName.value
    );
    let data = await response.text();

    if (data === "false") {
      content.value = "사용할 수 있는 닉네임입니다.";
      nickNameBtn.value = true;
    } else if (data === "true") {
      nickNameBtn.value = false;
      content.value = "중복된 닉네임입니다.";
    } else {
      // 예외 처리: 예상하지 못한 응답 형식이나 오류 처리
      content.value = "응답을 처리할 수 없습니다.";
    }
  }  else if(event == "회원가입") {
    let date = new Date(birthYear.value, birthMonth.value - 1, birthDay.value + 1);
    let dateString = date.toISOString().split("T")[0];
    await fetch("http://localhost:8080/users/signup", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email.value,
        password: password.value,
        nickName: nickName.value,
        phoneNumber: phoneNumber.value,
        gender: gender.value,
        birthday: dateString,
      }),
    })
      .then((response) => response.text())
      .then((data) => {
        // 응답 처리
        content.value = "회원가입에 성공했습니다.";
        router.push("./login");
      })
      .catch((error) => {
        // 에러 처리
        content.value = "회원가입에 실패했습니다 빠진 정보를 확인해주세요.";
      });
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

function nickNameVerify() {
  nickNameBtn.value = false;
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
        <form
          class="mgt-1 lc-vertical-alignment"
          enctype="application/x-www-form-urlencoded"
        >
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
          </div>
          <div
            class="mgt-2"
            :class="[
              email == '' ? 'd-none' : emailVerificationResult ? 'd-none' : 'color-red',
            ]"
          >
            이메일 형식을 지켜주세요
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
                :class="[
                  nickName === ''
                    ? 'input-1'
                    : !nickNameBtn
                    ? 'input-1-no'
                    : 'input-1-ok',
                ]"
                type="text"
                tabindex="0"
                placeholder="닉네임을 입력해주세요"
                required
                v-model="nickName"
                @input="nickNameVerify()"
              />
            </div>
            <div class="mgl-3">
              <button
                type="button"
                :class="[!nickNameBtn ? 'btn-1' : 'btn-1-off']"
                @click="showHandler('닉네임')"
                :disabled="nickNameBtn"
              >
                증복확인
              </button>
            </div>
          </div>
          <div class="mgt-2">닉네임:2~8글자(특수문자제외)</div>

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
              />
            </div>
          </div>

          <!--생년 월일-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="birth"> 생년월일 </label>
              <span class="essential-color">*</span>
            </div>
            <div class="div-input-2">
              <input
                id="birth-year"
                class="input-2"
                type="NUMBER"
                maxlength="4"
                tabindex="0"
                placeholder="YYYY"
                required
                v-model="birthYear"
              />
              <span class="birth-deco"> </span>
              <input
                id="birth-month"
                class="input-2"
                type="NUMBER"
                maxlength="2"
                tabindex="0"
                placeholder="MM"
                required
                v-model="birthMonth"
              />
              <span class="birth-deco"> </span>
              <input
                id="birth-day"
                class="input-2"
                type="NUMBER"
                maxlength="2"
                tabindex="0"
                placeholder="DD"
                required
                v-model="birthDay"
              />
            </div>
          </div>

          <!--성별-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="gender"> 성별 </label>
              <span class="essential-color">*</span>
            </div>
            <div class="input-1-noborder">
              <input
                type="radio"
                tabindex="0"
                name="gender"
                value="1"
                required
                v-model="gender"
              /><label for="gender" class="mgl-2"> 남자 </label>
              <input
                type="radio"
                tabindex="0"
                name="gender"
                value="2"
                required
                class="mgl-5"
                v-model="gender"
              />
              <label for="gender" class="mgl-2"> 여자 </label>
            </div>
          </div>
        </form>
      </div>
      <div class="div-btn">
        <button class="btn-2" @click="showHandler('회원가입')">
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
