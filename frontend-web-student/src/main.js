import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
//import bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

//import vue routers
import { createRouter, createWebHistory } from 'vue-router';

//import vuetify
//vuetify
//npm install vuetify@next @mdi/font
import { createVuetify } from 'vuetify';
import 'vuetify/styles';
import 'vuetify/dist/vuetify-labs.min.css';
const vuetify = createVuetify();

//import routers
import routers from "@/assets/list-routes/routers.js";

const app = createApp(App);
app.use(vuetify);

function initPage(routers, routerPath) {
    const router = createRouter({
        // mode: 'history',
        history: createWebHistory(),
        routes: routers,
    });
    app.use(router);

    router.replace(routerPath).catch((error) => {
        console.error('Error navigating: ', error);
        //router.replace('/screen-404').catch(err => console.error(err));
    });
    //app.unmount();
    //app.use(Vue3GeoLocation);
    app.mount('#app')
}

function execute(){
    //co the doi path khac
    const startPagePath = '/';
    initPage(routers, startPagePath);
}

execute();
//createApp(App).mount('#app')
