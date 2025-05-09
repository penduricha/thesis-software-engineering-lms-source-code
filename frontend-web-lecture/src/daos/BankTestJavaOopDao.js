import BankTestJavaOopService from "@/services/BankTestJavaOopService.js";


export default class BankTestJavaOopDao {
    static async get_List_Java_Test_Oop() {
        let listTest = [];
        await BankTestJavaOopService.fetch_List_Java_Test_Oop()
            .then(response => {
                listTest = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
            })
        return listTest;
    }

    static async create_Java_Test_Oop(testToPost) {
        let status = false;
        let responseReturn = null;
        await BankTestJavaOopService.post_Java_Test_Oop(testToPost)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
                return status;
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async get_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID){
        let bankTestJavaOop = {}
        await BankTestJavaOopService.fetch_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID)
            .then(response => {
                bankTestJavaOop = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
            })
        return bankTestJavaOop;
    }

    static async update_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID, testToPut){
        let status = false;
        let responseReturn = null;
        await BankTestJavaOopService.put_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID, testToPut)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
                return status;
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async delete_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID){
        let status = false;
        let responseReturn = null;
        await BankTestJavaOopService.delete_Java_Test_Oop_By_BankTestJavaOopID(bankTestJavaOopID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
                return status;
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async getBankTestJavaOopID_By_ExamID(examID){
        let responseReturn = null;
        await BankTestJavaOopService.fetchBankTestJavaOopID_By_ExamID(examID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        return responseReturn;
    }

    static async getBankTestJavaOopID_By_BankTestJavaOopID(bankTestJavaOopID) {
        let responseReturn = {};
        await BankTestJavaOopService.fetchBankTestJavaOopID_By_BankTestJavaOopID(bankTestJavaOopID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        return responseReturn;
    }

    static async updateBankTestJavaOopByExamID(examID, bankTestJavaOopID){
        let status = false;
        let responseReturn = null;
        await BankTestJavaOopService.patchBankTestJavaOop_By_ExamID(examID, bankTestJavaOopID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
                return status;
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async getStatus_Student_DoExam_By_BankTestJavaOopID(bankTestJavaOopID){
        let status = true;
        await BankTestJavaOopService.fetchStatus_Student_DoExam_By_BankTestJavaOopID(bankTestJavaOopID)
            .then(response => {
                status = response.data;
            }).catch(error => {
                console.error(error);
                return status;
            })
        return status;
    }
}