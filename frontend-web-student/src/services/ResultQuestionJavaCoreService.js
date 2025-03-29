import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class ResultQuestionJavaCoreService {
    static fetch_List_Questions_JavaCore_Exam_By_DetailMarkStudentID(detailMarkStudentID) {
        return axiosConfig.getAPIClient().get(`/result-java-core/get-list-questions-exam-and-mark/${detailMarkStudentID}`);
    }

    static fetch_List_TestCase_Answers_By_ResultQuestionJavaCoreExamID(resultQuestionJavaCoreID) {
        return axiosConfig.getAPIClient().get(`/result-java-core/get-list-test-case-answers/${resultQuestionJavaCoreID}`);
    }
}