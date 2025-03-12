import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class StudentService {


    static fetchStudentID_And_Password(studentID) {
        return axiosConfig.getAPIClient().get(`/student/studentID_password/${studentID}`);
    }

    static fetchStudentName_And_StudentID(studentID) {
        return axiosConfig.getAPIClient()
            .get(`/student/studentID_name/${studentID}`);
    }

    static patchUpdate_Password_By_StudentID(studentID, newPassword) {
        return axiosConfig.getAPIClient()
            .patch(`/student/updatePasswordStudent/${studentID}/${newPassword}`,{});
    }

    static fetchStudentPassword_By_StudentID(studentID) {
        return axiosConfig.getAPIClient().get(`/student/getPassword_By_StudentID/${studentID}`);
    }

    static fetchCourseID_By_StudentID(studentID) {
        return axiosConfig.getAPIClient().get(`/student/get_course_id_by_student_id/${studentID}`);
    }

    static fetch_Set_Date_Time_Start_Exam(studentID) {
        return axiosConfig.getAPIClient()
            .get(`/student/set_date_time_start_exam/${studentID}`);
    }

    static fetch_Reset_Date_Time_Start_Exam(studentID) {
        return axiosConfig.getAPIClient()
            .get(`/student/reset_date_time_start_exam/${studentID}`);
    }

    static fetch_Information_Student_Access_Exam_And_RemainMinutes (studentID) {
        return axiosConfig.getAPIClient()
            .get(`/student/get_information_exam_and_remain_minutes/${studentID}`);
    }
}