<script>
import './modal-view-src-code-java-class.scss';
import DetailMarkStudentDao from "@/daos/DetailMarkStudentDao.js";
export default {
  name: "ModalViewSourceCodeJavaClass",

  data() {
    return {
      codeSubmitString: null,
      suggest: null,
    }
  },

  methods: {
    async setModalViewSourceCodeJavaClass_By_DetailMarkStudentID(detailMarkStudentID) {
      let responseCodeSubmit = await DetailMarkStudentDao.getCodeSubmitJavaClass_By_DetailMarkStudentID(detailMarkStudentID);
      if(responseCodeSubmit) {
        this.codeSubmitString = responseCodeSubmit.codeSubmitString;
        this.suggest = responseCodeSubmit.suggest;
        console.log(this.codeSubmitString);
      }
    }
  },

  computed: {

  }
}
</script>

<template>
  <div class="modal fade" id="modal-view-src-code-java-class" tabindex="-1" aria-labelledby="modalResultLabel"
       aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content custom-modal">
        <div class="modal-title-wrapper">
          <h5>Source code submitted</h5>

          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="question-container">
            <span class="text-danger"
                  v-if="!this.codeSubmitString"
            >Students have not submitted their assignments or the submissions are overdue.</span>
            <span v-if="suggest">Suggest: {{suggest}}</span><br>

            <pre class="style-code-view">{{codeSubmitString}}</pre>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>