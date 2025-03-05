<script>
import './bank-exams.scss';
import '../../components/span/span-style.scss';


//import code-mirror
import {Codemirror} from "vue-codemirror";
import {shallowRef} from "vue";
import {oneDark} from "@codemirror/theme-one-dark";
import {java,} from "@codemirror/lang-java";

import {keymap} from "@codemirror/view";
import {autocompletion, completeFromList} from "@codemirror/autocomplete";
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";
import SessionStorageTestCase from "@/pages/bank-exams/SessionStorageTestCase.js";


export default {
  name: "ModalCreateQuestion",
  components: {Codemirror},

  created() {
    sessionStorage.removeItem("testCases");
  },

  mounted() {
    //this.setInputContent();
    //this.setInputCodeSample();
    this.loadSessionTestCase();
  },

  data() {
    return {
      content: null,
      codeSample: "public static int function() {\n\n}\n",

      codeRunToOutput: "public static void main(String[] args) {\n\n}\n",
      input: null,
      output: null,
      note: null,

      validationInput: null,
      validationOutput: null,
      validationCodeRunToOutput: null,

      validationContent: null,
      validationCodeSample: null,

      validationNullTestCases: null,

      listTestCases: [],
    }
  },

  methods: {
    saveTestCase() {
      const testCaseManager = new SessionStorageTestCase();
      if(!this.input) {
        this.validationInput = "Please enter input test case.";
      }

      if(!this.output) {
        this.validationOutput = "Please enter output test case.";
      }

      const validations = [
        this.validationInput,
        this.validationOutput,
      ];
      const allValidateFormAreEmpty = validations.every(val => val === null);

      if(this.note) {
        this.note = this.note.trim();
      }

      if(allValidateFormAreEmpty) {
        testCaseManager.addTestCase(this.input.trim(), this.output.trim(), this.note);
        this.listTestCases = testCaseManager.loadFromSessionStorage();
        this.resetFieldInputTestCase();
      }
    },

    deleteTestCase(indexTestCase) {
      const testCaseManager = new SessionStorageTestCase();
      testCaseManager.removeTestCase(indexTestCase );
      this.listTestCases = testCaseManager.loadFromSessionStorage();
    },

    loadSessionTestCase(){
      const testCaseManager = new SessionStorageTestCase();
      this.listTestCases = testCaseManager.loadFromSessionStorage();
    },

    validateNull(){
      if(!this.content) {
        this.validationContent = 'Please enter content question.';
      }

      if(!this.codeSample) {
        this.validationCodeSample = 'Please enter code sample.';
      }

      if(!this.codeRunToOutput) {
        this.validationCodeRunToOutput = 'Please enter code run to output.';
      }

      //check list session test case
      const testCaseManager = new SessionStorageTestCase();
      this.listTestCases = testCaseManager.loadFromSessionStorage();
      if(this.listTestCases.length === 0) {
        this.validationNullTestCases = 'Please enter test cases.';
      }
    },

    async createQuestion() {
      this.validateNull();
      const validations = [
        this.validationContent,
        this.validationCodeSample,
        this.validationNullTestCases,
          this.validationCodeRunToOutput,
      ];
      const allValidateFormAreEmpty = validations.every(val => val === null);
      if(allValidateFormAreEmpty) {
        //clean string
        this.content = this.content.trim();
        this.codeSample = this.codeSample
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
        this.codeSample = this.codeSample.trim();
        //this.codeRunToOutput = this.codeRunToOutput.match(/(public.*?\})/)[0];
        this.codeRunToOutput = this.codeRunToOutput
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
        this.codeRunToOutput = this.codeRunToOutput.trim();
        console.log("Code run to output: ", this.codeRunToOutput);

        const dataToPost = {
          "contentQuestion" : this.content,
          "codeSample" : this.codeSample,
          "codeRunToOutput" :   this.codeRunToOutput,
          "bankTestCaseJavaCoreList" : this.listTestCases,
        }

        console.log(dataToPost);

        let statusPost = await BankQuestionJavaCoreDao.create_Question_JavaCore(dataToPost);
        if(statusPost) {
          window.location.reload();
        } else {
          alert("Create failed.");
        }
      }
    },

    resetFieldInputTestCase() {
      this.input = null;
      this.output = null;
      this.note = null;
    },

    setInputContent() {
      if(this.content) {
        this.validationContent = null;
      }
    },

    setInputCodeSample() {
      if(this.codeSample) {
        this.validationCodeSample = null;
      }
    },

    setInputCodeRunToOutput() {
      if(this.codeRunToOutput) {
        this.validationCodeRunToOutput = null;
      }
    },

    setInput() {
      if(this.input) {
        this.validationNullTestCases = null;
        this.validationInput = null;
      }
    },

    setOutput() {
      if(this.output){
        this.validationNullTestCases = null;
        this.validationOutput = null;
      }
    },

    setNote() {
      if(this.note){
        this.validationNullTestCases = null;
      }
    }
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

  }
}
</script>

