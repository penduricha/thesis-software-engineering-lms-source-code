<script>
import './page-update-test-java-oop.scss';
import RouterDao from "@/routes/RoutersDao.js";
import AsideMenu from "@/components/aside/AsideMenu.vue";
import AsideAccount from "@/components/aside/AsideAccount.vue";
import NavBarBankExam from "@/pages/bank-exams-nav-bar/NavBarBankExam.vue";
import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";
import Validation from "@/validation/Validation.js";
import Quill from "quill";
import ImageBBManage from "@/pages/imagebb/ImageBBManage.js";
import ExamDao from "@/daos/ExamDao.js";


export default {
  name: "PageUpdateTestJavaOop",
  components: {NavBarBankExam, AsideAccount, AsideMenu},

  props: {
    bankTestJavaOopID: {
      type: Number,
      required: true,
    }
  },

  data() {
    return {
      //data view
      nameTest: null,
      htmlContentDescription: null,

      //image
      imageClassDiagramUrl: null,
      selectFileImage: null,
      imageDiagram: null,

      deleteUrl: null,

      //image to update
      imageDiagramToUpdate: null,

      //validate
      validateNameTest: null,
      validateHtmlContentDescription: null,
      validateImageClassDiagramUrl: null,

      //list java test oop
      listNameTestJavaOop: [],
    }
  },

  created() {
    this.saveRouter_Path(this.getRoute());
    this.setFieldInputTestJavaOop();
  },

  mounted() {
    this.setInputNameTest();
    this.setTextEditorQuill();
  },

  methods: {
    getRoute() {
      return this.$route.path + '?bankTestJavaOopID' + '=' + this.bankTestJavaOopID;
    },

    saveRouter_Path(route) {
      const routerDao = new RouterDao();
      routerDao.savePath_To_SessionStorage(route);
    },

    async setFieldInputTestJavaOop() {
      //call api
      if (this.bankTestJavaOopID) {
        let bankTestJavaOop = await BankTestJavaOopDao
            .get_Java_Test_Oop_By_BankTestJavaOopID(this.bankTestJavaOopID);
        this.nameTest = bankTestJavaOop.nameTest;
        let listTestJavaOop = await BankTestJavaOopDao.get_List_Java_Test_Oop();
        if (listTestJavaOop.length > 0) {
          listTestJavaOop = listTestJavaOop
              .filter(item => item.bankTestJavaOopID !== Number(this.bankTestJavaOopID));
          this.listNameTestJavaOop = listTestJavaOop.map(e => e.nameTest);
          console.log("List name test java oop: ", this.listNameTestJavaOop);
        }
        //set field input
        this.quill.clipboard.dangerouslyPasteHTML(bankTestJavaOop.descriptionTest);
        this.imageDiagram = bankTestJavaOop.imageDiagram;
        this.deleteUrl = bankTestJavaOop.deleteUrl;
      }

    },

    setInputNameTest() {
      if (!this.nameTest) {
        this.validateNameTest = null;
      } else {
        const trimmedNameTest = this.nameTest.trim();
        // Nếu exams rỗng, kiểm tra regex ngay
        if (this.listNameTestJavaOop.length === 0) {
          if (Validation.isFullOfSpaces(trimmedNameTest)) {
            this.validateNameTest = null;
          } else if (!Validation.validateString_Title(Validation.removeSpaces(trimmedNameTest))) {
            this.validateNameTest = "Name test is invalid.";
          } else {
            this.validateNameTest = null;
          }
        } else {
          // Nếu exams không rỗng, kiểm tra sự tồn tại trước
          const hasMatching = this.listNameTestJavaOop.some(ln => ln === trimmedNameTest);
          if (hasMatching) {
            this.validateNameTest = "Name test already exists.";
          } else if (Validation.isFullOfSpaces(trimmedNameTest)) {
            this.validateNameTest = null;
          } else if (!Validation.validateString_Title(Validation.removeSpaces(trimmedNameTest))) {
            this.validateNameTest = "Name test is invalid.";
          } else {
            this.validateNameTest = null; // Valid title
          }
        }
      }
    },

    setTextEditorQuill() {
      this.quill = new Quill(this.$refs.editor, {
        theme: 'snow',
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline'],
            ['link', 'image'],
            [{list: 'ordered'}, {list: 'bullet'}]
          ]
        }
      });
      this.quill.on('text-change', this.setInputDescription);
    },

    setInputDescription() {
      this.htmlContentDescription = this.quill.root.innerHTML;
      if (!Validation.validateRegexHTMLNull(this.htmlContentDescription)) {
        this.validateHtmlContentDescription = null;
      }
    },

    handleDrop(event) {
      const files = event.dataTransfer.files;
      if (files.length) {
        this.handleFile(files[0]);
      }
    },
    handleFileChange(event) {
      const file = event.target.files[0];
      this.selectFileImage = file;
      if (file) {
        this.handleFile(file);
      }
      this.validateImageClassDiagramUrl = null;
    },
    handleFile(file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageClassDiagramUrl = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    selectFile() {
      this.$refs.fileInput.click();
    },

    removeImage() {
      this.imageClassDiagramUrl = null;
      this.selectFileImage = null;
      // Reset the file input
      if (this.$refs.fileInput) {
        this.$refs.fileInput.value = null;
      }
    },

    handleBackToListJavaOopTest() {
      this.$router.replace({
        path: '/main-page/bank-exams/java-oop',
      }).catch((error) => {
        console.error('Error navigating :', error);
        alert(error);
      });
    },

    validateNullField() {
      this.htmlContentDescription = this.quill.root.innerHTML;

      if (!this.nameTest) {
        this.validateNameTest = 'Please enter name test.';
      }

      if (Validation.validateRegexHTMLNull(this.htmlContentDescription)) {
        this.validateHtmlContentDescription = 'Please enter description test.';
      }
    },


    async handleUpdateTestJavaOop() {
      this.validateNullField();
      const validations = [
        this.validateNameTest,
        this.validateHtmlContentDescription,
      ];

      const allValidateFormAreEmpty = validations.every(val => val === null);
      if (allValidateFormAreEmpty) {
        // let testToPut = {
        //   "nameTest": this.nameTest.trim(),
        //   "descriptionTest": this.htmlContentDescription,
        //   "imageDiagram": this.imageDiagram,
        // }
        let testToPut = {}
        if (this.imageClassDiagramUrl) {
          //go anh imagebb va upload anh moi
          //nho lam phan nay
          if (this.selectFileImage) {
            //await ImageBBManage.deleteImage(this.deleteUrl);
            let response = await ImageBBManage.uploadImage(this.selectFileImage);
            if (response) {
              testToPut = {
                "nameTest": this.nameTest.trim(),
                "descriptionTest": this.htmlContentDescription,
                "imageDiagram": response.data.data.display_url,
              }
            }
          }
        } else {
          testToPut = {
            "nameTest": this.nameTest.trim(),
            "descriptionTest": this.htmlContentDescription,
            "imageDiagram": this.imageDiagram,
          }
          let statusStudentDoExam = await BankTestJavaOopDao.getStatus_Student_DoExam_By_BankTestJavaOopID(this.bankTestJavaOopID);
          if(!statusStudentDoExam) {
            let statusPut = await BankTestJavaOopDao
                .update_Java_Test_Oop_By_BankTestJavaOopID(
                    this.bankTestJavaOopID, testToPut);
            if (statusPut) {
              alert("Update successfully.");
              this.handleBackToListJavaOopTest();
            } else {
              alert("Update failed.");
            }
          } else {
            alert("There are students taking the test, please wait until the students finish.");
          }

        }

      }
    }
  },

}
</script>

