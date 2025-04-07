<script>
import StudentLocalStorage from "@/pages/login/StudentLocalStorage.js";

export default {
  name: "ModalViewDetail_Information",

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
    }
  },

  methods: {
    async formatDateOFBirth(dateOfBirth) {

    },

    async setInformationStudent_By_StudentID() {
      const studentLocalStorage = new StudentLocalStorage();
      //console.log(student);
      this.studentID = studentLocalStorage.getStudentID_From_LocalStorage();
    }
  }
}
</script>

<template>

</template>

<style scoped lang="scss">
@use "@/scss/main";
</style>