<script>
import './modal-view-pie-chart.scss';
import MarkStudentDao from "@/daos/MarkStudentDao.js";
import PieChart from "@/pages/detail-course/component-manage-course/modal-view-chart/PieChart.vue";
export default {
  name: "ModalViewPieChart",
  components: {PieChart},

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
          .get_GroupBy_MarkStudent_MarkExam_By_ExamID(examID);
      this.pollingInterval_GroupByMarkStudent = await MarkStudentDao
          .startPolling_Get_GroupBy_MarkStudent_MarkExam_By_ExamID(examID,
              (updateGroupByMarkStudent) => {
                this.groupByMarkStudentsMarkExam = updateGroupByMarkStudent;
              });
      //transform array
      // if(this.groupByMarkStudentsMarkExam.length > 0) {
      //   this.groupByMarkStudentsMarkExam = this.groupByMarkStudentsMarkExam.map(item => ({
      //     label: item.markRange,
      //     y: item.count
      //   }));
      //   console.log("List group by mark student: ", this.groupByMarkStudentsMarkExam);
      // }

    }
  }
}
</script>

<template>
  <div class="modal fade" id="modal-view-pie-chart" tabindex="-1" aria-labelledby="modalResultLabel"
       aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content custom-modal">
        <div class="modal-title-wrapper">
          <h5>Pie chart about mark exam.</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p class="style-average-mark">
            <span>Average: {{averageMark}}</span>
          </p>
          <div class="view-pie-chart">
            <PieChart v-if="groupByMarkStudentsMarkExam.length > 0"
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