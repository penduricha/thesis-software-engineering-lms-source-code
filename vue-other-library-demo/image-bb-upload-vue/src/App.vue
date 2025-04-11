<template>
  <div>
    <input type="file" @change="onFileChange" />
    <button @click="uploadImage">Upload</button>
    <button @click="deleteImage">Delete</button>
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
    async deleteImage() {
      try {
        const response = await axios.post('https://ibb.co/ZRB9hn5y/8084c77c01b008874d560d94b27454f6');
        this.message = 'Ảnh đã được xóa thành công!';
        console.log(response.data);
      } catch (error) {
        this.message = 'Lỗi khi xóa ảnh: ' + error.message;
        console.error(error);
      }
    },

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
        this.imageUrl = response.data.data.display_url; // Lấy URL của ảnh đã upload
        console.log(response);
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