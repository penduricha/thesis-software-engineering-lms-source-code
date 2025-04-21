<script>
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import './list-courses.scss';
import './card-course.scss';
import CourseDao from "@/daos/CourseDao.js";
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";

export default {
  name: "ListCourses",
  components: {
    AsideMenu,
    AsideAccount

  },

  data(){
    return {
      courses: [],
    }
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setCourses();
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

    async setCourses(){
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      this.courses = await CourseDao.getCourses_By_LectureID(lectureID);
      if(!this.courses) {
        alert("No course found.");
      }
    },

    navigateTo_Course(courseID) {
      this.$router.push({
        path: '/main-page/list-courses/course-manage',
        query: {
          courseID: courseID,
        }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    handeNavigateToCourse(courseID) {
      this.navigateTo_Course(courseID);
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
        <span class="span-welcome">Welcome back to this platform.</span>
      </div>
    </section>
    <section class="section-list-course">
      <h4 class="title-menu">List Courses</h4>
      <div class="view-courses">
        <button v-if="courses.length === 0">
          <div class="placeholder content" style="width: 20rem; height: 10rem">

          </div>
        </button>
        <button v-if="courses.length === 0">
          <div class="placeholder content" style="width: 20rem; height: 10rem">

          </div>
        </button>
        <button v-if="courses.length === 0">
          <div class="placeholder content" style="width: 20rem; height: 10rem">

          </div>
        </button>
        <button v-for="course in courses" @click="handeNavigateToCourse(course.courseID)">
          <div class="card-course">
            <div class="top"></div>
            <div class="bottom">
              <span class="title">{{course.className}} - {{course.courseName}}</span>
              <span class="text">Semester {{course.semester}}, year: {{course.startYear}} - {{course.endYear}}</span>
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