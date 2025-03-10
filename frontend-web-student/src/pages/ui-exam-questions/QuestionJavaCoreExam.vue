<script>
import './question-exam-java-core.scss';
import RouterDao from "@/routes/RoutersDao.js";
// import { onMounted, ref } from "vue";
//text-editor
import {Codemirror} from "vue-codemirror";
import {shallowRef} from "vue";
import {oneDark} from "@codemirror/theme-one-dark";
import {java,} from "@codemirror/lang-java";

import {keymap} from "@codemirror/view";
import {autocompletion, completeFromList} from "@codemirror/autocomplete";

import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";

import QuestionJavaCoreExamDao from "@/daos/QuestionJavaCoreExamDao.js";
import ModalNotificationAfterSubmit from "@/pages/ui-exam-questions/ModalNotificationAfterSubmit.vue";
import CodeStorageDao from "@/daos/CodeStorageDao.js";

export default {
  name: "QuestionJavaCoreExam",
  components: {
    ModalNotificationAfterSubmit,
    Codemirror,
  },

  props: {
    examID: {
      type: Number,
      required: true,
    },

    duration: {
      type: Number,
      required: true,
    }
  },

  data(){
    return {

      indexQuestion: null,
      studentID: null,
      courseID: null,

      lastName: null,
      firstName: null,

      //questions
      questions: [],
      questionInit: null,
      testCasesInit: [],

      //content
      contentQuestion: null,
      score: null,

      //timer
      // Khởi tạo thời gian còn lại
      timeLeft: null,
      timer: null,

      //code to submit
      code: null,

      //code saved
      codeSaved: [],
      indexQuestionSaved: [],
    }
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudent();
    this.setQuestion_By_ExamID();
  },

  mounted() {
    this.checkTimeLeft();
    this.setDuration();
    //this.clickButtonSubmit();
  },

  beforeDestroy() {
    // Dọn dẹp khi component bị hủy
    clearInterval(this.timer);
  },

  methods: {
    //router

    getRoute() {
      console.log(this.$route.path );
      return this.$route.path
          + "?" + "examID=" + Number(this.examID)
          + "&" + "duration=" + Number(this.duration);
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
      this.indexQuestion = Number(sessionStorage.getItem("indexQuestion"));
      // const savedTime = localStorage.getItem('timeLeft');
      // if(savedTime) {
      //   localStorage.removeItem("timeLeft");
      // }

    },

    async setQuestion_By_ExamID() {
      this.questions = await QuestionJavaCoreExamDao.getQuestions_By_ExamID(this.examID);
      console.log("10 questions: ", this.questions);
      this.questionInit = this.questions[this.indexQuestion];
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(studentID) {
        if (this.questionInit) {
            this.testCasesInit = await QuestionJavaCoreExamDao.getTestCases_By_QuestionJavaCoreExamID(this.questionInit.questionJavaCoreExamID);
            //console.log("Test case: ", this.testCasesInit);
            this.contentQuestion = this.questionInit.contentQuestion;
            this.score = this.questionInit.score;
            //get code da save
            this.codeSaved = await CodeStorageDao.get_Code_Saved_By_StudentID(studentID);
            if(this.codeSaved.length > 0) {
              this.indexQuestionSaved = this.codeSaved.map(c => c.indexQuestion);
              console.log("Index question saved: ", this.indexQuestionSaved);
            }
            console.log("Code saved: ", this.codeSaved);
            let codeGet = await CodeStorageDao
                .get_Code_By_IndexQuestion_StudentID(studentID, Number(this.indexQuestion));
            if (codeGet) {
              this.code = codeGet;
            } else {
              this.code = this.questionInit.codeSample;
            }
          }
      }
    },

    async setStudent() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(studentID) {
        let student = await StudentDao.getStudentName_And_StudentID(studentID);
        //console.log(student);
        this.studentID = student.studentID;
        this.lastName = student.lastName;
        this.firstName = student.firstName;
        let courseID = await StudentDao.getCourseID_By_StudentID(studentID);
        if(courseID) {
          this.courseID = courseID;
        }

      }
    },

    checkTimeLeft() {
      const savedTime = sessionStorage.getItem('timeLeft');
      if (savedTime) {
        this.timeLeft = parseInt(savedTime, 10);
        // Bắt đầu lại timer nếu có thời gian lưu
        this.startTimer();
      } else {
        this.timeLeft = (this.duration) * 60;
      }
    },

    setDuration() {
      if (this.timer) {
        clearInterval(this.timer);
      }
      // Lưu thời gian vào localStorage
      sessionStorage.setItem('timeLeft', this.timeLeft);
      this.startTimer();
    },

    startTimer() {
      this.timer = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
          // Cập nhật thời gian trong localStorage
          sessionStorage.setItem('timeLeft', this.timeLeft);
        } else {
          clearInterval(this.timer);
          // Xóa thời gian khi đã hết
          sessionStorage.removeItem('timeLeft');
        }
        this.clickButtonSubmit();
      }, 1000);
    },

    async handleButtonQuestion(q, index) {
      //other method
      //save truoc
      //this.$refs.saveCode.click();
      sessionStorage.setItem('indexQuestion', index);
      this.indexQuestion = Number(sessionStorage.getItem('indexQuestion'));
      this.questionInit = q;
      if(this.questionInit) {
        this.testCasesInit = await QuestionJavaCoreExamDao
            .getTestCases_By_QuestionJavaCoreExamID(this.questionInit.questionJavaCoreExamID);
        console.log("Test case: ", this.testCasesInit);
        this.contentQuestion = this.questionInit.contentQuestion;
        //console.log("Content question: ", this.contentQuestion);
        this.score = this.questionInit.score;
        let codeGet = await CodeStorageDao
            .get_Code_By_IndexQuestion_StudentID(this.studentID, this.indexQuestion);
        if (codeGet) {
          this.code = codeGet;
        } else {
          this.code = this.questionInit.codeSample;
        }
        //fetch lai khi da saved
        const studentLocalStorage  = new StudentLocalStorage();
        let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
        this.codeSaved = await CodeStorageDao.get_Code_Saved_By_StudentID(studentID);
        if(this.codeSaved.length > 0) {
          this.indexQuestionSaved = this.codeSaved.map(c => c.indexQuestion);
          console.log("Index question saved: ", this.indexQuestionSaved);
        }
      }
    },

    handlePaste(event) {
      event.preventDefault();
      // Prevent paste action
    },

    clickButtonSubmit() {
      //thoi gian ket thuc
      if(this.timeLeft === 0 || this.duration <= 0) {
        this.$refs.submitButton.click();
      }
    },

    handleSubmit_And_Notification_Mark() {
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      let answerQuestions = this.questions.map(question => {
        const codeSavedItem = this.codeSaved
            .find(item => item.indexQuestion === this.questions.indexOf(question));
        return {
          questionJavaCoreID: question.questionJavaCoreExamID,
          // Nếu không có mã code thì dùng codeSample
          codeStudentSubmitted: codeSavedItem ? codeSavedItem.codeSave : question.codeSample
        };
      });

      const dataToSubmit = {
        studentID: studentID,
        examID: Number(this.examID),
        answerQuestions: answerQuestions === null ? [] : answerQuestions,
      }
      console.log("Data to submit: ", dataToSubmit);
    },

    async handleSave() {
      //save to database
      let codeExport = this.code
          .split('\n') // Tách mã thành từng dòng
          .filter(line =>
              line.trim() !== '' && // Bỏ qua dòng trống
              !line.includes('java.') && // Bỏ qua dòng chứa 'java.'
              !line.includes('javax.') && // Bỏ qua dòng chứa 'javax.'
              !line.includes('jdk.') && // Bỏ qua dòng chứa 'jdk.'
              !line.includes('com.') && // Bỏ qua dòng chứa 'com.'
              !line.includes('org.') && // Bỏ qua dòng chứa 'org.'
              !line.includes('import') // Bỏ qua dòng chứa 'import'
          )
          // Thay thế tab đầu dòng nếu có
          .map(line => line.replace(/^\t/, '\t'))
          .join('\n');
          // Kết hợp các dòng lại với nhau
      const codeSaveStoragePost = {
        "codeSave" : codeExport,
        "indexQuestionSave" : this.indexQuestion
      }
      if(this.studentID) {
        let status = await CodeStorageDao
            .save_Code_By_StudentId_IndexQuestion(codeSaveStoragePost, this.studentID);
        if(!status) {
          alert("Save failed.");
        } else {
          let codeGet = await CodeStorageDao
              .get_Code_By_IndexQuestion_StudentID(this.studentID, this.indexQuestion);
          console.log("Code get: ", codeGet);
          this.code = codeGet;
          //window.location.reload();
        }
      }
      //fetch lai khi da saved
      const studentLocalStorage  = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      this.codeSaved = await CodeStorageDao.get_Code_Saved_By_StudentID(studentID);
      if(this.codeSaved.length > 0) {
        this.indexQuestionSaved = this.codeSaved.map(c => c.indexQuestion);
        console.log("Index question saved: ", this.indexQuestionSaved);
      }
    },

    handleOpenModalTestDebugJava() {

    },

    // handleReset() {
    //
    // }
  },

  setup() {
    const extensions = [
      java(),
      oneDark,
      autocompletion(),
      keymap.of([
        { key: "Ctrl-Space", run: completeFromList }
      ])
    ];

    const view = shallowRef();
    const handleReady = (payload) => {
      view.value = payload.view;
    };

    return {
      extensions,
      handleReady,
    };
  },

  computed: {
    containerStyle() {
      return {
        minWidth: `${window.innerWidth}px`,
        minHeight: `${window.innerHeight}px`,
      };
    },

    setBorderColorChoose() {
      return (index) => {
        return (index === this.indexQuestion)
            ? 'border-choose'
            : 'border-no-choose';
      };
    },

    setButtonColorSavedCode() {
      return (index) => {
        return (this.indexQuestionSaved.includes(index))
            ? 'button-number-question-done'
            : 'button-number-question-no-done';
      };
    },

    // Tính toán thời gian còn lại
    formattedTime() {
      const minutes = Math.floor(this.timeLeft / 60);
      const seconds = this.timeLeft % 60;
      return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
    },

  },
}
</script>

