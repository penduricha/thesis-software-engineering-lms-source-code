
<script>
import './bank-exams.scss';
import '../main/list-courses.scss';
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";
import ModalCreateQuestion from "@/pages/bank-exams/ModalCreateQuestion.vue";
import '../detail-course/course-manage.scss';
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";
import ModalUpdateQuestion from "@/pages/bank-exams/ModalUpdateQuestion.vue";
import ModalDeleteQuestion from "@/pages/bank-exams/ModalDeleteQuestion.vue";
import SessionStorageQuestionJavaCoreChoose
  from "@/pages/bank-exams/create-exam-with-choose-questions/SessionStorageQuestionJavaCoreChoose.js";
import SessionStorageTestCase from "@/pages/bank-exams/SessionStorageTestCase.js";

export default {
  name: "BankExams",

  components: {
    ModalDeleteQuestion,
    ModalUpdateQuestion,
    ModalCreateQuestion,
    AsideAccount, AsideMenu
  },

  data() {
    return {
      searchQuery: null,
      selectedTopic: "All Topics",
      bankQuestionJavaCore: [],

      filteredQuestions: [],
      selectedQuestions: [],
    };
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setListBankQuestionJavaCore();
  },

  mounted() {
    //set filter root

  },

  methods: {
    getRoute() {
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
      const storedParameters = sessionStorage.getItem('parameters');
      // if (storedParameters) {
      //   sessionStorage.removeItem('parameters');
      // }
    },

    async setListBankQuestionJavaCore() {
      this.bankQuestionJavaCore = await BankQuestionJavaCoreDao.get_Bank_Questions_JavaCore();
      if(this.bankQuestionJavaCore.length > 0){
        this.filteredQuestions = this.bankQuestionJavaCore;
      }
    },

    filterSearchContentQuestion() {
      const query = this.searchQuery.toLowerCase().trim();
      this.filteredQuestions = this.bankQuestionJavaCore.filter(question =>
          question.contentQuestion.toLowerCase().includes(query)
      );
    },

    async handleOpenModalUpdateQuestion(question) {
      sessionStorage.removeItem("testCases");
      await this.$refs.modalUpdateQuestion.setAllInput(question);
    },

    handleDeleteQuestion(questionJavaCoreID){
      this.$refs.modalDeleteQuestion.setQuestionJavaCoreID(questionJavaCoreID);
    },

    navigateTo_CreateExam() {
      this.$router.replace({
        path: '/main-page/bank-exams/java-core/create-exam-choose',
        query: {
          //selectedQuestionIDs: this.selectedQuestionIDs,
        }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    handleNavigateCreateExam() {
      if(this.selectedQuestions.length === 0) {
        alert("Please select a question.");
      } else {
        //don sach session
        sessionStorage.removeItem("questionsSelected");

        //tao moi
        const sessionQuestionJavaCoreChoose = new SessionStorageQuestionJavaCoreChoose();
        this.selectedQuestions.forEach((question) => {
          sessionQuestionJavaCoreChoose.addQuestion(
            question.questionJavaCoreID,
            question.contentQuestion,
            question.codeSample,
          )
        })
        this.navigateTo_CreateExam();
      }
    },

    handleOpenModalAddQuestion() {
      const testCaseManager = new SessionStorageTestCase();
      if(testCaseManager.getAllTestCases().length > 0) {
        sessionStorage.removeItem("testCases");
      }
    },
  },

  computed: {
    setActiveButtonNavTopic() {
      if(this.getRoute() === "/main-page/bank-exams/java-core"){
        return "button-nav-course-active";
      } else {
        return null;
      }
    }
  }
};
</script>

<template>
  <body>
  <AsideMenu />
  <main >
    <section class="section-banks">
      <h5>Bank Exam Java Core</h5>
      <div class="tab-container">
        <button class="button-nav-in-course"
                :class="['active-button', setActiveButtonNavTopic]"
        >Java Core</button>
        <button class="button-nav-in-course">Java Single Class</button>
        <button class="button-nav-in-course">Java Mapping Class</button>
      </div>
      <div class="row mb-3">
        <div class="col-md-4">
          <div class="input-group">
            <input type="text"
                   class="form-control"
                   placeholder="Search content question..."
                   @input="filterSearchContentQuestion()"
                   v-model="searchQuery"
            >
          </div>
        </div>
        <div class="col-md-3">
          <button class="btn btn-primary button-purple"
                  data-bs-toggle="modal"
                  data-bs-target="#modal-create-question"
                  @click="handleOpenModalAddQuestion()"
          >Add Question</button>
        </div>
        <div class="col-md-3">
          <button class="btn btn-primary button-purple"
                  @click="handleNavigateCreateExam()"
          >Create exam java core</button>
        </div>
      </div>

      <table class="table table-striped">
        <thead>
        <tr>
          <th>Index</th>
          <th>Content Question</th>
          <th>Edit</th>
          <th>Delete</th>
          <th>Add to create exam</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(question, index) in filteredQuestions"
            :key="question.questionJavaCoreID"
        >
          <td>{{ index + 1 }}</td>
          <td>{{ question.contentQuestion }}</td>
          <td>
            <button class="btn btn-sm btn-warning"
                    data-bs-toggle="modal"
                    data-bs-target="#modal-update-question"
                    @click="handleOpenModalUpdateQuestion(question)"
            >Edit</button>
          </td>
          <td>
            <button class="btn btn-sm btn-danger"
                    data-bs-toggle="modal"
                    data-bs-target="#modal-delete-question"
                    @click="handleDeleteQuestion(question.questionJavaCoreID)"
            >Delete</button>
          </td>
          <td class="style-column-checkbox">
            <input type="checkbox" class="style-check-box check-box-table"
                   :value="question"
                   v-model="selectedQuestions"
            />
          </td>
        </tr>
        </tbody>
      </table>
    </section>

  </main>
  <AsideAccount />
  </body>

  <modal-create-question
      v-if="bankQuestionJavaCore.length > 0"
      :bank-java-core-exam="bankQuestionJavaCore"
  />
  <modal-update-question ref="modalUpdateQuestion"
                         v-if="bankQuestionJavaCore.length > 0"
                         :bank-java-core-exam="bankQuestionJavaCore"
  />
  <modal-delete-question ref="modalDeleteQuestion"/>
</template>

<style scoped lang="scss">
@use '@/scss/main';
.style-column-checkbox {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
