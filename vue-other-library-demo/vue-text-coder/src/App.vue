<template>
  <div>
    <codemirror
        v-model="code"
        placeholder="Code goes here..."
        :style="{ height: '400px' }"
        :autofocus="true"
        :indent-with-tab="true"
        :tab-size=6
        :extensions="extensions"
        @ready="handleReady"
        style="width: 70rem; height: 30rem"
    />
    <button
        @click="runCode"
        class="style-button-run"
    >
      Run
    </button>
<!--    <div style="margin-top: 10px;">-->
<!--      <h3>Output:</h3>-->
<!--      <span>{{ resultCode }}</span>-->
<!--    </div>-->
    <pre>{{codeExport}}</pre>
  </div>
</template>

<!--<script>-->
<!--import {Codemirror} from "vue-codemirror";-->
<!--import {defineComponent, ref, shallowRef} from "vue";-->
<!--import {javascript} from "@codemirror/lang-javascript";-->
<!--import {oneDark} from "@codemirror/theme-one-dark";-->
<!--import {java} from "@codemirror/lang-java";-->

<!--export default defineComponent({-->
<!--  components: {-->
<!--    Codemirror-->
<!--  },-->
<!--  setup() {-->
<!--    const code = ref(`import defaultExport from "module-name";\nconsole.log("Hello")`); // Mã mặc định-->
<!--    const resultCode = ref(''); // Biến để lưu kết quả-->
<!--    const extensions = [java(), oneDark];-->

<!--    const view = shallowRef();-->
<!--    const handleReady = (payload) => {-->
<!--      view.value = payload.view;-->
<!--    };-->

<!--    const runCode = () => {-->
<!--      // const filteredCode = code.value.split('\n').filter(line => !line.startsWith('import')).join('\n');-->
<!--      // console.log(filteredCode);-->
<!--      // const output = [];-->
<!--      // // Mảng để lưu kết quả từ console.log-->
<!--      // const originalConsoleLog = console.log;-->
<!--      // // Lưu lại console.log gốc-->
<!--      //-->
<!--      // // Ghi đè console.log-->
<!--      // console.log = (...args) => {-->
<!--      //   output.push(args.join(' '));-->
<!--      //   // Ghi lại các tham số vào mảng-->
<!--      // };-->
<!--      //-->
<!--      // try {-->
<!--      //   // Chạy mã đã lọc-->
<!--      //   eval(filteredCode);-->
<!--      //-->
<!--      //   // Thực thi mã từ trình soạn thảo-->
<!--      //   resultCode.value = output.length > 0 ? output.join('\n') : '';-->
<!--      // } catch (e) {-->
<!--      //   resultCode.value = `Error: ${e.message}`; // Lưu lỗi nếu có-->
<!--      // } finally {-->
<!--      //   console.log = originalConsoleLog;-->
<!--      //   // Khôi phục lại console.log gốc-->
<!--      // }-->
<!--    };-->

<!--    return {-->
<!--      code,-->
<!--      resultCode, // Trả về biến kết quả-->
<!--      extensions,-->
<!--      handleReady,-->
<!--      runCode,-->
<!--    };-->
<!--  }-->
<!--});-->
<!--</script>-->
<script>
import { Codemirror } from "vue-codemirror";
import { defineComponent, ref, shallowRef } from "vue";
import { oneDark } from "@codemirror/theme-one-dark";
import { java } from "@codemirror/lang-java";
import { autocompletion, completeFromList } from "@codemirror/autocomplete";
import { keymap } from "@codemirror/view";

export default defineComponent({
  components: {
    Codemirror
  },

  data() {
    return {
      code: null,
      codeExport: null,
    }
  },

  created() {
    this.code = 'public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println("Hello, World!");\n\t}\n}\n';
  },

  methods: {
    runCode () {
      // Placeholder for code execution logic
      //resultCode.value = "Code executed successfully!";
      // Example result
      this.codeExport = this.code
          .split('\n')
          .filter(line => line.trim() !== '' &&
              !line.includes('java.') &&
              !line.includes('javax.') &&
              !line.includes('jdk.') &&
              !line.includes('com.') &&
              !line.includes('org.') &&
              !line.includes('import'))
          .join('\n');
      if (this.codeExport) {
        const blob = new Blob([this.codeExport], { type: 'text/java' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'output.java';
        document.body.appendChild(a);
        a.click(); // Bắt đầu tải xuống
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
      }

    },
  },

  setup() {
    const resultCode = ref(''); // Variable to store result
    const extensions = [
      java(),
      oneDark,
      autocompletion(),
      keymap.of([
        { key: "Ctrl-Space", run: completeFromList } // Key binding for autocompletion
      ])
    ];

    const view = shallowRef();
    const handleReady = (payload) => {
      view.value = payload.view;
    };

    return {
      resultCode,
      extensions,
      handleReady,

    };
  }
});
</script>

<style>
/* Thêm CSS nếu cần */
.style-button-run {
  cursor: pointer;
  background-color: green;
  width: 5rem;
  color: white;
  height: 3rem;
  border-radius: 2px;
}
</style>