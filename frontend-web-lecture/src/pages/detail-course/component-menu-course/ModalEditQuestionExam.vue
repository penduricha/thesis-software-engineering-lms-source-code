<script>
import './modal-edit-question-exam.scss';
import QuestionJavaCoreExamDao from "@/daos/QuestionJavaCoreExamDao.js";
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";
import ExamDao from "@/daos/ExamDao.js";

export default {
  name: "ModalUpdateQuestionExam",

  mounted() {

  },

  data() {
    return {
      questionJavaCoreExams: [],

      bankQuestionJavaCore: [],

      tableQuestions: [],
      validateScoreTotal: null,

      filteredQuestions: [],

      //numbersScore: Array.from({length: 10}, (_, i) => i + 1),

      searchQuery: null,

      examID: null,
    }
  },

  methods: {
    async setQuestions(examID, topicExam) {
      this.examID = examID;
      if (topicExam === "Java core") {
        this.questionJavaCoreExams = await
            QuestionJavaCoreExamDao.getQuestions_By_ExamID(examID);
        console.log("List questions java core in exam: ", this.questionJavaCoreExams);

        this.bankQuestionJavaCore = await BankQuestionJavaCoreDao.get_Bank_Questions_JavaCore();

        if(this.bankQuestionJavaCore.length > 0 && this.questionJavaCoreExams.length > 0) {
          const idsToRemove = this.questionJavaCoreExams.map(question => question.questionJavaCoreID);
          this.tableQuestions = this.bankQuestionJavaCore.filter(question =>
              !idsToRemove.includes(question.questionJavaCoreID)
          );
          this.filteredQuestions = this.tableQuestions;
        }

        console.log("List questions java core in bank: ", this.tableQuestions);
      }
    },

    filterSearchContentQuestion() {
      const query = this.searchQuery.toLowerCase().trim();
      this.filteredQuestions = this.tableQuestions.filter(question =>
          question.contentQuestion.toLowerCase().includes(query)
      );
    },

    getTotalScoreQuestionJavaCoreExams() {
      if(this.questionJavaCoreExams.length > 0) {
        return this.questionJavaCoreExams.reduce((accumulator, question) => {
          return accumulator + question.score;
        }, 0);
      } else {
        return 0;
      }
    },

    handleAddQuestion(question, index) {

      if(this.getTotalScoreQuestionJavaCoreExams() >= 10) {
        alert("Total score must be 10.");
      } else {
        if (index >= 0 && index < this.tableQuestions.length) {
          this.tableQuestions.splice(index, 1);
          const newQuestionExam = {
            questionJavaCoreID: question.questionJavaCoreID,
            contentQuestion: question.contentQuestion,
            codeSample: question.codeSample,
            //dong nhat field
            questionJavaCoreExamID: null,
            //set diem là 10 - tong diem
            score: 10 - this.getTotalScoreQuestionJavaCoreExams(),
          }
          this.questionJavaCoreExams.push(newQuestionExam);
        }
        //cap nhat bang lai
        this.filteredQuestions = this.tableQuestions;
      }
    },

    async handleUpdateQuestionExam(){
      if(this.questionJavaCoreExams.length === 0){
        alert("Please add question.");
      }else if(this.getTotalScoreQuestionJavaCoreExams() < 10) {
        alert("Total score must be 10.");
      } else {
        if(!this.validateScoreTotal) {
          let statusStudentAccess = await ExamDao
              .getStatus_Access_Student_To_Exam_By_ExamID(this.examID);
          if(!statusStudentAccess) {
            console.log("Data to put: ", this.questionJavaCoreExams);
            if(this.examID) {
              let statusPut = await QuestionJavaCoreExamDao.update_Questions_JavaCoreExam(this.questionJavaCoreExams, this.examID);
              if(statusPut) {
                window.location.reload()
              } else {
                alert("Update failed.");
              }
            }
          } else {
            alert("There is student is currently accessing the test. Please wait until finish.");
          }
        }
      }
    },

    updateScoreByIndex(index, score) {
      if(this.getTotalScoreQuestionJavaCoreExams() > 10) {
        this.validateScoreTotal = "Total score can't greater than 10.";
      } else {
        this.validateScoreTotal = null;
      }
    },

    totalScoreExceptCurrent(index) {
      // Tính tổng điểm của các câu hỏi ngoại trừ câu hỏi hiện tại
      /*ràng buộc input number đáp ứng số điểm tổng === 10 */
      return this.questionJavaCoreExams.reduce((total, question, i) => {
        if (i !== index) {
          return total + parseFloat(question.score) || 0;
        }
        return total;
      }, 0);
    },

    getTotalScore() {
      return this.questionJavaCoreExams.reduce((total, score) => total + score, 0);
    },

    handleDeleteQuestion(index) {
      if (index >= 0 && index < this.questionJavaCoreExams.length) {
        this.questionJavaCoreExams.splice(index, 1);
        const idsToRemove = this.questionJavaCoreExams.map(question => question.questionJavaCoreID);
        this.tableQuestions = this.bankQuestionJavaCore.filter(question =>
            !idsToRemove.includes(question.questionJavaCoreID)
        );
        //cap nhat lai ds
        this.filteredQuestions = this.tableQuestions;
      }
    },
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
          <div class="mb-3 row">
            <h5>List questions exam:</h5>
          </div>
          <div class="mb-3 row style-view-table">
            <table class="table table-striped">
              <thead>
              <tr>
                <th>Index</th>
                <th>Content Question</th>
                <th>Score</th>
                <th>Action</th>
              </tr>
              </thead>
              <tbody>
              <tr v-if="questionJavaCoreExams.length === 0">
                <td colspan="5" class="text-center">
                  <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Loading...</span>
                  </div>
                </td>
              </tr>
              <tr v-for="(question, index) in questionJavaCoreExams"
                  :key="question.questionJavaCoreID"
              >
                <td>{{ index + 1 }}</td>
                <td>{{ question.contentQuestion }}</td>
                <td class="style-column-checkbox">
                  <input
                      type="number"
                      class="form-control"
                      v-model="question.score"
                      @input="updateScoreByIndex(index, question.score)"

                      min="0.25"
                      step="0.25"
                      @keydown.prevent
                  />
<!--                  :max="10 - totalScoreExceptCurrent(index)"-->
                </td>
                <td>
                  <button class="btn btn-sm btn-danger"
                          @click="handleDeleteQuestion(index)">
                    Delete
                  </button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>

          <span
              v-if="(getTotalScoreQuestionJavaCoreExams() > 10)"
              class="span-validate-modal-form"
          >{{validateScoreTotal}}</span>

          <div class="mb-3 row" style="display: flex; justify-content: center;">
            <button class="button-purple btn-edit-questions"
                    @click="handleUpdateQuestionExam()"
            >Update questions exam</button>
          </div>
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
            <h5>List questions bank:</h5>
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
              <tr v-if="filteredQuestions.length === 0">
                <td colspan="5" class="text-center">
                  <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Loading...</span>
                  </div>
                </td>
              </tr>
              <tr v-for="(question, index) in filteredQuestions"
                  :key="question.questionJavaCoreID"
              >
                <td>{{ index + 1 }}</td>
                <td>{{ question.contentQuestion }}</td>
                <td>
                  <button class="button-purple btn-add-modal-edit-questions"
                          @click="handleAddQuestion(question, index)"
                  >Add</button>
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
.table {
  width: 100%;
}
.table th,
.table td {
  text-align: left;
}
.table th:nth-child(1),
.table td:nth-child(1) {
  width: 10%; /* Ratio 1 */
}
.table th:nth-child(2),
.table td:nth-child(2) {
  width: 30%; /* Ratio 3 */
}
.table th:nth-child(3),
.table td:nth-child(3) {
  width: 10%; /* Ratio 2 */
}
.table th:nth-child(4),
.table td:nth-child(4) {
  width: 10%; /* Ratio 1 */
}
</style>