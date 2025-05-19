import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class MarkStudentService {
    static fetch_Mark_Student_Zero_If_Exam_Overdue(studentID, examID) {
        return axiosConfig.getAPIClient().get(
            `/mark_student/set_mark_zero/${studentID}/${examID}`);
    }

    static delete_Mark_Student_By_Mark_Student_ID(examID) {
        return axiosConfig.getAPIClient().delete(`/delete/mark-student-by-examID-id/${examID}`);
    }

    static fetch_List_Mark_Student_By_Student_ID(studentID) {
        return axiosConfig.getAPIClient().get(`/mark_student/get-list-mark-student-by-student-id/${studentID}`)
    }

    static put_MarkStudent_Zero_If_No_Submit(dataPut){
        return axiosConfig.getAPIClient().put(
            `/mark_student/put-mark-student-zero-java-class-if-no-submit`,
            dataPut,{});
    }

    static post_Data_Result_Java_Class_From_FormSubmission(dataPost) {
        return axiosConfig.getAPIClient().post(`/mark_student/post-mark-student-after-submit-exam-java-class`, dataPost, {});
    }
}