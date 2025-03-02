
import SessionTimeService from "@/services/SessionTimeService.js";

export default class SessionTimeDao {
    static async create_SessionTime(examSessionTime){
        let status = false;
        let responseReturn = null;
        await SessionTimeService.post_SessionTime(examSessionTime).then(response => {
            responseReturn = response.data;
            console.log(responseReturn);
        }).catch(error => {
            console.error(error);
        })

        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async get_SessionTime_By_StudentID(studentID){
        let time = null
        await SessionTimeService.fetch_SessionTime_By_StudentID(studentID).then(response => {
            time = response.data;
        }).catch(error => {
            console.error(error);
        })
        return time;
    }
}