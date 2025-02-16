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
}