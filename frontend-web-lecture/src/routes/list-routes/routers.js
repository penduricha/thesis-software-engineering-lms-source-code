import LoginPage from "@/pages/login/LoginPage.vue";
import ListCourses from "@/pages/main/ListCourses.vue";
import CourseManage from "@/pages/detail-course/CourseManage.vue";
import BankExamJavaCore from "@/pages/bank-exams-java-core/BankExamsJavaCore.vue";

import CreateExamJavaCoreFromBank
    from "@/pages/bank-exams-java-core/create-exam-with-choose-questions/CreateExamJavaCoreFromBank.vue";
import CreateExamJavaCoreRandomQuestions
    from "@/pages/bank-exams-java-core/create-exam-with-random-questions/CreateExamJavaCoreRandomQuestions.vue";
import BankExamsJavaOop from "@/pages/bank-exams-java-oop/BankExamsJavaOop.vue";
import PageUpdateTestJavaOop from "@/pages/bank-exams-java-oop/update-test/PageUpdateTestJavaOop.vue";
import ListStudentsByExamJavaCore
    from "@/pages/detail-course/component-manage-course/list-student-by-exam-java-core/ListStudentsByExamJavaCore.vue";
import ListStudentsByExamJavaClass
    from "@/pages/detail-course/component-manage-course/list-student-by-exam-java-class/ListStudentsByExamJavaClass.vue";

const routers = [
    // '/' khi init trang
    { path: '/', component: LoginPage },
    { path: '/login-page', component: LoginPage },
    { path: '/main-page/list-courses', component: ListCourses },
    { path: '/main-page/bank-exams/java-core', component: BankExamJavaCore },
    { path: '/main-page/list-courses/course-manage',
        component: CourseManage,
        props: (route) => ({ courseID: route.query.courseID })
    },
    { path: '/main-page/bank-exams/java-core/create-exam-choose',
        component: CreateExamJavaCoreFromBank ,
    },
    { path: '/main-page/bank-exams/java-core/create-exam-random',
        component: CreateExamJavaCoreRandomQuestions ,
    },
    {
        path: '/main-page/bank-exams/java-oop',
        component: BankExamsJavaOop
    },
    { path: '/main-page/bank-exams/java-oop/page-update',
        component: PageUpdateTestJavaOop,
        props: (route) => ({ bankTestJavaOopID: route.query.bankTestJavaOopID })
    },
    { path: '/main-page/list-courses/course-manage/list-students-by-exam-java-core',
        component: ListStudentsByExamJavaCore,
        props: (route) => ({
            examID: route.query.examID,
            courseID: route.query.courseID
        })
    },
    { path: '/main-page/list-courses/course-manage/list-students-by-exam-java-class',
        component: ListStudentsByExamJavaClass,
        props: (route) => ({
            examID: route.query.examID,
            courseID: route.query.courseID
        })
    },
];
export default routers;