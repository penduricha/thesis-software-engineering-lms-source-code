<template>
  <div
      @dragover.prevent
      @drop.prevent="handleDrop"
      class="drop-zone"
  >
    <p>Kéo và thả hình ảnh vào đây</p>
    <input type="file" @change="handleFileChange" accept="image/*" hidden ref="fileInput" />
    <button @click="selectFile">Chọn Hình Ảnh</button>
    <div v-if="imageUrl">
      <h3>Hình ảnh đã tải lên:</h3>
      <img :src="imageUrl" alt="Uploaded Image" class="preview"/>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: null
    };
  },
  methods: {
    handleDrop(event) {
      const files = event.dataTransfer.files;
      if (files.length) {
        this.handleFile(files[0]);
      }
    },
    handleFileChange(event) {
      const file = event.target.files[0];
      if (file) {
        this.handleFile(file);
      }
    },
    handleFile(file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    selectFile() {
      this.$refs.fileInput.click();
    }
  }
};
</script>

<style>
.drop-zone {
  border: 2px dashed #ccc;
  padding: 20px;
  text-align: center;
  cursor: pointer;
  margin: 20px 0;
}
.preview {
  margin-top: 10px;
  max-width: 100%;
  height: auto;
}
</style>