<script>
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";
import CourseDao from "@/daos/CourseDao.js";
import Validation from "@/validation/Validation.js";
import listMinuteDuration from "@/assets/data/listMinuteDuration.js";
import ExamDao from "@/daos/ExamDao.js";
import StringFormat from "@/models/StringFormat.js";
import Password from "@/models/Password.js";

export default {
  name: "ModalCreateExamJavaClass",

  props : {

  },

  created() {
    this.setLectureID();
    this.setCourses();
    this.setListTitleExam();
  },

  mounted() {
    this.setInputExamTitle();
    this.setListDuration();
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
      bankTestJavaOopID: null,

      lectureID: null,

      className: null,
      courseName: null,

      //form exam
      titleExam: null,
      typeExam: null,
      topicExam: "Java class",
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
      validateCourseIDChoose: null,

      courses: [],

      passwordExam: null,
      passwordExamHashed: null,

      courseIDChoose: null,

      //set list duration
      listMinuteDuration: [],
      listTitleExam: [],
    }
  },

  methods: {
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

    setBankTestJavaOopID(bankTestJavaOopID) {
      this.bankTestJavaOopID = bankTestJavaOopID;
      console.log(this.bankTestJavaOopID);
    },

    async setCourses(){
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      this.courses = await CourseDao.getCourses_By_LectureID(lectureID);
      if(!this.courses) {
        alert("No course found.");
      }
    },

    async setListTitleExam() {
      this.listTitleExam = await ExamDao.getListTitleExam();
    },

    setListDuration() {
      this.listMinuteDuration = listMinuteDuration;
    },

    //set field modal
    setInputExamTitle() {
      if (!this.titleExam) {
        this.validateTitleExam = null;
      } else {
        const trimmedTitle = this.titleExam.trim();

        // Nếu exams rỗng, kiểm tra regex ngay
        if (this.listTitleExam.length === 0) {
          if (Validation.isFullOfSpaces(trimmedTitle)) {
            this.validateTitleExam = null;
          } else if (!Validation.validateString_Title(Validation.removeSpaces(trimmedTitle))) {
            this.validateTitleExam = "Title exam is invalid.";
          } else {
            this.validateTitleExam = null; // Valid title
          }
        } else {
          // Nếu exams không rỗng, kiểm tra sự tồn tại trước
          const hasMatchingExam = this.listTitleExam.some(e => e === trimmedTitle);
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

    setSelectCourseIDChoose() {
      if(this.courseIDChoose) {
        this.validateCourseIDChoose = null;
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

      if(!this.courseIDChoose) {
        this.validateCourseIDChoose = "Please choose course.";
      }
    },

    async handleCreateExamJavaOop() {
      this.validationNullField();
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
        this.validateCourseIDChoose,
      ];
      const allValidateEmpty = validations.every(val => val === null);
      if (allValidateEmpty && this.bankTestJavaOopID) {
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
          "bankTestJavaOopID" : Number(this.bankTestJavaOopID),
        }

        let statusPost = await ExamDao.create_Exam_Java_Class_With_ChooseTest(examPost, Number(this.courseIDChoose));
        if(statusPost) {
          window.location.reload();
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
    },
  },

  computed : {

  }
}
</script>

<template>
  <div
      class="modal fade"
      id="modal-create-exam-java-class"
      tabindex="-1"
      aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content modal-content-create-exam">
        <div class="modal-header">
          <h5 class="modal-title" id="createExamModalLabel">
            Create exam java class
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
                    @click="handleCreateExamJavaOop()"
            >Create
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>