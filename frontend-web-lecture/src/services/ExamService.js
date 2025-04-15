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

    static fetchStatus_Access_Student_To_Exam_By_ExamID(examID) {
        return axiosConfig
            .getAPIClient()
            .get(`/student/find_student_access_exam/${examID}`);
    }

    static fetch_Title_Exam_By_TitleExam_CourseID(courseID, titleExam) {
        return axiosConfig
            .getAPIClient()
            .get(
                `/find-title-exam-by-course-id-title-exam/${courseID}/${titleExam}`);
    }

    static post_Exam_Java_Core_With_Choose_Question(dataPost ,courseID) {
        return axiosConfig
            .getAPIClient()
            .post (`/post_exam_java_core_with_choose_questions/${courseID}`, dataPost, {});
    }

    static post_Exam_Java_Core_With_Random_Question(dataPost, courseID, numberOfQuestionsToRandom) {
        return axiosConfig
            .getAPIClient()
            .post (`/post_exam_java_core_random_question/${courseID}/${numberOfQuestionsToRandom}`,
                dataPost, {});
    }

    static fetchListTitleExam() {
        return axiosConfig
            .getAPIClient()
            .get(`/exam/get-list-title-exam`);
    }

    static post_Exam_Java_Class_With_ChooseTest(dataPost, courseID) {
        return axiosConfig
            .getAPIClient()
            .post(`/post_exam_java_class_with_choose_test/${courseID}`, dataPost,{});
    }
}