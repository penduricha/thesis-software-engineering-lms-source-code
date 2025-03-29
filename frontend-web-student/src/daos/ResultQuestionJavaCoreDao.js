import QuestionJavaCoreExamService from "@/services/QuestionJavaCoreExamService.js";
import ResultQuestionJavaCoreService from "@/services/ResultQuestionJavaCoreService.js";

export default class ResultQuestionJavaCoreDao {
    static async get_List_Questions_JavaCore_Exam_By_DetailMarkStudentID(detailMarkStudentID) {
        let questions = [];
        await ResultQuestionJavaCoreService
            .fetch_List_Questions_JavaCore_Exam_By_DetailMarkStudentID(detailMarkStudentID)
            .then(response => {
                questions = response.data;
            }).catch(error => {
                console.error(error);
            })
        return questions;
    }

    static async get_List_TestCase_Answers_By_ResultQuestionJavaCoreExamID(resultQuestionJavaCoreID) {
        let listTestCases = [];
        await ResultQuestionJavaCoreService
            .fetch_List_TestCase_Answers_By_ResultQuestionJavaCoreExamID(resultQuestionJavaCoreID)
            .then(response => {
                listTestCases = response.data;
            }).catch(error => {
                console.error(error);
            })
        return listTestCases;
    }
}