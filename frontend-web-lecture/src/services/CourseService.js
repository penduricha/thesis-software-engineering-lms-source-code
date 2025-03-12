import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class CourseService {


    static fetchCourses_By_LectureID(lectureID) {
        return axiosConfig
            .getAPIClient()
            .get(`/courses/lectureID/${lectureID}`);
    }

    static fetchCourse_By_LectureID_And_CourseID(lectureID, courseID) {
        return axiosConfig
            .getAPIClient()
            .get(`/course/get_course_by_lectureID_courseID/${lectureID}/${courseID}`);
    }
}