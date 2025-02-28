<script>
export default {
name: "ModalExamLocked"
}
</script>

<template>
  <div class="modal fade" id="modal-exam-locked" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-body text-center">
          <h5 >Exam is locked</h5>
        </div>
        <div class="modal-footer d-flex justify-content-center border-0">
          <button type="button" class="btn-ok" data-bs-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
.btn-ok {
  width: 100%;
  background-color: var(--purple-iuh);
  color: var(--white);
  height: 2.5rem;
  font-size: 18px;
  border-radius: 2px;
}

.btn-ok:hover {
  opacity: 0.8;
}
</style>