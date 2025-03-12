import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();
export default class SessionTimeService {
    static post_SessionTime(examSessionTime) {
        return axiosConfig
            .getAPIClient()
            .post(`/session/create-session-time`, examSessionTime,{});
    }

    static fetch_SessionTime_By_StudentID(studentID) {
        return axiosConfig
            .getAPIClient()
            .get(`/session/time/${studentID}`);
    }
}