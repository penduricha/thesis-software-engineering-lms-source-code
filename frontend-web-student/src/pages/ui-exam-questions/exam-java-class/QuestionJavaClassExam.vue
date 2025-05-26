<script>
import RouterDao from "@/routes/RoutersDao.js";
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";
import ExamDao from "@/daos/ExamDao.js";
import listMenu from "@/components/aside/list-menu.js";
import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";
import './question-java-class.scss';
import JSZip from 'jszip';
import MarkStudentDao from "@/daos/MarkStudentDao.js";
import ReplaceString from "@/components/data-key-word/ReplaceString.js";
import axios from 'axios';

export default {
  name: "QuestionJavaClassExam",

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setStudent();
    this.setViewTable();
    this.setTestExam();
  },

  mounted() {
    this.checkTimeLeft();
    this.setDuration();
  },

  beforeDestroy() {
    // Dọn dẹp khi component bị hủy
    clearInterval(this.timer);
    clearInterval(this.pollingInterval);
  },

  components: {},

  props: {
    examID: {
      type: Number,
      required: true,
    },

    duration: {
      type: Number,
      required: true,
    }
  },

  data() {
    return {
      isModalVisible: false,
      modalData: null,
      isLoading: false,
      studentID: null,
      courseID: null,

      lastName: null,
      firstName: null,

      descriptionTest: null,

      //timer
      // Khởi tạo thời gian còn lại
      timeLeft: null,
      timer: null,

      //polling
      pollingInterval: null,

      viewTable: true,

      testJavaOop: {},

      zipUrl: null,
      selectFileZip: null,
      targetFileZip: null,

      validateUpdateZipFile: null,

      //code java submit cozeAI
      codeJavaSubmitted: '',
      codeJavaSubmittedSave: '',

      totalScore: 0,
    }
  },

  methods: {
    showModal(data) {
      this.modalData = data;
      this.isModalVisible = true;
    },

    async closeModal() {
      this.isModalVisible = false;
      await this.navigateToMainPage();
    },

    async saveSubmissionAndNavigateToMainPage() {

    },

    getRoute() {
      console.log(this.$route.path);
      return this.$route.path
          + "?" + "examID=" + Number(this.examID)
          + "&" + "duration=" + Number(this.duration);
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    async setStudent() {
      const studentLocalStorage = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if (studentID) {
        let student = await StudentDao.getStudentName_And_StudentID(studentID);
        //console.log(student);
        this.studentID = student.studentID;
        this.lastName = student.lastName;
        this.firstName = student.firstName;
        let courseID = await StudentDao.getCourseID_By_StudentID(studentID);
        if (courseID) {
          this.courseID = courseID;
        }
      }
    },

    async setTestExam() {
      if (this.examID) {
        this.testJavaOop = await BankTestJavaOopDao
            .getBankTestJavaOop_By_ExamID(Number(this.examID));
        console.log('Test exam: ', this.testJavaOop);
        if (this.testJavaOop) {
          this.descriptionTest = this.testJavaOop.descriptionTest;
        }
      }
    },

    checkTimeLeft() {
      const savedTime = sessionStorage.getItem('timeLeft');
      if (savedTime) {
        this.timeLeft = parseInt(savedTime, 10);
        // Bắt đầu lại timer nếu có thời gian lưu
        this.startTimer();
      } else {
        this.timeLeft = (this.duration) * 60;
      }
    },

    setDuration() {
      if (this.timer) {
        clearInterval(this.timer);
      }
      sessionStorage.setItem('timeLeft', this.timeLeft);
      this.startTimer();
    },

    clickButtonSubmit() {
      //thoi gian ket thuc
      if (this.timeLeft === 0 || this.duration <= 0) {
        this.$refs.submitButton.click();
      }
    },

    startTimer() {
      this.timer = setInterval(() => {
        if (this.timeLeft > 0) {
          this.timeLeft--;
          // Cập nhật thời gian trong localStorage
          sessionStorage.setItem('timeLeft', this.timeLeft);
        } else {
          clearInterval(this.timer);
          // Xóa thời gian khi đã hết
          sessionStorage.removeItem('timeLeft');
        }
        //this.clickButtonSubmit();
        //this.submitProject_And_NavigateToMainPage();
        this.submitProjectEndTime();
      }, 1000);
    },

    async setViewTable() {
      if (this.examID) {
        //ở đây có thể là diagram class
        this.viewTable = await ExamDao.get_View_Table_By_ExamID(this.examID);
      }
    },

    async navigateToMainPage() {
      clearInterval(this.timer);
      // Xóa thời gian khi đã hết
      sessionStorage.removeItem('timeLeft');
      const studentLocalStorage = new StudentLocalStorage();
      let studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      let status = await ExamDao.delete_Access_Exam(studentID, this.examID);
      let statusResetDate = await StudentDao.reset_Date_Time_Start_Exam(studentID);
      //mac du ko co
      //let statusDeleteCodeStorage = await CodeStorageDao.delete_Code_Storage_By_StudentID(studentID);

      if (!status || !statusResetDate) {
        alert("Can't return page because error system.");
      } else {
        window.location.reload();
        const itemsMenu = listMenu;
        const path = itemsMenu.find(item => item.index === 1)?.path;
        this.savePath_Init_To_LocalStorage(path);
        this.$router.replace({
          path: path,
          // query: {
          // }
        }).catch((error) => {
          console.error('Error navigating :', error);
          alert(error);
        });
      }
    },

    savePath_Init_To_LocalStorage(path) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_LocalStorage(path);
    },

    async checkZipFile() {
      const zip = new JSZip();
      try {
        const content = await zip.loadAsync(this.targetFileZip);
        const files = Object.keys(content.files);

        // Kiểm tra xem tất cả các file có phải là .java hay không
        //const allJavaFiles = files.every(file => file.endsWith('.java'));

        // Kiểm tra xem có file nào không phải là .java không
        // if (!allJavaFiles || files.length === 0) {
        //   this.validateUpdateZipFile = 'The zip file must contain only .java files.';
        //   return;
        // }

        // // Kiểm tra xem có ít nhất một thư mục con trong zip không
        // const hasSubfolder = files.some(file => file.split('/').length > 1);
        //
        // if (!hasSubfolder) {
        //   this.validateUpdateZipFile = 'The zip file must contain at least one subfolder.';
        //   return;
        // }
        // if(!this.zipUrl ||
        //     !this.selectFileZip ||
        //     !this.targetFileZip) {
        //   this.validateUpdateZipFile = 'Please upload zip file.';
        // }

        // Thực hiện upload file zip ở đây
        console.log('File is valid and ready to upload.');
      } catch (error) {
        console.error('Error reading zip file: ' + error);
        this.validateUpdateZipFile = 'Please upload a zip file.'
      }
    },

    async submitProjectEndTime() {
      if (this.timeLeft === 0 || this.duration <= 0) {
        if (!this.zipUrl ||
            !this.selectFileZip ||
            !this.targetFileZip) {
          //set diem bai ktra do la 0
          let dataPut = {
            "studentID": this.studentID,
            "examID": Number(this.examID)
          }
          let status = await MarkStudentDao.updateMark_Zero_If_No_Submitted(dataPut);
          if (status) {
            await this.navigateToMainPage();
          } else {
            alert('Error system. ');
            await this.navigateToMainPage();
          }
        } else {
          //upload va cham
          //submit project
          if (this.targetFileZip) {
            const zip = new JSZip();
            const content = await zip.loadAsync(this.targetFileZip);
            let combinedString = '';
            const files = Object.keys(content.files);
            //duyet file zip
            for (const fileName of files) {
              if (fileName.endsWith('.java')) {
                const fileData = await content.files[fileName].async('string');
                // Thêm xuống dòng
                combinedString += fileData + '\n';
              }
            }
            // xóa hết các dòng rỗng.
            this.codeJavaSubmitted = combinedString;
            this.codeJavaSubmittedSave = this.codeJavaSubmitted;
            this.codeJavaSubmitted = ReplaceString.escapeString(String(this.codeJavaSubmitted));
            console.log('code java submitted: ', this.codeJavaSubmitted);
            await this.submitProject();
            await this.navigateToMainPage();
          }

        }
      }
    },
    async submitProject() {
      this.isLoading = true;

      if (this.examID && this.studentID && this.codeJavaSubmitted && this.descriptionTest) {
        let parameters = {
          "examID": this.examID,
          "studentID": this.studentID,
          "input": this.codeJavaSubmitted,
          "debai": this.descriptionTest
        };

        let data = {
          "parameters": parameters,
          "workflow_id": "7496875298778054664"
        };

        try {
          const response = await axios.post('https://api.coze.com/v1/workflow/run', data, {
            headers: {
              'Authorization': 'Bearer pat_OCcuh0BZVEGRPFPQMscgA45KzBxVe8noXhF64WIXpxRIQTC6iLNDeexIlx3InpNv',
              'Content-Type': 'application/json'
            }
          });

          if (response?.data) {
            let rawData = response.data.data;

            let cleanedData = rawData
                .replace(/"```json/g, '')      // Loại bỏ ```json
                .replace(/\n/g, '')           // Loại bỏ \n
                .replace(/\\n/g, '')          // Loại bỏ \n dư thừa từ chuỗi JSON
                .replace(/\\/g, '')          // Loại bỏ \/
                .replace(/\\/g, '')           // Loại bỏ dấu \ không cần thiết
                .replace(/\"/g, '"')
                .replace(/```"/g, '')          // Đảm bảo chuỗi sử dụng dấu " đúng cách
                .replace(/(\r\n|\n|\r)/gm, '');


            console.log('Cleaned Data:', cleanedData);
            console.log('Json', JSON.parse(cleanedData));
            let parsedData = JSON.parse(cleanedData);
            parsedData.output.codeSubmitString = this.codeJavaSubmittedSave;
            parsedData.output.examID = Number(this.examID);

            console.log(parsedData);
            const totalScore = parsedData.output.detail.reduce((sum, item) => sum + item.scoreAchieve, 0);

            console.log("total score", totalScore);
            // Cập nhật lại totalScore
            parsedData.output.totalScore = totalScore;
            // Gọi phương thức showModal để hiển thị thông tin modal
            this.showModal(parsedData);
            //save thong tin vao database
            let status = await MarkStudentDao.postFormSubmissionJavaClass(parsedData.output);
            if(!status) {
              alert("Can't save to system.");
            }
          }
        } catch (error) {
          console.error('Error submitting project:', error);
          alert(error.message);
          this.isLoading = false;
          await this.navigateToMainPage();
        }
      }
      this.isLoading = false;
      //persist vao database

    },


    async submitProject_And_NavigateToMainPage() {
      await this.checkZipFile();
      if (!this.validateUpdateZipFile) {
        //submit project
        if (this.targetFileZip) {
          const zip = new JSZip();
          const content = await zip.loadAsync(this.targetFileZip);
          let combinedString = '';
          const files = Object.keys(content.files);
          //duyet file zip
          for (const fileName of files) {
            if (fileName.endsWith('.java')) {
              const fileData = await content.files[fileName].async('string');
              // Thêm xuống dòng
              combinedString += fileData + '\n';
            }
          }
          this.codeJavaSubmitted = combinedString;
          this.codeJavaSubmittedSave = this.codeJavaSubmitted;
          this.codeJavaSubmitted = ReplaceString.escapeString(String(this.codeJavaSubmitted));
          //this.codeJavaSubmitted = this.codeJavaSubmitted.replace(/^\s*[\r\n]/gm, '');
          console.log('code java submitted: ', this.codeJavaSubmitted);
          await this.submitProject();
        }
        //sau khi submit xong thi quay ve trang main page
        //await this.navigateToMainPage();
      }
    },

    //upload zip
    handleDrop(event) {
      const files = event.dataTransfer.files;
      if (files.length) {
        this.handleFileChange(files[0]);
      }
    },

    handleFileChange(event) {
      const file = event.target.files[0];
      if (file && file.name.endsWith('.zip')) {
        this.targetFileZip = file;
        this.selectFileZip = file.name; // Store the uploaded file name
        this.zipUrl = URL.createObjectURL(file); // Create a URL for the uploaded zip file
        this.validateUpdateZipFile = null; // Reset any validation messages
      } else {
        this.validateUpdateZipFile = 'Please upload a valid zip file.'; // Show an error message
        this.zipUrl = null; // Reset the zip URL if the file is invalid
        this.selectFileZip = null; // Reset the selected file name
        this.targetFileZip = null;
      }
    },

    // selectFile (){
    //
    // },

    // handleFile(file) {
    //   if (file && file.name.endsWith('.zip')) {
    //     this.selectFileZip = file.name; // Store the uploaded file name
    //     this.zipUrl = URL.createObjectURL(file); // Create a URL for the uploaded zip file
    //     this.validateUpdateZipFile = null; // Reset any validation messages
    //   } else {
    //     this.validateUpdateZipFile = 'Please upload a valid zip file.'; // Show an error message
    //   }
    // },

    selectFile() {
      this.$refs.fileInput.click();
    },

    removeZip() {
      this.zipUrl = null; // Clear the URL
      this.selectFileZip = null; // Clear the file name
      this.validateUpdateZipFile = null; // Reset validation message
      this.targetFileZip = null;
      // Reset the file input
      if (this.$refs.fileInput) {
        this.$refs.fileInput.value = null; // Clear the input value
      }
    },

    checkTotalScore(totalScore) {
      if(totalScore < 10)
        return 10;
      else if(totalScore < 0)
        return 0;
      else
        return totalScore;
    }
  },

  computed: {
    containerStyle() {
      return {
        minWidth: `${window.innerWidth}px`,
        minHeight: `${window.innerHeight}px`,
      };
    },

    // Tính toán thời gian còn lại
    formattedTime() {
      const minutes = Math.floor(this.timeLeft / 60);
      const seconds = this.timeLeft % 60;
      return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
    },

    // nameButtonMarkFlag() {
    //   return this.questionInit.isMarkedFlag ? "Remove flag" : "Mark flag";
    // },
  },
}

// const escapeString = (str) => {
//   return str.replace(/\\/g, '\\\\').replace(/"/g, '\\"');
// };
</script>

<template>
  <div :style="containerStyle">
    <header class="page-header">
      <div class="aside-questions">
        <div class="style-view-questions">
          <div class="view-list-questions">
            <button class="button-number-question button-submit" ref="submitButton"
                    @click="submitProject_And_NavigateToMainPage()">Submit
            </button>
          </div>
        </div>
        <span class="style-time">Time: {{ formattedTime }}</span>
      </div>
      <div class="aside-account-in-exam">
        <h1 class="style-name-student-exam">
          <img src="../../../assets/image/account-logo.png" alt="account logo" class="style-account-logo-in-exam">
          <span v-if="studentID" class="style-span-information">{{ lastName }} {{ firstName }} - {{ studentID }}</span>
        </h1>
      </div>
    </header>
    <div class="style-main">
      <!--      Hien thi de thi -->
      <section class="section-exam description-exam-java-class">
        <p v-if="testJavaOop" v-html="testJavaOop.descriptionTest"/>
      </section>
      <section class="section-code-editor section-exam-java-class">
        <!--      Hien thi phan upload -->
        <div class="view-image-class-diagrams">
          <img v-if="testJavaOop" :src="testJavaOop.imageDiagram" class="style-image-diagram-exam" alt="diagram exam">
        </div>
        <div class="view-upload-project">
          <div class="mb-3">
            <h5>Upload zip file:</h5>
            <div @dragover.prevent @drop.prevent="handleDrop" class="drop-zone">
              <h5>Drag and drop or choose zip file</h5>
              <input type="file" @change="handleFileChange" accept=".zip" hidden ref="fileInput" class="form-control"
                     :class="[{ 'is-invalid': validateUpdateZipFile !== null }]"/>
              <button @click="selectFile" class="button-purple style-btn-choose-image-class">Choose</button>

              <div v-if="zipUrl" class="style-view-image-uploaded">
                <h5>Zip file uploaded:</h5>
                <p>{{ selectFileZip }}</p>
                <button class="btn btn-danger" style="width: 4rem" @click="removeZip()">
                  <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                       class="bi bi-trash style-trash" viewBox="0 0 16 16">
                    <path
                        d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                    <path
                        d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                  </svg>
                </button>
              </div>
            </div>
            <span v-if="validateUpdateZipFile" class="span-validate-modal-form">
              {{ validateUpdateZipFile }}
            </span>
          </div>
        </div>
      </section>

    </div>
    <div v-if="isModalVisible" class="modal-overlay">
      <div class="modal-content">
        <h2 class="chug">You finished this exam</h2>
        <h3 class="chug"><strong>Total score:</strong> {{checkTotalScore(Number(modalData.output.totalScore)) }}</h3>

        <div v-for="(item, index) in modalData.output.detail" :key="index">
          <h4>{{ item.sentence }}</h4>
          <p><strong>Score achievement:</strong> {{ item.scoreAchieve }} / {{ item.maxScore }}</p>
          <p><strong>Reviews:</strong> {{ item.reviews }}</p>
        </div>

        <p><strong>Conclusion:</strong> {{ modalData.output.suggest }}</p>
        <!-- Cho nay sẽ save vao database-->
        <button @click="closeModal" class="button-close">Go to main page</button>
      </div>
    </div>
    <div v-if="isLoading" class="loading-overlay">
      <div class="loading-spinner"></div>
      <span class="loading-text">Grading, please wait...</span>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>