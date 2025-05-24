import CourseService from "@/services/CourseService.js";


export default class CourseDao {
    constructor() {
    }

    static async getCourses_By_LecturerID(lecturerID) {
        let courses = [];
        await CourseService.fetchCourses_By_LecturerID(lecturerID).then(response => {
            courses = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return courses;
    }

    static async getCourse_By_LecturerID_CourseID(lecturerID, courseID) {
        let course = {};
        await CourseService.fetchCourse_By_LecturerID_And_CourseID(lecturerID,courseID)
            .then(response => {
            course = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return course;
    }

}