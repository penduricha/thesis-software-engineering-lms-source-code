
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
    return {
      searchQuery: "",
      selectedTopic: "All Topics",
      questions: [
        { id: 1, content: "Write a function to find the sum of two numbers.", topic: "Java Core" },
        { id: 2, content: "Explain the concept of polymorphism in Java.", topic: "Java Core" }
      ],
      newQuestion: {
        content: "",
        codeSample: "",
        testType: "Regular Test",
        topic: "Java Core",
        testCases: []
      },
      showTestCaseSection: false,
      newTestCase: {
        input: "",
        output: "",
        note: ""
      }
    };
      filteredQuestions: [],
      selectedQuestions: [],
    };
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setListBankQuestionJavaCore();
  },

  mounted() {

  },

  methods: {
    getRoute() {
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },
  },

  setup() {

  },

  computed: {

  }
}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>

  </main>
  <AsideAccount/>
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
