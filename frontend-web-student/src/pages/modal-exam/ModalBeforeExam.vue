<script>
import './modal-exam-before.scss';
import ExamDao from "@/daos/ExamDao.js";
import Password from "@/models/Password.js";
import '../../components/span/span-style.scss';
import 'bootstrap/dist/js/bootstrap.js';
import '../../components/skeleton/loading-skeleton.scss';
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import RouterDao from "@/routes/RoutersDao.js";
import SessionTimeDao from "@/daos/SessionTimeDao.js";
import axios from 'axios';
import StudentDao from "@/daos/StudentDao.js";

export default {
  name: "ModalBeforeExam",

  components: {},

  props: {
    // studentID: {
    //   type: String,
    //   required: true,
    // }
  },

  created() {

  },

  mounted() {
    //this.setPasswordExam();
  },

  data() {
    return {
      passwordExam: null,
      studentID: null,

      examID: null,
      titleExam: null,
      typeExam: null,
      topicExam: null,
      nameLecture: null,
      retake: null,
      scoringMethod: null,
      numberQuestions: null,
      duration: null,
      endDate: null,
      linkPaperExam: null,
      passwordExamHashed: null,

      passwordExamInput: null,

      validatePasswordExam: null,

    }
  },

  methods: {
    async setExam_Information(examID, courseID) {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(studentID) {
        this.studentID = studentID;
      }

      console.log("Exam ID: ", examID);
      console.log("Course ID: ", courseID);
      let exam = await ExamDao.getExam_Information_Before_Exam(examID, courseID, this.studentID);
      console.log("Information exam: ", exam);
      this.examID = exam.examID;
      this.titleExam = exam.titleExam;
      this.typeExam = exam.typeExam;
      this.topicExam = exam.topicExam;
      this.nameLecture = exam.name;
      this.retake = exam.retakeExam ? "Yes" : "No";
      this.scoringMethod = exam.scoringMethod;
      this.numberQuestions = exam.numberQuestions;
      this.duration = exam.duration;
      const endDate = new Date(exam.endDate);
      this.endDate = endDate.getDate() + "/" + (endDate.getMonth() + 1) + "/" + endDate.getFullYear()
          + " " + endDate.getHours() + ":" + (endDate.getMinutes() < 10 ? `0${endDate.getMinutes()}` : endDate.getMinutes());
      this.linkPaperExam = exam.linkPaperExam;
      this.passwordExamHashed = exam.passwordExam;
    },

    preventPaste(event) {
      event.preventDefault();
    },

    setPasswordExam() {
      if (this.passwordExamInput || !this.passwordExamInput) {
        this.validatePasswordExam = null;
      }
      this.passwordExamInput = this.passwordExamInput.replace(/[^0-9]/g, '');
    },

    async navigateTo_JavaCoreExam() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      let status = await ExamDao.create_Access_Exam(studentID, this.examID);
      if(!status) {
        alert("Can't access to exam.");
      } else {
        let statusSetDateExam = await StudentDao.set_Date_Time_Start_Exam(studentID);
        if(statusSetDateExam) {
          const path = '/main-page/list-exams-page/exam-open/java-core-exam';
          sessionStorage.setItem('indexQuestion', 0);
          window.location.reload();
          this.$router.replace({
            path: path,
            query: {
              examID: this.examID,
              duration: this.duration
            }
          }).catch((error) => {
            console.error('Error navigating :', error);
            alert(error);
          });
        } else {
          alert("Failed to access to exam.")
        }
      }
    },

    async navigateTo_JavaClassExam() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      let status = await ExamDao.create_Access_Exam(studentID, this.examID);
      if(!status) {
        alert("Can't access to exam.");
      } else {
        let statusSetDateExam = await StudentDao.set_Date_Time_Start_Exam(studentID);
        if(statusSetDateExam) {
          const path = '/main-page/list-exams-page/exam-open/java-class-exam';
          sessionStorage.setItem('indexQuestion', 0);
          window.location.reload();
          this.$router.replace({
            path: path,
            query: {
              examID: this.examID,
              duration: this.duration
            }
          }).catch((error) => {
            console.error('Error navigating :', error);
            alert(error);
          });
        } else {
          alert("Failed to access to exam.")
        }
      }
    },

    async prepareNavigate() {
      if (this.topicExam === "Java core") {
        await this.navigateTo_JavaCoreExam();
      } else if (this.topicExam === "Java class") {
        await this.navigateTo_JavaClassExam();
      }
    },

    async handleGoToExam() {
      if (!this.passwordExamHashed) {
        //ktra ko cos mk thi vao
        await this.prepareNavigate();
        //ktra java oop
      } else {
        if (!this.passwordExamInput) {
          this.validatePasswordExam = "Please enter password.";
        } else {
          const passwordClass = new Password(this.passwordExamInput);
          const passwordExamInputHashed = passwordClass.xorEncryptDecrypt();
          if (this.passwordExamHashed !== passwordExamInputHashed) {
            this.validatePasswordExam = "Incorrect password";
          } else {
            await this.prepareNavigate();
          }
        }
      }
    },

    getStringMinutesByNumber(minute) {
      if(minute === 1) {
        return 'minute';
      }
      return 'minutes';
    },
  },

  computed: {}
}


// function hideModal() {
//   const modal = bootstrap.Modal.getInstance('#modal-java-core-before-exam');
//   modal.hide();
// }
</script>

<template>

  <div
      class="modal fade"
      id="modal-java-core-before-exam"
      tabindex="-1"
      aria-hidden="true"
  >
    <div class="modal-dialog modal-md">
      <div class="modal-content content-info-exam">
        <div class="modal-header">
          <h5 class="modal-title">
            Exam detail
          </h5>
          <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
          ></button>
        </div>
        <div class="modal-body modal-exam-body">
          <label><span>{{ titleExam }}</span></label>
          <label><span>Type exam: {{ typeExam }}</span></label>
          <label><span>Created by: {{ nameLecture }}</span></label>
          <label><span>Retake: {{ retake }}</span></label>
          <label v-if="scoringMethod"><span>Scoring method: {{ scoringMethod }}</span></label>
          <label v-if="this.topicExam === 'Java core'"><span>Questions: {{ numberQuestions }}</span></label>
          <label><span>Time: {{ duration }} {{getStringMinutesByNumber(duration)}}</span> </label>
          <label><span class="text-danger">Deadline: {{ endDate }}</span></label>
          <label v-if="linkPaperExam"><span>Link paper exam: {{ linkPaperExam }}</span></label>
          <div v-if="passwordExamHashed" class="mt-2">
            <input placeholder="Input password to do exam:"
                   type="password"
                   class="form-control mb-5 input-exam"
                   :class="[{'is-invalid': validatePasswordExam !== null}]"
                   v-model="passwordExamInput"
                   @paste="preventPaste($event)"
                   @input="setPasswordExam()"
                   maxlength="10"
            />
            <span
                v-if="validatePasswordExam"
                class="span-validate-modal-form"
            >{{ validatePasswordExam }}</span>
          </div>
          <button class="btn-do-exam"
                  type="submit"
                  @click="handleGoToExam()"
          >
            Do exam
          </button>
        </div>
      </div>
    </div>
  </div>


</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>