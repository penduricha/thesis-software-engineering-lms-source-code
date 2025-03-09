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
}