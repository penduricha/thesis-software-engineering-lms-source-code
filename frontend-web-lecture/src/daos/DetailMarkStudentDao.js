import ExamService from "@/services/ExamService.js";

export default class DetailMarkStudentDao {
    static async getMarkExam_After_Submitted_JavaCore( dataPost ) {
        let mark = 0;
        let dataResponseReturn = {};
        await ExamService
            .post_Exam_Java_Core_With_Choose_Question(dataPost).then(response => {
                dataResponseReturn = response.data;
            }).catch(error => {
                console.error(error);
                alert(error);
            })
        if(dataResponseReturn) {
            mark = dataResponseReturn.markExam;
        }
        return mark;
    }
}