<script>
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";
import StudentDao from "@/daos/StudentDao.js";

export default {
  name: "ModalViewDetailAccount",

  data() {
    return {
      // {
      //   "studentID": "22662361",
      //     "firstName": "Biên",
      //     "courseName": "Object-oriented programming",
      //     "gender": true,
      //     "dateOfBirth": 1087923600000,
      //     "className": "DHKTPM20BTT",
      //     "lastname": "Trần Thanh"
      // }
      studentID: null,
      lastName: null,
      firstName: null,
      dateOfBirth: null,
      className: null,
      courseName: null,
      gender: null,
    }
  },

  methods: {
    formatDateOFBirth(dateOfBirth) {
      if(dateOfBirth) {
        let dateOfBirthDate = new Date(dateOfBirth);
        let day = dateOfBirthDate.getDate();
        let month = dateOfBirthDate.getMonth() + 1;
        let year = dateOfBirthDate.getFullYear();
        return `${day}/${month}/${year}`;
      }
      return null;
    },

    formatName(firstName, lastName) {
      if(firstName && lastName) {
        return `${lastName} ${firstName}`;
      }
      return null;
    },

    getGender(gender) {
      if(gender) {
        return gender ? "Male" : "Female";
      }
    },

    async setInformationStudent_By_StudentID() {
      const studentLocalStorage = new StudentLocalStorage();
      //console.log(student);
      this.studentID = studentLocalStorage.getStudentID_From_LocalStorage();
      if(this.studentID) {
        let detailInformation = await StudentDao.get_Detail_Information_Student_By_StudentID(this.studentID);
        console.log("Detail Information", detailInformation);
        if(detailInformation) {
          this.lastName = detailInformation.lastName;
          this.firstName =  detailInformation.firstName;
          this.dateOfBirth = detailInformation.dateOfBirth;
          this.className = detailInformation.className
          this.courseName = detailInformation.courseName;
          this.gender = detailInformation.gender;
        }
      }
    }
  }
}
</script>

<template>
  <div
      class="modal fade"
      id="modal-view-detail-account"
      tabindex="-1"
      aria-hidden="true"
  >
    <div class="modal-dialog modal-md">
      <div class="modal-content content-info-exam">
        <div class="modal-header">
          <h5 class="modal-title">
            Detail Information
          </h5>
          <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
          ></button>
        </div>
        <div class="modal-body modal-exam-body">
          <label><span>Student id: {{ studentID }}</span></label>
          <label><span>Name: {{ formatName(firstName, lastName) }}</span></label>
          <label><span>Gender: {{ getGender(gender) }}</span></label>
          <label><span>Date of birth: {{ formatDateOFBirth(dateOfBirth) }}</span></label>
          <label><span>Course name: {{ courseName }}</span></label>
          <label><span>Class name: {{ className }}</span></label>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
@use "@/scss/main";
</style>