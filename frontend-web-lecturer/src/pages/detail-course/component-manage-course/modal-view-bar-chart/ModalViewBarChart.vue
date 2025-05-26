<script>
import './modal-view-bar-chart.scss';
import MarkStudentDao from "@/daos/MarkStudentDao.js";

import BarChart from "@/pages/detail-course/component-manage-course/modal-view-bar-chart/BarChart.vue";
import PieChart from "@/pages/detail-course/component-manage-course/modal-view-pie-chart/PieChart.vue";
export default {
  name: "ModalViewBarChart",
  components: {PieChart, BarChart},

  data() {
    return {
      averageMark: 0,
      pollingInterval_AverageMark: null,

      groupByMarkStudentsMarkExam: [],
      pollingInterval_GroupByMarkStudent: null,
    }
  },

  beforeDestroy() {
    clearInterval(this.pollingInterval_AverageMark);
    clearInterval(this.pollingInterval_GroupByMarkStudent);
  },

  methods: {
    async setDataToChart(examID) {
      this.averageMark = await MarkStudentDao.get_Average_MarkExam_By_ExamID(examID);
      this.pollingInterval_AverageMark = await MarkStudentDao
          .startPolling_Get_Average_MarkExam_By_ExamID(examID,
              (updatedMark) => {
                this.averageMark = updatedMark;
              });
      if(!this.averageMark) {
        this.averageMark = 0;
      }
      this.groupByMarkStudentsMarkExam = await MarkStudentDao
          .get_GroupBy_ZeroToTen_MarkStudent_MarkExam_By_ExamID(examID);
      this.pollingInterval_GroupByMarkStudent = await MarkStudentDao
          .get_GroupBy_ZeroToTen_MarkStudent_MarkExam_By_ExamID(examID,
              (updateGroupByMarkStudent) => {
                this.groupByMarkStudentsMarkExam = updateGroupByMarkStudent;
              });
      console.log(this.groupByMarkStudentsMarkExam);
    }
  }
}
</script>

<template>
  <div class="modal fade" id="modal-view-bar-chart" tabindex="-1" aria-labelledby="modalResultLabel"
       aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content custom-modal">
        <div class="modal-title-wrapper">
          <h5>Bar chart about mark exam.</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p class="style-average-mark">
            <span>Average: {{averageMark}}</span>
          </p>
          <div class="view-chart">
            <BarChart
                v-if="groupByMarkStudentsMarkExam.length > 0"
                :chartProps="groupByMarkStudentsMarkExam"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>