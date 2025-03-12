<script>
import './login-page.scss';
import RouterDao from "@/routes/RoutersDao.js";
import Password from "@/models/Password.js";
import listMenu from "@/components/aside/list-menu.js";
import StudentDao from "@/daos/StudentDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";

export default {
  name: "LoginPage",
  components: {

  },

  data(){
    return {
      studentId: null,
      password: null,
      //validation text,
      validationSpan: null,

      //checkbox
      rememberMe: false,
    }
  },

  created() {
    // this.isActiveRoute();
    this.saveRouter_Path(this.getRoute());
    this.getFromLocalStorage_CheckBox();
    this.getFromLocalStorage_ID_Password();
  },

  mounted() {
    // Kiểm tra localStorage khi component được mount

  },

  methods:{
    //router
    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    //setup input
    setInputStudentId() {
      // Chỉ cho phép các ký tự số
      this.studentId = this.studentId.replace(/[^0-9]/g, '');
      if(!this.studentId){
        this.validationSpan = null;
      } else {
        this.validationSpan = null;
      }
    },

    setInputPassword() {
      if(!this.password){
        this.validationSpan = null;
      } else {
        this.validationSpan = null;
      }
    },

    navigateTo_MainPage() {
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
    },

    async handleLogin() {
      const isEmptyInput = !this.studentId || !this.password;
      if (isEmptyInput) {
        this.validationSpan = 'Please enter student id and password.';
      } else {
        const passwordClass = new Password(this.password);
        let passwordHashed = await passwordClass.sha512();
        let student = await StudentDao.getStudentID_And_Password(this.studentId.trim());
        console.log("Student get: ",student);
        const wrongLogin = (!student || (student.password !== passwordHashed));
        if(wrongLogin) {
          this.validationSpan = "Incorrect username or password. Please try again.";
        } else {
          this.saveToLocalStorage_ID_Password();
          this.saveStudentID_To_LocalStorage(this.studentId.trim());
          this.navigateTo_MainPage();
        }
      }
    },

    savePath_Init_To_LocalStorage(path) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_LocalStorage(path);
    },

    saveStudentID_To_LocalStorage(studentID) {
      const studentLocalStorage  = new StudentLocalStorage();
      studentLocalStorage.saveStudentID_To_LocalStorage(studentID);
    },

    getFromLocalStorage_CheckBox() {
      const savedValue = localStorage.getItem('rememberMe');
      if (savedValue) {
        this.rememberMe = savedValue === 'true';
      }
    },

    saveToLocalStorage_CheckBox() {
      // Lưu trạng thái vào localStorage
      localStorage.setItem('rememberMe', this.rememberMe);
    },

    getFromLocalStorage_ID_Password() {
      const account = localStorage.getItem('account');
      //const parsedAccount = account ? JSON.parse(account) : null;
      console.log(account);
      console.log(this.rememberMe);
      if(this.rememberMe === true) {
        if(account){
          //đổi json thành đối tượng
          const parsedAccount = account ? JSON.parse(account) : null;
          this.studentId = parsedAccount.id;
          this.password = parsedAccount.password;
        } else {
          this.studentId = null;
          this.password = null;
        }

      } else {
        if(account) {
          localStorage.removeItem('account');
        }
        this.studentId = null;
        this.password = null;
      }
    },


    saveToLocalStorage_ID_Password() {
      // Lưu trạng thái vào localStorage
      const account = {
        id: this.studentId,
        password: this.password,
      };

      if(this.rememberMe) {
        // Chuyển đổi đối tượng thành chuỗi JSON save json
        localStorage.setItem('account', JSON.stringify(account));
      }
    },

    //lock paste
    preventPaste(event) {
      event.preventDefault();
    },


  }
}
</script>

<template>

  <section class="container-login">
    <div class="view-image-iuh">
      <img src="@/assets/image/iuh-logo.png"
           alt="iuh logo"
           class="style-logo-iuh"
      >
    </div>
    <div class="view-form-login">
      <div class="form-login">
        <h2 class="style-title-login">Login student</h2>
        <input type="text"
               class="style-input"
               placeholder="Student ID"
               v-model="studentId"
               @input="setInputStudentId()"
               maxlength="10"
        >

        <input type="password"
               class="style-input"
               placeholder="Password"
               v-model="password"
               @input="setInputPassword()"
               maxlength="10"
               @paste="preventPaste($event)"
        >

        <span v-if="validationSpan" class="span-validate-login">{{validationSpan}}</span>

        <button class="style-input style-button-login"
                @click="handleLogin()"
                @keyup.enter="handleLogin"
        >Sign in</button>

        <!--  keyup.enter là press enter event -->
        <!-- <input type="checkbox" value="Remember me">
        <label>Remember me</label> -->

        <div class="view-check-box">
          <input
              type="checkbox"
              id="rememberMe"
              v-model="rememberMe"
              @change="saveToLocalStorage_CheckBox()"
              class="style-check-box"
          />
          <span class="style-remember-me">Remember me</span>
        </div>
      </div>
    </div>
  </section>

</template>

<style lang="scss" scoped>
@use '@/scss/main';
@use 'login-page';
</style>