<script setup>
import { onBeforeUnmount, onMounted, reactive, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useUserDetailsStore } from "../store/useUserDetailsStore.js";
import { decodeCredential } from "vue3-google-login";
import { googleLogout } from "vue3-google-login";
let userDetails = useUserDetailsStore(); //피impo니아를 사용하는 방법
let router = useRouter();
let route = useRoute(); //라우팅의 정보를 가져다 주는애
let user = reactive({
  useremail: "",
  password: "",
  roles: "",
});
let loginFalse = ref(false);

async function loginHandler() {
  console.log(user.email);
  let response = await fetch("http://localhost:8080/members/login", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-type": "application/x-www-form-urlencoded",
    },
    body: `email=${user.email}&password=${user.password}`,
  });
  let json = await response.json();

  //새롭게 데이터를 받아보자
  userDetails.id = json.result.id;
  userDetails.nickname = json.result.nickName;
  userDetails.email = json.result.email;
  userDetails.roles = json.roles;
  console.log(userDetails.id,
  userDetails.nickname ,
  userDetails.email,
  userDetails.roles )
  let returnURL = route.query.returnURL;
  if (userDetails.email == null) loginFalse.value = true;
  else if (returnURL) router.push(returnURL);
  else router.push("/member/room");
}


async function loginoAuthHandler(event) {
  let response = await fetch("http://localhost:8080/members/loginAuth", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-type": "application/x-www-form-urlencoded",
    },
    body: `email=${event}`,
  });
  let json = await response.json();

  //새롭게 데이터를 받아보자
  userDetails.id = json.result.id;
  userDetails.nickname = json.result.nickName;
  userDetails.email = json.result.email;
  userDetails.roles = json.roles;
  let returnURL = route.query.returnURL;
  if (userDetails.email == null) loginFalse.value = true;
  else if (returnURL) router.push(returnURL);
  else router.push("/member/room");
}

// async function googleLogin(response) {
//   let userData = decodeCredential(response.credential);
//   userDetails.nickname = userData.name;
//   userDetails.email = userData.email;
//   userDetails.roles = ["MEMBER"];
//   let returnURL = route.query.returnURL;

//   if (userDetails.email == null) loginFalse.value = true;
//   else {
//     let response = await fetch(
//       "http://localhost:8080/users/emailCheck?email=" + userDetails.email
//     );
//     if (!response.ok) {
//       console.log("실패했나");
//     }
//     let data = await response.text();

//     if (data === "true") {
//       router.push("./signup");
//     } else {
//       router.push({
//         path: returnURL || "./member/room",
//         state: {
//           userDetails: userDetails,
//         },
//       });
//     }
//   }
// }

function logoutHandler() {
  googleLogout();
  userDetails.logout();
}

async function customLoginHandler(response) {
  await fetch(
    `https://www.googleapis.com/oauth2/v3/userinfo?access_token=${response.access_token}`
  )
    .then((res) => res.json())
    .then(async (userData) => {
      FindSignupUser(userData.email);
    });
}




// 사용자 정보를 가져오는 함수
async function fetchUserInfo(accessToken) {
  try {
    const response = await fetch('https://kapi.kakao.com/v2/user/me', {
      headers: {
        'Authorization': `Bearer ${accessToken}`,
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
      },
    });

    if (response.ok) {
      const userInfo = await response.json();
      return userInfo;
    } else {
      throw new Error('Failed to fetch user info');
    }
  } catch (error) {
    console.error(error);
    throw error;
  }
}

// 사용자 정보 가져오기
async function getUserInfoWithLogin() {
  try {
    const accessToken = await kakaoLogin();
    const userInfo = await fetchUserInfo(accessToken);
    console.log(userInfo);
    console.log(userInfo.kakao_account.email);
    FindSignupUser(userInfo.kakao_account.email);
    // 사용자 정보 출력 또는 다른 작업 수행
  } catch (error) {
    // 오류 처리
    console.error(error);
  }
}

// 카카오 로그인 실행 함수
async function kakaoLogin() {
  return new Promise((resolve, reject) => {
    Kakao.Auth.login({
      scope: 'account_email',
      success: auth => {
        resolve(auth.access_token);
      },
      fail: err => {
        reject(err);
      }
    });
  });
}

