import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class MarkStudentService {
    static fetch_Mark_Student_Zero_If_Exam_Overdue(studentID, examID) {
        return axiosConfig.getAPIClient().get(
            `/mark_student/set_mark_zero/${studentID}/${examID}`);
    }
}