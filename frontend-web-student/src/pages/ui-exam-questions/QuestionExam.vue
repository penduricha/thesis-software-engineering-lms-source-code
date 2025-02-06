<script>
import './question-exam.scss';
import RouterDao from "@/routes/RoutersDao.js";
import questionsList from "./questions.js";
import {onMounted, ref} from "vue";


export default {
  name: "QuestionExam",
  components: {

  },

  data(){
    return {
      indexQuestion: 1,
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
      console.log('Chiều rộng trình duyệt: ',window.innerWidth);
      console.log(this.$route.path);
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    handleButtonQuestion(q) {
      this.indexQuestion = q.index;
    },
  },

  setup() {
    // const questions = questions;
    //cach khac set bien
    const questions = ref(null)
    const setData_Components = () => {
      questions.value = questionsList;
    }

    onMounted(() => {
      setData_Components();
    });
    return {questions};
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
    }
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

          <span class="style-time">Time: 59:50:49</span>
        </div>
        <div class="aside-account-in-exam">
          <img src="@/assets/image/account-logo.png" alt="account logo" class="style-account-logo-in-exam">
          <span class="style-span-information">Tu Quang Nhat - 21107601<br>DHKTPM17BTT</span>
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

        </section>
      </div>
<!--      <footer class="page-footer">-->
<!--        <button class="button-navigate-page-exam">Previous</button>-->
<!--        <button class="button-navigate-page-exam">Next</button>-->
<!--      </footer>-->
    </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>