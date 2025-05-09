<script>
import './modal-view-detail-mark.scss';
import DetailMarkStudentDao from "@/daos/DetailMarkStudentDao.js";
import ModalBeforeExam from "@/pages/modal-exam/ModalBeforeExam.vue";
import ModalViewResultExamJavaCore
  from "@/pages/list-exams/modal-view-result-exam-java-core/ModalViewResultExamJavaCore.vue";
import ModalViewDetailAnswersJavaClass
  from "@/pages/list-exams/modal-view-detail-answers-java-class/ModalViewDetailAnswersJavaClass.vue";

export default {
  name: "ModalViewDetailMark",
  components: {ModalViewDetailAnswersJavaClass, ModalViewResultExamJavaCore, ModalBeforeExam, },

  props: {
    courseID :{
      type: Number,
      required: true,
      default: 1,
    }
  },

  created() {

  },

  mounted() {

  },

  data() {
    return {
      listDetailMarkStudent : [],

      retake: false,
      topicExam: null,
      examID: null,
    }
  },

  methods: {
    async setListDetailMarkStudent (markStudentID, examID, retake, topicExam) {
      this.listDetailMarkStudent = await DetailMarkStudentDao
          .get_Detail_Mark_Student_By_MarkStudentID(Number(markStudentID));
      this.retake = retake;
      this.topicExam = topicExam;
      this.examID = examID;
    },

    async handleGoToModalExamBefore() {
      if(this.examID && this.courseID) {
        await this.$refs.modalExamBefore.setExam_Information(this.examID, this.courseID);
      }
    },

    async handleViewDetailResult(detailMarkStudentID) {
      if(this.topicExam === "Java core") {
        //set modal
        await this.$refs.modalViewResultExamJavaCore
            .setResultExamJavaCoreToModal(detailMarkStudentID);
      } else if (this.topicExam === "Java class") {
        //set data cho java class, set detail answers java class
        await this.$refs.modalViewDetailAnswersJavaClass
            .setDetailAnswersJavaClass_To_Table(detailMarkStudentID);
      }
    },

    getIDModal_By_TopicExam() {
      // if(this.topicExam === "Java core") {
      //   return "#modal-java-core-before-exam"
      // }
      return "#modal-java-core-before-exam"
    },

    getIDModal_Result_By_TopicExam(detailMarkExam) {
      // if(!detailMarkExam) {
      //   return null;
      // } else {
      //   if(this.topicExam === "Java core") {
      //     return "#modal-detail-result-exam-java-core"
      //   } else if (this.topicExam === "Java class") {
      //     return "#modal-detail-result-exam-java-class"
      //   }
      // }
      if(this.topicExam === "Java core") {
        return "#modal-detail-result-exam-java-core"
      } else if (this.topicExam === "Java class") {
        return "#modal-detail-result-exam-java-class"
      }
    }
  },

  computed: {}
}
</script>

<template>
  <div class="modal fade" id="modal-form-detail-mark" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Detail mark</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <table class="table table-striped" v-if="listDetailMarkStudent.length > 0">
              <thead>
              <tr>
                <th>Index</th>
                <th>Date submit</th>
                <th>Mark</th>
                <th>View</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(d, index) in listDetailMarkStudent">
                <td>{{index + 1}}</td>
                <td>
                  {{new Date(d.dateSubmitted).getHours()}}:{{( new Date(d.dateSubmitted).getMinutes() < 10 ? `0${new Date(d.dateSubmitted).getMinutes()}` :
                    new Date(d.dateSubmitted).getMinutes())}}:{{ new Date(d.dateSubmitted).getSeconds()}}
                  {{new Date(d.dateSubmitted).getDate()}}/{{new Date(d.dateSubmitted).getMonth() + 1}}/{{new Date(d.dateSubmitted).getFullYear()}}
                </td>
                <td>{{d.detailMarkExam}}</td>
                <td>
                  <button class="button-view-exam"
                          @click="handleViewDetailResult(d.detailMarkStudentID)"
                          data-bs-toggle="modal"
                          :data-bs-target="getIDModal_Result_By_TopicExam(d.detailMarkExam)"
                  >View</button>
                </td>
              </tr>
              <!-- Thêm các hàng khác nếu cần -->
              </tbody>
            </table>
          </div>
          <div class="mb-3">
            <!-- Neu bai ktra do co cho lam lai-->
            <button class="btn-do-exam"
                    type="submit"
                    v-if="retake"
                    data-bs-toggle="modal"
                    :data-bs-target="getIDModal_By_TopicExam()"
                    @click="handleGoToModalExamBefore()"
            >
              Retake exam
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <modal-before-exam ref="modalExamBefore"/>
  <modal-view-result-exam-java-core ref="modalViewResultExamJavaCore" />
  <modal-view-detail-answers-java-class ref="modalViewDetailAnswersJavaClass" />
</template>

<style scoped lang="scss">
@use "@/scss/main";
</style>