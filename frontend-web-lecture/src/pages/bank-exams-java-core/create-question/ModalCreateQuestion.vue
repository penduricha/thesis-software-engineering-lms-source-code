<script>
import '../bank-exams.scss';
import '../../../components/span/span-style.scss';
import './modal-create-question.scss';

//import code-mirror
import { Codemirror } from "vue-codemirror";
import { shallowRef } from "vue";
import { oneDark } from "@codemirror/theme-one-dark";
import { java, } from "@codemirror/lang-java";

import { keymap } from "@codemirror/view";
import { autocompletion, completeFromList } from "@codemirror/autocomplete";
import BankQuestionJavaCoreDao from "@/daos/BankQuestionJavaCoreDao.js";
import SessionStorageTestCase from "@/pages/bank-exams-java-core/SessionStorageTestCase.js";
import Validation from "@/validation/Validation.js";
import ParameterStorageManager from "@/pages/bank-exams-java-core/ParameterStorageManager.js";
//npm i vue-csv-import
import { VueCsvImport, VueCsvToggleHeaders, VueCsvInput, VueCsvMap, VueCsvSubmit, VueCsvErrors } from 'vue-csv-import';
import LectureDao from "@/daos/LectureDao.js";
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";
import axios from 'axios';
import GenTestCase from "@/pages/bank-exams-java-core/GenTestCase.js";
// import { Alert } from 'bootstrap';

