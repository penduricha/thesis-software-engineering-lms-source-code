<script>
import './modal-create-test-java-oop.scss';
import 'quill/dist/quill.snow.css';
import Quill from 'quill';
import Validation from "@/validation/Validation.js";
import axios from "axios";
import BankTestJavaOopDao from "@/daos/BankTestJavaOopDao.js";

export default {
  name: "ModalCreateTestJavaOop",

  data() {
    return {
      //data
      nameTest: null,

      quill: null,
      htmlContentDescription: '',

      imageClassDiagramUrl: null,
      selectFileImage: null,
      imageDiagram: null,

      //text validation
      validateNameTest: null,
      validateHtmlContentDescription: null,
      validateImageClassDiagramUrl: null,
    }
  },

  mounted() {
    this.setInputNameTest();
    this.setTextEditorQuill();
    //this.setInputDescription();
  },

  methods: {
    setInputNameTest() {
      if (this.nameTest) {
        this.validateNameTest = null;
        //validate field nameTest;
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
      if(!Validation.validateRegexHTMLNull(this.htmlContentDescription )) {
        this.validateHtmlContentDescription = null;
      }
    },

    //upload image
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
    },

    validateNullField() {
      this.htmlContentDescription = this.quill.root.innerHTML;

      if(!this.nameTest) {
        this.validateNameTest = 'Please enter name test.';
      }

      if(!this.imageClassDiagramUrl) {
        this.validateImageClassDiagramUrl = 'Please upload a image diagram.';
      }

      if(Validation.validateRegexHTMLNull(this.htmlContentDescription)) {
        this.validateHtmlContentDescription = 'Please enter description test.';
      }
    },

    async handleCreateTest() {
      this.validateNullField();
      const validations = [
        this.validateNameTest,
        this.validateHtmlContentDescription,
        this.validateImageClassDiagramUrl,
      ];
      const allValidateFormAreEmpty = validations.every(val => val === null);
      if(allValidateFormAreEmpty) {
        //b1: upload anh
        //b2: call method post
        //b3: reload page
        //upload anh
        const formData = new FormData();
        formData.append('image', this.selectFileImage);
        try {
          const response = await axios.post(
              'https://api.imgbb.com/1/upload?key=e43c98a286cf7743092254f845c26e56', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          this.imageDiagram = response.data.data.url;
        } catch (error) {
          console.error("Error uploading image: ", error);
          alert(error);
        }
        if(this.imageDiagram) {
          let testToPost = {
            "nameTest": this.nameTest.trim(),
            "descriptionTest": this.htmlContentDescription,
            "imageDiagram": this.imageDiagram,
          }
          let status = await BankTestJavaOopDao.create_Java_Test_Oop(testToPost);
          if(status) {
            //post thanh cong
            alert("Create test java oop successfully.");
            window.location.reload();
          }
        }
      }
    }
  }
}
</script>

<template>
  <div class="modal fade" id="modal-create-test-java-oop" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Add new test</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div>
            <div class="mb-3">
              <div class="mb-3">
                <label class="form-label">Name test:</label>
                <input class="form-control" maxlength="30" v-model="nameTest" @input="setInputNameTest()"
                       :class="[{ 'is-invalid': validateNameTest !== null }]" placeholder="Name test:"
                />
                <span v-if="validateNameTest" class="span-validate-modal-form">
                  {{ validateNameTest }}</span>
              </div>
            </div>
            <div class="mb-3">
              <label class="form-label">Description:</label>
              <div ref="editor" class="editor style-text-editor-quill"/>
              <span v-if="validateHtmlContentDescription" class="span-validate-modal-form">
                  {{ validateHtmlContentDescription }}</span>
            </div>
            <div class="mb-3">
              <label class="form-label">Upload image diagram class:</label>
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
                  <img :src="imageClassDiagramUrl" alt="Uploaded Image" class="preview"/>

                </div>
              </div>
              <span v-if="validateImageClassDiagramUrl" class="span-validate-modal-form">
                  {{ validateImageClassDiagramUrl }}</span>
            </div>
            <div class="text-center mt-3">
              <button type="submit" class="button-purple style-btn-create-test" @click="handleCreateTest">Create test</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use '@/scss/main';
</style>