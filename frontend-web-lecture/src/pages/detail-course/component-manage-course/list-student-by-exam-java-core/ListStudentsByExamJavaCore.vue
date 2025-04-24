<script>
import * as XLSX from 'xlsx';
import './list-students-by-exam-java-core.scss'
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import NavBarBankExam from "@/pages/bank-exams-nav-bar/NavBarBankExam.vue";
import MarkStudentDao from "@/daos/MarkStudentDao.js";
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";
import LectureDao from "@/daos/LectureDao.js";
import ExamDao from "@/daos/ExamDao.js";

export default {
  name: "ListStudentsByExamByJavaCore",
  components: {NavBarBankExam, AsideAccount, AsideMenu},

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

      titleExam: null,
      nameLecture: null,
    }
  },

  create() {

  },

  beforeMount() {
    this.saveRouter_Path(this.getRoute());
    this.setListStudents_By_ExamID();
    this.setTitleExam_And_NameLecture();
  },

  mounted() {

  },

  methods : {
    async setTitleExam_And_NameLecture () {
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      let lecture = await LectureDao.getLectureName_And_LectureID(lectureID);
      console.log(lecture);
      this.lectureID = '0' + lecture.lectureID;
      this.nameLecture = lecture.name;
      if(this.examID) {
        let exam = await ExamDao.getExam_By_CourseID_ExamID(this.examID, this.courseID);
        if(exam) {
          this.titleExam = exam.titleExam;
        }
      }
    },

    async setListStudents_By_ExamID () {
      if(this.examID) {
        this.listStudentsByExamID = await MarkStudentDao.
          get_List_Mark_Student_By_ExamID(this.examID);
        console.log('list student by exam id: ', this.listStudentsByExamID);
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
          link.setAttribute('download', `exam_${this.titleExam}_${this.nameLecture}.csv`);
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
        } else if (this.typeDownloadSheet === 'xlsx') {
          if(this.nameLecture && this.titleExam) {
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
            XLSX.writeFile(wb, `exam_${this.titleExam}_${this.nameLecture}.xlsx`);
          }
        }
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
            List exam
          </span>
        </button>
      </div>
      <h5>List students: </h5>
      <div class="style-view-btn-download">
        <button class="btn button-purple"
                @click="handleDownload()"
        >Download</button>
        <select class="form-select"
            v-model="typeDownloadSheet"
        >
<!--          @change="setSelectExamType()"-->
<!--          v-model="typeExam"-->
<!--          :class="[{'is-invalid': validateTypeExam !== null}]"-->
          <option value = "csv">csv file</option>
          <option value = "xlsx">xlsx file</option>
        </select>
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
          <th>View detail</th>
        </tr>
        </thead>
        <tbody>
        <tr v-if="listStudentsByExamID.length === 0 && isLoadingTable">
          <td colspan="5" class="text-center">
            <div class="spinner-border text-primary" role="status">
              <span class="visually-hidden">Loading...</span>
            </div>
          </td>
        </tr>
        <tr v-if="listStudentsByExamID.length === 0 && !isLoadingTable">
          <td colspan="5" class="text-center">
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
            <button class="btn btn-primary">
              View detail
            </button>
          </td>
        </tr>
        </tbody>
      </table>
    </section>
  </main>
  <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">

</style>