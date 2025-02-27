import QuestionService from "@/services/QuestionService.js";

export default class QuestionDao {
    static async getQuestions_By_ExamID (examID) {
        let questions = [];
        await QuestionService
            .fetchQuestions_By_ExamID(examID)
            .then(response => {
                questions = response.data;
            }).catch(error => {
                console.error(error);
            })
        return questions;
    }

    static async getTestCases_By_QuestionJavaCoreExamID (questionJavaCoreExamID) {
        let testCases = [];
        await QuestionService
            .fetchTestCases_By_QuestionJavaCoreExamID(questionJavaCoreExamID)
            .then(response => {
                testCases = response.data;
            }).catch(error => {
                console.error(error);
            })
        return testCases;
    }
}