async function kakaoLoginMakeshift() {
  return new Promise((resolve, reject) => {
    Kakao.Auth.login({
      scope: 'account_email',
      success: auth => {
        console.log(auth);
        // 로그인 성공시 처리
        console.log(auth.account_email);
        // 클라이언트에서 로그인 성공 후 이메일 정보를 사용하고자 할 경우
        if (auth.kakao_account && auth.kakao_account.email) {
          console.log(auth.kakao_account.email);
          // 이메일 정보 처리
        } else {
          console.log("이메일 정보를 가져올 수 없습니다.");
        }
        resolve(); // 로그인 성공 시 Promise를 이행(resolve)합니다.
      },
      fail: err => {
        console.error(err);
        reject(err); // 로그인 실패 시 Promise를 거부(reject)합니다.
      }
    });
  });
}

let naverLogin = null; // 전역 변수로 naverLogin 객체 선언

const naverService = () => {
  const setNaver = () => {
    naverLogin = new window.naver.LoginWithNaverId({ // 전역 변수에 객체 할당
      clientId: 'wrbhmlU28DOMvzGf8SAv',
      callbackUrl: 'http://localhost:5173/login',
      loginButton: {
        color: "green",
        type: 1,
        height: 50,
      },
      popup: true,
    });
    naverLogin.init();
  };

  const getUserInfo = () => {
    naverLogin.getLoginStatus((status) => {
      if (status) {
        const email = naverLogin.user.email;
        FindSignupUser(email);
        const name = naverLogin.user.name;
        console.log(email, name);
      } else {
        console.log("AccessToken이 올바르지 않습니다.");
      }
    });
  };

  const logout = () => {
    naverLogin.logout();
  };

  return {
    setNaver,
    getUserInfo,
    logout,
  };
};

onMounted(() => {
  const { setNaver, getUserInfo } = naverService(); // naverService에서 setNaver 함수도 가져오도록 수정
  setNaver(); // naverLogin 객체 설정
  getUserInfo();
});

async function FindSignupUser(event) {
  let returnURL = route.query.returnURL;
  if (event == null) loginFalse.value = true;
  else {
    let response = await fetch(
      "http://localhost:8080/users/emailCheckAuth?email=" + event
    );
    let userEmailData = event;
    console.log(userEmailData);
    let data = await response.text();
    localStorage.removeItem('com.naver.nid.oauth.state_token');
    if (data === "true") {
      router.push({
        path: "./signupSocial",
        query: {
          AuthEmail: userEmailData,
        },
      });
    } else {

      loginoAuthHandler(event);
      userDetails.email = userEmailData;
      router.push({
        path: returnURL || "/member/room",
      });
    }
  }

  naverService().logout(); // 수정: 전역 변수인 naverLogin을 사용하여 로그아웃 호출
}
</script>
<template>
  <div class="container-1-nmg">
    <div class="main lc-vertical-alignment">
      <div class="main-svg" />
      <div class="form">
        <form method="post" enctype="application/x-www-form-urlencoded">
          <div class="mgt-1 mgl-5" v-if="loginFalse">
            <span style="color: rgb(220, 0, 0);">계정 및 암호를 확인해주세요</span>

          </div>
          <div class="input3 mgt-4">
            <div class="div-placeholder">
              <input id="email" class="text04 mgl-2" type="text" tabindex="0" placeholder="이메일" required v-model="user.email" />
            </div>
          </div>
          <div class="input3 mgt-2">
            <div class="div-placeholder mgt-1">
              <input id="password" class="text04 mgl-2" type="password" placeholder="비밀번호" required v-model="user.password" />
            </div>
          </div>
          <button class="button1 mgt-3" @click.prevent="loginHandler">
            <router-link to="./login" class="text07">로그인</router-link>
          </button>
        </form>
        <div class="lc-horizontal-alignment mgt-3">
          <div class="">
            <router-link to="./signup" class="text02">회원가입</router-link>
          </div>
        </div>
      </div>
      <div class="mgt-6">
        <router-link to="./login" class="text09 mgt-4">SNS계정으로 간편 로그인/회원가입</router-link>
      </div>
      <div class="lc-horizontal-alignment mgt-3">
        <!-- <GoogleLogin :callback="googleLogin">
          <div class="google-svg" />
        </GoogleLogin> -->

        <GoogleLogin :callback="customLoginHandler" class="wd-100" popup-type="TOKEN">
          <div class="google-svg" />
        </GoogleLogin>

        <div class="kakao-svg1 mgl-4" @click.prevent="getUserInfoWithLogin" />

        <div id="naverIdLogin" class="mgl-4 mgt-2" @click.prevent="openNaverLogin"></div>


      </div>
      <div class="mgt-3">
        <span class="text11">
          <router-link to="./login">로그인에 무슨 문제 있으신가요?</router-link>
        </span>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url("/css/reset.css");
@import url("/css/style.css");
@import url("/css/layout.css");
@import url("/css/box.css");
@import url("/css/button.css");

.layout-full-screen {
  background-color: rgba(250, 250, 250, 1);
}

