import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();
export default class DetailAnswersJavaClassService {
    static fetchListDetailAnswersJavaClass_By_DetailMarkStudentID(detailMarkStudentID) {
        return axiosConfig
            .getAPIClient()
            .get(`/detail-answers-java-class/get-list-detail-answers-java-class-by-detail-mark-student-id/${detailMarkStudentID}`);
    }
}