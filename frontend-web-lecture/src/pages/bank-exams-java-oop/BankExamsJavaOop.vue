<script>
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import NavBarBankExam from "@/pages/bank-exams-nav-bar/NavBarBankExam.vue";

export default {
  name: "BankExamsJavaOop",

  components: {
    NavBarBankExam,
    AsideAccount, AsideMenu

  },

  created() {
    this.saveRouter_Path(this.getRoute());
  },

  mounted() {

  },

  data() {
    return {

    }
  },

  methods: {
    getRoute() {
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

  },

  computed: {
    setActiveButtonNavTopic() {

    },
  }
}
</script>

<template>
  <body>
  <AsideMenu />
  <main >
    <section class="section-banks">
      <h5>Bank Exam</h5>
      <nav-bar-bank-exam />

    </section>

  </main>
  <AsideAccount />
  </body>
</template>

<style scoped lang="scss">
@use "@/scss/main";
</style>