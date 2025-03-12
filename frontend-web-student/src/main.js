import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
//import bootstrap
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js';

// npm i @fortawesome/fontawesome-free
import "@fortawesome/fontawesome-free/css/all.min.css";
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
import routers from "@/routes/list-routes/routers.js";
import RouterDao from "@/routes/RoutersDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";

const app = createApp(App);
app.use(vuetify);

//font awesome

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
    const routerDao = new RouterDao();
    const studentLocalStorage = new StudentLocalStorage();
    const startPagePath = '/';
    const checkPath_And_ID = routerDao.getPath_From_LocalStorage() && studentLocalStorage.getStudentID_From_LocalStorage();
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

// khi break mo ham nay cho chay
// function execute() {
//     const startPagePath = '/';
//     initPage(routers, startPagePath);
// }

execute();
//createApp(App).mount('#app')
