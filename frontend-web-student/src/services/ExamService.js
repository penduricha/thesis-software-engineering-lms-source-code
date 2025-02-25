import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class ExamService {
    static fetchExams_By_CourseID (courseID) {
        return axiosConfig.getAPIClient().get(`/get_exam_by_course_id/${courseID}`);
    }

    static fetchExam_By_CourseID_ExamID(examID, courseID) {
        return axiosConfig.getAPIClient()
            .get(`/view_exam_by_course_id_exam_id/${examID}/${courseID}`);
    }

    static fetchExams_By_StudentID_Student_Calendar(studentID, yearStartDate, monthStartDate, dateStartDate) {
        return axiosConfig
            .getAPIClient()
            .get(`/view_exam_by_calendar_student/${studentID}/${yearStartDate}/${monthStartDate}/${dateStartDate}`);
    }

    static fetchExam_Information_Before_Exam(examID, courseID) {
        return axiosConfig
            .getAPIClient()
            .get(`/view_exam_by_course_id_exam_id_student_exam_before/${examID}/${courseID}`)
    }
}