import QuestionJavaCoreExamService from "@/services/QuestionJavaCoreExamService.js";

export default class QuestionJavaCoreExamDao {
    static async getQuestions_By_ExamID (examID) {
        let questions = [];
        await QuestionJavaCoreExamService
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
        await QuestionJavaCoreExamService
            .fetchTestCases_By_QuestionJavaCoreExamID(questionJavaCoreExamID)
            .then(response => {
                testCases = response.data;
            }).catch(error => {
                console.error(error);
            })
        return testCases;
    }
}