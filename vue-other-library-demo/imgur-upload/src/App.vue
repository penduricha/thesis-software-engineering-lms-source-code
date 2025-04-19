<!--sl.u.AFrWPWqmw88ehtcMvSvpPYsyw3lUyO6o5XVwH8NAQ6RBWFr6TwA44L_7QWv7FuO2Zqc5vtC_lT8ig6kyc2uaaeyJ91V-ZAeUj8XQ_sFeLP95f_uxzTFKRsbpR3spruJPfC6Um0BAdwDZgKmg4uajPUuR-8I3U39fB3R80Czdlg5qpagqh0sgGC0QN-qJXE5xOOG4e7Ya-GX7IYqpP2oSJ42e-o0oucCyo0Ld0E78HPC4hba6rsHLHL86KisoA9uES174faAIWn8SkFUszO2RBnvlxTwD9Erw9MA1vzHdl9QxcFNbgXFCg7fFHlC4o3Xu4tnIU7TVou3nddmuIl73IzqIoWgSdl7k61kkEtSc5azQogVwEDAUxJ9ywnYRwRpw4VYeK_yespRZvO31Tx6aqx_uJrnOCxmio3Cr9axpUx_EeEUGcBe9wsSzZP4GWUmgsuK84VnhJkDMULFrLBz8OJQUTAa2eBaJ6Qk-1r0K4uTVpjEQA0237Nw7gZ35x4B7C-FyV5ERRDHpUUzwVSAPq4Q0-OvI6jCLrvxXcFs7qysJCwC2310AQzcoHYcn05R7qG_cw5yYp5_s65lkedsiNFmMb1et9nPVAVhL_dzVD-zJap4fzm72qOwMKlyBLqJMhCmcL48_nZlYdcjJCfI1q1T4RIXIPoBEvIeBkw_OZ6z4c7RqzeoJoJfq3kAxmiyGfGSJMW3Ch_P9h_vxugUcTM_4cY4EWvJ8OPZL4BPprN-_gAfPAgyHWI26Yd7jn9Xvn7rBVmBiQMb-O_yq3Jh2Ptns-W6VRrIPu2r-T7MiofiK5GPD6Nr_Xzyc7cVVHNfc5z13MyIbNth4-EOd8juZ7W4tXLlk1-aDiM_MUxc9OhDBk8ti4PswqiqKR_E3x8Q2qkHwB0uYnNdWKIRuL5K4GHEWe0GJFx3fcqq3AzVpeRcw3X9wQcUMMVveH9vPF5EK_YoAevUxeOGxchQXxyQwAd90EEpZYYSlc1U_iBuFGc8PNkJcfHj5FlJ_89nFzGHLOuzUZQbyNbNdn_3VVF1vVOhmJ8_UgNz5VbL_2k97cnSAGvJ830_Nz3cJBaImjlwIZHf0yJ83MMsLmv42340ifWJVAEkNPYNANAmOSL0SUe-FUbpeGK6piqoAMNZMPf2oO4qvqtmZRNisLbtKLmhEo-eVPuLqtmD06AYHWmNWy31zeb1okd5pgj4aDizwATq3jE6TIXKPeIbZG0m_FujPvecRoieoeWlfvQaRJr37S3aU9Hygh5G2GZOJ7DGd_JgfmNuNWcfa5ZPbgBWxoKN6Yi2qfrzmnYil4IezRqJg3FcPyD4mEJaDZ3h8RwAg3e8tdjjOMDnxHqL6x7yAppP_y_HizMydMYZqo0UKNit7fP4hxqJOnBeFeKtXKkwpHiKNKuwIECoF3X6I-jzYKHO9MZDj-6GQG0ZvQhCxsVwYDpmoIPrzTmnYMT7cI95b1_urQbQ-->
<!--<template>-->
<!--  <div>-->
<!--    <h1>Upload ZIP File to Mega</h1>-->
<!--    <input type="file" @change="onFileChange" accept=".txt" />-->
<!--    <button @click="uploadFile">Upload</button>-->
<!--    <button @click="uploadMega">Upload mega</button>-->
<!--    <p v-if="message">{{ message }}</p>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import { Storage } from 'megajs';-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      file: null,-->
<!--      message: '',-->
<!--      email: 'tunhatminh20@gmail.com', // Thay thế bằng email của bạn-->
<!--      password: 'messivodichworldcupUCL0981646636' // Thay thế bằng mật khẩu của bạn-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    async uploadMega() {-->
<!--      const storage = await new Storage({-->
<!--        email: this.email,-->
<!--        password: this.password-->
<!--      }).ready-->

<!--      const file = await storage.upload('hello-world.txt', 'Hello world!').complete-->
<!--      console.log('The file was uploaded!', file);-->
<!--    },-->

