<script>
import RouterDao from "@/routes/RoutersDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";
import ExamDao from "@/daos/ExamDao.js";

export default {
  name: "QuestionJavaClassExam",

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudent();
    this.setViewTable();
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
        this.viewTable = await ExamDao.get_View_Table_By_ExamID(this.examID);
      }
    },
  },

  computed: {

  },
}
</script>

<template>

</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>