<script>
import './question-exam-java-core.scss';
import RouterDao from "@/routes/RoutersDao.js";
import questionsList from "./questions.js";
// import { onMounted, ref } from "vue";
//text-editor
import { Codemirror } from "vue-codemirror";
import { shallowRef } from "vue";
import { oneDark } from "@codemirror/theme-one-dark";
import { java,  } from "@codemirror/lang-java";

import { keymap } from "@codemirror/view";
import {autocompletion, completeFromList} from "@codemirror/autocomplete";

import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";

import QuestionDao from "@/daos/QuestionDao.js";
import ModalNotificationAfterSubmit from "@/pages/ui-exam-questions/ModalNotificationAfterSubmit.vue";

export default {
  name: "QuestionExam",
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

      //timer
      // Khởi tạo thời gian còn lại
      timeLeft: null,
      timer: null,

      //code to submit
      code: null,
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
    },

    async setQuestion_By_ExamID() {
      //xu li local storage với api
      // Retrieve the questions from localStorage
      const questionsJavaCore = localStorage.getItem('questionsJavaCore');
      let parsedQuestionsJavaCore = [];
      // Check if there are questions in localStorage
      if (questionsJavaCore) {
        parsedQuestionsJavaCore = JSON.parse(questionsJavaCore);
      } else {
        // Fetch questions from the API and store them in localStorage
        const questions = await QuestionDao.getQuestions_By_ExamID(this.examID);
        localStorage.setItem('questionsJavaCore', JSON.stringify(questions));
        // Use the fetched questions
        parsedQuestionsJavaCore = questions;
      }
      // Proceed if there are questions available
      if (parsedQuestionsJavaCore.length > 0) {
        //console.log("Questions from localStorage or API: ", parsedQuestionsJavaCore);
        this.questions = parsedQuestionsJavaCore;
        console.log("10 questions: ", this.questions);
        this.questionInit = this.questions[this.indexQuestion];

        if (this.questionInit) {
          this.testCasesInit = await QuestionDao.getTestCases_By_QuestionJavaCoreExamID(this.questionInit.questionJavaCoreExamID);
          console.log("Test case: ", this.testCasesInit);
          this.contentQuestion = this.questionInit.contentQuestion;
          this.code = this.questionInit.codeSample;
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
      const savedTime = localStorage.getItem('timeLeft');
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
      localStorage.setItem('timeLeft', this.timeLeft);
      this.startTimer();
    },

    startTimer() {
      this.timer = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
          // Cập nhật thời gian trong localStorage
          localStorage.setItem('timeLeft', this.timeLeft);
        } else {
          clearInterval(this.timer);
          // Xóa thời gian khi đã hết
          localStorage.removeItem('timeLeft');
        }
      }, 1000);
    },

    async handleButtonQuestion(q, index) {
      //other method
      sessionStorage.setItem('indexQuestion', index);
      this.indexQuestion = Number(sessionStorage.getItem('indexQuestion'));
      this.questionInit = q;
      if(this.questionInit) {
        this.testCasesInit = await QuestionDao.getTestCases_By_QuestionJavaCoreExamID(this.questionInit.questionJavaCoreExamID);
        console.log("Test case: ", this.testCasesInit);
        this.contentQuestion = this.questionInit.contentQuestion;
        this.code = this.questionInit.codeSample;
      }
    },

    handlePaste(event) {
      event.preventDefault();
      // Prevent paste action
    },

    handleSubmit_And_Notification_Mark() {

    },

    handleSaveAll() {
      //save to localstorage

    },

    handleReset() {

    }
  },
  

  setup() {
  // Danh sách từ khóa Java quan trọng
  const javaKeywords = [
    "public", "private", "protected", "static", "final", "void", "class", "interface", "enum",
    "extends", "implements", "abstract", "new", "this", "super", "return", "import", "package",
    "if", "else", "switch", "case", "default", "break", "continue", "while", "do", "for", "try",
    "catch", "finally", "throw", "throws", "synchronized", "volatile", "transient", "native",
    "strictfp", "assert", "instanceof"
  ];

  // Danh sách kiểu dữ liệu Java (hỗ trợ khi khai báo biến)
  const javaDataTypes = [
    "boolean", "char", "byte", "short", "int", "long", "float", "double", "String",
    "List", "ArrayList", "LinkedList", "Set", "HashSet", "TreeSet", "Map", "HashMap", "TreeMap"
  ];

  // Danh sách hàm getter, setter, constructors
  const javaMethods = [
    { label: "getName()", type: "function", detail: "Getter method for name" },
    { label: "setName(String name)", type: "function", detail: "Setter method for name" },
    { label: "getAge()", type: "function", detail: "Getter method for age" },
    { label: "setAge(int age)", type: "function", detail: "Setter method for age" },
    { label: "toString()", type: "function", detail: "Convert object to string" },
    { label: "equals(Object obj)", type: "function", detail: "Check object equality" },
    { label: "hashCode()", type: "function", detail: "Generate hash code" },
    { label: "compareTo(Object obj)", type: "function", detail: "Compare two objects" },
    { label: "clone()", type: "function", detail: "Clone the object" },
    { label: "public class ClassName () { }", type: "snippet", detail: "Create a Java class" },
    { label: "private int ;", type: "variable", detail: "Declare private int variable" },
    { label: "private String ;", type: "variable", detail: "Declare private String variable" },
    { label: "private double ;", type: "variable", detail: "Declare private String variable" },
    { label: "private float ;", type: "variable", detail: "Declare private String variable" },
    { label: "private boolean ;", type: "variable", detail: "Declare private String variable" }
  ];

  // Tích hợp danh sách gợi ý vào CodeMirror
  const javaCompletion = completeFromList([
    ...javaKeywords.map((keyword) => ({
      label: keyword,
      type: "keyword"
    })),
    ...javaDataTypes.map((dataType) => ({
      label: dataType,
      type: "type"
    })),
    ...javaMethods
  ]);

  // 🔥 5️⃣ Cấu hình CodeMirror với Java Autocompletion nâng cao
  const codeMirrorExtensions = [
    java(),
    oneDark,
    autocompletion({ override: [javaCompletion] }),
    keymap.of([
      { key: "Ctrl-Space", run: autocompletion() } // Nhấn Ctrl + Space để gợi ý code
    ])
  ];

  const view = shallowRef();
  const handleReady = (payload) => {
    view.value = payload.view;
  };

  return {
    extensions: codeMirrorExtensions,
    handleReady,
  };




    // const questions = questions;
    //cach khac set bien
    //const questions = ref(null);
    //const code= ref(null);


    //set code auto
    // const codeJava = new CodeJava();
    // const setCodeString = () =>{
    //   code.value = codeJava.getCodeJava_ToDemo();
    // }


    // const setData_Components = () => {
    //   questions.value = questionsList;
    // }

    // onMounted(() => {
    //   setData_Components();
    //   setCodeString();
    // });

    // const extensions = [
    //   java(),
    //   oneDark,
    //   autocompletion(),
    //   keymap.of([
    //     { key: "Ctrl-Space", run: completeFromList }
    //   ])
    // ];

    // const view = shallowRef();
    // const handleReady = (payload) => {
    //   view.value = payload.view;
    // };

    // return {
    //   extensions,
    //   handleReady,
    // };
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
              <button class="button-number-question
                button-number-question-no-done"
                      v-for="(q, index) in questions"
                      @click="handleButtonQuestion(q, index)"
                      :class="['border-color-button-choose',
                        setBorderColorChoose(index)]"
              >
                {{index + 1}}
              </button>
              <button class="button-number-question button-submit"
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
                class="button-text-editor"
                @click="handleSaveAll()"
            >Save all
            </button>

            <button
                class="button-text-editor"
                @click="handleReset()"
            >Reset
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

  <modal-notification-after-submit  :exam-i-d="examID"/>
</template>

<style scoped lang="scss">
@use '@/scss/main';

</style>