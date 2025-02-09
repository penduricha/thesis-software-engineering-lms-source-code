<script>
import './login-page.scss';
import RouterDao from "@/routes/RoutersDao.js";
import Password from "@/models/Password.js";
import listMenu from "@/components/aside/list-menu.js";

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
  },

  mounted() {
    // Kiểm tra localStorage khi component được mount
    this.getFromLocalStorage_CheckBox();
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
      //alert('Login');
      const isEmptyInput = !this.studentId || !this.password;

      // const passwordClass = new Password(this.password);
      // let passwordHashed = passwordClass.sha512().trim();
      if (isEmptyInput) {
        this.validationSpan = 'Please enter student id and password.';
      } else {
        const passwordClass = new Password(this.password);
        //let passwordHashed = await passwordClass.sha512();
        this.navigateTo_MainPage();

      }
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

    //lock paste
    preventPaste(event) {
      event.preventDefault();
    },


  }
}
</script>

<template>
  <body>
  <div class="container-login">
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
               maxlength="20"
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
  </div>
  </body>
</template>

<style lang="scss" scoped>
@use '@/scss/main';
@use 'login-page';
</style>