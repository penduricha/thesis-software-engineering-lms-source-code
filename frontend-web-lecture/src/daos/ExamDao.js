import ExamService from "@/services/ExamService.js";


export default class ExamDao {
    static async getExams_By_CourseID(courseID) {
        let exams = [];
        await ExamService.fetchExams_By_CourseID(courseID).then(response => {
            exams = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exams;
    }

    static async getExam_By_CourseID_ExamID(examID, courseID) {
        let exam = null;
        await ExamService.fetchExam_By_CourseID_ExamID(examID, courseID).then(response => {
            exam = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exam;
    }

    static async create_Exam_By_CourseID(courseID, exam) {
        let status = false;
        let examResponse = null
        await ExamService.post_Exam_By_CourseID(courseID, exam).then(response => {
            examResponse = response.data;
        }).catch(error => {
            console.error(error);
        })

        if(examResponse) {
            status = true;
        }
        return status;
    }
}