<template >
    <div :style="containerStyle" >
      <header class="page-header">
        <div class="aside-questions">
          <div class="style-view-questions">
            <span class="span-questions">Questions:</span>
            <div class="view-list-questions">
              <!--              button-number-question-done-->
              <button class="button-number-question"
                      v-for="(q, index) in questions"
                      @click="handleButtonQuestion(q, index)"
                      :class="[
                        'border-color-button-choose', setBorderColorChoose(index),
                        'button-color-saved', setButtonColorSavedCode(index)
                      ]"
              >
                {{index + 1}}
              </button>
              <!--              Nếu dùng nhiều hàm scss-->
              <button class="button-number-question button-submit"
                      ref="submitButton"
                      @click = "handleSubmit_And_Notification_Mark()"
                      data-bs-toggle="modal"
                      data-bs-target="#modal-notification-mark"
              >Submit</button>
            </div>
          </div>
          <span class="style-time">Time: {{formattedTime}}</span>
        </div>
        <div class="aside-account-in-exam">
          <h1 class="style-name-student-exam">
            <img src="@/assets/image/account-logo.png" alt="account logo" class="style-account-logo-in-exam">
            <span v-if="studentID" class="style-span-information">{{lastName}} {{firstName}} - {{studentID}}</span>
          </h1>
        </div>
      </header>
      <div class="style-main">
        <section class="section-exam">
          <span class="text-exam">Score: {{score}}</span>
          <p class="text-exam">{{contentQuestion}}</p>
          <table class="table table-striped" v-if="testCasesInit.length > 0">
            <thead>
            <tr>
              <th>Index</th>
              <th>Input</th>
              <th>Output Expect</th>
              <th>Note</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(t, index) in testCasesInit">
              <td>{{index + 1}}</td>
              <td>{{t.inputTest}}</td>
              <td>{{t.outputExpect}}</td>
              <td>{{t.note}}</td>
            </tr>
            <!-- Thêm các hàng khác nếu cần -->
            </tbody>
          </table>
        </section>
        <section class="section-code-editor">
          <div class="view-button-text-editor">
            <button
                ref="saveCode"
                class="button-text-editor"
                @click="handleSave()"
            >Save all
            </button>

<!--            <button-->
<!--                class="button-text-editor"-->
<!--                @click="handleReset()"-->
<!--            >Reset-->
<!--            </button>-->
            <button
                ref="testDebug"
                class="button-text-editor"
                @click="handleOpenModalTestDebugJava()"
            >Test Debug Java
            </button>
          </div>
          <div class="view-text-editor">
            <codemirror
                v-model="code"
                placeholder="Write code hear ..."
                :autofocus="true"
                :indent-with-tab="true"
                :tab-size="4"
                :extensions="extensions"
                @ready="handleReady"
                class="style-text-editor"
                :style="{ height: '50rem', minHeight: 'calc(100vh - 6rem - 2.5rem)' }"
                @paste="handlePaste"
            />
          </div>
        </section>
      </div>
    </div>

  <modal-notification-after-submit  :exam-i-d="examID" :timer="timer"/>
</template>

<style scoped lang="scss">
@use '@/scss/main';

</style>