<template>
  <body>
  <AsideMenu/>
  <main>
    <section class="section-banks">
      <h5>Bank Exam</h5>
      <nav-bar-bank-exam/>
      <div class="view-back-list-course">
        <button class="button-return-list-course"
                @click="handleBackToListJavaOopTest()"
        >
          <div class="button_nav_calendar button-return-courses-image">
            <img src="@/assets/image/button_nav_left_calendar.png" alt="button nav left calendar"
                 class="style-button-nav-calendar">
          </div>
          <span class="
            span-button-return-list-course
            span-button-return-list-course-hover"
          >
            List java oop test
          </span>
        </button>
      </div>
      <div style="width: 100%;">
        <div class="modal-header" style="display: flex; justify-content: right">
          <div >
            <button type="submit"
                    class="btn-create-exam"
                    @click="handleUpdateTestJavaOop()"
            >Update test
            </button>
          </div>
        </div>
        <div class="modal-content modal-content-create-exam">

          <div class="modal-body">
            <div class="mb-3 row">
              <label for="nameTest" class="col-sm-3 col-form-label style-label-form">
                Name test:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <input
                    type="text"
                    class="form-control"
                    placeholder="Name test"
                    maxlength="30"
                    v-model="nameTest"
                    @input="setInputNameTest"
                    :class="[{ 'is-invalid': validateNameTest !== null }]"
                />
                <span
                    class="span-validate-modal-form"
                    v-if="validateNameTest"
                >
                  {{ validateNameTest }}
                </span>
              </div>
            </div>
            <div class="mb-3 row">
              <label for="description" class="col-sm-3 col-form-label style-label-form">
                Description:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <div ref="editor" class="editor style-text-editor-quill"/>
                <span
                    class="span-validate-modal-form"
                    v-if="validateHtmlContentDescription"
                >
                  {{ validateHtmlContentDescription }}
                </span>
              </div>
            </div>
            <div class="mb-3 row" style="margin-top: 5rem">
              <label for="nameTest" class="col-sm-3 col-form-label style-label-form">
                Image class diagram:<span class="required-star">*</span>
              </label>
              <div class="col-sm-9">
                <img class="style-image-diagram" :src="imageDiagram" :alt="imageDiagram"/>
                <span
                    class="span-validate-modal-form"
                    v-if="validateImageClassDiagramUrl"
                >
                  {{ validateImageClassDiagramUrl }}
                </span>
                <label class="form-label" style="margin-top: 2rem">Upload image diagram class (if you update):</label>
                <div
                    @dragover.prevent
                    @drop.prevent="handleDrop"
                    class="drop-zone"
                >
                  <h5>Drag and drop or choose image</h5>
                  <input type="file" @change="handleFileChange" accept="image/*" hidden ref="fileInput"
                         class="form-control"/>
                  <button @click="selectFile" class="button-purple style-btn-choose-image-class">Choose image</button>
                  <div v-if="imageClassDiagramUrl" class="style-view-image-uploaded">
                    <h5>Image after uploaded</h5>
                    <button class="btn btn-danger" style="width: 4rem" @click="removeImage()">
                      <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor"
                           class="bi bi-trash style-trash" viewBox="0 0 16 16">
                        <path
                            d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                        <path
                            d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                      </svg>
                    </button>
                    <img v-if="imageClassDiagramUrl" :src="imageClassDiagramUrl" alt="Uploaded Image"
                         class="preview style-image-to-update"/>
                  </div>

                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <AsideAccount/>
  </body>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>