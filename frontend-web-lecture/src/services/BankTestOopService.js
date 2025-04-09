import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();
export default class BankTestOopService {
    static fetch_List_Java_Test_Oop() {
        return axiosConfig
            .getAPIClient()
            .get(`/test-java-oop/get-list-test-java-oop`);
    }

    static post_Java_Test_Oop(testToPost) {
        return axiosConfig
            .getAPIClient()
            .post(`/test-java-oop/post-test-java-oop`, testToPost, {})
    }
}