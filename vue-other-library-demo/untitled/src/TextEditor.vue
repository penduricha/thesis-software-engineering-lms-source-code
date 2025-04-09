<template>
  <div>
    <div ref="editor" class="editor"></div>
    <button @click="getHtml">Lấy dữ liệu HTML</button>
    <div v-html="htmlContent"></div>
  </div>
</template>

<script>
import 'quill/dist/quill.snow.css';
import Quill from 'quill';

export default {
  data() {
    return {
      quill: null,
      htmlContent: '',
    };
  },
  mounted() {
    this.quill = new Quill(this.$refs.editor, {
      theme: 'snow',
      modules: {
        toolbar: [
          ['bold', 'italic', 'underline'],
          ['link', 'image'],
          [{ list: 'ordered' }, { list: 'bullet' }]
        ]
      }
    });
  },
  methods: {
    getHtml() {
      this.htmlContent = this.quill.root.innerHTML;
    }
  }
};
</script>

<style>
.editor {
  height: 200px;
}
</style>