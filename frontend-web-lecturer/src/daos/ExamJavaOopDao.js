import ExamService from "@/services/ExamService.js";
import ExamJavaOopService from "@/services/ExamJavaOopService.js";

export default class ExamJavaOopDao {
    static async get_Exist_Exam_By_BankTestJavaOopID(bankTestJavaOopID){
        let exist = false;
        await ExamJavaOopService.fetch_Exist_Exam_By_BankTestJavaOopID(bankTestJavaOopID).then(response => {
            exist = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exist;
    }
}