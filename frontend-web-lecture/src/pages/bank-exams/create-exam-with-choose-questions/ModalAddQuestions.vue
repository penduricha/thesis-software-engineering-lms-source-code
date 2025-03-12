<script>
import SessionStorageQuestionJavaCoreChoose
  from "@/pages/bank-exams/create-exam-with-choose-questions/SessionStorageQuestionJavaCoreChoose.js";
import './modal-add-question.scss';

export default {
  name: "ModalAddQuestions",

  props: {
    bankQuestionJavaCore : {
      type: Array,
      required: true,
      default: [],
    }
  },

  created() {

  },

  data() {
    return {
      filteredQuestions:[],

      tableQuestions: [],

      selectedQuestions: [],

      searchQuery: null,
    }
  },

  methods : {
    handleAddToExam() {
      if(this.selectedQuestions.length === 0) {
        alert("Please select a question.");
      } else {
        const sessionQuestionJavaCoreChoose = new SessionStorageQuestionJavaCoreChoose();
        this.selectedQuestions.forEach((question) => {
          sessionQuestionJavaCoreChoose.addQuestion(
              question.questionJavaCoreID,
              question.contentQuestion,
              question.codeSample,
          )
        })
        window.location.reload();
      }
    },

    setModalAddQuestions(){
      if (this.bankQuestionJavaCore.length > 0) {
        const sessionQuestionJavaCoreChoose = new SessionStorageQuestionJavaCoreChoose();
        const allQuestions = sessionQuestionJavaCoreChoose.getAllQuestions();
        const idsToRemove = allQuestions.map(question => question.questionJavaCoreID);

        //trich xuat data chua duoc them vao
        this.tableQuestions = this.bankQuestionJavaCore.filter(question =>
            !idsToRemove.includes(question.questionJavaCoreID)
        );
        this.filteredQuestions = this.tableQuestions;
      }
    },

    filterSearchContentQuestion() {
      const query = this.searchQuery.toLowerCase().trim();
      this.filteredQuestions = this.tableQuestions.filter(question =>
          question.contentQuestion.toLowerCase().includes(query)
      );
    }
  },
}
</script>

<template>
  <div class="modal fade" id="modal-add-questions" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Add Questions</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3 row">
            <div class="input-group">
              <input type="text"
                     class="form-control"
                     placeholder="Search content question..."
                     @input="filterSearchContentQuestion()"
                     v-model="searchQuery"
              >
            </div>
          </div>
          <div class="mb-3 row">
            <button class="button-purple button-add-questions col-sm-3 col-form-label"
              @click="handleAddToExam()"
            >
              Add to exam
            </button>
          </div>
          <div class="mb-3 row style-view-table">
            <table class="table table-striped">
              <thead>
              <tr>
                <th>Index</th>
                <th>Content Question</th>
                <th>Add</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(question, index) in filteredQuestions"
                  :key="question.questionJavaCoreID"
              >
                <td>{{ index + 1 }}</td>
                <td>{{ question.contentQuestion }}</td>
                <td class="style-column-checkbox">
                  <input type="checkbox" class="style-check-box"
                         :value="question"
                         v-model="selectedQuestions"
                  />
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
.style-view-table {
  padding-left: 1rem;
  padding-right: 1rem;
}
</style>