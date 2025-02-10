<script>
import './aside-account.scss';
import '../../pages/list-exams/list-exams-components.scss';
import ManageDateTime from "@/date-time/ManageDateTime.js";

export default {
  name: 'AsideAccount',

  props: {

  },

  components: {

  },

  data(){
    return {
      monthName: null,
      yearNumber: null,
      daysOfWeek: null,
      chooseDate: null,
    }
  },

  created() {
    this.getInformation_Date();
  },

  mounted() {

  },

  methods: {
    handleLogout() {
      this.navigateTo_LoginPage();
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
      // console.log('Date choose: ',this.chooseDate);
      // console.log('Days in week: ',this.daysOfWeek);
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
        <span class="style-span-information">Tu Quang Nhat</span>
        <span class="style-span-information">21107601</span>
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
  <div class="modal fade" id="modalChangePassword">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Change password</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="fullName" class="form-label">Enter old password</label>
            <input type="password" class="form-control">
          </div>
          <div class="form-group">
            <label for="telephone" class="form-label">Enter new password</label>
            <input type="password" class="form-control">
          </div>
          <div class="form-group">
            <label for="dateOfBirth" class="form-label">Confirm new password</label>
            <input type="password" class="form-control">
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn-update-password"
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