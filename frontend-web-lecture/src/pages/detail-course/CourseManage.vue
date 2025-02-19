<script>

import RouterDao from "@/routes/RoutersDao.js";
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";
//scss
import '../main/list-courses.scss';
import '../../components/aside/aside-account.scss';
import '../../components/aside/aside-menu-style.scss';
import './course-manage.scss';
import './modal-create-exam.scss';
import '../../components/span/span-style.scss';

//others
import CourseDao from "@/daos/CourseDao.js";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import AsideMenu from "@/components/aside/AsideMenu.vue";

export default {
  name: "CourseManage",

  props: {
    courseID: {
      type: Number,
      required: true
    }
  },

  components: {
    AsideMenu, AsideAccount

  },

  data() {
    return {
      lectureID: null,

      className: null,
      courseName: null,

      //form exam
      passwordExam: null,

    }
  },

  created() {
    this.setLectureID();
    this.setCourse();
    this.saveRouter_Path(this.getRoute());
  },

  mounted() {

  },

  methods: {
    getRoute() {
      //ở đây có props thì phải thêm path của props
      console.log(this.$route.path);
      return this.$route.path + "?" + "courseID=" + this.courseID;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    setLectureID() {
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      if(lectureID) {
        this.lectureID = lectureID;
      } else {
        this.lectureID = '1120050';
        //mã bảo hà
      }
    },

    navigateTo_ListCourses() {
      this.$router.replace({
        path: '/main-page/list-courses',
        // query: {
        // }
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    handleBackToListCourses() {
      this.navigateTo_ListCourses();
    },

    //lock paste
    preventPaste(event) {
      event.preventDefault();
    },


    async setCourse() {
      // console.log("Course ID: ",this.courseID);
      let course = await CourseDao.getCourse_By_LectureID_CourseID(
          this.lectureID,
          this.courseID);
      if(course) {
        this.className = course.className;
        this.courseName = course.courseName;
      }
    },

    //set field modal
    setInputExamTitle() {

    },

    setSelectExamType() {

    },

    setSelectTopicExam() {

    },

    setSelectRetake() {

    },

    setSelectScoringMethod() {

    },

    setSelectDurationTime() {

    },

    setInputStartDate() {

    },

    setInputEndDate() {

    },

    setInputExamPaper() {

    },

    setPasswordExam() {
      this.passwordExam = this.passwordExam.replace(/[^0-9]/g, '');
    }


  },

  computed: {

  }
}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>
    <section class="section-course">
      <div class="view-back-list-course">
        <button class="button-return-list-course"
                @click="handleBackToListCourses()"
        >
          <div class="button_nav_calendar button-return-courses-image">
            <img src="@/assets/image/button_nav_left_calendar.png" alt="button nav left calendar" class="style-button-nav-calendar">
          </div>
          <span class="
            span-button-return-list-course
            span-button-return-list-course-hover"

          >
            List courses
          </span>
        </button>
        <span class="span-button-return-list-course span-with-out-button span-slash-rotate">
          /
        </span>
        <span class="span-button-return-list-course span-with-out-button">
          {{className}} - {{courseName}}
        </span>
      </div>
      <div class="nav-style-in-course">
        <button class="button-nav-in-course button-nav-course-active">List exams</button>
        <button class="button-nav-in-course">Student Grades List</button>
        <button class="button-nav-in-course">Statistic</button>
      </div>
      <div class="view-list-exams">
<!--        <h5 class="text-no-exam">No exam</h5>-->
        <div class="exam">
          <div class="view-title-exam">
            <span class="text-exam">Java core 1</span>
          </div>
          <div class="view-topic-exam">
            <span class="text-exam">Java core</span>
          </div>
          <div class="view-button-view-exam">
            <button class="text-exam color-status-view">View</button>
          </div>
          <div class="view-button-view-delete">
            <button class="text-exam color-status-delete">Delete</button>
          </div>
        </div>
        <div class="exam">
          <div class="view-title-exam">
            <span class="text-exam">Java core 1</span>
          </div>
          <div class="view-topic-exam">
            <span class="text-exam">Java core</span>
          </div>
          <div class="view-button-view-exam">
            <button class="text-exam color-status-view">View</button>
          </div>
          <div class="view-button-view-delete">
            <button class="text-exam color-status-delete">Delete</button>
          </div>
        </div>
        <button class="exam button-create-exam"
                data-bs-toggle="modal"
                data-bs-target="#createExamModal"
        >
          Create new exam
        </button>
      </div>
    </section>
  </main>
  <AsideAccount/>
  </body>

  <!--  Modal form create exam-->
  <div
      class="modal fade"
      id="createExamModal"
      tabindex="-1"
      aria-labelledby="createExamModalLabel"
      aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content modal-content-create-exam">
        <div class="modal-header">
          <h5 class="modal-title" id="createExamModalLabel">
            Create New Exam by Topic
          </h5>
          <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <div>
            <div class="mb-3 row">
              <label for="title" class="col-sm-3 col-form-label">
                Title:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <input
                    type="text"
                    class="form-control is-invalid"
                    placeholder="Enter exam title"
                    maxlength="30"
                    @input="setInputExamTitle()"
                />
                <span class="span-validate-modal-form">Title cannot be empty.</span>
              </div>
            </div>
            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Type Exam:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select is-invalid"
                        @select="setSelectExamType()"
                >
                  <option value = "">Select exam type</option>
                  <option value = "Theory 1">Theory 1</option>
                  <option value = "Theory 2">Theory 2</option>
                  <option value = "Practice 1">Practice 1</option>
                  <option value = "Practice 2">Practice 2</option>
                </select>
                <span class="span-validate-modal-form">Please select exam type.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Topic:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select is-invalid"
                        @select="setSelectTopicExam()"
                >
                  <option value = "">Select topic</option>
                  <option value = "Java core">Java core</option>
                  <option value = "Java class single">Java class single</option>
                  <option value = "Java class mapping">Java class mapping</option>
                </select>
                <span class="span-validate-modal-form">Please select topic exam.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Retake:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select is-invalid"
                        @select="setSelectRetake()"
                >
                  <option value = "">Select retake option</option>
                  <option value = "Yes">Yes</option>
                  <option value = "No">No</option>
                </select>
                <span class="span-validate-modal-form">Please select retake option.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Scoring Method:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select is-invalid"
                        @select="setSelectScoringMethod()"
                >
                  <option value="">Select scoring method</option>
                  <option value="Max">Max</option>
                  <option value="Average">Average</option>
                </select>
                <span class="span-validate-modal-form">Please select scoring method.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Time:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select is-invalid"
                        @select="setSelectDurationTime()"
                >
                  <option value="">Select time duration</option>
                  <option value="30">30 minutes</option>
                  <option value="60">60 minutes</option>
                </select>
                <span class="span-validate-modal-form">Please select time duration.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label for="startDate" class="col-sm-3 col-form-label">
                Start Date:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <input
                    type="datetime-local"
                    class="form-control is-invalid"
                    @input="setInputStartDate()"
                />
                <span class="span-validate-modal-form">Start date is required.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label for="endDate" class="col-sm-3 col-form-label">
                End Date:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <input
                    type="datetime-local"
                    class="form-control is-invalid"
                    @input="setInputEndDate()"
                />
                <span class="span-validate-modal-form">End date is required and cannot be before start date.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label for="examPaper" class="col-sm-3 col-form-label">
                Exam Paper (Link File, if any):
              </label>
              <div class="col-sm-9">
                <input
                    type="text"
                    class="form-control is-invalid"
                    placeholder="https://drive.google.com/..."
                    maxlength="50"
                    @input="setInputExamPaper()"
                />
                <span class="span-validate-modal-form">Exam paper link is invalid.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label for="password" class="col-sm-3 col-form-label">
                Password (if any):
              </label>
              <div class="col-sm-9">
                <input
                    type="password"
                    class="form-control"
                    placeholder="Enter password"
                    v-model="passwordExam"
                    @paste="preventPaste($event)"
                    @input="setPasswordExam()"
                    maxlength="10"
                />
                <span class="form-text">
                (Password must be digits and greater than 5 characters)
              </span>
              </div>
            </div>

            <div class="text-end">
              <button type="submit" class="btn-create-exam">Create</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>