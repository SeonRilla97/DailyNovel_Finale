import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';
import App from './App.vue'
import router from './router'
import vue3GoogleLogin from 'vue3-google-login'
import VueKakaoSdk from 'vue-kakao-sdk'
import 'v-calendar/style.css';
import InfiniteLoading from "v3-infinite-loading"; // 무한 스크롤 용 api
import "v3-infinite-loading/lib/style.css";  // 무한 스크롤 용 css
import piniaPersist from 'pinia-plugin-persist' //로그인 유지



//모재영 추가
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';

// import './assets/main.css'


const app = createApp(App);

// 선유진 - 달력 API 230510
// Use plugin defaults (optional)
app.use(setupCalendar, {})

// Use the components
app.component('VCalendar', Calendar)
app.component('VDatePicker', DatePicker)



const pinia = createPinia();
pinia.use(piniaPersist)


const apiKey = '3e9086e14099149701e6a89e10372e30'
app.use(pinia)
app.use(vue3GoogleLogin,{
    clientId:'171277420861-evm2i2iojla9dom7b7fo5evdl3j2n63g.apps.googleusercontent.com'
})

app.use(VueKakaoSdk, { apiKey })

// 모재영 에디터 관련
app.component('QuillEditor', QuillEditor)
app.use(router)
app.mount('#app')
