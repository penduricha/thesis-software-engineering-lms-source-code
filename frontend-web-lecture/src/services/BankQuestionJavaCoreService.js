import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class BankQuestionJavaCoreService {
    static fetch_Bank_Questions_JavaCore() {
        return axiosConfig
            .getAPIClient()
            .get(`/bank-question-java-core/get-questions-java-core`);
    }

    static post_Question_JavaCore( dataQuestionPost ) {
        return axiosConfig
            .getAPIClient()
            .post(`/bank-question-java-core/create-question-and-test-cases`,
                dataQuestionPost, {});
    }

    static put_Question_JavaCore( dataQuestionPut ) {
        return axiosConfig
            .getAPIClient()
            .put(`/bank-question-java-core/update-question-and-test-cases`,
                dataQuestionPut, {});
    }

    static delete_Question_JavaCore( questionJavaCoreID ) {
        return axiosConfig
            .getAPIClient()
            .delete(
                `/bank-question-java-core/delete-create-question-and-test-cases-by-question-java-core-id/${questionJavaCoreID}`);
    }

    static fetch_Test_Cases_By_Question_JavaCore_ID( questionJavaCoreID ){
        return axiosConfig
            .getAPIClient()
            .get(`/bank-question-java-core/get-test-cases-by-question-java-core-id/${questionJavaCoreID}`);
    }

    static fetch_Total_Questions_JavaCore() {
        return axiosConfig
            .getAPIClient()
            .get(`/bank-question-java-core/get-total-bank-question-java-core`);
    }
}