import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';
import App from './App.vue'
import router from './router'
import vue3GoogleLogin from 'vue3-google-login'
import VueKakaoSdk from 'vue-kakao-sdk'
import 'v-calendar/style.css';

// import './assets/main.css'


const app = createApp(App);

// 선유진 - 달력 API 230510
// Use plugin defaults (optional)
app.use(setupCalendar, {})

// Use the components
app.component('VCalendar', Calendar)
app.component('VDatePicker', DatePicker)

const pinia = createPinia();

const apiKey = '3e9086e14099149701e6a89e10372e30'
app.use(pinia)
app.use(vue3GoogleLogin,{
    clientId:'171277420861-evm2i2iojla9dom7b7fo5evdl3j2n63g.apps.googleusercontent.com'
})
app.use(VueKakaoSdk, { apiKey })
app.use(router)
app.mount('#app')
