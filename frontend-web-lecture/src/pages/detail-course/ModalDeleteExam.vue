<script>
import ExamDao from "@/daos/ExamDao.js";
import './modal-delete-exam.scss';
import 'font-awesome/css/font-awesome.min.css';

export default {
  name: "ModalDeleteExam",

  props: {
    courseIDToDelete: {
      type: Number,
      required: true
    },

    lectureID: {
      type: String,
      required: true
    },
  },

  data() {
    return {
      examIDToDelete: null,
    }
  },

  methods: {
    async setExam(examIDToDelete) {
      this.examIDToDelete = examIDToDelete;
      let exam = await ExamDao.getExam_By_CourseID_ExamID(examIDToDelete, this.courseIDToDelete);
      if(!exam) {
        alert("Can't find exam.");
      }
    },

    async handleDeleteExam(){
      if(this.examIDToDelete) {
        let statusUpdate = await ExamDao.delete_Exam_By_ExamID(this.examIDToDelete);
        if(statusUpdate) {
          alert("Deleted exam successfully");
          window.location.reload();
        }else {
          alert("Deleted exam failed");
        }
      } else {
        alert("Deleted exam failed");
      }
    }
  }
}
</script>

<template>
  <div class="modal fade" id="deleteConfirmModal" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
      <div class="modal-content modal-content-dialog-delete">
        <div class="modal-header border-0 text-center">
          <h5 class="modal-title w-100 modal-title-dialog-delete" id="deleteConfirmModalLabel">
            <i class="fa-solid fa-circle-exclamation"></i>
          </h5>
        </div>
        <div class="modal-body text-center">
          <span class="delete-confirm-text">Are you sure to delete this exam</span>
        </div>
        <div class="modal-footer d-flex justify-content-center border-0">
          <button type="button" class="btn btn-confirm-yes"
                  @click="handleDeleteExam()"
          >Yes</button>
          <button type="button" class="btn btn-cancel-no" data-bs-dismiss="modal">No</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>