
const naverService = {
  setNaver() {
    const naverLogin = new window.naver.LoginWithNaverId({
      clientId: "wrbhmlU28DOMvzGf8SAv",
      callbackUrl: "http://localhost:5173/login",
      isPopup: false,
      loginButton: {
        color: "green",
        type: 3,
        height: 60,
      },
    });
    naverLogin.init();
  },
  getUserInfo() {
    this.setNaver();
    const naverLogin = new window.naver.LoginWithNaverId();
    naverLogin.getLoginStatus((status) => {
      if (status) {
        const email = naverLogin.user.email;
        const name = naverLogin.user.name;
        console.log(email, name);
      } else {
        console.log("AccessToken이 올바르지 않습니다.");
      }
    });
  },
};

export default naverService;