.main-svg {
  width: 11.75rem;
  height: 5.9375rem;
  background-image: url("../../assets/img/logo/mainlogo.svg");
  background-size: contain;
  background-repeat: no-repeat;
}

.form {
  width: 18.75rem;
  height: 12.875rem;
}

.text {
  color: rgba(66, 66, 66, 1);
  font-size: 0.9375rem;
  font-style: DemiLight;
  text-align: center;
  font-family: Noto Sans KR;
  font-weight: 400;
  line-height: 0.9375rem;
  font-stretch: normal;
  text-decoration: none;
}

.text02 {
  color: rgba(66, 66, 66, 1);
  font-size: 0.9375rem;
  font-style: DemiLight;
  text-align: center;
  font-family: Noto Sans KR;
  font-weight: 400;
  line-height: 0.9375rem;
  font-stretch: normal;
  text-decoration: none;
}

.input3 {
  width: 18.75rem;
  height: 3.125rem;
  border: 1px solid rgba(219, 219, 219, 1) !important;
  border-radius: 0.25rem 0.25rem 0 0;
  background-color: rgba(255, 255, 255, 1);
}

.div-placeholder {
  width: 16.75rem;
  height: 1.3125rem;
  flex-shrink: 0;
}

.text04 {
  width: 16.75rem;
  height: 3.125rem;
  color: rgb(106, 96, 96);
  font-size: 0.9375rem;
  font-style: DemiLight;
  text-align: left;
  font-family: Noto Sans KR;
  font-weight: 400;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}

.button1 {
  width: 18.75rem;
  height: 3.25rem;
  border-color: rgba(242, 178, 99, 1);
  border-style: solid;
  border-width: 1px;
  border-radius: 0.25rem;
  background-color: rgba(242, 178, 99, 1);
}

.text07 {
  color: rgba(255, 255, 255, 1);
  width: 3.75rem;
  font-size: 1.0625rem;
  font-style: Bold;
  text-align: center;
  font-family: Noto Sans KR;
  font-weight: 700;
  line-height: 1.25rem;
  font-stretch: normal;
  text-decoration: none;
}

.text09 {
  color: rgba(117, 117, 117, 1);
  font-size: 0.75rem;
  font-style: DemiLight;
  text-align: center;
  font-family: Noto Sans KR;
  font-weight: 400;
  line-height: 0.9375rem;
  font-stretch: normal;
  text-decoration: none;
}

.google-svg {
  border: 0px solid red;
  width: 48px;
  height: 50px;
  background-repeat: no-repeat;
  background-image: url("data:image/svg+xml,%3Csvg width='50' height='50' viewBox='0 0 512 512' fill='none' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink'%3E%3Crect width='512' height='512' fill='url(%23pattern0)'/%3E%3Cdefs%3E%3Cpattern id='pattern0' patternContentUnits='objectBoundingBox' width='1' height='1'%3E%3Cuse xlink:href='%23image0_2059_6409' transform='scale(0.00195312)'/%3E%3C/pattern%3E%3Cimage id='image0_2059_6409' width='512' height='512' xlink:href='data:image/png;base64,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'/%3E%3C/defs%3E%3C/svg%3E%0A");
}

