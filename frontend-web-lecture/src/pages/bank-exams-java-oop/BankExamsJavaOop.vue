<script>
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import NavBarBankExam from "@/pages/bank-exams-nav-bar/NavBarBankExam.vue";
import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";
import ModalCreateTestJavaOop from "@/pages/bank-exams-java-oop/create-test/ModalCreateTestJavaOop.vue";

import ModalDeleteTestJavaOop from "@/pages/bank-exams-java-oop/delete-test/ModalDeleteTestJavaOop.vue";
import ModalCreateExamJavaClass
  from "@/pages/bank-exams-java-oop/modal-create-exam-java-class/ModalCreateExamJavaClass.vue";

export default {
  name: "BankExamsJavaOop",

  components: {
    ModalCreateExamJavaClass,
    ModalDeleteTestJavaOop,
    ModalCreateTestJavaOop,
    NavBarBankExam,
    AsideAccount, AsideMenu

  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setListTestJavaOop();
  },

  mounted() {

  },

  data() {
    return {
      listTestJavaOop: [],

      listNameTestJavaOop: [],

      searchQuery: null,
      filteredQuestions: [],
    }
  },

  methods: {
    getRoute() {
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    async setListTestJavaOop() {
      this.listTestJavaOop = await BankTestJavaOopDao.get_List_Java_Test_Oop();
      if(this.listTestJavaOop.length > 0) {
        this.filteredQuestions = this.listTestJavaOop;
        this.listNameTestJavaOop = this.listTestJavaOop.map(e => e.nameTest);
        console.log("List name title java oop: ", this.listNameTestJavaOop);
      }
      if(this.listTestJavaOop.length === 0) {
        alert("No test exam java oop.");
      }
    },

    filterSearchContentQuestion() {
      const query = this.searchQuery.toLowerCase().trim();
      this.filteredQuestions = this.listTestJavaOop.filter(l =>
            l.nameTest.toLowerCase().includes(query));
    },

    handleSetModalCreateExamJavaOop (bankTestJavaOopID){
      this.$refs.modalCreateExamJavaClass.setBankTestJavaOopID(bankTestJavaOopID);
    },

    handleSetModalDelete(bankTestJavaOopID) {
      this.$refs.modalDeleteTestJavaOop.setBankTestJavaOopID(bankTestJavaOopID);
    },

    handleNavigateToUpdateTest(bankTestJavaOopID) {
      this.$router.push({
        path: '/main-page/bank-exams/java-oop/page-update',
        query: {
          bankTestJavaOopID: Number(bankTestJavaOopID),
        }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },
  },

  computed: {
    async setActiveButtonNavTopic() {

    },
  }
}
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
                   placeholder="Search name exam..."
                   @input="filterSearchContentQuestion"
                   v-model="searchQuery"
            >
          </div>
        </div>
        <div class="col-md-3">
          <button class="btn btn-primary button-purple"
                  data-bs-toggle="modal"
                  data-bs-target="#modal-create-test-java-oop"
          >Add test exam</button>
        </div>
      </div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>Index</th>
          <th>Name test</th>
          <th>Edit</th>
          <th>Delete</th>
          <th>Create exam</th>
        </tr>
        </thead>
        <tbody>
          <tr v-if="filteredQuestions.length > 0" v-for="(l, index) in filteredQuestions">
            <td>
              {{index + 1}}
            </td>
            <td>
              {{l.nameTest}}
            </td>
            <td>
              <button class="btn btn-sm btn-warning"
                     @click="handleNavigateToUpdateTest(l.bankTestJavaOopID)"
              >Edit</button>
            </td>
            <td>
              <button class="btn btn-sm btn-danger"
                      data-bs-toggle="modal"
                      data-bs-target="#modal-delete-test-java-oop"
                      @click="handleSetModalDelete(l.bankTestJavaOopID)"
              >Delete</button>
            </td>
            <td>
              <button class="btn btn-sm btn-primary"
                      data-bs-toggle="modal"
                      data-bs-target="#modal-create-exam-java-class"
                      @click="handleSetModalCreateExamJavaOop(l.bankTestJavaOopID)"
              >Create to exam</button>
            </td>
          </tr>
        </tbody>
      </table>
    </section>

  </main>
  <AsideAccount />
  </body>
  <modal-create-test-java-oop  :list-name-test-java-oop="listNameTestJavaOop"/>
  <modal-delete-test-java-oop ref="modalDeleteTestJavaOop"/>
  <modal-create-exam-java-class ref="modalCreateExamJavaClass"/>
</template>

<style scoped lang="scss">
@use "@/scss/main";
</style>