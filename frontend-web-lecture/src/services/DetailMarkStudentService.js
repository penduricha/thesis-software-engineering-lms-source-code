import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class DetailMarkStudentService {
    static post_Data_Submit_JavaCore( dataPost ) {
        return axiosConfig
            .getAPIClient()
            .post(`/mark_student/post-mark-student-after-submit-exam-java-core`, dataPost, {});
    }
}