
import MarkStudentService from "@/services/MarkStudentService.js";

export default class MarkStudentDao{
    static async set_Mark_Student_Zero_If_Exam_Overdue(studentID, examID) {
        let responseReturn = null;
        await MarkStudentService
            .fetch_Mark_Student_Zero_If_Exam_Overdue(studentID, examID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        return responseReturn;
    }

}