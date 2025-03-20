<script>
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";
import './list-exams-components.scss';
import './list-result-exams.scss';

export default {
  name: "ListResultExams",
  components: {
    AsideMenu,
    AsideAccount

  },

  data(){
    return {

    }
  },

  created() {
    this.saveRouter_Path(this.getRoute());
  },

  mounted() {

  },

  methods: {
    //router
    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },
  }
}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>
    <h4 class="title-menu">Exam result</h4>
    <section class="section-welcome">
      <div class="background-welcome">
        <span class="span-welcome">Hello Nhat, there are your exam results.</span>
      </div>
    </section>
    <section class="section-list-exams">
      <h4 class="h2-all-exams-style">All exams</h4>
      <div class="button-div-exam">
        <span class="text-button-exam flex-title">Title exam</span>
        <div class="div-type-exam flex-type-exam">
          <span class="text-button-exam">Type exam</span>
        </div>
        <span class="text-button-exam flex-date">Date open</span>
        <span class="text-button-exam flex-status">Mark</span>
      </div>
      <button class="button-exam">
        <span class="text-button-exam flex-title">Java core 1</span>
        <div class="div-type-exam flex-type-exam">
          <span class="text-button-exam">Theory 1</span>
        </div>
        <span class="text-button-exam flex-date">9:00 10/5/2025</span>
        <span class="text-button-exam flex-status">8.00</span>
      </button>
      <button class="button-exam">
        <span class="text-button-exam flex-title">Java core 2</span>
        <div class="div-type-exam flex-type-exam">
          <span class="text-button-exam">Theory 1</span>
        </div>
        <span class="text-button-exam flex-date">9:00 15/5/2025</span>
        <span class="text-button-exam flex-status">10.00</span>
      </button>
    </section>
  </main>
  <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>