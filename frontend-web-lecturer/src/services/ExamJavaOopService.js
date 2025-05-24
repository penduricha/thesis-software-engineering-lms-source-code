import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class ExamService {
    static fetch_Exist_Exam_By_BankTestJavaOopID(bankTestJavaOopID) {
        return axiosConfig.getAPIClient().get(`/check-exam-by-bankTestJavaOopID/${bankTestJavaOopID}`);
    }
}