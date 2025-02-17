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

}