export default {
  name: "ModalCreateQuestion",
  components: {
    Codemirror,
    VueCsvImport, VueCsvToggleHeaders, VueCsvInput, VueCsvMap, VueCsvSubmit, VueCsvErrors
  },

  props: {
    bankJavaCoreExam: {
      type: Array,
      required: true,
      default: [],
    }
  },

  created() {
    sessionStorage.removeItem("testCases");
    sessionStorage.removeItem('parameters');
  },

  mounted() {
    //this.setInputContent();
    //this.setInputCodeSample();
    this.loadSessionTestCase();
    this.setInputContent();
    this.setInputNameFunction();
    this.loadSessionParameters();
  },

  data() {
    return {
      loaddingGenTestCase: false,
      content: null,
      //codeSample: "public static int function() {\n\n}\n",
      codeSample: null,

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

      //code sample
      nameFunction: null,
      validateNameFunction: null,

      dataType: null,
      validateDataType: null,
      listDataType: ['int', 'String', 'boolean', 'double', 'Long'],

      //para
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
      if (p.getAllParameters().length > 0) {
        this.parameterSession = p.getAllParameters();
      }
    },

    saveTestCase() {
      const testCaseManager = new SessionStorageTestCase();


      if (!this.input) {
        this.validationInput = "Please enter input test case.";
      }

      if (!this.output) {
        this.validationOutput = "Please enter output test case.";
      }

      const validations = [
        this.validationInput,
        this.validationOutput,
      ];
      const allValidateFormAreEmpty = validations.every(val => val === null);

      if (this.note) {
        this.note = this.note.trim();
      }

      if (allValidateFormAreEmpty) {
        testCaseManager.addTestCase(this.input.trim(), this.output.trim(), this.note);
        this.listTestCases = testCaseManager.loadFromSessionStorage();
        this.resetFieldInputTestCase();
      }
    },

    deleteTestCase(indexTestCase) {
      const testCaseManager = new SessionStorageTestCase();
      testCaseManager.removeTestCase(indexTestCase);
      this.listTestCases = testCaseManager.loadFromSessionStorage();
    },

    loadSessionTestCase() {
      const testCaseManager = new SessionStorageTestCase();
      this.listTestCases = testCaseManager.loadFromSessionStorage();
    },

    validateNull() {
      if (!this.content) {
        this.validationContent = 'Please enter content question.';
      }

      if (!this.codeSample) {
        this.validationCodeSample = 'Please enter code sample.';
      }

      if (!this.codeRunToOutput) {
        this.validationCodeRunToOutput = 'Please enter code run to output.';
      }

      //check list session test case
      const testCaseManager = new SessionStorageTestCase();
      if (this.listTestCases.length === 0) {
        this.testCaseManager = testCaseManager.loadFromSessionStorage();
      }
      if (this.listTestCases.length === 0) {
        this.validationNullTestCases = 'Please enter test cases.';
      }
    },

    async createQuestion() {
      // console.log("this",this.listTestCases);
      this.validateNull();
      const validations = [
        this.validationContent,
        this.validationCodeSample,
        this.validationNullTestCases,
        this.validationCodeRunToOutput,
      ];
      const allValidateFormAreEmpty = validations.every(val => val === null);
      if (allValidateFormAreEmpty) {
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
          "contentQuestion": this.content,
          "codeSample": this.codeSample,
          "codeRunToOutput": this.codeRunToOutput,
          "bankTestCaseJavaCoreList": this.listTestCases,
        }

        console.log(dataToPost);

        let statusPost = await BankQuestionJavaCoreDao.create_Question_JavaCore(dataToPost);
        if (statusPost) {
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
      if (!this.content) {
        this.validationContent = null;
      } else {
        if (this.bankJavaCoreExam.length > 0) {
          const contentQuestion = this.content.trim();
          const hasMatchingContentQuestion =
            this.bankJavaCoreExam.some(q => q.contentQuestion === contentQuestion);
          if (hasMatchingContentQuestion) {
            this.validationContent = "Content question already exists.";
          } else {
            this.validationContent = null;
          }
        }
      }
    },

    setSelectDataType() {
      if (this.dataType) {
        this.validateDataType = null;
      }
    },

    setSelectedDataTypeParameter() {
      if (this.dataTypeParameter) {
        this.validateDataTypeParameter = null;
      }
    },

    setInputCodeSample() {
      if (this.codeSample) {
        this.validationCodeSample = null;
      }
    },

    setInputCodeRunToOutput() {
      if (this.codeRunToOutput) {
        this.validationCodeRunToOutput = null;
      }
    },

    setInputNameFunction() {
      if (!this.nameFunction) {
        this.validateNameFunction = null;
      } else {
        if (!Validation.validateNameFunction_Java(this.nameFunction)) {
          this.validateNameFunction = "Name function is invalid";
        } else {
          this.validateNameFunction = null;
        }
      }
    },

    setInputNameParameter() {
      if (!this.nameParameter) {
        this.validateNameParameter = null;
      } else {
        if (!Validation.validateNameParameter_Java(this.nameParameter)) {
          this.validateNameParameter = "Name parameter is invalid";
        } else {
          this.validateNameParameter = null;
        }
      }
    },

    setInput() {
      if (this.input) {
        this.validationNullTestCases = null;
        this.validationInput = null;
      }
    },

    setOutput() {
      if (this.output) {
        this.validationNullTestCases = null;
        this.validationOutput = null;
      }
    },

    setNote() {
      if (this.note) {
        this.validationNullTestCases = null;
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

    //save para meter
    saveParameter() {
      if (!this.nameParameter) {
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
      if (!this.dataTypeParameter) {
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

    //xu li ham generate
    handleGenerateFunction() {
      if (!this.nameFunction) {
        this.validateNameFunction = "Please enter name function.";
      }

      if (!this.dataType) {
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

    handleSaveDataParameter() {
      if (!this.dataParameter) {
        this.validateDataInputParameter = "Please enter data parameter."
      } else {
        this.dataInputParameters.push(this.dataParameter);
        this.dataParameter = null;
      }
    },

    setInputDataParameter() {
      if (this.dataParameter) {
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

    parseCsvData(csvText) {
      const rows = csvText.split('\n').filter(row => row.trim() !== '');
      const dataRows = rows.slice(1);
      return dataRows.map(row => {
        const columns = row.split(',');
        return {
          inputTest: columns[0] || "",
          outputExpect: columns[1] || "",
          note: columns[2] || ''
        };
      });
    },

    handleCsvChange(event) {
      const file = event.target.files[0];
      if (file) {
        // Kiểm tra đuôi tệp
        const fileExtension = file.name.split('.').pop().toLowerCase();
        if (fileExtension !== 'csv') {
          alert('Vui lòng chọn tệp có đuôi .csv');
          return;
        }
        const reader = new FileReader();
        reader.onload = (e) => {
          const text = e.target.result;
          sessionStorage.removeItem("testCases");
          this.listTestCases = [];
          this.listTestCases = this.parseCsvData(text);
          sessionStorage.setItem("testCases", JSON.stringify(this.listTestCases));
        };
        reader.readAsText(file);
      }
    },

    async handleGenerateTestCase() {
      this.listTestCases = [];
      this.loaddingGenTestCase = true
      try {
         const res = await axios.post("https://api.coze.com/v1/workflow/run", {
           "parameters": {
             "input": this.content
           },
           "workflow_id": "7496876093182754834"
         }, {
           headers: {
             'Authorization': 'Bearer pat_OCcuh0BZVEGRPFPQMscgA45KzBxVe8noXhF64WIXpxRIQTC6iLNDeexIlx3InpNv',
             'Content-Type': 'application/json'
           }
         });
         let data = res.data.data;
         console.log("data", data);

         let cleanedData = data.slice(10, -1);
         console.log("cleanedData", cleanedData);
         cleanedData = cleanedData.replace(/\\/g, '');

         // console.log("cleanedData", cleanedData);
         // this.listTestCases = JSON.parse(cleanedData)
        //
        // console.log("cleanedData", cleanedData); // In ra mảng kết quả
        // this.listTestCases = JSON.parse(cleanedData)
        if(this.content) {
          //this.listTestCases = await GenTestCase.getTestCaseGenerate(this.content);

          this.listTestCases = JSON.parse(cleanedData);
        } else {
          this.validationContent = "Please enter content question.";
        }
      } catch (err) {
        console.log("err Gen Testcase", err);
      } finally {
        this.loaddingGenTestCase = false
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
          <h5 class="modal-title">Add new question</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <div class="mb-3">
              <label class="form-label">Content:</label>
              <textarea v-model="content" class="form-control" rows="4" @input="setInputContent()"
                :class="[{ 'is-invalid': validationContent !== null }]" />
              <span v-if="validationContent" class="span-validate-modal-form">{{ validationContent }}</span>
            </div>
            <div class="mb-3">
              <label class="form-label">Code sample:</label>
              <codemirror v-model="codeSample" placeholder="Write code sample ..." :autofocus="true"
                :indent-with-tab="true" :tab-size="4" :extensions="extensions" @ready="handleReady"
                style="width: 100%; height: 5rem; cursor: not-allowed" @input="setInputCodeSample" disabled />

            </div>
            <div class="mb-3">
              <label class="form-label">Name function:</label>
              <input class="form-control" v-model="nameFunction"
                :class="[{ 'is-invalid': validateNameFunction !== null }]" placeholder="Name function"
                @input="setInputNameFunction()" />
              <span v-if="validateNameFunction" class="span-validate-modal-form" @input="nameFunction">{{
                validateNameFunction }}</span>
            </div>
            <div class="mb-3">
              <label class="form-label">Select data type:</label>
              <select class="form-select" @change="setSelectDataType"
                :class="[{ 'is-invalid': validateDataType !== null }]" v-model="dataType">
                <option v-for="d in listDataType">{{ d }}</option>
              </select>
              <span v-if="validateDataType" class="span-validate-modal-form">{{ validateDataType }}</span>
            </div>

            <div class="mb-3">
              <label class="form-label">Add parameter:</label>
              <div style="display: flex; gap: 3rem">
                <input class="form-control" v-model="nameParameter" @input="setInputNameParameter"
                  :class="[{ 'is-invalid': validateNameParameter !== null }]" placeholder="Name parameter" />
                <select class="form-select" v-model="dataTypeParameter"
                  :class="[{ 'is-invalid': validateDataTypeParameter !== null }]"
                  @change="setSelectedDataTypeParameter()">
                  <option v-for="d in listDataType">{{ d }}</option>
                </select>
                <button type="button" class="button-purple style-button-save-para" @click="saveParameter">Save
                  parameter</button>
              </div>
              <span v-if="validateNameParameter" class="span-validate-modal-form">{{ validateNameParameter }}</span>
              <span v-if="validateDataTypeParameter" class="span-validate-modal-form">{{ validateDataTypeParameter
              }}</span>
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
              <button class="button-purple style-button-generate-func" @click="handleGenerateFunction()">Generate
                function</button>
            </div>
            <div class="mb-3">
              <label class="form-label">Code test out put:</label>
              <codemirror v-model="codeRunToOutput" placeholder="Code run..." :autofocus="true" :indent-with-tab="true"
                :tab-size="4" :extensions="extensions" @ready="handleReady" style="width: 100%; height: 30rem;" />
            </div>
            <div>
              <h5>Test cases</h5>
              <div class="mb-3">
                <h5>Input parameter</h5>
                <div style="display: flex; gap: 3rem">
                  <input class="form-control" placeholder="Data parameter" v-model="dataParameter"
                    @input="setInputDataParameter" :class="[{ 'is-invalid': validateDataInputParameter !== null }]" />
                  <button type="button" class="button-purple style-button-save-para"
                    @click="handleSaveDataParameter()">Save data parameter</button>
                </div>
                <span v-if="validateDataInputParameter" class="span-validate-modal-form">{{ validateDataInputParameter
                }}</span>
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
                <button type="button" class="button-purple style-button-generate-func"
                  @click="handleGenerateInputData">Generate input data</button>
              </div>
              <div class="mb-3">
                <label class="form-label">Input data (text):</label>
                <input v-model="input" class="form-control" :class="[{ 'is-invalid': validationInput !== null }]"
                  @input="setInput()" />
                <span v-if="validationInput" class="span-validate-modal-form">{{ validationInput }}</span>
              </div>
              <div class="mb-3">
                <label class="form-label">Expected Output:</label>
                <input v-model="output" class="form-control" :class="[{ 'is-invalid': validationOutput !== null }]"
                  @input="setOutput" />
                <span v-if="validationOutput" class="span-validate-modal-form">{{ validationOutput }}</span>
              </div>
              <div class="mb-3">
                <label class="form-label">Note:</label>
                <input v-model="note" class="form-control" @input="setNote()" />
              </div>
              <button type="button" class="btn button-purple" @click="saveTestCase"> <i class="fas fa-file-import" style="font-size: 1.2rem; color: #4caf50;"></i> Save test case</button>

              <vue-csv-import :fields="{
                inputTest: { required: true, label: 'Input Test' },
                outputExpect: { required: true, label: 'Output Expect' },
                note: { required: false, label: 'Note' }
              }">
                <vue-csv-input>
                  <label for="fileInput" class="btn button-purple ml-3">
                    📂Upload file CSV
                  </label>
                  <input type="file" id="fileInput" @change="handleCsvChange" style="display: none;" />
                </vue-csv-input>
              </vue-csv-import>
              <button type="button" class="btn button-purple ml-3" @click="handleGenerateTestCase">🤖 Generate
                test case</button>
              <table class="table table-bordered mt-3">
                <thead>
                  <tr>
                    <th>Index</th>
                    <th>Input</th>
                    <th>Expected output</th>
                    <th>Note</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-if="loaddingGenTestCase">
                    <td colspan="5" class="text-center">
                      <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Loading...</span>
                      </div>
                    </td>
                  </tr>
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
            <span v-if="validationNullTestCases" class="span-validate-modal-form">{{ validationNullTestCases }}</span>

            <div class="text-center mt-3">
              <button type="submit" class="btn button-purple" @click="createQuestion">Create question</button>
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