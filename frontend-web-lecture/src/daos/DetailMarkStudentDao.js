import ExamService from "@/services/ExamService.js";
import MarkStudentService from "@/services/MarkStudentService.js";
import DetailMarkStudentService from "@/services/DetailMarkStudentService.js";

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

    static async getDetailMarkStudentsByMarkStudent_MarkStudentID( markStudentID){
        let listDetailMarkStudents = [];
            await DetailMarkStudentService.
        fetchDetailMarkStudentsByMarkStudent_MarkStudentID(markStudentID).then(response => {
            listDetailMarkStudents = response.data;
        }).catch(error => {
            console.error(error);
            alert(error);
        })
        return listDetailMarkStudents;
    }

    static async getCodeSubmitJavaClass_By_DetailMarkStudentID(detailMarkStudentID) {
        let codeSubmitJavaClass = {} ;
        await DetailMarkStudentService.
        fetchCodeSubmitJavaClass_By_DetailMarkStudentID(detailMarkStudentID).then(response => {
            codeSubmitJavaClass = response.data;
        }).catch(error => {
            console.error(error);
            alert(error);
        })
        return codeSubmitJavaClass;
    }
}