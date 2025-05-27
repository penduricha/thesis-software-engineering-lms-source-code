<script>

import RouterDao from "@/routes/RoutersDao.js";
import LecturerLocalStorage from "@/pages/login/LecturerLocalStorage.js";
//scss
import '../main/list-courses.scss';
import '../../components/aside/aside-account.scss';
import '../../components/aside/aside-menu-style.scss';
import './course-manage.scss';
import './modal-create-exam.scss';
import '../../components/span/span-style.scss';
import '../../components/checkbox/checkbox-view-table.scss';

//others
import CourseDao from "@/daos/CourseDao.js";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import Validation from "@/validation/Validation.js";
import StringFormat from "@/models/StringFormat.js";
import Password from "@/models/Password.js";

import ExamDao from "@/daos/ExamDao.js";

import ListExam from "@/pages/detail-course/component-manage-course/ListExam.vue";
import ModalUpdateQuestionExam from "@/pages/detail-course/component-manage-course/ModalEditQuestionExam.vue";
import listMinuteDuration from "@/assets/data/listMinuteDuration.js";
import listTopicExam from "@/assets/data/listTopicExam.js";
import ModalEditJavaClassExam from "@/pages/detail-course/component-manage-course/ModalEditJavaClassExam.vue";
import ModalDeleteExam from "@/pages/detail-course/delete-exam/ModalDeleteExam.vue";
import ModalUpdateExam from "@/pages/detail-course/update-exam/ModalUpdateExam.vue";

