
import DetailMarkStudentService from "@/services/DetailMarkStudentService.js";

export default class DetailMarkStudentDao {
    static async getMarkExam_After_Submitted_JavaCore(dataPost) {
        let mark = 0;
        let dataResponseReturn = {};
        try {
            const response = await DetailMarkStudentService.post_Data_Submit_JavaCore(dataPost);
            dataResponseReturn = response.data;
            if (dataResponseReturn) {
                mark = dataResponseReturn.markExam;
            }
        } catch (error) {
            console.error(error);
            alert('Exception debug code: ' + error);
            mark = -1;
        }
        return mark;
    }
}