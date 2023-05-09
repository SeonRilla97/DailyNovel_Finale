<script setup>

</script>
<template>
    <div class="container-1-nmg">
        <div class="main lc-vertical-alignment ">
            <div class="main-svg" />
            <div class="form">
                <form method="post">
                    <div class="input3 ">
                        <div class="div-placeholder">
                            <input id="email" class="text04 " type="text" tabindex="0" placeholder="이메일">
                        </div>
                    </div>
                    <div class="input3 mgt-3">
                        <div class="div-placeholder">
                            <input class="text04" type="password" placeholder="비밀번호">
                        </div>
                    </div>
                    <button class="button1 mgt-3">
                        <router-link to="./login" class="text07">로그인</router-link>
                    </button>
                </form>
                <div class="main-logo lc-horizontal-alignment pdt-3">
                    <router-link to="./" class="text">비밀번호 재설정</router-link>
                    <router-link to="./" class="text02">회원가입</router-link>
                </div>
            </div>
            <div>
            <router-link to="./login" class="text09">SNS계정으로 간편 로그인/회원가입</router-link>
            </div>
            <div class="lc-horizontal-alignment">
                <div class="facebook-svg" />
                <div class="kakao-svg1" />
                <div class="naver-svg2" />
            </div>
            <span class="text11">
                <router-link to="./login">로그인에 무슨 문제 있으신가요?</router-link>
            </span>
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
    background-image: url('./assets/1.png');
}

.form {
    width: 18.75rem;
    height: 12.875rem;
}

.main-logo {
    width: 18.75rem;
    height: 0.9375rem;

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
    color: rgba(189, 189, 189, 1);
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

.facebook-svg {
    width: 48px;
    height: 50px;
    background-image: url("data:image/svg+xml,%3Csvg width='48' height='49' viewBox='0 0 48 49' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cg clip-path='url(%23clip0_1537_4364)'%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M0 24.9697C0 11.7147 10.745 0.969727 24 0.969727C37.255 0.969727 48 11.7147 48 24.9697C48 38.2247 37.255 48.9697 24 48.9697C10.745 48.9697 0 38.2247 0 24.9697Z' fill='%233B5998'/%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M25.77 35.9697V24.9697H29.154V21.5847H25.769V19.0467C25.757 18.2907 25.849 17.7617 27.462 17.3547H29.154V13.9697H25.769C22.519 13.9697 21.249 15.8097 21.539 19.0467V21.5847H19V24.9697H21.538V35.9697H25.769H25.77Z' fill='white'/%3E%3C/g%3E%3Cdefs%3E%3CclipPath id='clip0_1537_4364'%3E%3Crect y='0.969727' width='48' height='48' rx='24' fill='white'/%3E%3C/clipPath%3E%3C/defs%3E%3C/svg%3E%0A");
}

.kakao-svg1 {
    width: 48px;
    height: 50px;
    background-image: url("data:image/svg+xml,%3Csvg width='48' height='49' viewBox='0 0 48 49' fill='none' xmlns='http://www.w3.org/2000/svg'%3E%3Cg clip-path='url(%23clip0_1537_4369)'%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M0 24.9697C0 11.7147 10.745 0.969727 24 0.969727C37.255 0.969727 48 11.7147 48 24.9697C48 38.2247 37.255 48.9697 24 48.9697C10.745 48.9697 0 38.2247 0 24.9697Z' fill='%23FFEB00'/%3E%3Cpath fill-rule='evenodd' clip-rule='evenodd' d='M24 12.2471C32.284 12.2471 39 17.5531 39 24.0971C39 30.6421 32.284 35.9471 24 35.9471C23.08 35.9471 22.178 35.8811 21.303 35.7561L15.222 39.8611C15.1492 39.9188 15.0597 39.9513 14.9668 39.9539C14.8739 39.9565 14.7827 39.9289 14.7069 39.8753C14.631 39.8217 14.5745 39.745 14.5459 39.6566C14.5172 39.5683 14.518 39.473 14.548 39.3851L15.962 34.1031C11.777 32.0011 9 28.3051 9 24.0971C9 17.5531 15.716 12.2471 24 12.2471ZM30.22 20.6541C29.804 20.6541 29.502 20.9511 29.502 21.3611V27.3001C29.502 27.7101 29.791 27.9861 30.22 27.9861C30.63 27.9861 30.938 27.6911 30.938 27.3001V25.3681L31.453 24.8421L33.338 27.4121C33.615 27.7861 33.764 27.9521 34.029 27.9801C34.066 27.9831 34.104 27.9851 34.141 27.9851C34.295 27.9851 34.801 27.9451 34.857 27.4221C34.895 27.1291 34.72 26.9021 34.509 26.6261L32.466 23.9511L34.193 22.2081L34.369 22.0121C34.603 21.7521 34.722 21.6221 34.722 21.4251C34.731 21.0031 34.382 20.7731 34.035 20.7641C33.761 20.7641 33.578 20.9141 33.465 21.0261L30.937 23.6601V21.3601C30.937 20.9381 30.649 20.6541 30.22 20.6541ZM20.856 20.6541C20.296 20.6541 19.938 21.0861 19.789 21.4911L17.706 27.0081C17.6642 27.1079 17.6422 27.2149 17.641 27.3231C17.641 27.6951 17.951 27.9861 18.347 27.9861C18.706 27.9861 18.925 27.8241 19.037 27.4761L19.358 26.5061H22.357L22.67 27.4881C22.781 27.8231 23.01 27.9861 23.37 27.9861C23.737 27.9861 24.025 27.7131 24.025 27.3661C24.025 27.3101 24.008 27.1701 23.944 26.9971L21.925 21.4891C21.738 20.9591 21.348 20.6541 20.856 20.6541ZM17.936 20.7181H13.484C13.067 20.7181 12.842 21.0551 12.842 21.3721C12.842 21.6721 13.01 22.0241 13.484 22.0241H14.994V27.2341C14.994 27.6981 15.268 27.9861 15.71 27.9861C16.153 27.9861 16.428 27.6981 16.428 27.2351V22.0251H17.936C18.41 22.0251 18.579 21.6731 18.579 21.3721C18.579 21.0551 18.354 20.7181 17.936 20.7181ZM25.49 20.6541C25.048 20.6541 24.773 20.9411 24.773 21.4041V27.1111C24.773 27.6081 25.053 27.9051 25.523 27.9051H28.197C28.631 27.9051 28.874 27.5841 28.883 27.2781C28.8888 27.1909 28.8767 27.1034 28.8474 27.021C28.8182 26.9387 28.7724 26.8631 28.713 26.7991C28.591 26.6691 28.413 26.5991 28.197 26.5991H26.207V21.4041C26.207 20.9411 25.933 20.6541 25.49 20.6541ZM20.87 21.9701L21.953 25.2351H19.761L20.842 21.9701C20.8431 21.9672 20.845 21.9648 20.8475 21.9631C20.85 21.9614 20.853 21.9605 20.856 21.9605C20.859 21.9605 20.862 21.9614 20.8645 21.9631C20.867 21.9648 20.8689 21.9672 20.87 21.9701Z' fill='%233C2929'/%3E%3C/g%3E%3Cdefs%3E%3CclipPath id='clip0_1537_4369'%3E%3Crect y='0.969727' width='48' height='48' rx='24' fill='white'/%3E%3C/clipPath%3E%3C/defs%3E%3C/svg%3E ");
}

.naver-svg2 {
    width: 48px;
    height: 50px;
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
    border-color: #F2B263;
}
</style>
