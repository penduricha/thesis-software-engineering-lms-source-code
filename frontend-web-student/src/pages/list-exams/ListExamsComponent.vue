<script>
import './list-exams-components.scss';
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";
import ExamDao from "@/daos/ExamDao.js";
import ModalBeforeExam from "@/pages/modal-exam/ModalBeforeExam.vue";
import ModalExamLocked from "@/pages/modal-exam/ModalExamLocked.vue";
import ModalExamOverdue from "@/pages/modal-exam/ModalExamOverdue.vue";
import SessionTimeDao from "@/daos/SessionTimeDao.js";
import axios from "axios";
import MarkStudentDao from "@/daos/MarkStudentDao.js";

export default {
  name: 'ListExamsComponents',
  components: {
    ModalExamOverdue,
    ModalExamLocked,
    ModalBeforeExam,
    AsideMenu,
    AsideAccount

  },

  data(){
    return {
      studentID: null,
      courseID: null,

      //exam
      exams: [],

      //polling
      pollingInterval: null,
    }
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudentID_And_CourseID_And_Exams();

    //this.set_Zero_If_Exam_Overdue();
  },

  mounted() {

    this.checkStatusDoExam();
  },

  beforeDestroy() {
    clearInterval(this.pollingInterval);
    // Dọn dẹp interval khi component bị hủy
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
          this.exams = await ExamDao.getExams_By_CourseID(courseID, this.studentID);
          this.pollingInterval = await ExamDao.startPolling_GetExams_By_CourseID(courseID, studentID,(updatedExams) => {
            this.exams = updatedExams;
            // Cập nhật danh sách bài kiểm tra
          });
          console.log("Exams: ", this.exams);
          if(this.exams.length > 0) {
            for (const e of this.exams) {
              if(e.status === "Overdue") {
                try{
                  //set 0 if exam overdue
                  let statusResponseSet =
                      await MarkStudentDao.set_Mark_Student_Zero_If_Exam_Overdue(studentID, Number(e.examID));
                  console.log("Set 0.0 with examID is: ",statusResponseSet);
                }catch(err) {
                  console.error(err);
                  alert(err);
                }
              }
            }
          }
        }
      }
    },

    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
    },

    async checkStatusDoExam() {
      const studentLocalStorage = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      //kiem tra co lam bai ktra hay ko
      let informationDoExam = await StudentDao.get_Information_Student_Access_Exam_And_RemainMinutes(studentID);

      if(informationDoExam.examID !== undefined) {
        //get them topic exam
        //navigate
        let topicExam = await ExamDao.get_TopicExam_By_ExamID(Number(informationDoExam.examID));

        if(topicExam === "Java core") {
          const pathJavaCoreExam = '/main-page/list-exams-page/exam-open/java-core-exam';
          sessionStorage.setItem('indexQuestion', 0);
          window.location.reload();
          if(Number(informationDoExam.remainMinutes) <=0) {
            this.$router.replace({
              path: pathJavaCoreExam,
              query: {
                examID: informationDoExam.examID,
                duration: 0,
              }
            }).catch((error) => {
              console.error('Error navigating :', error);
              alert(error);
            });
          } else {
            this.$router.replace({
              path: pathJavaCoreExam,
              query: {
                examID: informationDoExam.examID,
                duration: Number(informationDoExam.remainMinutes)
              }
            }).catch((error) => {
              console.error('Error navigating :', error);
              alert(error);
            });
          }
        } else if(topicExam === "Java class") {
          const pathJavaClassExam = '/main-page/list-exams-page/exam-open/java-class-exam';
          sessionStorage.setItem('indexQuestion', 0);
          window.location.reload();
          if(Number(informationDoExam.remainMinutes) <=0) {
            this.$router.replace({
              path: pathJavaClassExam,
              query: {
                examID: informationDoExam.examID,
                duration: 0,
              }
            }).catch((error) => {
              console.error('Error navigating :', error);
              alert(error);
            });
          } else {
            this.$router.replace({
              path: pathJavaClassExam,
              query: {
                examID: informationDoExam.examID,
                duration: Number(informationDoExam.remainMinutes)
              }
            }).catch((error) => {
              console.error('Error navigating :', error);
              alert(error);
            });
          }
        }

      }
    },

    async saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
      //remove session, local storage;
      if (sessionStorage.getItem('indexQuestion')) {
        sessionStorage.removeItem('indexQuestion');
      }
    },

    async handButtonClick(status, exam) {
      if(status === "Open"){
        if(this.courseID)
          await this.handleGoToModalExamBefore(exam.examID, this.courseID);
      }
    },

    async handleGoToModalExamBefore(examID, courseID) {
      await this.$refs.modalExamBefore.setExam_Information(examID, courseID);
    },

    // getStatusExam(e) {
    //   if(this.exams.length > 0) {
    //     const dateTimeNow = new Date();
    //     const startDate = new Date(e.startDate);
    //     const endDate = new Date(e.endDate);
    //     if(dateTimeNow < startDate) {
    //       return "Locked";
    //     } else if(dateTimeNow > endDate) {
    //       return "Overdue";
    //     } else if((dateTimeNow >= startDate) && (dateTimeNow <= endDate)) {
    //       return "Open";
    //     }
    //     return null;
    //   }
    //   return null;
    // },

    getModalIDToOpen(status) {
      if(status === "Open"){
        return "#modal-java-core-before-exam";
      } else if(status === "Locked") {
        return "#modal-exam-locked";
      } else if(status === "Overdue") {
        return "#modal-exam-overdue";
      }
      return null;
    },
  },

  computed: {
    setColorButton() {
      return (e) => {
        const status = e.status;
        if (status === "Locked" || status === "Overdue") {
          return "color-status-lock";
        } else if (status === "Open") {
          return "color-status-open";
        } else if (status === "Completed") {
          return "color-status-complete";
        }
      };
    },

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
          <span class="text-button-exam flex-title">Topic exam</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">Type exam</span>
          </div>
          <span class="text-button-exam flex-date">Date open</span>
          <span class="text-button-exam flex-status">Status</span>
        </div>
        <h5 v-if="(exams.length === 0)" class="text-no-exam">No exam</h5>
        <!--        Cần xét điều kiện-->
        <button class="button-exam"
                v-if="exams.length > 0"
                v-for="e in exams"
                data-bs-toggle="modal"
                :data-bs-target="getModalIDToOpen(e.status)"
                @click="handButtonClick(e.status, e)"
        >
          <span class="text-button-exam flex-title">{{e.titleExam}}</span>
          <span class="text-button-exam flex-title">{{e.topicExam}}</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">{{e.typeExam}}</span>
          </div>
          <span class="text-button-exam flex-date">
            {{new Date(e.startDate).getHours()}}:{{( new Date(e.startDate).getMinutes() < 10 ? `0${new Date(e.startDate).getMinutes()}` : new Date(e.startDate).getMinutes())}}
            {{new Date(e.startDate).getDate()}}/{{new Date(e.startDate).getMonth() + 1}}/{{new Date(e.startDate).getFullYear()}}
          </span>
          <span class="text-button-exam flex-status"
                :class="['span-status-color',setColorButton(e)]"
          >
            {{e.status}}
          </span>
        </button>
      </section>
    </main>
    <AsideAccount @handleButtonClick="handButtonClick"/>
  </body>
  <modal-before-exam ref="modalExamBefore"/>
  <modal-exam-locked ref="modalExamLocked"/>
  <modal-exam-overdue ref="modalExamOverdue"/>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>