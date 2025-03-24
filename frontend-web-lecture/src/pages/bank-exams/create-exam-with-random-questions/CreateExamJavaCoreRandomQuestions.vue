<script>
import './create-exam-java-core-random-questions.scss';

import CourseDao from "@/daos/CourseDao.js";
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";
import Validation from "@/validation/Validation.js";
import RouterDao from "@/routes/RoutersDao.js";
import StringFormat from "@/models/StringFormat.js";
import Password from "@/models/Password.js";
import ExamDao from "@/daos/ExamDao.js";
import ModalAddQuestions from "@/pages/bank-exams/create-exam-with-choose-questions/ModalAddQuestions.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";
//import '../../components/checkbox/checkbox-view-table.scss';

export default {
  name: "CreateExamJavaCoreRandomQuestions",

  props: {
    // selectedQuestionIDs: {
    //   type: Array,
    //   required: true,
    // },

  },

  components: {
    ModalAddQuestions,
    AsideAccount, AsideMenu

  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setLectureID();
    //this.setExams();
    this.setCourses();
    this.setTotalBankQuestionJavaCore();
  },

  mounted() {
    //call method set input
    this.setInputExamTitle();
    // this.setSelectExamType();
    // this.setSelectTopicExam();
    // this.setSelectRetake();
    this.isLockSelectScoringMethod();
    this.setInputStartDate();
    this.setInputEndDate();
    this.setInputExamPaper();
  },

  data() {
    return {
      lectureID: null,
      courses: [],
      numberOfQuestions: 0,
      //validateNumberOfQuestions: null,

      titleExam: null,
      typeExam: null,
      topicExam: "Java core",
      retake: null,
      scoringMethod: null,
      duration: null,
      startDate: null,
      endDate: null,
      //viewTable: false,
      examPaper: null,
      courseIDChoose: null,

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
      validateCourseIDChoose: null,

      validateSelectQuestion: null,

      passwordExam: null,
      passwordExamHashed: null,

      numberOfQuestionsToRandom: 1,
      validateNumberOfQuestionsToRandom: null,

    }
  },

  methods : {
    preventPaste(event) {
      event.preventDefault();
    },

    setLectureID() {
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      if (lectureID) {
        this.lectureID = lectureID;
      } else {
        this.lectureID = '1120050';
        //mã bảo hà
      }
    },

    async setCourses(){
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      this.courses = await CourseDao.getCourses_By_LectureID(lectureID);
      if(!this.courses) {
        alert("No course found.");
      }
    },

    async setTotalBankQuestionJavaCore() {
      this.numberOfQuestions = await BankQuestionJavaCoreDao.get_Total_Questions_JavaCore();
    },

    getRoute() {
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    //set field modal
    setInputExamTitle() {
      if (!this.titleExam) {
        this.validateTitleExam = null;
      } else {
        const trimmedTitle = this.titleExam.trim();
        if (Validation.isFullOfSpaces(trimmedTitle)) {
          this.validateTitleExam = null;
        } else if (!Validation.validateString_Title(Validation.removeSpaces(trimmedTitle))) {
          this.validateTitleExam = "Title exam is invalid.";
        } else {
          this.validateTitleExam = null; // Valid title
        }
      }
    },

    setSelectExamType() {
      if(this.typeExam) {
        this.validateTypeExam = null;
      }
    },

    setSelectTopicExam() {
      if(this.topicExam) {
        this.validateTopicExam = null;
      }
    },

    setSelectRetake() {
      if(this.retake) {
        this.validateRetake = null;
      }
    },

    setSelectScoringMethod() {
      if(this.scoringMethod) {
        this.validateScoringMethod = null;
      }
    },

    setSelectDurationTime() {
      if(this.duration) {
        this.validateDuration = null;
      }
    },

    setSelectCourseIDChoose() {
      if(this.courseIDChoose) {
        this.validateCourseIDChoose = null;
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
      if(this.retake === "Yes") {
        return false;
      } else {
        this.scoringMethod = null;
        return true;
      }
    },

    validationNullField() {
      if(!this.titleExam) {
        this.validateTitleExam = "Please enter title exam.";
      }

      if(!this.typeExam) {
        this.validateTypeExam = "Please choose type exam.";
      }

      if(!this.topicExam) {
        this.validateTopicExam = "Please choose topic exam.";
      }

      if(!this.retake) {
        this.validateRetake = "Please choose retake exam.";
      }

      if(!this.scoringMethod && !this.isLockSelectScoringMethod()) {
        this.validateScoringMethod = "Please choose scoring method.";
      }

      if(!this.duration) {
        this.validateDuration = "Please choose time duration exam.";
      }

      if(!this.startDate) {
        this.validateStartDate = "Please choose start date.";
      }

      if(!this.endDate) {
        this.validateEndDate = "Please choose end date.";
      }

      if(!this.courseIDChoose) {
        this.validateCourseIDChoose = "Please choose course.";
      }

      //map
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
      const allValidateEmpty = validations.every(val => val === null);

      if (allValidateEmpty) {
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
        console.log("Exam post: ",examPost);
        console.log("Course ID choose: ", this.courseIDChoose);

        if(this.courseIDChoose && this.titleExam && this.numberOfQuestionsToRandom) {
          let isExistTitleExam = await ExamDao
              .get_Exist_Title_Exam_By_TitleExam_CourseID(Number(this.courseIDChoose), this.titleExam.trim());
          if(isExistTitleExam) {
            this.validateTitleExam = "Title exam is exist";
          } else {
            let statusPost =
                await ExamDao.create_Exam_Java_Core_With_Random_Question(examPost,
                    Number(this.courseIDChoose),
                    Number(this.numberOfQuestionsToRandom));
            if(statusPost) {
              //navigate
              this.$router.replace({
                path: '/main-page/list-courses/course-manage',
                query: {
                  courseID: this.courseIDChoose,
                }
              }).catch((error) => {
                console.error('Error navigating :', error);
                alert(error);
              });
            } else {
              alert("Create exam failed.");
            }
          }
        }

      }
    },
  },

  computed : {

  }

}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>
    <section class="section-course">
      <div style="width: 100%;">
        <div class="modal-content modal-content-create-exam">
          <div class="modal-header">
            <h5 class="modal-title" id="createExamModalLabel">
              Create exam by random questions
            </h5>
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
                      maxlength="15"
                      @input="setInputExamTitle()"
                      v-model="titleExam"
                  />
                  <span v-if="validateTitleExam"
                        class="span-validate-modal-form"
                  >{{validateTitleExam}}
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
                    <option value = "Practice 1">Practice 1</option>
                    <option value = "Practice 2">Practice 2</option>
                    <option value = "Practice 3">Practice 3</option>
                  </select>
                  <span
                      v-if="validateTypeExam"
                      class="span-validate-modal-form"
                  >{{validateTypeExam}}</span>
                </div>
              </div>

<!--              <div class="mb-3 row">-->
<!--                <label class="col-sm-3 col-form-label">-->
<!--                  Topic:<span class="required-star">*</span>-->
<!--                </label>-->
<!--                <div class="col-sm-9">-->
<!--                  <select class="form-select"-->
<!--                          @change="setSelectTopicExam()"-->
<!--                          v-model="topicExam"-->
<!--                          :class="[{'is-invalid': validateTopicExam !== null}]"-->
<!--                  >-->
<!--                    <option value = "Java core">Java core</option>-->
<!--                    &lt;!&ndash;                  <option value = "Java class single">Java class single</option>&ndash;&gt;-->
<!--                    &lt;!&ndash;                  <option value = "Java class mapping">Java class mapping</option>&ndash;&gt;-->
<!--                  </select>-->
<!--                  <span-->
<!--                      v-if="validateTopicExam"-->
<!--                      class="span-validate-modal-form"-->
<!--                  >{{validateTopicExam}}.</span>-->
<!--                </div>-->
<!--              </div>-->

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
                    <option value = "Yes">Yes</option>
                    <option value = "No">No</option>
                  </select>
                  <span
                      v-if="validateRetake"
                      class="span-validate-modal-form"
                  >{{validateRetake}}</span>
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
                  >{{validateScoringMethod}}</span>
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
                    <option value=30>30 minutes</option>
                    <option value=60>60 minutes</option>
                  </select>
                  <span
                      v-if="validateDuration"
                      class="span-validate-modal-form"
                  >{{validateDuration}}</span>
                </div>
              </div>

              <div class="mb-3 row">
                <label class="col-sm-3 col-form-label">
                  Number questions random:<span class="required-star">*</span>
                </label>
                <div class="col-sm-9">
                  <select class="form-select"
                          v-model="numberOfQuestionsToRandom"
                  >
                    <option
                        v-for="index in Array.from({ length: this.numberOfQuestions - 1 },
                        (v, i) => i + 1)">{{index}}</option>
                  </select>

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
                  >{{validateStartDate}}</span>
                  <!--@keydown.prevent; ko cho nhập bằng bàn phím-->
                </div>

              </div>
            </div>

            <div class="mb-3 row">
              <label for="endDate" class="col-sm-3 col-form-label">
                End Date  (SA is am, CH is pm.):<span class="required-star">*</span>
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
                >{{validateEndDate}}</span>
              </div>
            </div>

<!--            <div class="mb-3 row">-->
<!--              <label for="viewTable" class="col-sm-3 col-form-label">-->
<!--                View table-->
<!--              </label>-->
<!--              <div class="col-sm-9" style="display: flex; align-items: center">-->
<!--                <input-->
<!--                    type="checkbox"-->
<!--                    class="style-check-box-view-exam"-->
<!--                    v-model="viewTable"-->
<!--                />-->
<!--              </div>-->
<!--            </div>-->

            <div class="mb-3 row" v-if="courses.length > 0">
              <label class="col-sm-3 col-form-label">
                Choose course:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <select class="form-select"
                        @change="setSelectCourseIDChoose()"
                        :class="[{'is-invalid': validateCourseIDChoose !== null}]"
                        v-model="courseIDChoose"
                >
                  <option v-for="c in courses"
                          :value="c.courseID"
                  >{{c.className}} - {{c.courseName}}</option>
                </select>

                <span
                    v-if="validateCourseIDChoose"
                    class="span-validate-modal-form"
                >{{validateCourseIDChoose}}</span>
              </div>
            </div>

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
                >{{validateExamPaper}}</span>
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
            <span
                v-if="validateSelectQuestion"
                class="span-validate-modal-form"
            >{{validateSelectQuestion}}</span>
            <div class="text-end">
              <button type="submit"
                      class="btn-create-exam"
                      style="margin-top: 1rem"
                      @click="handleCreateExam()"
              >Create exam</button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>