<script>
import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";

export default {
  name: "ModalEditJavaClassExam",

  components: {

  },

  props: {

  },

  data() {
    return {
      searchQuery: null,
      filteredTests: [],
      listTestJavaOop: [],
      tableData: [],

      examID: null,
      nameTest: null,
    }
  },

  created() {

  },

  mounted() {

  },

  methods: {
    async setListTestJavaOop(examID, topicExam) {
      if (topicExam === 'Java class') {
        this.listTestJavaOop = await BankTestJavaOopDao.get_List_Java_Test_Oop();
        if (this.listTestJavaOop.length > 0) {
          let bankTestJavaOopID = await BankTestJavaOopDao.getBankTestJavaOopID_By_ExamID(examID);
          this.examID = examID;
          if(bankTestJavaOopID) {
            let test = await BankTestJavaOopDao.getBankTestJavaOopID_By_BankTestJavaOopID(bankTestJavaOopID);
            //console.log("Test java oop: ", test);
            if(test) {
              this.nameTest = test.nameTest;
            }
          }
          this.listTestJavaOop = this.listTestJavaOop.filter(e => e.bankTestJavaOopID !== bankTestJavaOopID);
          this.tableData = this.listTestJavaOop;
          this.filteredTests = this.tableData;
        }
      }
    },

    filterSearchNameTest() {
      const query = this.searchQuery.toLowerCase().trim();
      this.filteredTests = this.tableData.filter(f =>
          f.nameTest.toLowerCase().includes(query)
      );
    },

    async handleSetToExam(bankTestJavaOopID) {
      if(this.examID) {
        let status = await BankTestJavaOopDao.updateBankTestJavaOopByExamID(this.examID, bankTestJavaOopID);
        if(status) {
          alert("Set test java class successfully.");
          window.location.reload();
        } else {
          alert("Set failed");
        }
      }
    },

    clearData() {
      this.searchQuery = null;
      this.filteredTests = [];
      this.listTestJavaOop = [];
      this.tableData = [];
      this.examID = null;
      this.nameTest = null;
    },
  }
}
</script>

<template>
  <div
      class="modal fade"
      id="modal-update-test-java-class"
      tabindex="-1"
      aria-hidden="true"
      @click.self = "clearData"
  ><div class="modal-dialog modal-lg">
    <div class="modal-content modal-content-create-exam">
      <div class="modal-header">
        <h5 class="modal-title">
          Update test java class exam
        </h5>
        <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="clearData"
        ></button>
      </div>
      <div class="modal-body">
        <div class="mb-3 row">
          <h5>List test java class</h5>
          <h6 class="modal-title">
            Name of test: {{nameTest}}
          </h6>
        </div>
        <div class="mb-3 row">
          <div class="input-group">
            <input type="text"
                   class="form-control"
                   placeholder="Search content question..."
                   @input="filterSearchNameTest()"
                   v-model="searchQuery"
            >
          </div>
        </div>
        <div class="mb-3 row style-view-table">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Index</th>
              <th>Name test</th>
              <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr v-if="tableData.length === 0">
              <td colspan="5" class="text-center">
                <div class="spinner-border text-primary" role="status">
                  <span class="visually-hidden">Loading...</span>
                </div>
              </td>
            </tr>
            <tr v-if="filteredTests.length > 0" v-for="(l, index) in filteredTests">
              <td>
                {{index + 1}}
              </td>
              <td>
                {{l.nameTest}}
              </td>
              <td>
                <button class="btn btn-primary"
                        @click="handleSetToExam(l.bankTestJavaOopID)"
                >Set to exam</button>
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
</style>