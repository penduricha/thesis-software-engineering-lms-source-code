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

    static async startPolling_GetQuestions_By_ExamID(examID, callback) {
        let lastFetchedQuestions = await this.getQuestions_By_ExamID(examID);
        // Check every 5 seconds
        return setInterval(async () => {
            const currentQuestions = await this.getQuestions_By_ExamID(examID);
            // Compare the current questions with the last fetched questions
            if (JSON.stringify(currentQuestions) !== JSON.stringify(lastFetchedQuestions)) {
                lastFetchedQuestions = currentQuestions; // Update last fetched questions
                callback(currentQuestions); // Call the callback to update data
            }
        }, 5000);
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

    static async setMarkedFlag_False_By_ExamID (examID) {
        let status = false;
        let responseReturn = null;
        await QuestionJavaCoreExamService
            .fetch_Switch_Mark_Flag_False_By_ExamID(examID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }
}