import LoginPage from "@/pages/login/LoginPage.vue";
import ListExamsComponent from "@/pages/list-exams/ListExamsComponent.vue";
import ListResultExams from "@/pages/list-exams/ListResultExams.vue";
import QuestionJavaCoreExam from "@/pages/ui-exam-questions/exam-java-core/QuestionJavaCoreExam.vue";
import QuestionJavaClassExam from "@/pages/ui-exam-questions/exam-java-class/QuestionJavaClassExam.vue";



const routers = [
    // '/' khi init trang
    { path: '/', component: LoginPage },
    { path: '/login-page', component: LoginPage},
    {
        path: '/main-page/list-result-exams', component: ListResultExams
    },
    {
        path: '/main-page/list-exams-page', component: ListExamsComponent
    },
    { path: '/main-page/list-exams-page/exam-open/java-core-exam',
        component: QuestionJavaCoreExam,
        props: (route) => ({
            examID: route.query.examID,
            duration: route.query.duration
        })
    },
    { path: '/main-page/list-exams-page/exam-open/java-class-exam',
        component: QuestionJavaClassExam,
        props: (route) => ({
            examID: route.query.examID,
            duration: route.query.duration
        })
    },
];
export default routers;