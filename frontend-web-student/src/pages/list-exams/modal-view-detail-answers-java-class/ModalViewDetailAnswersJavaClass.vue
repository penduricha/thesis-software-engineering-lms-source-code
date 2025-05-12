<script>
import './modal-view-detail-answers-java-class.scss';
import DetailAnswersJavaClassDao from "@/daos/DetailAnswersJavaClassDao.js";

export default {
  name: "ModalViewDetailAnswersJavaClass",

  props: {},

  data() {
    return {
      listDetailAnswersJavaClass: [],
    }
  },

  mounted() {

  },

  methods: {
    async setDetailAnswersJavaClass_To_Table(detailMarkStudentID) {
      this.listDetailAnswersJavaClass = await DetailAnswersJavaClassDao
          .getListDetailAnswersJavaClass_By_DetailMarkStudentID(detailMarkStudentID);
      console.log(this.listDetailAnswersJavaClass);
    }
  },

  computed: {},

}
</script>

<template>
  <div class="modal fade" id="modal-detail-result-exam-java-class" tabindex="-1" aria-labelledby="modalResultLabel"
       aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content custom-modal">
        <div class="modal-title-wrapper">
          <h5 class="custom-title">Result exam</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <!--          tu tu call api sau-->
          <!--          <span class="result-text">Your mark is <strong>8.00</strong>. Your correct answer(s): <strong>4/5</strong>.</span>-->
          <!-- Question 1 -->
          <div class="question-container">
            <table class="table table-striped custom-table">
              <thead>
              <tr>
                <th>Index</th>
                <th>Sentence</th>
                <th>Score achieve</th>
                <th>Max score</th>
                <th>Reviews</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(l, lIndex) in listDetailAnswersJavaClass || []" :key="lIndex" v-if="listDetailAnswersJavaClass.length > 0">
                <td>{{ lIndex + 1 }}</td>
                <td>{{ l.sentence }}</td>
                <td>{{ l.scoreAchievement }}</td>
                <td>{{ l.maxScore }}</td>
                <td>{{ l.reviews }}</td>

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