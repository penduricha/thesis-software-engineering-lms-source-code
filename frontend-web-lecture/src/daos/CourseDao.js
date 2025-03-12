import CourseService from "@/services/CourseService.js";


export default class CourseDao {
    constructor() {
    }

    static async getCourses_By_LectureID(lectureID) {
        let courses = [];
        await CourseService.fetchCourses_By_LectureID(lectureID).then(response => {
            courses = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return courses;
    }

    static async getCourse_By_LectureID_CourseID(lectureID, courseID) {
        let course = {};
        await CourseService.fetchCourse_By_LectureID_And_CourseID(lectureID,courseID)
            .then(response => {
            course = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return course;
    }

}