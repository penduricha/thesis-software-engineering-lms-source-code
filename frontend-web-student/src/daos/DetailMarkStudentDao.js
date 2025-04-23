
import DetailMarkStudentService from "@/services/DetailMarkStudentService.js";


export default class DetailMarkStudentDao {
    static async getMarkExam_After_Submitted_JavaCore(dataPost) {
        let mark = 0;
        let dataResponseReturn = {};
        try {
            const response = await DetailMarkStudentService.post_Data_Submit_JavaCore(dataPost);
            dataResponseReturn = response.data;
            if (dataResponseReturn) {
                if (dataResponseReturn.markExam !== undefined) {
                    mark = dataResponseReturn.markExam;
                } else if (dataResponseReturn.detailMarkExam !== undefined) {
                    mark = dataResponseReturn.detailMarkExam;
                }
            }
        } catch (error) {
            console.error(error);
            alert('Exception debug code: ' + error);
            mark = -1;
        }
        return mark;
    }

    static async get_Detail_Mark_Student_By_MarkStudentID( markStudentID ) {
        let listDetailMarkStudent = [];
        await DetailMarkStudentService.fetch_Detail_Mark_Student_By_MarkStudentID(markStudentID).then(response => {
            listDetailMarkStudent = response.data;
        }).catch(error => {
            console.error(error);
        })
        return listDetailMarkStudent;
    }
}