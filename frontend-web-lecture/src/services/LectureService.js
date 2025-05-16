import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class LectureService {
    static fetchLectureID_And_Password(lectureID) {
        return axiosConfig.getAPIClient().get(`/lecture/lectureID_password/${lectureID}`);
    }

    static fetchLectureName_And_LectureID(lectureID) {
        return axiosConfig.getAPIClient().get(`/lecture/lectureID_name/${lectureID}`);
    }

    static patchUpdate_Password_By_LectureID(lectureID, newPassword) {
        return axiosConfig.getAPIClient().patch(
            `/lecture/updatePasswordLecture/${lectureID}/${newPassword}`,
            {});
    }

    static fetchLecturePassword_By_LectureID(lectureID) {
        return axiosConfig.getAPIClient().get(`/lecture/getPassword_By_LectureID/${lectureID}`);
    }

    static postCompiler_Code_Run_To_Output ( dataPost ) {
        return axiosConfig.getAPIClient().post(`/lecture/compiler_main_code_in_create_update_question`, dataPost, {});
    }

    static fetchDetailInformationLecture_By_LectureID(lectureID) {
        return axiosConfig.getAPIClient().get(`/lecture/find-detail-information-lecture/${lectureID}`);
    }
}