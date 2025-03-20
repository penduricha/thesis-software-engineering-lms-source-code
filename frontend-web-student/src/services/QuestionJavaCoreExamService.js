import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class QuestionJavaCoreExamService {
    static fetchQuestions_By_ExamID (examID) {
        return axiosConfig.getAPIClient().get(`/get_questions_java_core_exam_by_exam_id/${examID}`);
    }

    static fetchTestCases_By_QuestionJavaCoreExamID (questionJavaCoreExamID) {
        return axiosConfig.getAPIClient()
            .get(`/bank_test_cases/get_test_case_by_question_java_core_exam_id/${questionJavaCoreExamID}`);
    }

    static fetch_Switch_Mark_Flag_QuestionJavaCoreExamID ( questionJavaCoreExamID ) {
        return axiosConfig.getAPIClient()
            .get(`/switch-marked-flag-by-question-java-core-exam-id/${questionJavaCoreExamID}`);
    }

    static fetch_Switch_Mark_Flag_False_By_ExamID (examID) {
        return axiosConfig.getAPIClient()
            .get(`/switch-marked-flag-false-by-exam-id/${examID}`);
    }
}