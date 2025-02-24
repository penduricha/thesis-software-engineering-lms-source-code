<script>
import './list-exams-components.scss';
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";
import ExamDao from "@/daos/ExamDao.js";

export default {
  name: 'ListExamsComponents',
  components: {
    AsideMenu,
    AsideAccount

  },

  data(){
    return {
      studentID: null,
      courseID: null,

      //exam
      exams: null,
    }
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudentID_And_CourseID_And_Exams();
  },

  mounted() {

  },

  methods: {
    async setStudentID_And_CourseID_And_Exams() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(studentID) {
        this.studentID = studentID;
        let courseID = await StudentDao.getCourseID_By_StudentID(studentID);
        if(courseID) {
          this.courseID = courseID;
          this.exams = await ExamDao.getExams_By_CourseID(courseID);
          console.log("Exams: ", this.exams);
        }
      }
    },

    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    getStatusExam(e) {
      const dateTimeNow = new Date();
      const startDate = new Date(e.startDate);
      const endDate = new Date(e.endDate);
      if(dateTimeNow < startDate) {
        return "Locked";
      } else if(dateTimeNow > endDate) {
        return "Overdue";
      } else if((dateTimeNow >= startDate) && (dateTimeNow <= endDate)) {
        return "Open";
      }
      return null;
    }
  },

  computed: {
    setColorButton() {
      return (e) => {
        const status = this.getStatusExam(e);
        if (status === "Locked" || status === "Overdue") {
          return "color-status-lock";
        } else if (status === "Open") {
          return "color-status-open";
        }
        return ""; // Default case, if needed
      };
    }
  }
}
</script>

<template>
  <body>
    <AsideMenu/>
    <main>
      <h4 class="title-menu">Exam coding</h4>
      <section class="section-welcome">
        <div class="background-welcome">
          <span class="span-welcome">Hello! Nice to meet you on this examining platform.</span>
        </div>
      </section>
      <section class="section-list-exams">
        <h4 class="h2-all-exams-style">All exams</h4>
        <div class="button-div-exam">
          <span class="text-button-exam flex-title">Title exam</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">Type exam</span>
          </div>
          <span class="text-button-exam flex-date">Date open</span>
          <span class="text-button-exam flex-status">Status</span>
        </div>
        <button class="button-exam">
          <span class="text-button-exam flex-title">Java core 1</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">Theory 1</span>
          </div>
          <span class="text-button-exam flex-date">9:00 8/5/2025</span>
          <span class="text-button-exam flex-status color-status-open">Open</span>
        </button>
        <button class="button-exam" v-for="e in exams">
          <span class="text-button-exam flex-title">{{e.titleExam}}</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">{{e.typeExam}}</span>
          </div>
          <span class="text-button-exam flex-date">
            {{new Date(e.startDate).getHours()}}:{{new Date(e.startDate).getMinutes()}}
            {{new Date(e.startDate).getDate()}}/{{new Date(e.startDate).getMonth() + 1}}/{{new Date(e.startDate).getFullYear()}}
          </span>
          <span class="text-button-exam flex-status"
                :class="['span-status-color',setColorButton(e)]"
          >
            {{getStatusExam(e)}}
          </span>
        </button>
<!--        <button class="button-exam">-->
<!--          <span class="text-button-exam flex-title">Java core 2</span>-->
<!--          <div class="div-type-exam flex-type-exam">-->
<!--            <span class="text-button-exam">Theory 2</span>-->
<!--          </div>-->
<!--          <span class="text-button-exam flex-date">9:00 15/5/2025</span>-->
<!--          <span class="text-button-exam flex-status color-status-lock">Locked</span>-->
<!--        </button>-->
<!--        <button class="button-exam">-->
<!--          <span class="text-button-exam flex-title">Java class 1</span>-->
<!--          <div class="div-type-exam flex-type-exam">-->
<!--            <span class="text-button-exam">Theory 1</span>-->
<!--          </div>-->
<!--          <span class="text-button-exam flex-date">9:00 1/5/2025</span>-->
<!--          <span class="text-button-exam flex-status color-status-complete">Completed</span>-->
<!--        </button>-->
      </section>
    </main>
    <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';

</style>