export default {
  name: "CourseManage",

  props: {
    courseID: {
      type: Number,
      required: true
    }
  },

  components: {
    ModalEditJavaClassExam,
    ModalUpdateQuestionExam,
    ListExam,
    ModalDeleteExam,
    ModalUpdateExam,
    AsideMenu, AsideAccount
  },

  data() {
    return {
      lecturerID: null,

      className: null,
      courseName: null,

      //form exam
      titleExam: null,
      typeExam: null,
      topicExam: null,
      retake: null,
      scoringMethod: null,
      duration: null,
      startDate: null,
      endDate: null,

      //view table
      //viewTable: false,
      examPaper: null,

      //validate form exam;
      validateTitleExam: null,
      validateTypeExam: null,
      validateTopicExam: null,
      validateRetake: null,
      validateScoringMethod: null,
      validateDuration: null,
      validateStartDate: null,
      validateEndDate: null,
      validateExamPaper: null,

      //exam
      exams: [],
      //update exam
      examIDToUpdate: null,
      examIDToDelete: null,


      passwordExam: null,
      passwordExamHashed: null,

      //set list duration
      listMinuteDuration: [],

      //list topic exam
      listTopicExam: listTopicExam,
    }
  },

  created() {
    this.setLecturerID();
    this.setCourse();
    this.setExams();
    this.saveRouter_Path(this.getRoute());
  },

  mounted() {
    this.setInputExamTitle();
    this.setListDuration();
    this.isLockSelectScoringMethod();
    this.setInputStartDate();
    this.setInputEndDate();
    this.setInputExamPaper();
  },

  methods: {
    getRoute() {
      //ở đây có props thì phải thêm path của props
      console.log(this.$route.path);
      return this.$route.path + "?" + "courseID=" + Number(this.courseID);
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    setLecturerID() {
      const lecturerLocalStorage = new LecturerLocalStorage();
      let lecturerID = lecturerLocalStorage.getLecturerID_From_LocalStorage();
      if (lecturerID) {
        this.lecturerID = lecturerID;
      } else {
        this.lecturerID = '1120050';
        //mã bảo hà
      }
    },

    setListDuration() {
      this.listMinuteDuration = listMinuteDuration;
    },

    async setExams() {
      this.exams = await ExamDao.getExams_By_CourseID(this.courseID);
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
      let course = await CourseDao.getCourse_By_LecturerID_CourseID(
          this.lecturerID,
          this.courseID);
      if (course) {
        this.className = course.className;
        this.courseName = course.courseName;
        // this.courseID = course.courseID;
      }
    },

    //set field modal
    setInputExamTitle() {
      if (!this.titleExam) {
        this.validateTitleExam = null;
      } else {
        const trimmedTitle = this.titleExam.trim();

        // Nếu exams rỗng, kiểm tra regex ngay
        if (this.exams.length === 0) {
          if (Validation.isFullOfSpaces(trimmedTitle)) {
            this.validateTitleExam = null;
          } else if (!Validation.validateString_Title(Validation.removeSpaces(trimmedTitle))) {
            this.validateTitleExam = "Title exam is invalid.";
          } else {
            this.validateTitleExam = null; // Valid title
          }
        } else {
          // Nếu exams không rỗng, kiểm tra sự tồn tại trước
          const hasMatchingExam = this.exams.some(exam => exam.titleExam === trimmedTitle);
          if (hasMatchingExam) {
            this.validateTitleExam = "Title exam already exists.";
          } else if (Validation.isFullOfSpaces(trimmedTitle)) {
            this.validateTitleExam = null;
          } else if (!Validation.validateString_Title(Validation.removeSpaces(trimmedTitle))) {
            this.validateTitleExam = "Title exam is invalid.";
          } else {
            this.validateTitleExam = null; // Valid title
          }
        }
      }
    },

    setSelectExamType() {
      if (this.typeExam) {
        this.validateTypeExam = null;
      }
    },

    setSelectTopicExam() {
      if (this.topicExam) {
        this.validateTopicExam = null;
      }
    },

    setSelectRetake() {
      if (this.retake) {
        this.validateRetake = null;
      }
    },

    setSelectScoringMethod() {
      if (this.scoringMethod) {
        this.validateScoringMethod = null;
      }
    },

    setSelectDurationTime() {
      if (this.duration) {
        this.validateDuration = null;
      }
    },

    setInputStartDate() {
      if (!this.startDate) {
        this.validateStartDate = null;
        return;
      }
      try {
        const selectedDate = new Date(this.startDate);
        if (isNaN(selectedDate.getTime())) {
          this.validateStartDate = "The selected date is invalid.";
        }
        const currentDate = new Date();
        const thresholdDate = new Date(currentDate.getTime() + 15 * 60000); // Thêm 15 phút
        if (selectedDate < thresholdDate) {
          this.validateStartDate = "The selected date and time must be at least 15 minutes after the current time.";
        } else {
          this.validateStartDate = null;
        }
      } catch (error) {
        this.validateStartDate = error.message;
      }
    },

    setInputEndDate() {
      if (this.endDate) {
        try {
          const selectedDate = new Date(this.endDate);
          if (isNaN(selectedDate.getTime())) {
            this.validateEndDate = "The selected date is invalid.";
            return;
          }
          const startDate = new Date(this.startDate);
          // Check if end date is at least one hour after start date
          const oneHourAfterStart = new Date(startDate.getTime() + 60 * 60 * 1000);
          if (selectedDate <= oneHourAfterStart) {
            this.validateEndDate = "End date must be at least one hour after start date.";
          } else {
            this.validateEndDate = null;
          }

        } catch (error) {
          this.validateEndDate = error.message;
        }
      }
    },

    setInputExamPaper() {
      if (!this.examPaper) {
        this.validateExamPaper = null;
      } else {
        const trimmedExamPaper = this.examPaper.trim();
        const googleDriveRegex = /^https:\/\/drive\.google\.com\/.*$/;

        if (!googleDriveRegex.test(trimmedExamPaper)) {
          this.validateExamPaper = "Link exam paper must be a Google Drive link.";
        } else {
          this.validateExamPaper = null;
        }
      }
    },

    setPasswordExam() {
      this.passwordExam = this.passwordExam.replace(/[^0-9]/g, '');
    },

    isLockSelectScoringMethod() {
      if (this.retake === "Yes") {
        return false;
      } else {
        this.scoringMethod = null;
        this.validateScoringMethod = null;
        return true;
      }
    },

    validationNullField() {
      if (!this.titleExam) {
        this.validateTitleExam = "Please enter title exam.";
      }

      if (!this.typeExam) {
        this.validateTypeExam = "Please choose type exam.";
      }

      if (!this.topicExam) {
        this.validateTopicExam = "Please choose topic exam.";
      }

      if (!this.retake) {
        this.validateRetake = "Please choose retake exam.";
      }

      if (!this.scoringMethod && !this.isLockSelectScoringMethod()) {
        this.validateScoringMethod = "Please choose scoring method.";
      }

      if (!this.duration) {
        this.validateDuration = "Please choose time duration exam.";
      }

      if (!this.startDate) {
        this.validateStartDate = "Please choose start date.";
      }

      if (!this.endDate) {
        this.validateEndDate = "Please choose end date.";
      }
    },

    async handleViewModal(examID) {
      this.examIDToUpdate = examID;
      console.log("Exam ID to update: ", this.examIDToUpdate);
      await this.$refs.modalUpdateExam.setExam(this.examIDToUpdate);
    },

    async handleOpenDeleteModal(examID) {
      this.examIDToDelete = examID;
      console.log("Exam ID to update: ", this.examIDToDelete);
      await this.$refs.modalDeleteExam.setExam(this.examIDToDelete);
    },

    async handleOpenEditQuestionsModal(examID, topicExam) {
      await new Promise(resolve => setTimeout(resolve, 2000)); // Trì hoãn 5 giây
      await this.$refs.modalUpdateQuestionExams.setQuestions(examID, topicExam);
    },

    async handleOpenEditTestJavaClassModal(examID, topicExam) {
      await new Promise(resolve => setTimeout(resolve, 2000));
      await this.$refs.modalEditJavaCLassExams.setListTestJavaOop(examID, topicExam);
    },

    async handleCreateExam() {
      this.validationNullField();
      //tạo danh sách ktra các validate xem còn nào còn thông báo ko
      const validations = [
        this.validateTitleExam,
        this.validateTypeExam,
        this.validateTopicExam,
        this.validateRetake,
        this.validateScoringMethod,
        this.validateDuration,
        this.validateStartDate,
        this.validateEndDate,
        this.validateExamPaper,
      ];
      const allAreEmpty = validations.every(val => val === null);
      if (allAreEmpty) {
        this.titleExam = StringFormat.normalizeSpaces(this.titleExam.trim());
        if (this.passwordExam) {
          const passwordClass = new Password(this.passwordExam);
          this.passwordExamHashed = passwordClass.xorEncryptDecrypt();
          console.log("Password exam hashed: ", this.passwordExamHashed);
        }
        //chuyển thành new Date
        const dateStartDate = new Date(this.startDate);
        const dateEndDate = new Date(this.endDate);
        if (this.examPaper) {
          this.examPaper = this.examPaper.trim();
        }
        const examPost = {
          "titleExam": this.titleExam,
          "typeExam": this.typeExam,
          "topicExam": this.topicExam,
          "retakeExam": this.retake,
          "scoringMethod": this.scoringMethod,
          "duration": Number(this.duration),
          "startDateDay": dateStartDate.getDate(),
          "startDateMonth": dateStartDate.getMonth() + 1,
          "startDateYear": dateStartDate.getFullYear(),
          "startDateHour": dateStartDate.getHours(),
          "startDateMinute": dateStartDate.getMinutes(),
          "endDateDay": dateEndDate.getDate(),
          "endDateMonth": dateEndDate.getMonth() + 1,
          "endDateYear": dateEndDate.getFullYear(),
          "endDateHour": dateEndDate.getHours(),
          "endDateMinute": dateEndDate.getMinutes(),
          "viewTable" : true,
          "linkExamPaper": this.examPaper,
          "passwordExam": this.passwordExamHashed,
        }
        let statusCreate = await ExamDao.create_Exam_By_CourseID(this.courseID, examPost);
        if (statusCreate) {
          alert("Created exam successfully");
          window.location.reload();
        } else {
          alert("Created exam failed");
        }
      }
    }

  },

  computed: {
    setActiveButtonNavListExam() {
      if(this.getRoute().includes("/main-page/list-courses/course-manage")){
        return "button-nav-course-active";
      } else {
        return null;
      }
    }
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
            <img src="@/assets/image/button_nav_left_calendar.png" alt="button nav left calendar"
                 class="style-button-nav-calendar">
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
          {{ className }} - {{ courseName }}
        </span>
      </div>
      <!--      Menu nav-->
      <div class="nav-style-in-course">
        <button class="button-nav-in-course"
                :class="['active-button', setActiveButtonNavListExam]"
        >List exams</button>
<!--        <button class="button-nav-in-course">List student</button>-->
<!--        <button class="button-nav-in-course">Statistic</button>-->
      </div>
      <list-exam :exams="exams"
                 @view-exam="handleViewModal"
                 @delete-exam="handleOpenDeleteModal"
                 @edit-questions-exam="handleOpenEditQuestionsModal"
                 @edit-test-java-class-exam=" handleOpenEditTestJavaClassModal"
                  :course-i-d="courseID"/>
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
            Create new exam by topic
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
                    class="form-control"
                    :class="[{'is-invalid': validateTitleExam !== null}]"
                    placeholder="Enter exam title"
                    maxlength="30"
                    @input="setInputExamTitle()"
                    v-model="titleExam"
                />
                <span v-if="validateTitleExam"
                      class="span-validate-modal-form"
                >{{ validateTitleExam }}
                </span>
              </div>
            </div>
            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Type Exam:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select"
                        @change="setSelectExamType()"
                        v-model="typeExam"
                        :class="[{'is-invalid': validateTypeExam !== null}]"
                >
                  <option value="Practice 1">Practice 1</option>
                  <option value="Practice 2">Practice 2</option>
                  <option value="Practice 3">Practice 3</option>
                </select>
                <span
                    v-if="validateTypeExam"
                    class="span-validate-modal-form"
                >{{ validateTypeExam }}</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Topic:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select"
                        @change="setSelectTopicExam()"
                        v-model="topicExam"
                        :class="[{'is-invalid': validateTopicExam !== null}]"
                >
                  <option v-for="l in listTopicExam"
                          :value="l"
                  >{{l}}</option>
                </select>
                <span
                    v-if="validateTopicExam"
                    class="span-validate-modal-form"
                >{{ validateTopicExam }}.</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Retake:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select"
                        @change="setSelectRetake()"
                        :class="[{'is-invalid': validateRetake !== null}]"
                        v-model="retake"
                >
                  <option value="Yes">Yes</option>
                  <option value="No">No</option>
                </select>
                <span
                    v-if="validateRetake"
                    class="span-validate-modal-form"
                >{{ validateRetake }}</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Scoring Method:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select"
                        @change="setSelectScoringMethod()"
                        :class="[{'is-invalid': validateScoringMethod !== null}]"
                        v-model="scoringMethod"
                        :disabled="isLockSelectScoringMethod()"
                >
                  <option value="Max">Max</option>
                  <option value="Average">Average</option>
                  <option value="Last exam">Last exam</option>
                </select>
                <span
                    v-if="validateScoringMethod"
                    class="span-validate-modal-form"
                >{{ validateScoringMethod }}</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label class="col-sm-3 col-form-label">
                Time:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select"
                        @change="setSelectDurationTime()"
                        :class="[{'is-invalid': validateDuration !== null}]"
                        v-model="duration"
                >
                  <option v-if="listMinuteDuration.length > 0"
                          v-for="l in listMinuteDuration" :value="l.duration">
                    {{l.durationText}}
                  </option>
                </select>
                <span
                    v-if="validateDuration"
                    class="span-validate-modal-form"
                >{{ validateDuration }}</span>
              </div>
            </div>

            <div class="mb-3 row">
              <label for="startDate" class="col-sm-3 col-form-label">
                Start Date (SA is am, CH is pm.):<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <input
                    type="datetime-local"
                    class="form-control"
                    @input="setInputStartDate()"
                    v-model="startDate"
                    :class="[{'is-invalid': validateStartDate !== null}]"
                    @keydown.prevent
                />
                <span
                    v-if="validateStartDate"
                    class="span-validate-modal-form"
                >{{ validateStartDate }}</span>
                <!--@keydown.prevent; ko cho nhập bằng bàn phím-->
              </div>

            </div>
          </div>

          <div class="mb-3 row">
            <label for="endDate" class="col-sm-3 col-form-label">
              End Date (SA is am, CH is pm.):<span class="required-star">*</span>
            </label>
            <div class="col-sm-9">
              <input
                  type="datetime-local"
                  class="form-control"
                  @input="setInputEndDate()"
                  v-model="endDate"
                  :class="[{'is-invalid': validateEndDate !== null}]"
              />
              <span
                  v-if="validateEndDate"
                  class="span-validate-modal-form"
              >{{ validateEndDate }}</span>
            </div>
          </div>

<!--          <div class="mb-3 row">-->
<!--            <label for="viewTable" class="col-sm-3 col-form-label">-->
<!--              View table-->
<!--            </label>-->
<!--            <div class="col-sm-9" style="display: flex; align-items: center">-->
<!--              <input-->
<!--                  type="checkbox"-->
<!--                  class="style-check-box-view-exam"-->
<!--                  v-model="viewTable"-->
<!--              />-->
<!--            </div>-->
<!--          </div>-->

          <div class="mb-3 row">
            <label for="examPaper" class="col-sm-3 col-form-label">
              Exam Paper (Link File, if any):
            </label>
            <div class="col-sm-9">
              <input
                  type="text"
                  class="form-control"
                  placeholder="https://drive.google.com/..."
                  maxlength="50"
                  @input="setInputExamPaper()"
                  v-model="examPaper"
                  :class="[{'is-invalid': validateExamPaper !== null}]"
              />
              <span
                  v-if="validateExamPaper"
                  class="span-validate-modal-form"
              >{{ validateExamPaper }}</span>
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
            <button type="submit"
                    class="btn-create-exam"
                    @click="handleCreateExam()"
            >Create
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <modal-update-exam ref="modalUpdateExam"
                     :lecturer-i-d="this.lecturerID"
                     :course-i-d-to-update="this.courseID"
   :exams="this.exams"/>

  <modal-delete-exam ref="modalDeleteExam"
                     :lecturer-i-d="this.lecturerID"
                     :course-i-d-to-delete="this.courseID"
  />

  <modal-update-question-exam ref="modalUpdateQuestionExams"  />
  <modal-edit-java-class-exam ref="modalEditJavaCLassExams" />
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>