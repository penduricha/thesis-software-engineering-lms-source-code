import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();
export default class ExecuteJavaService {
    static post_Test_Debug_Form_JavaCore(mapCode, studentID, examID, questionJavaCoreExamID) {
        return axiosConfig
            .getAPIClient()
            .post(`/debug-code-testing-java-core/${studentID}/${examID}/${questionJavaCoreExamID}`, mapCode, {});
    }
}