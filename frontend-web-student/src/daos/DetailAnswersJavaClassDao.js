import ExamService from "@/services/ExamService.js";
import DetailAnswersJavaClassService from "@/services/DetailAnswersJavaClassService.js";

export default class DetailAnswersJavaClassDao {
    static async getListDetailAnswersJavaClass_By_DetailMarkStudentID(detailMarkStudentID) {
        let listDetailAnswersJavaClass = [];
        await DetailAnswersJavaClassService
            .fetchListDetailAnswersJavaClass_By_DetailMarkStudentID(detailMarkStudentID)
            .then(response => {
                listDetailAnswersJavaClass = response.data;
            }).catch(error => {
                console.error(error);
            })
        return listDetailAnswersJavaClass;
    }
}