<script>
import './modal-exam-before.scss';
import ExamDao from "@/daos/ExamDao.js";

export default {
  name: "ModalBeforeExam",

  components: {

  },

  props: {

  },

  created() {

  },

  mounted() {

  },

  data() {
    return {
      passwordExam: null,

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
    }
  },

  methods: {
    async setExam_Information(examID, courseID) {
      let exam = await ExamDao.getExam_Information_Before_Exam(examID, courseID);
      console.log("Information exam: ", exam);
      this.examID = exam.examID;
      this.titleExam = exam.titleExam;
      this.typeExam = exam.typeExam;
      this.topicExam = exam.topicExam;
      this.nameLecture = exam.name;
      this.retake = exam.retake ? "Yes" : "No";
      this.scoringMethod = exam.scoringMethod;
      this.numberQuestions = exam.numberQuestions;
      this.duration = exam.duration;
      const endDate = new Date(exam.endDate);
      this.endDate = endDate.getDate() + "/" + (endDate.getMonth() + 1) + "/" + endDate.getFullYear()
          + " " + endDate.getHours() + ":" + endDate.getMinutes();
      this.linkPaperExam = exam.linkPaperExam;
      this.passwordExamHashed = exam.passwordExamHashed;
    },

    preventPaste(event) {
      event.preventDefault();
    },

    setPasswordExam() {
      this.passwordExamInput = this.passwordExamInput.replace(/[^0-9]/g, '');
    },

    navigateTo_JavaCoreExam() {
      this.$router.replace({
        path: '/main-page/list-exams-page/exam-open/java-core-exam',
        query: {
          examID: this.examID,
          duration: this.duration
        }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    handleGoToExam() {
      if(!this.passwordExamHashed) {
        //ktra ko cos mk thi vao
        if(this.topicExam === "Java core") {
          this.navigateTo_JavaCoreExam();
        }
      }
    },
  },

  computed: {

  }
}
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
        <div class="modal-body modal-exam-body">
          <label><span>{{titleExam}}</span></label>
          <label><span>Type exam: {{typeExam}}</span></label>
          <label><span>Created by: {{nameLecture}}</span></label>
          <label><span>Retake: {{retake}}</span></label>
          <label v-if="scoringMethod"><span>Scoring method: {{scoringMethod}}</span></label>
          <label><span>Questions: {{this.numberQuestions}}</span></label>
          <label><span>Time: {{duration}} minutes</span> </label>
          <label><span class="text-danger">Deadline: {{endDate}}</span></label>
          <label v-if="linkPaperExam"><span>Link paper exam: {{linkPaperExam}}</span></label>
          <div v-if="passwordExamHashed" class="mt-2">
            <input placeholder="Input password to do exam:"
                   type="password"
                   class="form-control mb-5 input-exam"
                   v-model="passwordExamInput"
                   @paste="preventPaste($event)"
                   @input="setPasswordExam()"
            />
          </div>
          <button class="btn-do-exam"
                  @click="handleGoToExam()"
                  data-bs-dismiss="modal"
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