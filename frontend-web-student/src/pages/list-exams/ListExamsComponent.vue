<script>
import './list-exams-components.scss';
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";

export default {
  name: 'ListExamsComponents',
  components: {
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
    <aside class="aside-menu">

    </aside>
    <main>
      <h4 class="title-menu">Exam coding</h4>
      <section class="section-welcome">
        <div class="background-welcome">
          <span class="span-welcome">Hello Nhat! Nice to meet you on this examining platform.</span>
        </div>
      </section>
      <section class="section-list-exams">
        <h4 class="h2-all-exams-style">All exams</h4>
        <button class="button-exam">
          <span class="text-button-exam flex-title">Java core 1</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">Type exam</span>
            <span class="text-type-exam">Theory 1</span>
          </div>
          <span class="text-button-exam flex-date">9:00 8/5/2025</span>
          <span class="text-button-exam flex-status color-status-open">Open</span>
        </button>
        <button class="button-exam">
          <span class="text-button-exam flex-title">Java core 2</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">Type exam</span>
            <span class="text-type-exam">Theory 1</span>
          </div>
          <span class="text-button-exam flex-date">9:00 15/5/2025</span>
          <span class="text-button-exam flex-status color-status-lock">Locked</span>
        </button>
        <button class="button-exam">
          <span class="text-button-exam flex-title">Java class 1</span>
          <div class="div-type-exam flex-type-exam">
            <span class="text-button-exam">Type exam</span>
            <span class="text-type-exam">Theory 2</span>
          </div>
          <span class="text-button-exam flex-date">9:00 1/5/2025</span>
          <span class="text-button-exam flex-status color-status-complete">Completed</span>
        </button>
      </section>
    </main>
    <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';

</style>