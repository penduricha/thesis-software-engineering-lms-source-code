<script>
import './modal-view-result-exam-java-core.scss';
import ResultQuestionJavaCoreDao from "@/daos/ResultQuestionJavaCoreDao.js";

export default {
  name: "ModalViewResultExamJavaCore",

  props: {},

  data() {
    return {
      listQuestions: [],
    }
  },

  mounted() {

  },

  methods: {
    async setResultExamJavaCoreToModal(detailMarkStudentID) {
      // Fetch the list of questions
      this.listQuestions = await ResultQuestionJavaCoreDao.get_List_Questions_JavaCore_Exam_By_DetailMarkStudentID(detailMarkStudentID);
      // Iterate over each question to add the listResultTestCase
      for (const question of this.listQuestions) {
        question.listResultTestCase = await this.getListResultTestCases_By_ResultQuestionJavaCoreID(question.resultQuestionJavaCoreID);
      }
    },

    async getListResultTestCases_By_ResultQuestionJavaCoreID(resultQuestionJavaCoreID) {
      let list = [];
      list = await ResultQuestionJavaCoreDao
          .get_List_TestCase_Answers_By_ResultQuestionJavaCoreExamID(resultQuestionJavaCoreID);
      console.log(list);
      return list;
    },

    getPassFail(fail) {
      if (fail === true) {
        return "Fail";
      } else {
        return "Pass";
      }
    }
  },

  computed: {
    setColorPassFail() {
      return (fail) => {
        return (fail === true)
            ? 'fail-result'
            : 'pass-result';
      };
    }
  },


}
</script>

<template>
  <div class="modal fade" id="modal-detail-result-exam-java-core" tabindex="-1" aria-labelledby="modalResultLabel"
       aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content custom-modal">
        <div class="modal-title-wrapper">
          <h5 class="custom-title">Result exam</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!--          tu tu call api sau-->
          <!--          <span class="result-text">Your mark is <strong>8.00</strong>. Your correct answer(s): <strong>4/5</strong>.</span>-->
          <!-- Question 1 -->
          <div class="question-container">
            <div v-if="listQuestions.length > 0" v-for="(q, index) in listQuestions" :key="q.resultQuestionJavaCoreID">
              <h6 class="question-title">Question {{ index + 1 }}: {{ q.contentQuestion }}</h6>
              <h6 class="question-title">Code submitted:</h6>
              <p class="point"><code>{{q.codeStudentSubmitted}}</code></p>

              <div class="table-responsive">
                <table class="table table-striped custom-table">
                  <thead>
                  <tr>
                    <th>Index</th>
                    <th>Input</th>
                    <th>Output expect</th>
                    <th>Output</th>
                    <th>Result</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="(l, lIndex) in q.listResultTestCase || []" :key="lIndex">
                    <td>{{ lIndex + 1 }}</td>
                    <td>{{ l.inputTest }}</td>
                    <td>{{ l.outputExpect }}</td>
                    <td>{{ l.outputCodeStudent }}</td>
                    <td :class="['color-set', setColorPassFail(l.fail)]">{{ getPassFail(l.fail) }}</td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <p class="point">Score: {{ q.markAchieve }}/{{ q.score }}</p>
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