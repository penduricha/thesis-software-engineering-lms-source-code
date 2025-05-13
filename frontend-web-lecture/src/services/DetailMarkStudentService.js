import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class DetailMarkStudentService {
    static post_Data_Submit_JavaCore( dataPost ) {
        return axiosConfig
            .getAPIClient()
            .post(`/mark_student/post-mark-student-after-submit-exam-java-core`, dataPost, {});
    }

    static fetchDetailMarkStudentsByMarkStudent_MarkStudentID(markStudentID) {
        return axiosConfig
            .getAPIClient()
            .get(`/detail-mark-student/get-list-detail-mark-student/${markStudentID}`);
    }

    static fetchCodeSubmitJavaClass_By_DetailMarkStudentID(detailMarkStudentID) {
        return axiosConfig
            .getAPIClient()
            .get(`/code-submit-java-class/get-code-submit-java-class-by-detail-mark-student-id/${detailMarkStudentID}`);
    }
}