<template>
  <div class="modal fade" id="modal-create-question" tabindex="-1" aria-hidden="true">
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
              <textarea v-model="content"
                        class="form-control"
                        rows="4"
                        @input="setInputContent()"
                        :class="[{'is-invalid': validationContent !== null}]"
              />
              <span
                  v-if="validationContent"
                  class="span-validate-modal-form"
              >{{validationContent}}</span>
            </div>
            <div class="mb-3">
              <label class="form-label">Code sample:</label>
              <codemirror
                  v-model="codeSample"
                  placeholder="Write code run to out put hear ..."
                  :autofocus="true"
                  :indent-with-tab="true"
                  :tab-size="4"
                  :extensions="extensions"
                  @ready="handleReady"
                  style="width: 100%; height: 5rem;"
                  @input="setInputCodeSample"
              />
              <span
                  v-if="validationCodeSample"
                  class="span-validate-modal-form"
              >{{validationCodeSample}}</span>
            </div>
            <div class="mb-3">
              <label class="form-label">Code test out put:</label>
              <codemirror
                  v-model="codeRunToOutput"
                  placeholder="Write code run to out put hear ..."
                  :autofocus="true"
                  :indent-with-tab="true"
                  :tab-size="4"
                  :extensions="extensions"
                  @ready="handleReady"
                  style="width: 100%; height: 30rem;"
                  @input="setInputCodeRunToOutput()"
              />
              <span
                  v-if="validationCodeRunToOutput"
                  class="span-validate-modal-form"
              >{{validationCodeRunToOutput}}</span>
            </div>
            <div>
              <h5>Test Cases</h5>
              <div class="mb-3">
                <label class="form-label">Input:</label>
                <input v-model="input" class="form-control"
                       :class="[{'is-invalid': validationInput !== null}]"
                       @input="setInput()"
                />
                <span
                    v-if="validationInput"
                    class="span-validate-modal-form"
                >{{validationInput}}</span>
              </div>
              <div class="mb-3">
                <label class="form-label">Expected Output:</label>
                <input v-model="output" class="form-control"
                       :class="[{'is-invalid': validationOutput !== null}]"
                       @input="setOutput"
                />
                <span
                    v-if="validationOutput"
                    class="span-validate-modal-form"
                >{{validationOutput}}</span>
              </div>
              <div class="mb-3">
                <label class="form-label">Note:</label>
                <input v-model="note" class="form-control"
                       @input="setNote()"
                />
              </div>
              <button type="button" class="btn button-purple" @click="saveTestCase">Save Test Case</button>

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
                <tr v-for="(testCase, index) in listTestCases" :key="index">
                  <td>{{ index + 1 }}</td>
                  <td>{{ testCase.inputTest }}</td>
                  <td>{{ testCase.outputExpect }}</td>
                  <td>{{ testCase.note }}</td>
                  <td>
                    <button class="btn btn-sm btn-danger" @click="deleteTestCase(index)">Delete</button>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>
            <span
                v-if="validationNullTestCases"
                class="span-validate-modal-form"
            >{{validationNullTestCases}}</span>
            <div class="text-center mt-3">
              <button type="submit" class="btn button-purple"
                      @click="createQuestion"
              >Create question</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>