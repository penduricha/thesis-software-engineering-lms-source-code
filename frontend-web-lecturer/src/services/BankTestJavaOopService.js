import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();
export default class BankTestJavaOopService {
    static fetch_List_Java_Test_Oop() {
        return axiosConfig
            .getAPIClient()
            .get(`/test-java-oop/get-list-test-java-oop`);
    }

    static post_Java_Test_Oop(testToPost) {
        return axiosConfig
            .getAPIClient()
            .post(`/test-java-oop/post-test-java-oop`, testToPost, {})
    }

    static fetch_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID) {
        return axiosConfig
            .getAPIClient()
            .get(`/test-java-oop/get-test-java-oop-by-id/${bankTestJavaOopID}`);
    }

    static put_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID, testToPut) {
        return axiosConfig
            .getAPIClient()
            .put(`/test-java-oop/put-test-java-oop/${bankTestJavaOopID}`, testToPut, {});
    }

    //cho nay sua be
    static delete_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID) {
        return axiosConfig
            .getAPIClient()
            .delete(`/test-java-oop/delete-test-java-oop-by-id/${bankTestJavaOopID}`);
    }

    static fetchBankTestJavaOopID_By_ExamID(examID) {
        return axiosConfig
            .getAPIClient()
            .get(`/test-java-oop/get-bank-test-java-oop-id-by-exam-id/${examID}`);
    }

    static fetchBankTestJavaOopID_By_BankTestJavaOopID(bankTestJavaOopID) {
        return axiosConfig
            .getAPIClient()
            .get(`/test-java-oop/get-test-java-oop-by-id/${bankTestJavaOopID}`);
    }

    static patchBankTestJavaOop_By_ExamID(examID,newBankTestJavaOopID) {
        return axiosConfig
            .getAPIClient()
            .patch(`/test-java-oop/update-bank-test-java-oop-id-by-exam-id/${examID}/${newBankTestJavaOopID}`);
    }

    static fetchStatus_Student_DoExam_By_BankTestJavaOopID(bankTestJavaOopID) {
        return axiosConfig
            .getAPIClient()
            .get(`/test-java-oop/get-status-student-do-exam-by-id/${bankTestJavaOopID}`);
    }
}