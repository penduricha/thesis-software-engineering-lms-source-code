<script>
import './modal-view-information.scss';
import LectureLocalStorage from "@/pages/login/LectureLocalStorage.js";
import LectureDao from "@/daos/LectureDao.js";

export default {
  name: "ModalViewInformation",

  data() {
    return {
      lectureID: null,
      name: null,
      email: null,
      phoneNumber: null,
    }
  },

  methods: {
    async setModalViewInformation() {
      const lectureLocalStorage = new LectureLocalStorage();
      let lectureID = lectureLocalStorage.getLectureID_From_LocalStorage();
      if(lectureID) {
        let detailInfo = await LectureDao.getDetailInformationLecture_By_LectureID(lectureID);
        if(detailInfo) {
          this.lectureID = lectureID;
          this.name = detailInfo.name;
          this.email = detailInfo.email;
          this.phoneNumber = detailInfo.phoneNumber;
        }
      }
    }
  },

  computed: {

  }
}
</script>

<template>
  <div
      class="modal fade"
      id="modal-view-information"
      tabindex="-1"
      aria-hidden="true"
  >
    <div class="modal-dialog modal-md">
      <div class="modal-content content-info-exam">
        <div class="modal-header">
          <h5 class="modal-title">
            Information
          </h5>
          <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
          ></button>
        </div>
        <div class="modal-body modal-exam-body">
          <label><span>Lecture id: {{ lectureID }}</span></label>
          <label><span>Name: {{ name }}</span></label>
          <label><span>Email: {{ email }}</span></label>
          <label><span>Phone number: {{ phoneNumber }}</span></label>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">

</style>