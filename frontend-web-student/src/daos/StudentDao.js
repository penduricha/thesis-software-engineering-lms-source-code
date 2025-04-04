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

    static async getStudentPasswordBy_StudentID(studentID) {
        let studentPasswordHashed = null;
        await StudentService.fetchStudentPassword_By_StudentID(studentID).then(response => {
            studentPasswordHashed = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return studentPasswordHashed;
    }

    static async update_Password_By_StudentID(studentID, newPassword) {
        let status = false;
        let newPasswordReturned = null;
        await StudentService.patchUpdate_Password_By_StudentID(studentID, newPassword).then(response => {
            newPasswordReturned = response.data;
            console.log('patched password: ', newPasswordReturned)
            status = !!newPasswordReturned;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        console.log('patched password: ', newPasswordReturned);
        return status;
    }

    static async getCourseID_By_StudentID(studentID) {
        let courseID = null
        await StudentService.fetchCourseID_By_StudentID(studentID).then(response => {
            courseID = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return courseID;
    }

    static async set_Date_Time_Start_Exam(studentID) {
        let status = false;
        let responseReturn = null
        await StudentService.fetch_Set_Date_Time_Start_Exam(studentID).then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async reset_Date_Time_Start_Exam(studentID) {
        let status = false;
        let responseReturn = null
        await StudentService.fetch_Reset_Date_Time_Start_Exam(studentID).then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async get_Information_Student_Access_Exam_And_RemainMinutes (studentID) {
        let responseReturn = null;
        await StudentService.fetch_Information_Student_Access_Exam_And_RemainMinutes(studentID)
        .then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return responseReturn;
    }

    static async get_Detail_Information_Student_By_StudentID(studentID){
        let detailInformation = {}
        await StudentService.fetch_Detail_Information_Student_By_StudentID(studentID)
            .then(response => {
                detailInformation = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
            })
        return detailInformation;
    }
}