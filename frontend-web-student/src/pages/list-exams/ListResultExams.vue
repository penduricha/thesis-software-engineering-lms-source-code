<script>
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";
import './list-exams-components.scss';
import './list-result-exams.scss';
import MarkStudentDao from "@/daos/MarkStudentDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import ModalViewDetailMark from "@/pages/list-exams/modal-view-detail-mark/ModalViewDetailMark.vue";
import StudentDao from "@/daos/StudentDao.js";
import ExamDao from "@/daos/ExamDao.js";

export default {
  name: "ListResultExams",
  components: {
    ModalViewDetailMark,
    AsideMenu,
    AsideAccount

  },

  data(){
    return {
      listMarkStudent: [],

      studentID: null,
      courseID: null,

      //update api
      pollingInterval: null,
    }
  },

  beforeDestroy() {
    clearInterval(this.pollingInterval);
    // Dọn dẹp interval khi component bị hủy
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudentID_And_CourseID();
    this.setListMarkStudent();
  },

  mounted() {

  },

  methods: {
    //router
    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    async setListMarkStudent() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(studentID) {
        this.listMarkStudent = await MarkStudentDao.get_List_Mark_Student_By_Student_ID(studentID);
        this.pollingInterval = await MarkStudentDao.startPolling_Get_List_Mark_Student_By_Student_ID(studentID, (updatedData) => {
          this.listMarkStudent = updatedData;
        });
      }

    },

    async handleSetModal(markStudentID, examID, retake, topicExam) {
      await this.$refs.modalViewDetailMark.setListDetailMarkStudent(markStudentID, examID, retake, topicExam);
    },

    async setStudentID_And_CourseID() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(studentID) {
        this.studentID = studentID;
        let courseID = await StudentDao.getCourseID_By_StudentID(studentID);
        if(courseID) {
          this.courseID = courseID;
        }
      }
    },
  }
}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>
    <h4 class="title-menu">Exam result</h4>
    <section class="section-welcome">
      <div class="background-welcome">
        <span class="span-welcome">Hello, there are your exam results.</span>
      </div>
    </section>
    <section class="section-list-exams">
      <h4 class="h2-all-exams-style">All exams</h4>
      <div class="button-div-exam">
        <span class="text-button-exam flex-title">Title exam</span>
        <span class="text-button-exam flex-title">Topic exam</span>
        <div class="div-type-exam flex-type-exam">
          <span class="text-button-exam">Type exam</span>
        </div>
        <span class="text-button-exam flex-status">Mark</span>
      </div>
      <h5 v-if="(listMarkStudent.length === 0)" class="text-no-exam">No mark</h5>
      <button class="button-exam" v-for="l in listMarkStudent" v-if="listMarkStudent.length > 0"
              data-bs-toggle="modal"
              data-bs-target="#modal-form-detail-mark"
              @click="handleSetModal(l.markStudentID, l.examID, l.retakeExam,  l.topicExam)"
      >
        <span class="text-button-exam flex-title">{{l.titleExam}}</span>
        <span class="text-button-exam flex-title">{{l.topicExam}}</span>
        <div class="div-type-exam flex-type-exam">
          <span class="text-button-exam">{{l.typeExam}}</span>
        </div>
        <span class="text-button-exam flex-status">{{parseFloat((l.markExam).toFixed(2))}}</span>
      </button>
    </section>
  </main>
  <AsideAccount/>
  </body>
  <modal-view-detail-mark ref="modalViewDetailMark"  :course-i-d="courseID"/>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>