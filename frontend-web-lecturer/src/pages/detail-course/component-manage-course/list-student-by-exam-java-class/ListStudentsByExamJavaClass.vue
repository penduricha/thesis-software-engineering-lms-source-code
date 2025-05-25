<script>
import './list-students-by-exam-java-class.scss'
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import NavBarBankExam from "@/pages/bank-exams-nav-bar/NavBarBankExam.vue";
import MarkStudentDao from "@/daos/MarkStudentDao.js";

import ExamDao from "@/daos/ExamDao.js";
import * as XLSX from "xlsx";
import DetailMarkStudentDao from "@/daos/DetailMarkStudentDao.js";
import ModalViewSourceCodeJavaClass
  from "@/pages/detail-course/component-manage-course/list-student-by-exam-java-class/modal-view-src-code/ModalViewSourceCodeJavaClass.vue";
export default {
  name: "ListStudentsByExamByClass",
  components: {ModalViewSourceCodeJavaClass, NavBarBankExam, AsideAccount, AsideMenu},

  props: {
    examID: {
      type: Number,
      required: true,
    },

    courseID: {
      type: Number,
      required: true,
    },

  },

  data() {
    return {
      listStudentsByExamID: [],
      isLoadingTable: true,
      typeDownloadSheet: 'csv',

      //detailMarkStudentIDSelect: null;
    }
  },

  create() {

  },

  beforeMount() {
    this.saveRouter_Path(this.getRoute());
    //this.setTitleExam_And_NameLecturer();
    this.setListStudents_By_ExamID();
  },

  mounted() {

  },

  methods : {
    async setListStudents_By_ExamID () {
      if(this.examID) {
        this.listStudentsByExamID = await MarkStudentDao.
          get_List_Mark_Student_By_ExamID(this.examID);
        //set them field con
        if(this.listStudentsByExamID.length > 0) {
          //console.log("Mark student id: ", Number(this.listStudentsByExamID.markStudentID));
          for (const student of this.listStudentsByExamID) {
            const detailMarks = await DetailMarkStudentDao
                .getDetailMarkStudentsByMarkStudent_MarkStudentID(Number(student.markStudentID));
            // Kiểm tra nếu detailMarks không phải là mảng
            student.listDetailMarkStudent = Array.isArray(detailMarks) ? detailMarks : [];
          }
          //set cho component
          this.listStudentsByExamID.forEach(student => {
            if (student.listDetailMarkStudent.length > 0) {
              student.selectedDetailMarkID = student.listDetailMarkStudent[0].detailMarkStudentID;
            } else {
              student.selectedDetailMarkID = null;
            }
          });
          //console.log('list student mark by exam id: ', this.listStudentsByExamID);
        }
      }
      this.isLoadingTable = false;
    },

    formatDateOfBirth(dateOfBirth) {
      let date = new Date(dateOfBirth);
      let day = date.getDate();
      let month = date.getMonth() + 1;
      let year = date.getFullYear();
      return `${day}/${month}/${year}`;
    },

    formatDateSubmitted(dateSubmitted) {
      let date = new Date(dateSubmitted);
      let day = date.getDate();
      let month = date.getMonth() + 1;
      let year = date.getFullYear();
      let hour = date.getHours();
      let minute = date.getMinutes();
      let second = date.getSeconds()
      return `${day}/${month}/${year} ${hour}:${minute}:${second}`;
    },

    getGenderString(gender) {
      return gender ? 'Male' : 'Female';
    },

    getRoute() {
      //ở đây có props thì phải thêm path của props
      return this.$route.path
          + "?" + "examID=" + this.examID
          + "&" + "courseID=" + this.courseID;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    navigateTo_Course() {
      this.$router.push({
        path: '/main-page/list-courses/course-manage',
        query: {
          courseID: this.courseID,
        }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    convertToCSV(data) {
      const header = ['Index', 'Student ID', 'Name', 'Gender', 'Date of Birth', 'Mark'];
      const rows = data.map((l, index) => [
        index + 1,
        l.studentID,
        `${l.lastName} ${l.firstName}`,
        this.getGenderString(l.gender),
        this.formatDateOfBirth(l.dateOfBirth),
        l.markExam,
      ]);

      return [header].concat(rows).map(row => row.join(',')).join('\n');
    },

    handleDownload() {
      if(this.listStudentsByExamID.length > 0) {
        if(this.typeDownloadSheet === 'csv') {
          const csvContent = this.convertToCSV(this.listStudentsByExamID);
          const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
          const link = document.createElement('a');
          link.href = URL.createObjectURL(blob);
          link.setAttribute('download', `exam_${this.titleExam}_${this.nameLecturer}.csv`);
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
        } else if (this.typeDownloadSheet === 'xlsx') {
          if(this.nameLecturer && this.titleExam) {
            const ws = XLSX.utils.json_to_sheet(this.listStudentsByExamID.map((l, index) => ({
              'Index': index + 1,
              'Student ID': l.studentID,
              'Name student': `${l.lastName} ${l.firstName}`,
              'Gender': this.getGenderString(l.gender),
              'Date of Birth': this.formatDateOfBirth(l.dateOfBirth),
              'Mark': l.markExam,
            })));
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, ws, 'Students');
            XLSX.writeFile(wb, `exam_${this.titleExam}_${this.nameLecturer}.xlsx`);
          }
        }
      }
    },

    async handleSetViewSrcCode(detailMarkStudentID) {
      console.log(detailMarkStudentID);
      // Xử lý chi tiết khác
      await this.$refs.modalViewSrcCodeJavaClass
          .setModalViewSourceCodeJavaClass_By_DetailMarkStudentID(detailMarkStudentID);
    },

    // async getDetailMarkStudentsByMarkStudent_MarkStudentID( markStudentID) {
    //   let list = [];
    //   list = await DetailMarkStudentDao.
    //   getDetailMarkStudentsByMarkStudent_MarkStudentID(markStudentID);
    //   //this.detailMarkStudentIDSelect = list[0];
    //   return list;
    // }
    async setModalViewPieChart() {
      if(this.examID) {
        await this.$refs.modalViewPieChart.setDataToChart(this.examID);
      }
    }
  },

  computed: {

  }
}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>
    <section class="section-banks">
      <div class="view-back-list-course">
        <button class="button-return-list-course"
                @click="navigateTo_Course()"
        >
          <div class="button_nav_calendar button-return-courses-image">
            <img src="@/assets/image/button_nav_left_calendar.png" alt="button nav left calendar"
                 class="style-button-nav-calendar">
          </div>
          <span class="
            span-button-return-list-course
            span-button-return-list-course-hover"
          >
            Course manage
          </span>
        </button>
      </div>
      <h5>List students: </h5>
      <div class="style-view-btn-download">
        <button class="btn button-purple"
                @click="handleDownload()"
        >Download</button>
        <select class="form-select style-form-select-download"
                v-model="typeDownloadSheet"
        >
          <!--          @change="setSelectExamType()"-->
          <!--          v-model="typeExam"-->
          <!--          :class="[{'is-invalid': validateTypeExam !== null}]"-->
          <option value = "csv">csv file</option>
          <option value = "xlsx">xlsx file</option>
        </select>
        <button class="btn button-purple"
                data-bs-toggle="modal"
                data-bs-target="#modal-view-pie-chart"
                @click="setModalViewPieChart()"
        >View chart</button>
      </div>

      <table class="table table-striped">
        <thead>
        <tr>
          <th>Index</th>
          <th>Student ID</th>
          <th>Name</th>
          <th>Gender</th>
          <th>Date of birth</th>
          <th>Mark</th>
          <th>View source code</th>
          <th>Choose time submitted</th>
        </tr>
        </thead>
        <tbody>
        <tr v-if="listStudentsByExamID.length === 0 && isLoadingTable">
          <td colspan="8" class="text-center">
            <div class="spinner-border text-primary" role="status">
              <span class="visually-hidden">Loading...</span>
            </div>
          </td>
        </tr>
<!--        <tr v-if="listStudentsByExamID.length === 0 && isLoadingTable">-->
<!--          <td colspan="5" class="text-center">-->
<!--            <div class="spinner-border text-primary" role="status">-->
<!--              <span class="visually-hidden">Loading...</span>-->
<!--            </div>-->
<!--          </td>-->
<!--        </tr>-->
        <tr v-if="listStudentsByExamID.length === 0 && !isLoadingTable">
          <td colspan="8" class="text-center">
            <h4>No student</h4>
          </td>
        </tr>
        <tr v-if="listStudentsByExamID.length > 0" v-for="(l, index) in listStudentsByExamID">
          <td>
            {{index + 1}}
          </td>
          <td>
            {{l.studentID}}
          </td>
          <td>
            {{l.lastName}} {{l.firstName}}
          </td>
          <td>
            {{getGenderString(l.gender)}}
          </td>
          <td>
            {{formatDateOfBirth(l.dateOfBirth)}}
          </td>
          <td>
            {{l.markExam}}
          </td>
          <td>
            <button class="btn btn-primary" @click="handleSetViewSrcCode(l.selectedDetailMarkID)"
                    data-bs-toggle="modal"
                    data-bs-target="#modal-view-src-code-java-class"
            >
              View source code
            </button>
          </td>
          <td>
            <select class="form-select form-control"
                    v-model="l.selectedDetailMarkID"
            >
              <option v-for="d in l.listDetailMarkStudent" :key="d.detailMarkStudentID" :value="d.detailMarkStudentID">
                {{ formatDateSubmitted(d.dateSubmitted) }}
              </option>
            </select>
          </td>
        </tr>
        </tbody>
      </table>
    </section>
  </main>
  <AsideAccount/>
  </body>
  <modal-view-source-code-java-class ref="modalViewSrcCodeJavaClass" />
</template>

<style scoped lang="scss">

</style>