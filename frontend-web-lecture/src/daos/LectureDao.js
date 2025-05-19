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

    static async getLecturePasswordBy_LectureID(lectureID) {
        let lecturePasswordHashed = null;
        await LectureService.fetchLecturePassword_By_LectureID(lectureID).then(response => {
            lecturePasswordHashed = response.data;
        }).catch(error => {
            console.error(error);
            // alert(error)
        });
        return lecturePasswordHashed;
    }

    static async update_Password_By_LectureID(lectureID, newPassword) {
        let status = false;
        let newPasswordReturned = null;
        await LectureService.patchUpdate_Password_By_LectureID(lectureID, newPassword).then(response => {
            newPasswordReturned = response.data;
            console.log('patched password: ', newPasswordReturned);
            status = !!newPasswordReturned;
        }).catch(error => {
            console.error(error);
            // alert(error)
        });
        console.log('patched password: ', newPasswordReturned);
        return status;
    }

    static async test_Compiler_Code_Run_To_Output( dataPost ) {
        let status = false;
        await LectureService.postCompiler_Code_Run_To_Output(dataPost).then(response => {
            status = response.data;
        }).catch(error => {
            console.error(error);
            // alert(error)
        });
        return status;
    }

    static async getDetailInformationLecture_By_LectureID (lectureID) {
        let detailInfo = {};
        await LectureService.fetchDetailInformationLecture_By_LectureID(lectureID).then(response => {
            detailInfo = response.data;
        }).catch(error => {
            console.error(error);
            // alert(error)
        });
        return detailInfo;
    }
}