import BankTestJavaOopService from "@/services/BankTestJavaOopService.js";

export default class BankTestJavaOopDao {
    static async getBankTestJavaOop_By_ExamID(examID) {
        let responseReturn = {};
        await BankTestJavaOopService.fetchBankTestJavaOop_By_ExamID(examID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        return responseReturn;
    }
}