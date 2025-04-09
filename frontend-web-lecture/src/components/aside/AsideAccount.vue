<script>
import './aside-account.scss';
import '../../pages/main/list-courses.scss';
import '../span/span-style.scss';
import ManageDateTime from "@/date-time/ManageDateTime.js";
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";
import RouterDao from "@/routes/RoutersDao.js";
import LectureDao from "@/daos/LectureDao.js";
import Password from "@/models/Password.js";
import ExamDao from "@/daos/ExamDao.js";
import ModalUpdateExam from "@/pages/detail-course/update-exam/ModalUpdateExam.vue";
import '../skeleton/loading-skeleton.scss';

export default {
  name: 'AsideAccount',

  props: {

  },

  components: {
    ModalUpdateExam,
  },

  data(){
    return {
      events: [
        'click',
        'mousemove',
        'mouseup',
        'mousedown',
        'scroll',
        'keypress',
        'unload',
      ],

      monthName: null,
      yearNumber: null,
      daysOfWeek: null,
      chooseDate: null,

      //id name
      lectureID: null,
      name: null,

      examsCalendar: null,

      //Time to logout
      timeout: null,

      //password change;
      currentPassword: null,
      newPassword: null,
      confirmNewPassword: null,

      //validateSpan
      validationCurrentPassword: null,
      validationNewPassword: null,
      validationConfirmNewPassword: null,
    }
  },

  created() {
    this.setAccount();
    this.getInformation_Date();
  },

  mounted() {
    this.events.forEach(function(event) {
      window.addEventListener(event, this.resetTimer)
    }, this);
    this.setTimers();
  },

  destroyed() {
    this.events.forEach(function(event) {
      window.removeEventListener(event, this.resetTimer)
    }, this);
    this.resetTimer();
  },



  methods: {
    handleLogout() {
      const lectureLocalStorage = new LectureLocalStorage();
      const routerDao = new RouterDao();
      //remove Local-Storage
      routerDao.removePath_From_LocalStorage();
      lectureLocalStorage.removeLectureID_From_LocalStorage();
      this.navigateTo_LoginPage();
    },

    //lock paste
    preventPaste(event) {
      event.preventDefault();
    },

    setTimers() {
      this.timeout = setTimeout(this.handleLogout, 2700000);
    },

    resetTimer() {
      clearTimeout(this.timeout);
      this.setTimers();
    },

    async setAccount() {
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      let lecture = await LectureDao.getLectureName_And_LectureID(lectureID);
      console.log(lecture);
      this.lectureID = '0' + lecture.lectureID;
      this.name = lecture.name;
      if(this.lectureID) {
        const dateNow = new Date();
        let yearStartDate = dateNow.getFullYear();
        let monthStartDate = dateNow.getMonth() + 1;
        let dateStartDate = dateNow.getDate();
        this.examsCalendar = await ExamDao.
        getExams_By_LectureID_Lecture_Calendar(this.lectureID, yearStartDate, monthStartDate, dateStartDate);
        console.log("Exam calendar: ",this.examsCalendar);
      }
    },

    navigateTo_LoginPage() {
      this.$router.replace({
        path: '/login-page',
        // query: {
        // }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    async getInformation_Date() {
      const dateInWeek = new ManageDateTime();
      this.monthName = dateInWeek.getCurrentDayInfo().monthName;
      this.yearNumber = dateInWeek.getCurrentDayInfo().year;
      this.daysOfWeek = dateInWeek.getListDayOfWeek();
      this.chooseDate = dateInWeek.getCurrentDayInfo();
    },

    async handleButtonDate(d) {
      const dateInWeek = new ManageDateTime();
      const dateChoose = new Date(d.date);
      this.chooseDate = dateInWeek.getDate_Choose(dateChoose);
      this.yearNumber = this.chooseDate.year;
      this.monthName = this.chooseDate.monthName;
      // console.log("Day choose: ", dateChoose.getDate());
      // console.log("Month choose: ", dateChoose.getMonth() + 1);
      // console.log("Year choose: ", dateChoose.getFullYear());
      let yearStartDate = dateChoose.getFullYear();
      let monthStartDate = dateChoose.getMonth() + 1;
      let dateStartDate = dateChoose.getDate();
      if(this.lectureID) {
        this.examsCalendar = await ExamDao.
        getExams_By_LectureID_Lecture_Calendar(this.lectureID, yearStartDate, monthStartDate, dateStartDate);
      }
    },

    handleNavigateLastWeek() {
      const dateInWeek = new ManageDateTime();
      const date = this.chooseDate.date;
      const dateLastWeek = new Date(date)
      dateLastWeek.setDate(date.getDate() - 7);
      this.chooseDate = dateInWeek.getDate_Choose(dateLastWeek);
      this.daysOfWeek = dateInWeek.getWeek_By_Day(dateLastWeek);
      this.yearNumber = this.chooseDate.year;
      this.monthName = this.chooseDate.monthName;
      // console.log('week: ',this.daysOfWeek);
    },

    handleNavigateNextWeek() {
      const dateInWeek = new ManageDateTime();
      const date = this.chooseDate.date;
      const dateNextWeek = new Date(date)
      dateNextWeek.setDate(date.getDate() + 7);
      this.chooseDate = dateInWeek.getDate_Choose(dateNextWeek);
      this.daysOfWeek = dateInWeek.getWeek_By_Day(dateNextWeek);
      this.yearNumber = this.chooseDate.year;
      this.monthName = this.chooseDate.monthName;
      // console.log('week: ',this.daysOfWeek);
    },

    //set input
    setInputCurrentPassword(){
      if(!this.currentPassword){
        this.validationCurrentPassword = null;
      } else {
        this.validationCurrentPassword = null;
      }
    },

    setInputNewPassword() {
      if(!this.newPassword){
        this.validationNewPassword = null;
      } else {
        this.validationNewPassword = null;
      }
    },

    setInputConfirmNewPassword() {
      if(!this.confirmNewPassword){
        this.validationConfirmNewPassword = null;
      } else {
        this.validationConfirmNewPassword = null;
      }
    },


    //validate and update
    async validateCurrentPassword() {
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      const passwordClass = new Password(this.currentPassword);
      let passwordHashed = await passwordClass.sha512();

      let lecturePasswordHashed = await LectureDao.getLecturePasswordBy_LectureID(lectureID);
      if (lecturePasswordHashed !== passwordHashed) {
        this.validationCurrentPassword = 'Current password doesn\'t match.';
      } else {
        try {
          const passwordClassNew = new Password(this.newPassword);
          let passwordNewHashed = await passwordClassNew.sha512();
          let statusUpdate = await LectureDao.update_Password_By_LectureID(lectureID, passwordNewHashed);
          console.log('patched password status: ', statusUpdate);
          if (statusUpdate) {
            alert("Update password successfully.");
            window.location.reload();
          } else {
            alert("Update Failed.");
          }
        } catch (error) {
          console.error(error);
        }
      }
    },

    validateRequiredInput() {
      if(!this.currentPassword) {
        this.validationCurrentPassword = 'Please enter current password.';
      }

      if(!this.newPassword) {
        this.validationNewPassword = 'Please enter new password.';
      }

      if(!this.confirmNewPassword) {
        this.validationConfirmNewPassword = 'Please enter confirm password.';
      }
    },

    validateNewPassword() {
      const isNotNullInput = this.currentPassword && this.newPassword && this.confirmNewPassword;
      if(isNotNullInput) {
        if(this.newPassword !== this.confirmNewPassword) {
          this.validationConfirmNewPassword = 'Confirm password doesn\'t match new password.';
        }
      }
    },

    handleUpdatePassword() {
      this.validateRequiredInput();
      this.validateNewPassword();
      const noValidateSpan =
          !this.validationCurrentPassword
          && !this.validationNewPassword
          && !this.validationConfirmNewPassword;
      if(noValidateSpan) {
        this.validateCurrentPassword();
      }
    },

    handleCloseModal() {
      this.validationCurrentPassword = null;
      this.validationNewPassword = null;
      this.validationConfirmNewPassword = null;
    }

  },

  computed: {
    setStyleButton() {
      return (day) => {
        return ((day.dayNumber === this.chooseDate.dayNumber) )
            ? 'button-view-date-choose'
            : 'button-view-date-no-choose';
      };
    }
  }
}
</script>

<template>
  <aside class="aside-account">
    <button class="view-account"
    >
      <img src="@/assets/image/account-logo.png" alt="account logo" class="style-account-logo">
      <div class="view-name-and-button-information">
        <div v-if="!name" class="placeholder content style-span-information-skeleton"></div>
        <div v-if="!name" class="placeholder content style-span-information-skeleton"></div>
        <span class="style-span-information">{{name}}</span>
        <span class="style-span-information">{{lectureID}}</span>
      </div>
      <img src="@/assets/image/button_nav_left_calendar.png"
             alt="button nav left calendar"
             class="style-button-nav-calendar style-icon-drop-down"
           data-bs-toggle="dropdown" aria-expanded="true"
      >
      <ul class="dropdown-menu dropdown-menu-lg-start view-menu-dropdown">
        <li class="dropdown-item button-item-dropdown">
          View Profile
        </li>
        <li class="dropdown-item button-item-dropdown"
            data-bs-toggle="modal" data-bs-target="#modalChangePassword"
        >
          Change Password
        </li>
        <li class="dropdown-item button-item-dropdown"
            style="color: var(--danger-color-red)"
            @click="handleLogout()"
        >
          Log out
        </li>
      </ul>
    </button>
    <!--    frame dropdown-->

    <div class="view-calendar-schedule">
      <div class="view-header-calendar">
        <h5 class="style-text-month">{{monthName}} {{yearNumber}}</h5>
        <div class="view-button-nav-calendar">
          <button class="button_nav_calendar"
                  @click="handleNavigateLastWeek()"
          >
            <img src="@/assets/image/button_nav_left_calendar.png"
                 alt="button nav left calendar"
                 class="style-button-nav-calendar">
          </button>
          <button class="button_nav_calendar"
                  @click="handleNavigateNextWeek()"
          >
            <img src="@/assets/image/button_nav_right_calendar.png"
                 alt="button nav right calendar"
                 class="style-button-nav-calendar">
          </button>
        </div>
      </div>

      <div class="view-items-calendar">
        <button v-for="d in daysOfWeek"
                class="button-view-date"
                :class="['color-and-background-color',setStyleButton(d)]"
                @click="handleButtonDate(d)"
        >
          <span>{{ d.dayName }}</span>
          <span>{{ d.dayNumber }}</span>
        </button>
      </div>

      <h5 class="text-schedule-exam">Schedule exam</h5>
      <h5 v-if="!examsCalendar || examsCalendar.length === 0" class="text-no-exam">No exam</h5>
      <div class="view-items-exams-by-day">
        <button class="item-exam"
                v-for="(exam, index) in examsCalendar"
        >
          <!--          Vào trang chi tiết bài kiểm tra-->
          <div class="view-item-exam view-index-exam">
            {{index+1}}
          </div>
          <div class="view-item-exam view-content-exam">
            <span class="title-exam">{{exam.titleExam}} - {{exam.className.substring(2)}}</span>
            <span class="type-exam">{{exam.typeExam}}</span>
          </div>
        </button>
      </div>
    </div>
  </aside>

  <!-- Modal change password -->
  <div class="modal fade" id="modalChangePassword" @click.self="handleCloseModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Change password</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" @click="handleCloseModal"></button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="fullName" class="form-label">Enter old password</label>
            <input type="password"
                   class="form-control"
                   :class="[{'is-invalid': validationCurrentPassword !== null}]"
                   @paste="preventPaste($event)"
                   v-model="currentPassword"
                   @input="setInputCurrentPassword()"
            >
            <span v-if="validationCurrentPassword"
                  class="span-validate-form-font-size invalid-feedback"
            >
              {{validationCurrentPassword}}
            </span>
          </div>
          <div class="form-group">
            <label for="telephone" class="form-label">Enter new password</label>
            <input type="password"
                   class="form-control"
                   :class="[{'is-invalid': validationNewPassword !== null}]"
                   @paste="preventPaste($event)"
                   v-model="newPassword"
                   @input="setInputNewPassword()"
            >
            <span v-if="validationNewPassword"
                  class="span-validate-form-font-size invalid-feedback"
            >
              {{validationNewPassword}}
            </span>
          </div>
          <div class="form-group">
            <label for="dateOfBirth" class="form-label">Confirm new password</label>
            <input type="password"
                   class="form-control"
                   :class="[{'is-invalid': validationConfirmNewPassword !== null}]"
                   @paste="preventPaste($event)"
                   v-model="confirmNewPassword"
                   @input="setInputConfirmNewPassword()"
            >
            <span v-if="validationConfirmNewPassword"
                  class="span-validate-form-font-size invalid-feedback"
            >
              {{validationConfirmNewPassword}}
            </span>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit"
                  class="btn-update-password"

                  @click="handleUpdatePassword()"
          >Update password
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';

</style>