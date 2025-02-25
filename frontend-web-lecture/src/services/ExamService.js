import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class ExamService {
    static fetchExams_By_CourseID(courseID) {
        return axiosConfig.getAPIClient().get(`/get_exam_by_course_id/${courseID}`);
    }

    static post_Exam_By_CourseID(courseID, exam) {
        return axiosConfig.getAPIClient().post(`/post_exam/${courseID}`, exam, {});
    }

    static fetchExam_By_CourseID_ExamID(examID, courseID) {
        return axiosConfig.getAPIClient().get(`/view_exam_by_course_id_exam_id/${examID}/${courseID}`)
    }

    static put_Exam_By_ExamID(exam, examID) {
        return axiosConfig.getAPIClient().put(`/put_exam/${examID}`, exam, {});
    }

    static delete_Exam_By_ExamID(examID) {
        return axiosConfig.getAPIClient().delete(`/delete_exam/${examID}`);
    }

    static fetchExams_By_LectureID_Lecture_Calendar(lectureID, yearStartDate, monthStartDate, dateStartDate) {
        return axiosConfig
            .getAPIClient()
            .get(`/view_exam_by_calendar_lecture/${lectureID}/${yearStartDate}/${monthStartDate}/${dateStartDate}`);
    }
}