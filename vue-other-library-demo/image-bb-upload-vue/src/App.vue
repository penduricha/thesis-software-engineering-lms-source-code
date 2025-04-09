<template>
  <div>
    <input type="file" @change="onFileChange" />
    <button @click="uploadImage">Upload</button>
    <div v-if="imageUrl">
      <p>Image uploaded successfully:</p>
      <img :src="imageUrl" alt="Uploaded Image" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      selectedFile: null,
      imageUrl: ''
    };
  },
  methods: {
    onFileChange(event) {
      this.selectedFile = event.target.files[0];
    },
    async uploadImage() {
      if (!this.selectedFile) {
        alert("Please select a file first!");
        return;
      }

      const formData = new FormData();
      formData.append('image', this.selectedFile);
      try {
        const response = await axios.post('https://api.imgbb.com/1/upload?key=e43c98a286cf7743092254f845c26e56', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        this.imageUrl = response.data.data.url; // Lấy URL của ảnh đã upload
      } catch (error) {
        console.error("Error uploading image: ", error);
      }
    }
  }
};
</script>

<style scoped>
/* Thêm styles tùy ý ở đây */
</style>