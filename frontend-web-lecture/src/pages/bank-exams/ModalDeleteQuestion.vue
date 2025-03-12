<script>
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";

export default {
  name: "ModalDeleteQuestion",

  props : {

  },

  data() {
    return {
      questionJavaCoreID: null,
    }
  },

  methods: {
    setQuestionJavaCoreID(questionJavaCoreID) {
      this.questionJavaCoreID = questionJavaCoreID;
    },

    async handleDeleteQuestion() {
      console.log("questionJavaCoreID: ", this.questionJavaCoreID);
      let statusDelete = await BankQuestionJavaCoreDao.delete_Question_JavaCore(this.questionJavaCoreID);
      if(statusDelete) {
        window.location.reload();
      } else {
        alert("Deleted question failed.");
      }
    },
  }
}
</script>

<template>
  <div class="modal fade" id="modal-delete-question" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
      <div class="modal-content modal-content-dialog-delete">
        <div class="modal-header border-0 text-center">
          <h5 class="modal-title w-100 modal-title-dialog-delete" id="deleteConfirmModalLabel">
            <i class="fa-solid fa-circle-exclamation"></i>
          </h5>
        </div>
        <div class="modal-body text-center">
          <span class="delete-confirm-text">Are you sure to delete this question.</span>
        </div>
        <div class="modal-footer d-flex justify-content-center border-0">
          <button type="button" class="btn btn-confirm-yes"
                  @click="handleDeleteQuestion()"
          >Yes</button>
          <button type="button" class="btn btn-cancel-no" data-bs-dismiss="modal">No</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>