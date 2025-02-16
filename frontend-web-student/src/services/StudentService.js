import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class StudentService {


    static fetchStudentID_And_Password(studentID) {
        return axiosConfig.getAPIClient().get(`/student/studentID_password/${studentID}`);
    }

    static fetchStudentName_And_StudentID(studentID) {
        return axiosConfig.getAPIClient().get(`/student/studentID_name/${studentID}`);
    }
    
}