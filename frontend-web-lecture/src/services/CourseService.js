import AxiosConfig from "@/services/AxiosConfig.js";

const axiosConfig = new AxiosConfig();

export default class CourseService {


    static fetchCourses_By_LectureID(lectureID) {
        return axiosConfig.getAPIClient().get(`/courses/lectureID/${lectureID}`);
    }
}