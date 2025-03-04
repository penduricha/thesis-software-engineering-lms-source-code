
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
    },

    async setListBankQuestionJavaCore() {
      this.bankQuestionJavaCore = await BankQuestionJavaCoreDao.get_Bank_Questions_JavaCore();
      if(this.bankQuestionJavaCore.length > 0){
        this.filteredQuestions = this.bankQuestionJavaCore;
      }
    },

    filterSearchContentQuestion() {
      const query = this.searchQuery.toLowerCase();
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
    }
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
          >Add Question</button>
        </div>
      </div>

      <table class="table table-striped">
        <thead>
        <tr>
          <th>Index</th>
          <th>Content Question</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(question, index) in filteredQuestions">
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
        </tr>
        </tbody>
      </table>
    </section>

  </main>
  <AsideAccount />
  </body>

  <modal-create-question />
  <modal-update-question ref="modalUpdateQuestion"/>
  <modal-delete-question ref="modalDeleteQuestion"/>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>
