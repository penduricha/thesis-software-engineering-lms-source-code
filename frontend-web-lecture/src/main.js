
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

//font awesome
import '@fortawesome/fontawesome-free/css/all.min.css';
//import routers
import routers from "@/routes/list-routes/routers.js";
import RouterDao from "@/routes/RoutersDao.js";
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";

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
    const routerDao = new RouterDao();
    const lectureLocalStorage = new LectureLocalStorage();
    const startPagePath = '/';

    const checkPath_And_ID =
        routerDao.getPath_From_LocalStorage() &&
        lectureLocalStorage.getLectureID_From_LocalStorage();

    if(checkPath_And_ID && !routerDao.getPath_From_SessionStorage()) {
        initPage(routers, routerDao.getPath_From_LocalStorage());
    } else {
        if(!routerDao.getPath_From_SessionStorage()) {
            initPage(routers, startPagePath);
        } else {
            initPage(routers, routerDao.getPath_From_SessionStorage());
        }
    }
}

execute();
//createApp(App).mount('#app')
