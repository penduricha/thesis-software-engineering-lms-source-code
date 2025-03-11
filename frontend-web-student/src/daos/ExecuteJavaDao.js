
import ExecuteJavaService from "@/services/ExecuteJavaService.js";

export default class ExecuteJavaDao {
    static async get_Output_Form_JavaCore(mapCode, studentID, examID, questionJavaCoreExamID){
        let output = null;
        await ExecuteJavaService
            .post_Test_Debug_Form_JavaCore(mapCode, studentID, examID, questionJavaCoreExamID)
            .then(response => {
                output = response.data;
            }).catch(error => {
                console.error(error);
            })
        return output;
    }
}