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

    static async getQuestions_By_ExamID_After_Mark_Or_Remove_Flag(examID ,questionJavaCoreExamID) {
        let questions = [];
        let responseReturn = null;
        await QuestionJavaCoreExamService
            .fetch_Switch_Mark_Flag_QuestionJavaCoreExamID(questionJavaCoreExamID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        if(responseReturn) {
            questions = await this.getQuestions_By_ExamID (examID);
        }
        return questions;
    }
}