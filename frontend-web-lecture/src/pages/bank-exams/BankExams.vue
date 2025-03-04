<script>
import './bank-exams.scss';
import '../main/list-courses.scss';
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import RouterDao from "@/routes/RoutersDao.js";

export default {
  name: "BankExams",

  components: {
    AsideAccount, AsideMenu
  },

  data() {
    return {
      searchQuery: "",
      selectedTopic: "All Topics",
      questions: [
        { id: 1, content: "Write a function to find the sum of two numbers.", topic: "Java Core" },
        { id: 2, content: "Explain the concept of polymorphism in Java.", topic: "Java Core" }
      ],
      newQuestion: {
        content: "",
        codeSample: "",
        testType: "Regular Test",
        topic: "Java Core",
        testCases: []
      },
      showTestCaseSection: false,
      newTestCase: {
        input: "",
        output: "",
        note: ""
      }
    };
  },

  created() {
    this.saveRouter_Path(this.getRoute());
  },

  methods: {
    getRoute() {
      return this.$route.path;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    addTestCase() {
      this.showTestCaseSection = true;
    },

    saveTestCase() {
      if (!this.newTestCase.input.trim() || !this.newTestCase.output.trim()) {
        alert("Input Test and Expected Output are required!");
        return;
      }
      this.newQuestion.testCases.push({ ...this.newTestCase });
      this.newTestCase = { input: "", output: "", note: "" };
    },

    deleteTestCase(index) {
      this.newQuestion.testCases.splice(index, 1);
    },

    submitQuestion() {
      if (!this.newQuestion.content.trim()) {
        alert("Question content is required!");
        return;
      }
      this.questions.push({
        id: this.questions.length + 1,
        content: this.newQuestion.content,
        topic: this.newQuestion.topic
      });
      this.newQuestion = {
        content: "",
        codeSample: "",
        testType: "Regular Test",
        topic: "Java Core",
        testCases: []
      };
      this.showTestCaseSection = false;
    }
  }
};
</script>

<template>
  <body>
    <AsideMenu />
    <main >
      <section class="section-banks">
        <button class="btn  mb-3 " @click="$router.push('/main-page/list-courses')">
        <i class="fas fa-arrow-left"></i> Bank Exam
      </button>
      <div class="tab-container">
        <button 
          class="tab-btn" 
        >
         Java Core
        </button>
        <button 
          class="tab-btn" 
        >
          Java No Mapping
        </button>
        <button 
          class="tab-btn" 
        >
          Java Mapping
        </button>
      </div>
      <div class="row mb-3">
        <div class="col-md-4">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Search question...">
            
          </div>
        </div>
        <div class="col-md-3">
          <select v-model="selectedTopic" class="form-select">
            <option>All Topics</option>
            <option>Java Core</option>
            <option>No Mapping</option>
            <option>Mapping</option>
          </select>
        </div>
        <div class="col-md-3 text-end">
          <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#questionModal">+ Add Question</button>
        </div>
      </div>

      <table class="table table-striped">
        <thead>
          <tr>
            <th>Index</th>
            <th>Content</th>
            <th>Topic</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="question in questions" :key="question.id">
            <td>{{ question.id }}</td>
            <td>{{ question.content }}</td>
            <td>{{ question.topic }}</td>
            <td class="actions">
              <button class="btn btn-sm btn-warning">Edit</button>
              <button class="btn btn-sm btn-danger">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
      </section>
      
    </main>
    <AsideAccount />

    <div class="modal fade" id="questionModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Add New Question</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="submitQuestion">
              <div class="mb-3">
                <label class="form-label">Content:</label>
                <textarea v-model="newQuestion.content" class="form-control" rows="3"></textarea>
              </div>
              <div class="mb-3">
                <label class="form-label">Topic:</label>
                <select v-model="newQuestion.topic" class="form-select">
                  <option>Java Core</option>
                  <option>No Mapping</option>
                  <option>Mapping</option>
                </select>
              </div>
              <div class="mb-3">
                <button type="button" class="btn btn-info" @click="addTestCase">+ Add Test Case</button>
              </div>

              <div v-if="showTestCaseSection">
                <h5>Test Cases</h5>
                <div class="mb-3">
                  <label class="form-label">Input:</label>
                  <textarea v-model="newTestCase.input" class="form-control" rows="2"></textarea>
                </div>
                <div class="mb-3">
                  <label class="form-label">Expected Output:</label>
                  <textarea v-model="newTestCase.output" class="form-control" rows="2"></textarea>
                </div>
                <div class="mb-3">
                  <label class="form-label">Note:</label>
                  <textarea v-model="newTestCase.note" class="form-control" rows="2"></textarea>
                </div>
                <button type="button" class="btn btn-success" @click="saveTestCase">Save Test Case</button>

                <table class="table table-bordered mt-3">
                  <thead>
                    <tr>
                      <th>Index</th>
                      <th>Input</th>
                      <th>Expected Output</th>
                      <th>Note</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(testCase, index) in newQuestion.testCases" :key="index">
                      <td>{{ index + 1 }}</td>
                      <td>{{ testCase.input }}</td>
                      <td>{{ testCase.output }}</td>
                      <td>{{ testCase.note }}</td>
                      <td>
                        <button class="btn btn-sm btn-danger" @click="deleteTestCase(index)">Delete</button>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="text-center mt-3">
                <button type="submit" class="btn btn-success">Submit Question</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>