import LoginPage from "@/pages/login/LoginPage.vue";
import ListCourses from "@/pages/main/ListCourses.vue";
import CourseManage from "@/pages/detail-course/CourseManage.vue";

const routers = [
    // '/' khi init trang
    { path: '/', component: LoginPage },
    { path: '/login-page', component: LoginPage },
    { path: '/main-page/list-courses', component: ListCourses },
    { path: '/main-page/list-courses/course-manage',
        component: CourseManage,
        props: (route) => ({ courseID: route.query.courseID })
    },
];
export default routers;