<!--    onFileChange(event) {-->
<!--      this.file = event.target.files[0];-->
<!--    },-->
<!--    async uploadFile() {-->
<!--      if (!this.file) {-->
<!--        this.message = 'Please select a ZIP file.';-->
<!--        return;-->
<!--      }-->

<!--      try {-->
<!--        const storage = await new Storage({-->
<!--          email: this.email,-->
<!--          password: this.password,-->
<!--          allowUploadBuffering: true // Đảm bảo bật tùy chọn này-->
<!--        }).ready;-->

<!--        const fileContent = await this.readFile(this.file); // Đọc nội dung file ZIP-->
<!--        const fileName = this.file.name; // Lấy tên file từ đối tượng file-->

<!--        const file = await storage.upload("hello.txt", "hello").complete;-->
<!--        console.log('The file was uploaded!', file);-->
<!--        this.message = 'The file was uploaded successfully: ' + file.name;-->
<!--      } catch (error) {-->
<!--        console.error(error);-->
<!--        this.message = 'Upload failed: ' + error.message;-->
<!--      }-->
<!--    },-->
<!--    readFile(file) {-->
<!--      return new Promise((resolve, reject) => {-->
<!--        const reader = new FileReader();-->
<!--        reader.onload = (e) => resolve(e.target.result);-->
<!--        reader.onerror = (err) => reject(err);-->
<!--        reader.readAsArrayBuffer(file); // Đọc file dưới dạng ArrayBuffer-->
<!--      });-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style>-->
<!--/* Thêm CSS nếu cần */-->
<!--</style>-->
<template>
  <div>
    <input type="file" @change="handleFileChange" />
    <button @click="uploadFile" :disabled="!file">Upload to Dropbox</button>
  </div>
</template>

<script>
import { Dropbox } from 'dropbox';

export default {
  data() {
    return {
      file: null,
      //token: 'sl.u.AFrWPWqmw88ehtcMvSvpPYsyw3lUyO6o5XVwH8NAQ6RBWFr6TwA44L_7QWv7FuO2Zqc5vtC_lT8ig6kyc2uaaeyJ91V-ZAeUj8XQ_sFeLP95f_uxzTFKRsbpR3spruJPfC6Um0BAdwDZgKmg4uajPUuR-8I3U39fB3R80Czdlg5qpagqh0sgGC0QN-qJXE5xOOG4e7Ya-GX7IYqpP2oSJ42e-o0oucCyo0Ld0E78HPC4hba6rsHLHL86KisoA9uES174faAIWn8SkFUszO2RBnvlxTwD9Erw9MA1vzHdl9QxcFNbgXFCg7fFHlC4o3Xu4tnIU7TVou3nddmuIl73IzqIoWgSdl7k61kkEtSc5azQogVwEDAUxJ9ywnYRwRpw4VYeK_yespRZvO31Tx6aqx_uJrnOCxmio3Cr9axpUx_EeEUGcBe9wsSzZP4GWUmgsuK84VnhJkDMULFrLBz8OJQUTAa2eBaJ6Qk-1r0K4uTVpjEQA0237Nw7gZ35x4B7C-FyV5ERRDHpUUzwVSAPq4Q0-OvI6jCLrvxXcFs7qysJCwC2310AQzcoHYcn05R7qG_cw5yYp5_s65lkedsiNFmMb1et9nPVAVhL_dzVD-zJap4fzm72qOwMKlyBLqJMhCmcL48_nZlYdcjJCfI1q1T4RIXIPoBEvIeBkw_OZ6z4c7RqzeoJoJfq3kAxmiyGfGSJMW3Ch_P9h_vxugUcTM_4cY4EWvJ8OPZL4BPprN-_gAfPAgyHWI26Yd7jn9Xvn7rBVmBiQMb-O_yq3Jh2Ptns-W6VRrIPu2r-T7MiofiK5GPD6Nr_Xzyc7cVVHNfc5z13MyIbNth4-EOd8juZ7W4tXLlk1-aDiM_MUxc9OhDBk8ti4PswqiqKR_E3x8Q2qkHwB0uYnNdWKIRuL5K4GHEWe0GJFx3fcqq3AzVpeRcw3X9wQcUMMVveH9vPF5EK_YoAevUxeOGxchQXxyQwAd90EEpZYYSlc1U_iBuFGc8PNkJcfHj5FlJ_89nFzGHLOuzUZQbyNbNdn_3VVF1vVOhmJ8_UgNz5VbL_2k97cnSAGvJ830_Nz3cJBaImjlwIZHf0yJ83MMsLmv42340ifWJVAEkNPYNANAmOSL0SUe-FUbpeGK6piqoAMNZMPf2oO4qvqtmZRNisLbtKLmhEo-eVPuLqtmD06AYHWmNWy31zeb1okd5pgj4aDizwATq3jE6TIXKPeIbZG0m_FujPvecRoieoeWlfvQaRJr37S3aU9Hygh5G2GZOJ7DGd_JgfmNuNWcfa5ZPbgBWxoKN6Yi2qfrzmnYil4IezRqJg3FcPyD4mEJaDZ3h8RwAg3e8tdjjOMDnxHqL6x7yAppP_y_HizMydMYZqo0UKNit7fP4hxqJOnBeFeKtXKkwpHiKNKuwIECoF3X6I-jzYKHO9MZDj-6GQG0ZvQhCxsVwYDpmoIPrzTmnYMT7cI95b1_urQbQ'
      token: 'sl.u.AFoA_XUccIXf7LQPEYhZiZgyRdN7jiJXRmmGvhzKqgPJ3YSAiXUaETR9gg3KQzV80do-z4M00Drpf2cHGsISuhzBb9AJV1UaHeqbj3TqvsMuzM8nM-mmyRV8JD_FABq-fYyLZJn8V_H1wQp0DrhYGplJWNzNAH9TjHuE0DbuyfBDlh-6wFdNti-IQhT1Q58UBoPMvZKwQwMbZkogxb2tqk4dJqsBQURvDRCeQ8cNofsr8WfMq2E0mPoQFKmDqKJ_-ehtgfyUQYwgZLzf7yXFseFExcBzZupn_xMNbHyfjnmA5R8erOUv872EdoDAhdpXd1I2Rcq152odocB9cA0y353yybTg_7AQUbpCJw40nzLcy-344auf1udQQ5tTUoS-XcNMsWW-yPxZp1N51mR2mJPhvLRKqUyx02R_jSY_-LplUA1qe6v79d8JnxyGBALlg9awc09mIWEWbtU6VIyoIW0ssc8Pic4RUFNsIGKaEc2psQJRbPJBnMVz9DS3yyX8d6CBB0Oih0G7U2CZxtau6KypZClVkNKdwXCid2H03-ORyrNF-HgSJo7K8eLWwmhMGi3VsD1r2WUBnh-gzUn-PMgvUH9AfV3nBRcGl83S2FOnds2yJfFPT2VybCjL37ZkK89tLV01BL9uWKzkD5dkK2ntq6rF9t2bD90UNkFEjH3hPP9OdWr4vy8l7I4_9zGSy_MqYODzt3nWxu_TuFo0fGbBVNteifJcxv-zV-Lv6ksa4Yc1BX7ITL0TRazjzxXNLnbisdOb7ajTka2aIyKr3j9hwOjQ8a310FKWce9fqlDFyubIR8InM2m23Y7e39q8ypbKvHkAZ3Gaeet4Vm698SSK98aMfbpsAsgGE2sKwYUVFNN_ZEn7zjjMubuFNyNitcXMrQl58pn6dnKjBAVidojkcGG6VX9LABJO5aTvgSh14sf3I78oDITxcu1PAi5PBQpzhwaKPAn0vTcJSpGEL6cOLudHA23KnaZ6-7WJzg-NzcZpqknLjGLT_9yaJrR2WedmJbIE7F9QGfuBWrEjpxMBWufDz_1yEhmZOe5JmE8I8OZeM5x9QKY13Mm9DpvliMbsAe-PSOxYCZUVBc26nCiOUGgBah1or6LR6vS8ww9CxFONoYZ9z2GROwBxznZopYw2effcVx46En4AWmwx5SNmGtgNmnyQ5e0vZHeZO0hDP4O3ulYxFWlPyi30gXqAaZ92G_BEPIlUTkmp33YLQm3lOLpOIHK-ITNlVQ2U74Ix55ADqWPWIsrfHvNDLWvTOwevF3eZbFPZ7GR_Zb5KZXPwJlEL54kopvGYlgcrNx9EO_CwJ2aD7BFKTFvcEzd2IFfiNgxSdcIxLWMwVKr81kVh6Yi8bydZXNneoDO1XhSbRs6ulK174gaIuYvmkrqG46HSoCWQpbBEPbvwX4oxEyJ8yNmGYlU-D1ypdUDEN1twfsprRqcVOE32FfwNV8YZo2Q'
    };
  },
  methods: {
    handleFileChange(event) {
      this.file = event.target.files[0];
    },
    async uploadFile() {
      if (!this.file) return;

      const dbx = new Dropbox({ accessToken: this.token }); // Thay thế bằng Access Token của bạn

      try {
        const response = await dbx.filesUpload({
          path: '/' + this.file.name,
          contents: this.file,
        });
        console.log('File uploaded successfully:', response);
      } catch (error) {
        console.error('Error uploading file:', error);
      }
    },
  },
};
</script>