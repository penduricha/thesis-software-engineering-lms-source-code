import './assets/main.css'
import { createVuetify } from 'vuetify';
import { createApp } from 'vue'
import App from './App.vue'
const vuetify = createVuetify();
createApp(App).use(vuetify);
createApp(App).mount('#app')
