<script>
import RouterDao from "@/routes/RoutersDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";
import ExamDao from "@/daos/ExamDao.js";
import listMenu from "@/components/aside/list-menu.js";
import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";
import './question-java-class.scss';
export default {
  name: "QuestionJavaClassExam",

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudent();
    this.setViewTable();
    this.setTestExam();
  },

  mounted() {
    this.checkTimeLeft();
    this.setDuration();
  },

  beforeDestroy() {
    // Dọn dẹp khi component bị hủy
    clearInterval(this.timer);
    clearInterval(this.pollingInterval);
  },

  components: {

  },

  props: {
    examID: {
      type: Number,
      required: true,
    },

    duration: {
      type: Number,
      required: true,
    }
  },

  data() {
    return {
      studentID: null,
      courseID: null,

      lastName: null,
      firstName: null,

      //timer
      // Khởi tạo thời gian còn lại
      timeLeft: null,
      timer: null,

      //polling
      pollingInterval: null,

      viewTable: true,

      testJavaOop: {},
    }
  },

  methods: {
    getRoute() {
      console.log(this.$route.path );
      return this.$route.path
          + "?" + "examID=" + Number(this.examID)
          + "&" + "duration=" + Number(this.duration);
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    async setStudent() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(studentID) {
        let student = await StudentDao.getStudentName_And_StudentID(studentID);
        //console.log(student);
        this.studentID = student.studentID;
        this.lastName = student.lastName;
        this.firstName = student.firstName;
        let courseID = await StudentDao.getCourseID_By_StudentID(studentID);
        if(courseID) {
          this.courseID = courseID;
        }
      }
    },

    async setTestExam() {
      if(this.examID) {
        this.testJavaOop = await BankTestJavaOopDao
            .getBankTestJavaOop_By_ExamID(Number(this.examID));
        console.log('Test exam: ', this.testJavaOop);
      }
    },

    checkTimeLeft() {
      const savedTime = sessionStorage.getItem('timeLeft');
      if (savedTime) {
        this.timeLeft = parseInt(savedTime, 10);
        // Bắt đầu lại timer nếu có thời gian lưu
        this.startTimer();
      } else {
        this.timeLeft = (this.duration) * 60;
      }
    },

    setDuration() {
      if (this.timer) {
        clearInterval(this.timer);
      }
      sessionStorage.setItem('timeLeft', this.timeLeft);
      this.startTimer();
    },

    clickButtonSubmit() {
      //thoi gian ket thuc
      if(this.timeLeft === 0 || this.duration <= 0) {
        this.$refs.submitButton.click();
      }
    },

    startTimer() {
      this.timer = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
          // Cập nhật thời gian trong localStorage
          sessionStorage.setItem('timeLeft', this.timeLeft);
        } else {
          clearInterval(this.timer);
          // Xóa thời gian khi đã hết
          sessionStorage.removeItem('timeLeft');
        }
        this.clickButtonSubmit();
      }, 1000);
    },

    async setViewTable() {
      if(this.examID) {
        //ở đây có thể là diagram class
        this.viewTable = await ExamDao.get_View_Table_By_ExamID(this.examID);
      }
    },

    async navigateToMainPage () {
      clearInterval(this.timer);
      // Xóa thời gian khi đã hết
      sessionStorage.removeItem('timeLeft');
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      let status = await ExamDao.delete_Access_Exam(studentID, this.examID);
      let statusResetDate = await StudentDao.reset_Date_Time_Start_Exam(studentID);
      //mac du ko co
      //let statusDeleteCodeStorage = await CodeStorageDao.delete_Code_Storage_By_StudentID(studentID);

      if(!status || !statusResetDate ) {
        alert("Can't return page because error system.");
      } else {
        window.location.reload();
        const itemsMenu = listMenu;
        const path = itemsMenu.find(item => item.index === 1)?.path;
        this.savePath_Init_To_LocalStorage(path);
        this.$router.replace({
          path: path,
          // query: {
          // }
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }
      //window.location.reload();
      // const itemsMenu = listMenu;
      // const path = itemsMenu.find(item => item.index === 1)?.path;
      // this.savePath_Init_To_LocalStorage(path);
      // this.$router.replace({
      //   path: path,
      //   // query: {
      //   // }
      // }).catch((error) => {
      //   console.error('Error navigating :', error);
      //   alert(error);
      // });
    },

    savePath_Init_To_LocalStorage(path) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_LocalStorage(path);
    },

    async submitProject_And_NavigateToMainPage () {
      await this.navigateToMainPage();
    }
  },

  computed: {
    containerStyle() {
      return {
        minWidth: `${window.innerWidth}px`,
        minHeight: `${window.innerHeight}px`,
      };
    },

    // Tính toán thời gian còn lại
    formattedTime() {
      const minutes = Math.floor(this.timeLeft / 60);
      const seconds = this.timeLeft % 60;
      return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
    },

    // nameButtonMarkFlag() {
    //   return this.questionInit.isMarkedFlag ? "Remove flag" : "Mark flag";
    // },
  },
}
</script>

<template>
  <div :style="containerStyle" >
    <header class="page-header">
      <div class="aside-questions">
        <div class="style-view-questions">
          <div class="view-list-questions">
            <button class="button-number-question button-submit"
                    ref="submitButton"
                    @click="submitProject_And_NavigateToMainPage()"
            >Submit</button>
          </div>
        </div>
        <span class="style-time">Time: {{formattedTime}}</span>
      </div>
      <div class="aside-account-in-exam">
        <h1 class="style-name-student-exam">
          <img src="../../../assets/image/account-logo.png" alt="account logo" class="style-account-logo-in-exam">
          <span v-if="studentID" class="style-span-information">{{lastName}} {{firstName}} - {{studentID}}</span>
        </h1>
      </div>
    </header>
    <div class="style-main">
      <!--      Hien thi de thi -->
      <section class="section-exam description-exam-java-class">
        <p v-if="testJavaOop" v-html="testJavaOop.descriptionTest"/>
      </section>
      <section class="section-code-editor section-exam-java-class">
        <!--      Hien thi phan upload -->
        <div class="view-image-class-diagrams">
          <img v-if="testJavaOop" :src="testJavaOop.imageDiagram" class="style-image-diagram-exam" alt="diagram exam">
        </div>
        <div class="view-upload-project">

        </div>
      </section>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>