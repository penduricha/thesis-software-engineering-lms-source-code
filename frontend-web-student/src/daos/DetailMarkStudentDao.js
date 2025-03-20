
import DetailMarkStudentService from "@/services/DetailMarkStudentService.js";

export default class DetailMarkStudentDao {
    static async getMarkExam_After_Submitted_JavaCore( dataPost ) {
        let mark = 0;
        let dataResponseReturn = {};
        await DetailMarkStudentService
            .post_Data_Submit_JavaCore(dataPost).then(response => {
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