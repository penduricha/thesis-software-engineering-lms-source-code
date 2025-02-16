import LectureService from "@/services/LectureService.js";

export default class LectureDao {
    constructor() {
    }

    static async getLectureID_And_Password(lectureID) {
        let lecture = null;
        await LectureService.fetchLectureID_And_Password(lectureID).then(response => {
            lecture = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return lecture;
    }

    static async getLectureName_And_LectureID(lectureID) {
        let lecture = null;
        await LectureService.fetchLectureName_And_LectureID(lectureID).then(response => {
            lecture = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return lecture;
    }
}