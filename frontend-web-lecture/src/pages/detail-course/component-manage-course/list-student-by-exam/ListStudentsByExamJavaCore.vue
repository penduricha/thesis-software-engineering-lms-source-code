<script>
import './list-students-by-exam-java-core.scss'
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import NavBarBankExam from "@/pages/bank-exams-nav-bar/NavBarBankExam.vue";
export default {
  name: "ListStudentsByExamByJavaCore",
  components: {NavBarBankExam, AsideAccount, AsideMenu},

  props: {
    examID: {
      type: Number,
      required: true,
    },

    courseID: {
      type: Number,
      required: true,
    },

  },

  create() {
    this.saveRouter_Path(this.getRoute());
    this.setListStudents_By_ExamID();

  },

  mounted() {

  },

  methods : {
    async setListStudents_By_ExamID () {

    },

    getRoute() {
      //ở đây có props thì phải thêm path của props
      console.log(this.$route.path);
      console.log(this.$route.path
          + "?" + "examID=" + this.examID
          + "&" + "courseID=" + this.courseID);
      return this.$route.path
          + "?" + "examID=" + this.examID
          + "&" + "courseID=" + this.courseID;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    navigateTo_Course() {
      this.$router.push({
        path: '/main-page/list-courses/course-manage',
        query: {
          courseID: this.courseID,
        }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },
  },

  computed: {

  }
}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>
    <section class="section-banks">
      <div class="view-back-list-course">
        <button class="button-return-list-course"
                @click="navigateTo_Course()"
        >
          <div class="button_nav_calendar button-return-courses-image">
            <img src="@/assets/image/button_nav_left_calendar.png" alt="button nav left calendar"
                 class="style-button-nav-calendar">
          </div>
          <span class="
            span-button-return-list-course
            span-button-return-list-course-hover"
          >
            Course manage
          </span>
        </button>
      </div>
      <h5>List students: </h5>

    </section>
  </main>
  <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">

</style>