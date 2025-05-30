import BankQuestionJavaCoreService from "@/services/BankQuestionJavaCoreService.js";


export default class BankQuestionJavaCoreDao {
    static async get_Bank_Questions_JavaCore() {
        let bankQuestionsJavaCore = [];
        await BankQuestionJavaCoreService.fetch_Bank_Questions_JavaCore()
            .then(response => {
            bankQuestionsJavaCore = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return bankQuestionsJavaCore;
    }

    static async get_Test_Cases_By_Question_JavaCore_ID( questionJavaCoreID ) {
        let testCases = [];
        await BankQuestionJavaCoreService.fetch_Test_Cases_By_Question_JavaCore_ID(questionJavaCoreID)
            .then(response => {
                testCases = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
            })
        return testCases;
    }

    static async create_Question_JavaCore( dataQuestionPost ) {
        let status = false;
        let responseReturn = null;
        await BankQuestionJavaCoreService.post_Question_JavaCore( dataQuestionPost )
            .then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async update_Question_JavaCore( dataQuestionPut ) {
        let status = false;
        let responseReturn = null;
        await BankQuestionJavaCoreService.put_Question_JavaCore( dataQuestionPut )
            .then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async delete_Question_JavaCore( questionJavaCoreID ){
        let responseReturn = null;
        await BankQuestionJavaCoreService.delete_Question_JavaCore(questionJavaCoreID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
            })
        return Number(questionJavaCoreID) === Number(responseReturn);
    }

    static async get_Total_Questions_JavaCore(){
        let numberOfQuestions = 0;
        await BankQuestionJavaCoreService.fetch_Total_Questions_JavaCore()
            .then(response => {
                numberOfQuestions = Number(response.data);
            }).catch(error => {
                console.error(error);
                //alert(error)
            })
        return numberOfQuestions;
    }
}