import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class LecturerService {
    static fetchLecturerID_And_Password(lecturerID) {
        return axiosConfig.getAPIClient().get(`/lecturer/lecturerID_password/${lecturerID}`);
    }

    static fetchLecturerName_And_LecturerID(lecturerID) {
        return axiosConfig.getAPIClient().get(`/lecturer/lecturerID_name/${lecturerID}`);
    }

    static patchUpdate_Password_By_LecturerID(lecturerID, newPassword) {
        return axiosConfig.getAPIClient().patch(
            `/lecturer/updatePasswordLecturer/${lecturerID}/${newPassword}`,
            {});
    }

    static fetchLecturerPassword_By_LecturerID(lecturerID) {
        return axiosConfig.getAPIClient().get(`/lecturer/getPassword_By_LecturerID/${lecturerID}`);
    }

    static postCompiler_Code_Run_To_Output ( dataPost ) {
        return axiosConfig.getAPIClient().post(`/lecturer/compiler_main_code_in_create_update_question`, dataPost, {});
    }

    static fetchDetailInformationLecturer_By_LecturerID(lecturerID) {
        return axiosConfig.getAPIClient().get(`/lecturer/find-detail-information-lecturer/${lecturerID}`);
    }
}