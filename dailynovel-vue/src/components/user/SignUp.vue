<script setup>
import { ref } from 'vue';
import Modal from './LoginModal.vue';

let content= ref("");

let showModal = ref(false);

async  function showHandler(event) {

  
  if(event =='닉네임'){ 
    // let response = await fetch("http://localhost:8080/users/nicknamecheck", {
    //     method: "POST",
    //     headers: {
    //         "Accept": "application/json",
    //         "Content-type": "application/x-www-form-urlencoded"
    //     },
    //     body: `email=${user.email}&password=${user.password}`
    // });
    // let json = await response.json();


    content.value = '사용가능한 닉네임입니다.';

    content.value = '중복된 닉네임입니다.';
  }
  
  else if(event=='이메일'){

    content.value = '사용가능한 이메일입니다.';

    content.value = '이미 가입된 이메일입니다.';
    
    
  }

  else{

    content.value = '회원가입에 성공했습니다.';


  }

  showModal.value = true;
}
function dlgHandler(a) {

  showModal.value = false;

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
        <from class="mgt-1 lc-vertical-alignment">
          <!--이메일-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="email">
                이메일
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input id="email" class="input-1" type="text" tabindex="0" placeholder="이메일을 입력해주세요" required />
            </div>
            <div class="mgl-3">
              <button type="button" class="btn-1" @click="showHandler('이메일')">증복확인</button>
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
              <input id="password" class="input-1" type="text" tabindex="0" placeholder="패스워드를 입력해주세요" required />
            </div>
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
              <input id="password-check" class="input-1" type="password" tabindex="0" placeholder="패스워드를 한번 더 입력해주세요"
                required />
            </div>
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
              <input id="nickname" class="input-1" type="text" tabindex="0" placeholder="닉네임을 입력해주세요" required />
            </div>
            <div class="mgl-3">
              <button type="button" class="btn-1" @click="showHandler('닉네임')">증복확인</button>
            </div>
          </div>

          <!--전화 번호-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="phone-number">
                전화번호
                <span class="essential-color">*</span>
              </label>
            </div>
            <div class="div-input">
              <input id="phone-number" class="input-1" type="text" tabindex="0" placeholder="숫자만 입력해주세요" required />
            </div>
          </div>

          <!--생년 월일-->
          <div class="mgt-3 display-flex">
            <div class="div-label">
              <label for="birth"> 생년월일 </label>
            </div>
            <div class="div-input-2">
              <input id="birth-year" class="input-2" type="text" tabindex="0" placeholder="YYYY" required />
              <span class="birth-deco">
              </span>
              <input id="birth-month" class="input-2" type="text" tabindex="0" placeholder="MM" required />
              <span class="birth-deco">
              </span>
              <input id="birth-day" class="input-2" type="text" tabindex="0" placeholder="DD" required />
            </div>
          </div>
        </from>
      </div>
      <div class="div-btn">
        <button class="btn-2" @click="showHandler">
          <span class="text-5" > 가입하기 </span>
        </button>
      </div>
    </div>
  </div>
    <Modal :content="content" :show="showModal" @ok="dlgHandler">
  </Modal>
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
</style>
