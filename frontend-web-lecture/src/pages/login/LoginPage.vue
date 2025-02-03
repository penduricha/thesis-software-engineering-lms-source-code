<script>
import './login-page.scss';
import RouterDao from "@/routes/RoutersDao.js";
import Password from "@/models/Password.js";

export default {
  name: "LoginPage",
  components: {

  },

  data(){
    return {
      lectureId: null,
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
    setInputLectureId() {
      // Chỉ cho phép các ký tự số
      this.lectureId = this.lectureId.replace(/[^0-9]/g, '');
      if(!this.lectureId){
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

    handleLogin() {
      //alert('Login');
      const isEmptyInput = !this.lectureId || !this.password;
      // const passwordClass = new Password(this.password);
      // let passwordHashed = passwordClass.sha512().trim();
      if(isEmptyInput) {
        this.validationSpan = 'Please enter lecture id and password.';
      } else {
        // const passwordClass = new Password(this.password);
        // let passwordHashed = passwordClass.sha512().trim();
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


  }
}
</script>

<template>
  <body>
    <main class="container-login">
      <div class="view-image-iuh">
        <img src="@/assets/image/iuh-logo.png"
             alt="iuh logo"
             class="style-logo-iuh"
        >
      </div>
      <div class="view-form-login">
        <div class="form-login">
          <h2 class="style-title-login">Login lecture</h2>
          <input type="text"
                 class="style-input"
                 placeholder="Lecture Id"
                 v-model="lectureId"
                 @input="setInputLectureId()"
                 maxlength="10"
          >

          <input type="password"
                 class="style-input"
                 placeholder="Password"
                 v-model="password"
                 @input="setInputPassword()"
                 maxlength="20"
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
    </main>
  </body>
</template>

<style lang="scss" scoped>
@use '@/scss/main';
@use 'login-page';
</style>