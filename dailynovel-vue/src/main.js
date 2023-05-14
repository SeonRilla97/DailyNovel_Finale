import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';
import App from './App.vue'
import router from './router'
import 'v-calendar/style.css';

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

// 모재영 에디터 관련
app.component('QuillEditor', QuillEditor)

app.use(createPinia())

app.use(router)
app.mount('#app')
