<script>
import './aside-account.scss';
import '../span/span-style.scss';
import '../../pages/list-exams/list-exams-components.scss';
import ManageDateTime from "@/date-time/ManageDateTime.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";
import RouterDao from "@/routes/RoutersDao.js";
import Password from "@/models/Password.js";

export default {
  name: 'AsideAccount',

  beforeRouteLeave(to, from, next) {
    const answer = window.confirm('Bạn có chắc chắn muốn rời khỏi trang này?');
    if (answer) {
      next(); // Cho phép rời khỏi trang
    } else {
      next(false); // Ngăn không cho rời khỏi trang
    }
    //clearTimeout(this.timeout);
  },

  props: {

  },

  components: {

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
      studentID: null,
      lastName: null,
      firstName: null,

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
    this.getInformation_Date();
    this.setAccount();
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
      const studentLocalStorage = new StudentLocalStorage();
      const routerDao = new RouterDao();
      //remove Local-Storage
      routerDao.removePath_From_LocalStorage();
      studentLocalStorage.removeStudentID_From_LocalStorage();
      this.navigateTo_LoginPage();
    },

    setTimers() {
      this.timeout = setTimeout(this.handleLogout, 45 * 10 * 1000);
    },

    resetTimer() {
      clearTimeout(this.timeout);
      this.setTimers();
    },

    // handleBeforeUnload(event) {
    //   // Lưu trạng thái rằng người dùng đã thoát
    //   localStorage.setItem('logoutTriggered', 'true');
    //
    //   const message = 'Bạn có chắc chắn muốn rời khỏi trang này?';
    //   event.returnValue = message; // Chrome yêu cầu thiết lập giá trị này
    //   return message; // Các trình duyệt khác có thể sử dụng giá trị này
    // },

    async setAccount(){
      const studentLocalStorage = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      let student = await StudentDao.getStudentName_And_StudentID(studentID);
      //console.log(student);
      this.studentID = student.studentID;
      this.lastName = student.lastName;
      this.firstName = student.firstName;
    },

    //lock paste
    preventPaste(event) {
      event.preventDefault();
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

    getInformation_Date() {
      const dateInWeek = new ManageDateTime();
      this.monthName = dateInWeek.getCurrentDayInfo().monthName;
      this.yearNumber = dateInWeek.getCurrentDayInfo().year;
      this.daysOfWeek = dateInWeek.getListDayOfWeek();
      this.chooseDate = dateInWeek.getCurrentDayInfo();
    },

    handleButtonDate(d) {
      const dateInWeek = new ManageDateTime();
      const dateChoose = new Date(d.date);
      this.chooseDate = dateInWeek.getDate_Choose(dateChoose);
      this.yearNumber = this.chooseDate.year;
      this.monthName = this.chooseDate.monthName;
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

    // async updatePassword() {
    //   let statusUpdate = StudentDao.update_Password_By_StudentID()
    // },

    async validateCurrentPassword() {
      const studentLocalStorage = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      const passwordClass = new Password(this.currentPassword);
      let passwordHashed = await passwordClass.sha512();

      let studentPasswordHashed = await StudentDao.getStudentPasswordBy_StudentID(studentID);
      if(studentPasswordHashed !== passwordHashed) {
        this.validationCurrentPassword = 'Current password doesn\'t match.';
      } else {
        try {
          const passwordClassNew = new Password(this.newPassword);
          let passwordNewHashed = await passwordClassNew.sha512();
          let statusUpdate = StudentDao.update_Password_By_StudentID(studentID, passwordNewHashed);
          console.log('patched password status: ', statusUpdate);
          if(statusUpdate) {
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
      const noValidateSpan =!this.validationCurrentPassword && !this.validationNewPassword && !this.validationConfirmNewPassword;
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
        <span class="style-span-information">{{lastName}} {{firstName}}</span>
        <span class="style-span-information">{{studentID}}</span>
      <!--        <button class="button-view-information">View information</button>-->
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
      <div class="view-items-exams-by-day">
        <button class="item-exam">
          <div class="view-item-exam view-index-exam">
            1
          </div>
          <div class="view-item-exam view-content-exam">
            <span class="title-exam">Java core 1</span>
            <span class="type-exam">Theory 1</span>
          </div>
        </button>
        <button class="item-exam">
          <div class="view-item-exam view-index-exam">
            2
          </div>
          <div class="view-item-exam view-content-exam">
            <span class="title-exam">Java core 2</span>
            <span class="type-exam">Theory 1</span>
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
          <button type="button" class="btn-close" data-bs-dismiss="modal" @click="handleCloseModal()"></button>
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