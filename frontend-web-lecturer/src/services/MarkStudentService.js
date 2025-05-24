import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class MarkStudentService {
    static fetch_List_Mark_Student_By_ExamID(examID) {
        return axiosConfig.getAPIClient().get(`/mark_student/get-list-mark-student-by-exam-id/${examID}`);
    }

    static fetch_Average_MarkExam_By_ExamID(examID) {
        return axiosConfig.getAPIClient().get(`/mark_student/get-average-mark-exam-by-exam-id/${examID}`);
    }

    static fetch_GroupBy_MarkStudent_MarkExam_By_ExamID(examID) {
        return axiosConfig.getAPIClient().get(`/mark_student/get-group-by-mark-exam-by-exam-id/${examID}`);
    }
}