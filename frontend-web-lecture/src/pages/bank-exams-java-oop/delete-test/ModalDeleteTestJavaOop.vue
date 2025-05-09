<script>


import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";
import ExamDao from "@/daos/ExamDao.js";
import ExamJavaOopDao from "@/daos/ExamJavaOopDao.js";

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
      //check them sv có làm, ktra them bai kiem tra co duoc tao hay ko
      if(this.bankTestJavaOopIDToDelete) {
        // let statusStudentAccess = await ExamDao
        //     .getStatus_Access_Student_To_Exam_By_ExamID(this.examID);

        let existExam = await ExamJavaOopDao.get_Exist_Exam_By_BankTestJavaOopID(Number(this.bankTestJavaOopIDToDelete));
        console.log('Exist exam: ', existExam);
        if(existExam) {
          alert("Can't delete test java class because it is created to exam.");
        } else {
          let status = BankTestJavaOopDao
              .delete_Java_Test_Oop_By_BankTestJavaOopID(this.bankTestJavaOopIDToDelete)
          if(status) {
            alert("Delete test java class successfully.");
            window.location.reload();
          }
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