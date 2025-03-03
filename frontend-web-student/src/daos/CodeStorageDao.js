import CodeStorageService from "@/services/CodeStorageService.js";

export default class CodeStorageDao {
    static async save_Code_By_StudentId_IndexQuestion (codeSaveStorage, studentID) {
        let status = false;
        let responseReturn = null;
        await CodeStorageService.save_Code_By_StudentId_IndexQuestion(codeSaveStorage, studentID).then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
        })
        if (responseReturn) {
            status = true;
        }
        return status;
    }

    static async get_Code_By_IndexQuestion_StudentID(studentID, indexQuestion) {
        let codeGet = null;
        await CodeStorageService.fetch_Code_By_IndexQuestion_StudentID(studentID, indexQuestion).then(response => {
            codeGet = response.data;
        }).catch(error => {
            console.error(error);
        })
        return codeGet;
    }

    static async delete_Code_Storage_By_StudentID ( studentID ) {
        let responseReturn = null;
        await CodeStorageService.delete_Code_Storage_By_StudentID(studentID).then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
        })
        return Number(responseReturn) === Number(studentID);
    }
}