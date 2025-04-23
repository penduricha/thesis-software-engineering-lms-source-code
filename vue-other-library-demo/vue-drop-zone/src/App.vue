<template>
  <div id="app">
    <h1>Upload .zip File</h1>
    <FileUpload />
  </div>
</template>

<script>
import FileUpload from './FileUpload.vue';

export default {
  components: {
    FileUpload,
  },
};
</script>

<style>
#app {
  text-align: center;
  margin-top: 50px;
}
</style>