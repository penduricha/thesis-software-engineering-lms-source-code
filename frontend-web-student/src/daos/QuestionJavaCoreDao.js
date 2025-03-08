import QuestionJavaCoreService from "@/services/QuestionJavaCoreService.js";

export default class QuestionJavaCoreDao {
    static async getQuestions_By_ExamID (examID) {
        let questions = [];
        await QuestionJavaCoreService
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
        await QuestionJavaCoreService
            .fetchTestCases_By_QuestionJavaCoreExamID(questionJavaCoreExamID)
            .then(response => {
                testCases = response.data;
            }).catch(error => {
                console.error(error);
            })
        return testCases;
    }
}