import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();
export default class BankTestJavaOopService {
    static fetchBankTestJavaOop_By_ExamID(examID) {
        return axiosConfig
            .getAPIClient()
            .get(`/test-java-oop/get-bank-test-java-oop-by-exam-id/${examID}`);
    }
}