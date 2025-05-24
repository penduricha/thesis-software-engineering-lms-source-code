<script>

import {Codemirror} from "vue-codemirror";
import {shallowRef} from "vue";
import {oneDark} from "@codemirror/theme-one-dark";
import {java,} from "@codemirror/lang-java";

import {keymap} from "@codemirror/view";
import {autocompletion, completeFromList} from "@codemirror/autocomplete";
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";
import SessionStorageTestCase from "@/pages/bank-exams-java-core/SessionStorageTestCase.js";
import Validation from "@/validation/Validation.js";
import ParameterStorageManager from "@/pages/bank-exams-java-core/ParameterStorageManager.js";
export default {
  name: "ModalUpdateQuestion",
  components: {Codemirror},

  props: {
    bankJavaCoreExam : {
      type: Array,
      required: true,
      default: [],
    }
  },

  created() {
    //sessionStorage.removeItem('parameters');
    sessionStorage.removeItem('parameters');
  },

  mounted() {
    this.setInputContent();
    //this.setInputCodeSample();
    this.setInputNameFunction();
    this.loadSessionParameters();

  },

  data() {
    return {
      questionJavaCoreID: null,
      content: null,
      codeSample: null,

      codeRunToOutput: null,
      input: null,
      output: null,
      note: "",

      validationInput: null,
      validationOutput: null,
      validationCodeRunToOutput: null,

      validationContent: null,
      validationCodeSample: null,

      validationNullTestCases: null,

      listTestCases: [],

      //code sample
      nameFunction: null,
      validateNameFunction: null,

      dataType: null,
      validateDataType: null,
      listDataType: ['int', 'String', 'boolean', 'double', 'Long'],

      parameterSession: [],
      nameParameter: null,
      dataTypeParameter: null,
      validateNameParameter: null,
      validateDataTypeParameter: null,

      dataParameter: null,
      validateDataInputParameter: null,
      dataInputParameters: [],
    }
  },

  methods: {
    loadSessionParameters() {
      const p = new ParameterStorageManager();
      if(p.getAllParameters().length > 0) {
        this.parameterSession = p.getAllParameters();
      }
    },

    async setAllInput(q){
      const testCaseManager = new SessionStorageTestCase();
      this.questionJavaCoreID = q.questionJavaCoreID;
      this.content = q.contentQuestion;
      this.codeSample = q.codeSample;
      this.codeRunToOutput = q.codeRunToOutput;
      if(!this.codeRunToOutput) {
        this.codeRunToOutput = "public static void main(String[] args) {\n\n}\n";
      }
      let testCases = await BankQuestionJavaCoreDao
          .get_Test_Cases_By_Question_JavaCore_ID(Number(q.questionJavaCoreID));
      testCases.forEach(testCase => {
        const { inputTest, outputExpect, note } = testCase;
        testCaseManager.addTestCase(inputTest, outputExpect, note);
      });
      this.loadSessionTestCase();
    },

    loadSessionTestCase(){
      const testCaseManager = new SessionStorageTestCase();
      this.listTestCases = testCaseManager.loadFromSessionStorage();
    },

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

      if(allValidateFormAreEmpty) {
        testCaseManager.addTestCase(this.input.trim(), this.output.trim(), this.note.trim());
        this.listTestCases = testCaseManager.loadFromSessionStorage();
        this.resetFieldInputTestCase();
      }
    },

    deleteTestCase(indexTestCase) {
      const testCaseManager = new SessionStorageTestCase();
      testCaseManager.removeTestCase(indexTestCase );
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

      if(this.listTestCases.length === 0) {
        this.validationNullTestCases = 'Please enter test cases.';
      }
    },

    async updateQuestion() {
      this.validateNull();
      const validations = [
        this.validationContent,
        this.validationCodeSample,
        this.validationNullTestCases,
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
        //console.log("Code run to output: ", this.codeRunToOutput);
        if(this.listTestCases.length > 0 && this.questionJavaCoreID) {
          const dataToPut = {
            "questionJavaCoreID" : this.questionJavaCoreID,
            "contentQuestion" : this.content,
            "codeSample" : this.codeSample,
            "codeRunToOutput" :   this.codeRunToOutput,
            "bankTestCaseJavaCoreList" : this.listTestCases,
          }

          console.log(dataToPut);

          let statusPut = await BankQuestionJavaCoreDao.update_Question_JavaCore(dataToPut);
          if(statusPut) {
            window.location.reload();
          } else {
            alert("Updated failed.");
          }
        }
      }
    },

    resetFieldInputTestCase() {
      this.input = null;
      this.output = null;
      this.note = null;
    },

    setInputContent() {
      if(!this.content) {
        this.validationContent = null;
      } else {
        if(this.bankJavaCoreExam.length > 0) {
          const contentQuestion = this.content.trim();
          const hasMatchingContentQuestion =
              this.bankJavaCoreExam.some(q => q.contentQuestion === contentQuestion);
          if (hasMatchingContentQuestion) {
            //this.validationContent = "Content question already exists.";
            this.validationContent = null;
          } else {
            this.validationContent = null;
          }
        }
      }
    },

    setInputCodeSample() {
      if(this.codeSample) {
        this.validationCodeSample = null;
      }
    },

    setInputNameFunction() {
      if(!this.nameFunction) {
        this.validateNameFunction = null;
      } else {
        if(!Validation.validateNameFunction_Java(this.nameFunction)) {
          this.validateNameFunction = "Name function is invalid";
        } else {
          this.validateNameFunction = null;
        }
      }
    },

    setInputNameParameter() {
      if(!this.nameParameter) {
        this.validateNameParameter = null;
      } else {
        if(!Validation.validateNameParameter_Java(this.nameParameter)) {
          this.validateNameParameter = "Name parameter is invalid";
        } else {
          this.validateNameParameter = null;
        }
      }
    },

    setSelectDataType() {
      if(this.dataType) {
        this.validateDataType = null;
      }
    },

    setSelectedDataTypeParameter() {
      if(this.dataTypeParameter) {
        this.validateDataTypeParameter = null;
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
    },

    //xu li ham generate
    handleGenerateFunction() {
      if(!this.nameFunction) {
        this.validateNameFunction = "Please enter name function.";
      }

      if(!this.dataType) {
        this.validateDataType = "Please choose data type.";
      }

      const validationsToGenerate = [
        this.validateNameFunction,
        this.validateDataType
      ];
      const validateGenerateEmpty = validationsToGenerate.every(val => val === null);
      if (validateGenerateEmpty) {
        if (this.parameterSession.length > 0) {
          // Create parameter string from parameterSession
          const params = this.parameterSession.map(param => `${param.dataType} ${param.name}`).join(', ');
          this.codeSample = `public static ${this.dataType} ${this.nameFunction}(${params}) {\n\n}\n`;
        } else {
          this.codeSample = `public static ${this.dataType} ${this.nameFunction}() {\n\n}\n`;
        }
      }
    },

    saveParameter() {
      if(!this.nameParameter) {
        this.validateNameParameter = "Please enter name parameter.";
      } else if (this.parameterSession.length > 0) {
        const hasMatchingNameParameter =
            this.parameterSession.some(p => p.name === this.nameParameter.trim());
        if (hasMatchingNameParameter) {
          this.validateNameParameter = "Name parameter is exist.";
        } else {
          this.validateNameParameter = null;
        }

      }
      if (!this.dataTypeParameter){
        this.validateDataTypeParameter = "Please choose data type parameter.";
      } else {
        const p = new ParameterStorageManager();
        p.addParameter(this.nameParameter.trim(), this.dataTypeParameter);
        this.parameterSession = p.getAllParameters();
        console.log("List parameter: ", this.parameterSession);
      }
    },

    deleteParameter(index) {
      const p = new ParameterStorageManager();
      p.removeParameter(index);
      this.parameterSession = p.getAllParameters();
    },

    handleSaveDataParameter() {
      if(!this.dataParameter) {
        this.validateDataInputParameter = "Please enter data parameter."
      } else {
        this.dataInputParameters.push(this.dataParameter);
        this.dataParameter = null;
      }
    },

    setInputDataParameter() {
      if(this.dataParameter) {
        this.validateDataInputParameter = null;
      }
    },

    deleteDataInputParameter(index) {
      if (index >= 0 && index < this.dataInputParameters.length) {
        this.dataInputParameters.splice(index, 1);
      } else {
        console.error('Index out of bounds');
      }
    },

    handleGenerateInputData() {
      if (!this.nameFunction) {
        this.validateNameFunction = "Please enter name function.";
      } else if (this.dataInputParameters.length > 0) {
        const params = this.dataInputParameters.join(",");
        let codeLines = this.codeRunToOutput.split('\n');
        const insertIndex = codeLines.length - 2; // Insert before the last line
        codeLines.splice(insertIndex, 0, `\tSystem.out.println(${this.nameFunction}(${params}));`);
        this.codeRunToOutput = codeLines.join('\n') + '';
      }
    },
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
  <div class="modal fade" id="modal-update-question" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Edit question</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
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
                  placeholder="Code sample ..."
                  :autofocus="true"
                  :indent-with-tab="true"
                  :tab-size="4"
                  :extensions="extensions"
                  @ready="handleReady"
                  style="width: 100%; height: 5rem;"
                  @input="setInputCodeSample"
                  disabled
              />
            </div>
            <div class="mb-3">
              <label class="form-label">Name function:</label>
              <input  class="form-control"
                      v-model="nameFunction"
                      :class="[{'is-invalid': validateNameFunction !== null}]"
                      placeholder="Name function"
                      @input="setInputNameFunction()"
              />
              <span
                  v-if="validateNameFunction"
                  class="span-validate-modal-form"
                  @input="nameFunction"
              >{{validateNameFunction}}</span>
            </div>
            <div class="mb-3">
              <label class="form-label">Select data type:</label>
              <select class="form-select"
                      @change="setSelectDataType"
                      :class="[{'is-invalid': validateDataType !== null}]"
                      v-model="dataType"
              >
                <option v-for="d in listDataType">{{d}}</option>
              </select>
              <span
                  v-if="validateDataType"
                  class="span-validate-modal-form"
              >{{validateDataType}}</span>
            </div>

            <div class="mb-3">
              <label class="form-label">Add parameter:</label>
              <div style="display: flex; gap: 3rem">
                <input  class="form-control"
                        v-model="nameParameter"
                        @input="setInputNameParameter"
                        :class="[{'is-invalid': validateNameParameter !== null}]"
                        placeholder="Name parameter"
                />
                <select class="form-select"
                        v-model="dataTypeParameter"
                        :class="[{'is-invalid': validateDataTypeParameter !== null}]"
                        @change="setSelectedDataTypeParameter()"
                >
                  <option v-for="d in listDataType">{{d}}</option>
                </select>
                <button type="button"
                        class="button-purple style-button-save-para"
                        @click="saveParameter"
                >Save parameter</button>
              </div>
              <span
                  v-if="validateNameParameter"
                  class="span-validate-modal-form"
              >{{validateNameParameter}}</span>
              <span
                  v-if="validateDataTypeParameter"
                  class="span-validate-modal-form"
              >{{validateDataTypeParameter}}</span>
            </div>

            <table class="table table-bordered mt-3">
              <thead>
              <tr>
                <th>Index</th>
                <th>Name parameter</th>
                <th>Data type</th>
                <th>Actions</th>
              </tr>
              </thead>
              <tbody>
              <tr v-if="parameterSession.length > 0" v-for="(p, index) in parameterSession" :key="index">
                <td>{{ index + 1 }}</td>
                <td>{{ p.name }}</td>
                <td>{{ p.dataType }}</td>
                <td>
                  <button class="btn btn-sm btn-danger" @click="deleteParameter(index)">Delete</button>
                </td>
              </tr>
              </tbody>
            </table>

            <div class="mb-3">
              <button class="button-purple style-button-generate-func"
                      @click="handleGenerateFunction()"
              >Generate function</button>
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
<!--              <span-->
<!--                  v-if="validationCodeRunToOutput"-->
<!--                  class="span-validate-modal-form"-->
<!--              >{{validationCodeRunToOutput}}</span>-->
            </div>
            <div>
              <h5>Test cases</h5>
              <div class="mb-3">
                <h5>Input parameter</h5>
                <div style="display: flex; gap: 3rem">
                  <input  class="form-control"
                          placeholder="Data parameter"
                          v-model="dataParameter"
                          @input="setInputDataParameter"
                          :class="[{'is-invalid': validateDataInputParameter !== null}]"
                  />
                  <button type="button"
                          class="button-purple style-button-save-para"
                          @click="handleSaveDataParameter()"
                  >Save data parameter</button>
                </div>
                <span
                    v-if="validateDataInputParameter"
                    class="span-validate-modal-form"
                >{{validateDataInputParameter}}</span>
                <table class="table table-bordered mt-3">
                  <thead>
                  <tr>
                    <th>Index</th>
                    <th>Input data</th>
                    <th>Actions</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr v-for="(d, index) in dataInputParameters" :key="index">
                    <td>{{ index + 1 }}</td>
                    <td>{{ d }}</td>
                    <td>
                      <button class="btn btn-sm btn-danger" @click="deleteDataInputParameter(index)">Delete</button>
                    </td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <div class="mb-3">
                <button type="button"
                        class="button-purple style-button-generate-func"
                        @click="handleGenerateInputData"
                >Generate input data</button>
              </div>
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
                <label class="form-label">Expected output:</label>
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
              <button class="btn button-purple"
                      @click="updateQuestion"
              >Update question</button>
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