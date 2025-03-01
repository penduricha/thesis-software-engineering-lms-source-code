<script>
import './bank-exams.scss';
import '../main/list-courses.scss';
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";

export default {
  name: "BankExams",

  components: {
    AsideAccount, AsideMenu
  },

  data() {

  },

  created() {
    this.saveRouter_Path(this.getRoute());
  },

  mounted() {

  },

  methods: {
    getRoute() {
      console.log(this.$route.path);
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },
  },

  setup() {

  },

  computed: {

  }
}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>

  </main>
  <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>