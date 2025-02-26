<script>
import './question-exam-java-core.scss';
import RouterDao from "@/routes/RoutersDao.js";
import questionsList from "./questions.js";
import { onMounted, ref } from "vue";
//text-editor
import { Codemirror } from "vue-codemirror";
import { shallowRef } from "vue";
import { oneDark } from "@codemirror/theme-one-dark";
import { java,  } from "@codemirror/lang-java";

import { keymap } from "@codemirror/view";
import {autocompletion, completeFromList} from "@codemirror/autocomplete";
import CodeJava from "@/pages/ui-exam-questions/CodeJava.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";
import ExamDao from "@/daos/ExamDao.js";

export default {
  name: "QuestionExam",
  components: {
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
      indexQuestion: 1,
      studentID: null,
      courseID: null,

      lastName: null,
      firstName: null,

      //questions
      questions: null,

      //timer
      // Khởi tạo thời gian còn lại
      timeLeft: null,
      timer: null,
    }
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudent();

    this.checkTimeLeft();
    this.setDuration();
  },

  mounted() {

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
    },

    async setQuestion_By_ExamID() {

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
        this.timeLeft = this.duration * 60;
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

    handleButtonQuestion(q) {
      this.indexQuestion = q.index;
    },

    handlePaste(event) {
      event.preventDefault();
      // Prevent paste action
    },
  },

  setup() {
    // const questions = questions;
    //cach khac set bien
    const questions = ref(null);
    const code= ref(null);


    //set code auto
    const codeJava = new CodeJava();
    const setCodeString = () =>{
      code.value = codeJava.getCodeJava_ToDemo();
    }


    const setData_Components = () => {
      questions.value = questionsList;
    }

    onMounted(() => {
      setData_Components();
      setCodeString();
    });

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
      code,
      extensions,
      handleReady,
      questions,
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
      return (q) => {
        return (q.index === this.indexQuestion)
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
    <div :style="containerStyle">
      <header class="page-header">
        <div class="aside-questions">
          <div class="style-view-questions">
            <span class="span-questions">Questions:</span>
            <div class="view-list-questions">
              <!--              button-number-question-done-->
              <button class="button-number-question
                button-number-question-no-done"
                      :key="q.index"
                      v-for="q in questions"
                      @click="handleButtonQuestion(q)"
                      :class="['border-color-button-choose',
                        setBorderColorChoose(q)]"
              >
                {{q.index}}
              </button>
              <button class="button-number-question button-submit">Submit</button>
            </div>
          </div>

          <span class="style-time">Time: {{formattedTime}}</span>
        </div>
        <div class="aside-account-in-exam">
          <img src="@/assets/image/account-logo.png" alt="account logo" class="style-account-logo-in-exam">
          <span class="style-span-information">{{lastName}} {{firstName}} - {{studentID}}</span>
        </div>
      </header>
      <div class="style-main">
        <section class="section-exam">
          <p class="text-exam">Viết hàm tính tổng 2 số nguyên a và số nguyên b. Bắt ngoại lệ nếu không phải là số nguyên và trả về 0.
            Cho bảng test-case như sau:</p>
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Index</th>
              <th>Input</th>
              <th>Output Expect</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td>1</td>
              <td>1, 2</td>
              <td>3</td>
            </tr>
            <tr>
              <td>2</td>
              <td>2, 2.5</td>
              <td>0</td>
            </tr>
            <!-- Thêm các hàng khác nếu cần -->
            </tbody>
          </table>
        </section>
        <section class="section-code-editor">
<!--          <div class="view-button-text-editor">-->
<!--            <button-->
<!--                class="button-text-editor"-->
<!--            >Save all-->
<!--            </button>-->

<!--            <button-->
<!--                class="button-text-editor"-->
<!--            >Reset-->
<!--            </button>-->

<!--            <button-->
<!--                class="button-text-editor"-->
<!--            >Compile all-->
<!--            </button>-->

<!--            <button-->
<!--                class="button-text-editor"-->
<!--            >Test output-->
<!--            </button>-->
<!--          </div>-->
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
</template>

<style scoped lang="scss">
@use '@/scss/main';

</style>