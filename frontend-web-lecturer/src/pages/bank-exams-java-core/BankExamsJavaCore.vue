
<script>
import './bank-exams.scss';
import '../main/list-courses.scss';
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";
import ModalCreateQuestion from "@/pages/bank-exams-java-core/create-question/ModalCreateQuestion.vue";
import '../detail-course/course-manage.scss';
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";
import ModalUpdateQuestion from "@/pages/bank-exams-java-core/update-question/ModalUpdateQuestion.vue";
import ModalDeleteQuestion from "@/pages/bank-exams-java-core/delete-question/ModalDeleteQuestion.vue";
import SessionStorageQuestionJavaCoreChoose
  from "@/pages/bank-exams-java-core/create-exam-with-choose-questions/SessionStorageQuestionJavaCoreChoose.js";
import SessionStorageTestCase from "@/pages/bank-exams-java-core/SessionStorageTestCase.js";
import NavBarBankExam from "@/pages/bank-exams-nav-bar/NavBarBankExam.vue";

export default {
  name: "BankExams",

  components: {
    NavBarBankExam,
    ModalDeleteQuestion,
    ModalUpdateQuestion,
    ModalCreateQuestion,
    AsideAccount, AsideMenu
  },

  data() {
    return {
      searchQuery: null,
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
      // const storedParameters = sessionStorage.getItem('parameters');
      // // if (storedParameters) {
      // //   sessionStorage.removeItem('parameters');
      // // }
    },

    async setListBankQuestionJavaCore() {
      this.bankQuestionJavaCore = await BankQuestionJavaCoreDao.get_Bank_Questions_JavaCore();
      if(this.bankQuestionJavaCore.length > 0){
        this.filteredQuestions = this.bankQuestionJavaCore;
      }
      if(this.bankQuestionJavaCore.length === 0) {
        alert("No question java core.");
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
      this.$router.push({
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
      if(this.selectedQuestions.length <= 0) {
        alert("Please select a question.");
      } else {
        //don sach session
        sessionStorage.removeItem("questionsSelected");
        let score = 10.0 / this.selectedQuestions.length;
        let roundedScore = Math.round(score * 4) / 4;
        //tao moi
        const sessionQuestionJavaCoreChoose = new SessionStorageQuestionJavaCoreChoose();
        this.selectedQuestions.forEach((question) => {
          sessionQuestionJavaCoreChoose.addQuestion(
            question.questionJavaCoreID,
            question.contentQuestion,
            question.codeSample,
            Number(roundedScore),
          )
        })
        this.navigateTo_CreateExam();
      }
    },

    handleNavigateCreateExamJavaCoreRandom() {
      this.$router.push({
        path: '/main-page/bank-exams/java-core/create-exam-random',
        query: {
          //selectedQuestionIDs: this.selectedQuestionIDs,
        }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
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
      if(this.getRoute().includes("/main-page/bank-exams/java-core")){
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
      <h5>Bank Exam</h5>
      <nav-bar-bank-exam />
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
          >Add question</button>
        </div>
        <div class="col-md-5">
          <button class="btn btn-primary button-purple"
                  @click="handleNavigateCreateExam()"
          >Create exam java core</button>
        </div>
      </div>
      <div class="row-mb-3">
        <button class="btn btn-primary button-purple"
                @click="handleNavigateCreateExamJavaCoreRandom()"
        >Create exam java core by random</button>
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
