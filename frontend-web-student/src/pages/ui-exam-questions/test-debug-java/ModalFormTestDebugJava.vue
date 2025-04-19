<script>
import './modal-form-test-debug-java.scss'
import {autocompletion, completeFromList} from "@codemirror/autocomplete";
import {java} from "@codemirror/lang-java";
import {oneDark} from "@codemirror/theme-one-dark";
import {keymap} from "@codemirror/view";
import {shallowRef} from "vue";
import javaKeyWords from "@/components/data-key-word/javaKeyWords.js";
//import lib code mirror
import {Codemirror} from "vue-codemirror";
import ExecuteJavaDao from "@/daos/ExecuteJavaDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";

export default {
  name: "ModalFormTestDebugJava",
  components: {Codemirror},

  props: {
    examID : {
      type: Number,
      default: 1,
      required: true,
    },

    questionJavaCoreExamID : {
      type: Number,
      default: 1,
      required: true,
    }
  },

  data() {
    return {
      code: null,
      output: null,
    }
  },

  methods: {
    setCode( codeFromUIExam ) {
      this.code = "public class Main {\n\t" + codeFromUIExam + "\n\t" + "public static void main(String[] args) {\n\n\t}\n}";
    },

    async runDebugJava() {
      if(this.code) {
        let mapCodeToPost = {
          "codeMain" : this.code,
        }
        const studentLocalStorage  = new StudentLocalStorage();
        let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
        this.output = await
            ExecuteJavaDao.get_Output_Form_JavaCore(mapCodeToPost, studentID, this.examID, this.questionJavaCoreExamID);
      }
    },
  },

  setup() {
    // Danh sách từ khóa Java quan trọng
    const javaKeywords = javaKeyWords;

    // Danh sách kiểu dữ liệu Java (hỗ trợ khi khai báo biến)
    const javaDataTypes = [
      "boolean", "char", "byte", "short", "int", "long", "float", "double", "String",
      "List", "ArrayList", "LinkedList", "Set", "HashSet", "TreeSet", "Map", "HashMap", "TreeMap"
    ];
    // Danh sách hàm getter, setter, constructors
    const javaMethods = [
      {label: "getName()", type: "function", detail: "Getter method for name"},
      {label: "setName(String name)", type: "function", detail: "Setter method for name"},
      {label: "getAge()", type: "function", detail: "Getter method for age"},
      {label: "setAge(int age)", type: "function", detail: "Setter method for age"},
      {label: "toString()", type: "function", detail: "Convert object to string"},
      {label: "equals(Object obj)", type: "function", detail: "Check object equality"},
      {label: "hashCode()", type: "function", detail: "Generate hash code"},
      {label: "compareTo(Object obj)", type: "function", detail: "Compare two objects"},
      {label: "clone()", type: "function", detail: "Clone the object"},
      {label: "public class ClassName () { }", type: "snippet", detail: "Create a Java class"},
      {label: "private int ;", type: "variable", detail: "Declare private int variable"},
      {label: "private String ;", type: "variable", detail: "Declare private String variable"},
      {label: "private double ;", type: "variable", detail: "Declare private String variable"},
      {label: "private float ;", type: "variable", detail: "Declare private String variable"},
      {label: "private boolean ;", type: "variable", detail: "Declare private String variable"}
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
      autocompletion({override: [javaCompletion]}),
      keymap.of([
        {key: "Ctrl-Space", run: autocompletion()} // Nhấn Ctrl + Space để gợi ý code
      ])
    ];

    const view = shallowRef();
    const handleReady = (payload) => {
      view.value = payload.view;
    };

    return {
      extensions: codeMirrorExtensions,
      handleReady,
    }
  },
}
</script>

<template>
  <div class="modal fade" id="modal-form-debug-java" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Debug code java.</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <button class="btn btn-success" @click="runDebugJava" style="width: 5rem">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-play style-icon-run-code" viewBox="0 0 16 16">
                <path d="M10.804 8 5 4.633v6.734zm.792-.696a.802.802 0 0 1 0 1.392l-6.363 3.692C4.713 12.69 4 12.345 4 11.692V4.308c0-.653.713-.998 1.233-.696z"/>
              </svg>
            </button>
          </div>
          <div class="mb-3">
            <codemirror
                v-model="code"
                placeholder="Write code sample ..."
                :autofocus="true"
                :indent-with-tab="true"
                :tab-size="4"
                :extensions="extensions"
                @ready="handleReady"
                style="width: 100%; height: 35rem;"
            />
          </div>
          <div class="mb-3" style="padding-left: 0.5rem; padding-right: 0.5rem;">
            <h5>Output:</h5>
            <pre class="style-output">{{output}}</pre>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>