import LecturerService from "@/services/LecturerService.js";

export default class LecturerDao {
    constructor() {
    }

    static async getLecturerID_And_Password(lecturerID) {
        let lecturer = null;
        await LecturerService.fetchLecturerID_And_Password(lecturerID).then(response => {
            lecturer = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return lecturer;
    }

    static async getLecturerName_And_LecturerID(lecturerID) {
        let lecturer = null;
        await LecturerService.fetchLecturerName_And_LecturerID(lecturerID).then(response => {
            lecturer = response.data;
        }).catch(error => {
            console.error(error);
            //alert(error)
        })
        return lecturer;
    }

    static async getLecturerPasswordBy_LecturerID(lecturerID) {
        let lecturerPasswordHashed = null;
        await LecturerService.fetchLecturerPassword_By_LecturerID(lecturerID).then(response => {
            lecturerPasswordHashed = response.data;
        }).catch(error => {
            console.error(error);
            // alert(error)
        });
        return lecturerPasswordHashed;
    }

    static async update_Password_By_LecturerID(lecturerID, newPassword) {
        let status = false;
        let newPasswordReturned = null;
        await LecturerService.patchUpdate_Password_By_LecturerID(lecturerID, newPassword).then(response => {
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
        await LecturerService.postCompiler_Code_Run_To_Output(dataPost).then(response => {
            status = response.data;
        }).catch(error => {
            console.error(error);
            // alert(error)
        });
        return status;
    }

    static async getDetailInformationLecturer_By_LecturerID (lecturerID) {
        let detailInfo = {};
        await LecturerService.fetchDetailInformationLecturer_By_LecturerID(lecturerID).then(response => {
            detailInfo = response.data;
        }).catch(error => {
            console.error(error);
            // alert(error)
        });
        return detailInfo;
    }
}