import './assets/main.css'

/*main.js*/
import { createApp } from 'vue'
import App from './App.vue'

import CanvasJSChart from '@canvasjs/vue-charts';

const app = createApp(App);
app.use(CanvasJSChart);
app.mount('#app');