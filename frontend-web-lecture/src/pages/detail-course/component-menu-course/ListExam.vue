<template>
  <div class="view-list-exams">
    <h5 v-if="!exams || exams.length === 0" class="text-no-exam">No exam</h5>
    <div v-for="e in exams" :key="e.examID" class="exam">
      <div class="view-title-exam">
        <span class="text-exam">{{ e.titleExam }}</span>
      </div>
      <div class="view-topic-exam">
        <span class="text-exam">{{ e.topicExam }}</span>
      </div>
      <div class="view-button-view-exam">
        <button
            class="text-exam color-status-view"
            data-bs-toggle="modal"
            data-bs-target="#updateExamModal"
            @click="handleViewModal(e.examID)"
        >Edit exam</button>
      </div>
      <div class="view-button-view-exam">
        <button
            class="text-exam color-status-view"
            data-bs-toggle="modal"
            data-bs-target="#modal-update-questions-exam"
        >Edit questions</button>
      </div>
      <div class="view-button-view-delete">
        <button
            class="text-exam color-status-delete"
            @click="handleOpenDeleteModal(e.examID)"
            data-bs-toggle="modal"
            data-bs-target="#deleteConfirmModal"
        >Delete</button>
      </div>
    </div>
    <button class="exam button-create-exam" data-bs-toggle="modal" data-bs-target="#createExamModal">
      Create new exam
    </button>
  </div>

</template>

<script>
import '../../main/list-courses.scss';
// import '../../../components/aside/aside-account.scss';
// import '../../components/aside/aside-menu-style.scss';
import '../course-manage.scss';
import '../../../components/span/span-style.scss';
import ModalUpdateQuestionExam from "@/pages/detail-course/component-menu-course/ModalUpdateQuestionExam.vue";

export default {
  name: "ListExam",
  components: {ModalUpdateQuestionExam},
  props: {
    exams: {
      type: Array,
      required: true
    }
  },
  methods: {
    handleViewModal(examID) {
      this.$emit('view-exam', examID);
    },
    handleOpenDeleteModal(examID) {
      this.$emit('delete-exam', examID);
    }
  }
}
</script>

<style scoped>
/* Add your CSS styles here */
</style>