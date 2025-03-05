<script>
import './modal-notification-after-submit.scss';
import listMenu from "@/components/aside/list-menu.js";
import RouterDao from "@/routes/RoutersDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import ExamDao from "@/daos/ExamDao.js";
import StudentDao from "@/daos/StudentDao.js";
import CodeStorageDao from "@/daos/CodeStorageDao.js";

export default {
  name: "ModalNotificationAfterSubmit",

  props: {
    examID : {
      type: Number,
      required: true,
    },

    timer: {
      required: true,
    }
  },

  created() {

  },

  mounted() {

  },

  data() {
    return {

    }
  },

  methods: {
    savePath_Init_To_LocalStorage(path) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_LocalStorage(path);
    },

    async submit_Transaction_And_Get_Mark() {

    },

    async navigateTo_MainPage() {
      clearInterval(this.timer);
      // Xóa thời gian khi đã hết
      sessionStorage.removeItem('timeLeft');
      const studentLocalStorage  = new StudentLocalStorage();

      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      let status = await ExamDao.delete_Access_Exam(studentID, this.examID);

      let statusResetDate = await StudentDao.reset_Date_Time_Start_Exam(studentID);
      let statusDeleteCodeStorage = await CodeStorageDao.delete_Code_Storage_By_StudentID(studentID);
      if(!status || !statusResetDate || !statusDeleteCodeStorage) {
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
    },
  }
}
</script>

<template>
  <div class="modal fade" id="modal-notification-mark" tabindex="-1" aria-labelledby="modalNotificationMarkLabel" aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
    <div class="modal-dialog modal-sm">
      <div class="modal-content modal-content-notify-mark">
        <div class="modal-header border-0 text-center">
          <div class="modal-title w-100 modal-title-notify-mark" id="modalNotificationMarkLabel">
            <i class="fa-solid fa-circle-check"></i>
            <h4 class="style-notification">Your mark is 8.00</h4>
          </div>
        </div>
        <div class="modal-body text-center">
          <span class="mark-notification-text">You have successfully completed the exam.</span>
        </div>
        <div class="modal-footer d-flex justify-content-center border-0">
          <button class="btn-return-list"
                  @click="navigateTo_MainPage"
          >Return to list exams</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>