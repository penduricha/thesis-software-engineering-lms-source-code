import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class MarkStudentService {
    static fetch_List_Mark_Student_By_ExamID(examID) {
        return axiosConfig.getAPIClient().get(`/mark_student/get-list-mark-student-by-exam-id/${examID}`);
    }
}