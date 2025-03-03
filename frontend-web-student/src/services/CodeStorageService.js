import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();
export default class CodeStorageService {
    static save_Code_By_StudentId_IndexQuestion (codeSaveStorage, studentID) {
        return axiosConfig
            .getAPIClient()
            .post(`/student/save_code_by_studentID/${studentID}`, codeSaveStorage,{});
    }

    static fetch_Code_By_IndexQuestion_StudentID(studentID, indexQuestion) {
        return axiosConfig
            .getAPIClient()
            .get(`/student/get_code_by_studentID_indexQuestion/${studentID}/${indexQuestion}`);
    }

    static delete_Code_Storage_By_StudentID ( studentID ){
        return axiosConfig
            .getAPIClient()
            .delete(`/student/delete-code-storage_by-student-id/${studentID}`);
    }
}