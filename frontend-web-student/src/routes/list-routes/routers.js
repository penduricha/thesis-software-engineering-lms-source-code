import LoginPage from "@/pages/login/LoginPage.vue";
import ListExamsComponent from "@/pages/list-exams/ListExamsComponent.vue";

const routers = [
    // '/' khi init trang
    { path: '/', component: ListExamsComponent},
    { path: '/login-page', component: LoginPage},
    { path: '/list-exams-page', component: ListExamsComponent},
];
export default routers;