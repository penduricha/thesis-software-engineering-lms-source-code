<script>
import './modal-view-information.scss';
import LecturerLocalStorage from "@/pages/login/LecturerLocalStorage.js";
import LecturerDao from "@/daos/LecturerDao.js";

export default {
  name: "ModalViewInformation",

  data() {
    return {
      lecturerID: null,
      name: null,
      email: null,
      phoneNumber: null,
    }
  },

  methods: {
    async setModalViewInformation() {
      const lecturerLocalStorage = new LecturerLocalStorage();
      let lecturerID = lecturerLocalStorage.getLecturerID_From_LocalStorage();
      if(lecturerID) {
        let detailInfo = await LecturerDao.getDetailInformationLecturer_By_LecturerID(lecturerID);
        if(detailInfo) {
          this.lecturerID = lecturerID;
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
          <label><span>Lecturer id: {{ lecturerID }}</span></label>
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