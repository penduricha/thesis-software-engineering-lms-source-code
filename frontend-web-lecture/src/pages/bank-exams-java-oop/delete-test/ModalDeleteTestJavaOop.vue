<script>


import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";

export default {
  name: "ModalDeleteTestJavaOop",

  data() {
    return {
      bankTestJavaOopIDToDelete: null,
    }
  },

  methods: {
    setBankTestJavaOopID(bankTestJavaOopID) {
      this.bankTestJavaOopIDToDelete = bankTestJavaOopID;
    },

    handleResetModal() {
      this.bankTestJavaOopIDToDelete = null;
    },

    async handleDeleteTest() {
      //check them sv có làm
      if(this.bankTestJavaOopIDToDelete) {
        let status = BankTestJavaOopDao
            .delete_Java_Test_Oop_By_BankTestJavaOopID(this.bankTestJavaOopIDToDelete)
        if(status) {
          alert("Delete test java oop successfully.");
          window.location.reload();
        }
      }
    }
  }
}
</script>

<template>
  <div class="modal fade" id="modal-delete-test-java-oop" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
      <div class="modal-content modal-content-dialog-delete">
        <div class="modal-header border-0 text-center">
          <h5 class="modal-title w-100 modal-title-dialog-delete" id="deleteConfirmModalLabel">
            <i class="fa-solid fa-circle-exclamation"></i>
          </h5>
        </div>
        <div class="modal-body text-center">
          <span class="delete-confirm-text">Are you sure to delete this test.</span>
        </div>
        <div class="modal-footer d-flex justify-content-center border-0">
          <button type="button" class="btn btn-confirm-yes"
                  @click="handleDeleteTest()"
          >Yes</button>
          <button type="button" class="btn btn-cancel-no" data-bs-dismiss="modal"
                  @click="handleResetModal()"
          >No</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>