<script>
export default {
  name: "NavBarBankExam",

  methods: {
    getRoute() {
      return this.$route.path;
    },
    /*
    this.$router.replace({
        path: i.path,
        // query: {
        // }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
     */
    handleNavBankExamsJavaCore() {
      this.$router.replace({
        path: "/main-page/bank-exams/java-core",
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    handleNavBankExamsJavaOop() {
      this.$router.replace({
        path: "/main-page/bank-exams/java-oop",
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    }

  },

  computed: {
    setActiveNavJavaCore() {
      if(this.getRoute().includes("/main-page/bank-exams/java-core")){
        return "button-nav-course-active";
      } else {
        return null;
      }
    },

    setActiveNavJavaOop() {
      if(this.getRoute().includes("/main-page/bank-exams/java-oop")){
        return "button-nav-course-active";
      } else {
        return null;
      }
    }
  }
}
</script>

<template>
  <div class="tab-container">
    <button class="button-nav-in-course"
            :class="['active-button', setActiveNavJavaCore]"
            @click.prevent="handleNavBankExamsJavaCore"
    >Java Core</button>
    <button class="button-nav-in-course"
            :class="['active-button', setActiveNavJavaOop]"
            @click.prevent="handleNavBankExamsJavaOop"
    >Java Oop Class</button>
  </div>
</template>

<style scoped lang="scss">
@use "@/scss/main";
</style>