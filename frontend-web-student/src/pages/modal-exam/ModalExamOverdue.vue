<script>
import './modal-exam-overdue.scss';

export default {
  name: "ModalExamOverdue",
  data() {
    return {
      studentID: null,
    }
  },

  methods: {
    async setMarkStudent_Exam_Overdue_Zero() {

    },
  }
}

</script>

<template>
  <div class="modal fade" id="modal-exam-overdue"
       tabindex="-1"
       aria-labelledby="modalOverdueLabel"
       aria-hidden="true"
       data-bs-backdrop="static"
       data-bs-keyboard="false">
    <div class="modal-dialog modal-sm">
      <div class="modal-content custom-modal">
        <div class="modal-body text-center">
          <span class="exam-overdue-text">Exam is overdue.</span>
        </div>
        <div class="modal-footer d-flex justify-content-center border-0">
          <button type="button" class="btn-confirm-ok" data-bs-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>