.kakao-svg1 {
  width: 48px;
  height: 50px;
  background-repeat: no-repeat;
  background-image: url("data:image/svg+xml,%3Csvg width='48' height='49' viewBox='0 0 48 49' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cg clip-path='url(%23clip0_1537_4369)'%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M0 24.9697C0 11.7147 10.745 0.969727 24 0.969727C37.255 0.969727 48 11.7147 48 24.9697C48 38.2247 37.255 48.9697 24 48.9697C10.745 48.9697 0 38.2247 0 24.9697Z' fill='%23FFEB00'/%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M24 12.2471C32.284 12.2471 39 17.5531 39 24.0971C39 30.6421 32.284 35.9471 24 35.9471C23.08 35.9471 22.178 35.8811 21.303 35.7561L15.222 39.8611C15.1492 39.9188 15.0597 39.9513 14.9668 39.9539C14.8739 39.9565 14.7827 39.9289 14.7069 39.8753C14.631 39.8217 14.5745 39.745 14.5459 39.6566C14.5172 39.5683 14.518 39.473 14.548 39.3851L15.962 34.1031C11.777 32.0011 9 28.3051 9 24.0971C9 17.5531 15.716 12.2471 24 12.2471ZM30.22 20.6541C29.804 20.6541 29.502 20.9511 29.502 21.3611V27.3001C29.502 27.7101 29.791 27.9861 30.22 27.9861C30.63 27.9861 30.938 27.6911 30.938 27.3001V25.3681L31.453 24.8421L33.338 27.4121C33.615 27.7861 33.764 27.9521 34.029 27.9801C34.066 27.9831 34.104 27.9851 34.141 27.9851C34.295 27.9851 34.801 27.9451 34.857 27.4221C34.895 27.1291 34.72 26.9021 34.509 26.6261L32.466 23.9511L34.193 22.2081L34.369 22.0121C34.603 21.7521 34.722 21.6221 34.722 21.4251C34.731 21.0031 34.382 20.7731 34.035 20.7641C33.761 20.7641 33.578 20.9141 33.465 21.0261L30.937 23.6601V21.3601C30.937 20.9381 30.649 20.6541 30.22 20.6541ZM20.856 20.6541C20.296 20.6541 19.938 21.0861 19.789 21.4911L17.706 27.0081C17.6642 27.1079 17.6422 27.2149 17.641 27.3231C17.641 27.6951 17.951 27.9861 18.347 27.9861C18.706 27.9861 18.925 27.8241 19.037 27.4761L19.358 26.5061H22.357L22.67 27.4881C22.781 27.8231 23.01 27.9861 23.37 27.9861C23.737 27.9861 24.025 27.7131 24.025 27.3661C24.025 27.3101 24.008 27.1701 23.944 26.9971L21.925 21.4891C21.738 20.9591 21.348 20.6541 20.856 20.6541ZM17.936 20.7181H13.484C13.067 20.7181 12.842 21.0551 12.842 21.3721C12.842 21.6721 13.01 22.0241 13.484 22.0241H14.994V27.2341C14.994 27.6981 15.268 27.9861 15.71 27.9861C16.153 27.9861 16.428 27.6981 16.428 27.2351V22.0251H17.936C18.41 22.0251 18.579 21.6731 18.579 21.3721C18.579 21.0551 18.354 20.7181 17.936 20.7181ZM25.49 20.6541C25.048 20.6541 24.773 20.9411 24.773 21.4041V27.1111C24.773 27.6081 25.053 27.9051 25.523 27.9051H28.197C28.631 27.9051 28.874 27.5841 28.883 27.2781C28.8888 27.1909 28.8767 27.1034 28.8474 27.021C28.8182 26.9387 28.7724 26.8631 28.713 26.7991C28.591 26.6691 28.413 26.5991 28.197 26.5991H26.207V21.4041C26.207 20.9411 25.933 20.6541 25.49 20.6541ZM20.87 21.9701L21.953 25.2351H19.761L20.842 21.9701C20.8431 21.9672 20.845 21.9648 20.8475 21.9631C20.85 21.9614 20.853 21.9605 20.856 21.9605C20.859 21.9605 20.862 21.9614 20.8645 21.9631C20.867 21.9648 20.8689 21.9672 20.87 21.9701Z' fill='%233C2929'/%3E%3C/g%3E%3Cdefs%3E%3CclipPath id='clip0_1537_4369'%3E%3Crect y='0.969727' width='48' height='48' rx='24' fill='white'/%3E%3C/clipPath%3E%3C/defs%3E%3C/svg%3E ");
}

.naver-svg2 {
  width: 48px;
  height: 50px;
  background-repeat: no-repeat;
  background-image: url("data:image/svg+xml,%3Csvg width='48' height='49' viewBox='0 0 48 49' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cg clip-path='url(%23clip0_1537_4374)'%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M0 24.9697C0 11.7147 10.745 0.969727 24 0.969727C37.255 0.969727 48 11.7147 48 24.9697C48 38.2247 37.255 48.9697 24 48.9697C10.745 48.9697 0 38.2247 0 24.9697Z' fill='%2300C63B'/%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M21 26.2007V34.9697H14V15.9697H21L27 24.7387V15.9697H34V34.9697H27L21 26.2007Z' fill='white'/%3E%3C/g%3E%3Cdefs%3E%3CclipPath id='clip0_1537_4374'%3E%3Crect y='0.969727' width='48' height='48' rx='24' fill='white'/%3E%3C/clipPath%3E%3C/defs%3E%3C/svg%3E%0A");
}

.text11 {
  top: 673px;
  left: 68px;
  color: rgba(194, 200, 204, 1);
  font-size: 14px;
  font-style: DemiLight;
  text-align: left;
  font-family: Noto Sans KR;
  font-weight: 400;
  line-height: 18px;
  font-stretch: normal;
  text-decoration: none;
}

.group157-vector34 {
  top: 407px;
  left: 1135px;
  width: 364px;
  height: 0px;
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

.input3:focus {
  border-color: #f2b263;
}
</style>