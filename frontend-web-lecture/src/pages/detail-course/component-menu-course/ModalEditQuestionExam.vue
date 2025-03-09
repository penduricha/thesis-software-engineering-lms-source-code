<script>
import './modal-edit-question-exam.scss';
import QuestionJavaCoreExamDao from "@/daos/QuestionJavaCoreExamDao.js";

export default {
  name: "ModalUpdateQuestionExam",

  mounted() {

  },

  data() {
    return {
      questionJavaCoreExams: [],

      bankQuestionJavaCoreExam: [],

      tableQuestionJavaCoreExamChoose: [],

      numbersScore: Array.from({ length: 10 }, (_, i) => i + 1),
    }
  },

  methods: {
    async setQuestions(examID, topicExam) {
      if(topicExam === "Java core") {
        this.questionJavaCoreExams = await
            QuestionJavaCoreExamDao.getQuestions_By_ExamID(examID);
        console.log("List questions java core in exam: ", this.questionJavaCoreExams);
      }
    }
  }
}
</script>

<template>
  <div
      class="modal fade"
      id="modal-update-questions-exam"
      tabindex="-1"
      aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content modal-content-create-exam">
        <div class="modal-header">
          <h5 class="modal-title">
            Update Questions Exam
          </h5>
          <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <div>
            <div class="mb-3 row">

            </div>


          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>