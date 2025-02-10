<script>
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import './list-courses.scss';
import './card-course.scss';

export default {
  name: "ListCourses",
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

    <section class="section-welcome">
      <div class="background-welcome">
        <span class="span-welcome">Welcome back, Dang Thi Thu Ha</span>
      </div>
    </section>
    <section class="section-list-course">
      <h4 class="title-menu">List Courses</h4>
      <div class="view-courses">
        <button>
          <div class="card">
            <div class="top"></div>
            <div class="bottom">
              <span class="title">DHKTPM20BTT - OOP</span>
              <span class="text">Semester 1, year: 2025 - 2026</span>
            </div>
          </div>
        </button>
        <button>
          <div class="card">
            <div class="top"></div>
            <div class="bottom">
              <span class="title">DHKTPM20BTT - OOP</span>
              <span class="text">Semester 1, year: 2025 - 2026</span>
            </div>
          </div>
        </button>
        <button>
          <div class="card">
            <div class="top"></div>
            <div class="bottom">
              <span class="title">DHKTPM20BTT - OOP</span>
              <span class="text">Semester 1, year: 2025 - 2026</span>
            </div>
          </div>
        </button>
        <button>
          <div class="card">
            <div class="top"></div>
            <div class="bottom">
              <span class="title">DHKTPM20BTT - OOP</span>
              <span class="text">Semester 1, year: 2025 - 2026</span>
            </div>
          </div>
        </button>
      </div>


    </section>
  </main>
  <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>