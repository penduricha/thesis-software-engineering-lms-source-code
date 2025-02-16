import StudentService from "@/services/StudentService.js";

export default class StudentDao {
    constructor() {
    }

    static async getStudentID_And_Password(studentID) {
        let student = null;
        await StudentService.fetchStudentID_And_Password(studentID).then(response => {
            student = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return student;
    }

    static async getStudentName_And_StudentID(studentID) {
        let student = null;
        await StudentService.fetchStudentName_And_StudentID(studentID).then(response => {
            student = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return student;
    }
}