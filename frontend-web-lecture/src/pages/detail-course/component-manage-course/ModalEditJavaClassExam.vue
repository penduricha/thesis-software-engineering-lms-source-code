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

    }
  },

  created() {

  },

  mounted() {

  },

  methods: {
    async setListTestJavaOop(examID, topicExam) {
      if(topicExam === 'Java class') {
        this.listTestJavaOop = await BankTestJavaOopDao.get_List_Java_Test_Oop();
        if(this.listTestJavaOop.length > 0) {
          this.listNameTestJavaOop = this.listTestJavaOop.filter(e => e.bankTestJavaOopID !== bankTestJavaOopID)
          if(this.listTestJavaOop.length > 0) {
            this.filteredQuestions = this.listTestJavaOop;
          }
        }
      }
    },

    filterSearchNameTest() {

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
        ></button>
      </div>
      <div class="modal-body">
        <div class="mb-3 row">
          <h5>List test java class:</h5>
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
              <th>Edit</th>
              <th>Delete</th>
              <th>Create exam</th>
            </tr>
            </thead>
            <tbody>
            <tr v-if="filteredTests.length > 0" v-for="(l, index) in filteredTests">
              <td>
                {{index + 1}}
              </td>
              <td>
                {{l.nameTest}}
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