import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class CourseService {


    static fetchCourses_By_LecturerID(lecturerID) {
        return axiosConfig
            .getAPIClient()
            .get(`/courses/lecturerID/${lecturerID}`);
    }

    static fetchCourse_By_LecturerID_And_CourseID(lecturerID, courseID) {
        return axiosConfig
            .getAPIClient()
            .get(`/course/get_course_by_lecturerID_courseID/${lecturerID}/${courseID}`);
    }
}