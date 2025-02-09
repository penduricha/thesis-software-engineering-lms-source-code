import LoginPage from "@/pages/login/LoginPage.vue";
import ListExamsComponent from "@/pages/list-exams/ListExamsComponent.vue";
import ListResultExams from "@/pages/list-exams/ListResultExams.vue";
import QuestionExam from "@/pages/ui-exam-questions/QuestionExam.vue";
import questionExam from "@/pages/ui-exam-questions/QuestionExam.vue";


const routers = [
    // '/' khi init trang
    { path: '/', component: LoginPage},
    { path: '/login-page', component: LoginPage},
    { path: '/main-page/list-result-exams', component: ListResultExams},
    { path: '/main-page/list-exams-page', component: ListExamsComponent},
    { path: '/page-do-exam', component: QuestionExam},
];
export default routers;