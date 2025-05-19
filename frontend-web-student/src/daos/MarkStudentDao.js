
import MarkStudentService from "@/services/MarkStudentService.js";

export default class MarkStudentDao{
    static async set_Mark_Student_Zero_If_Exam_Overdue(studentID, examID) {
        let responseReturn = null;
        await MarkStudentService
            .fetch_Mark_Student_Zero_If_Exam_Overdue(studentID, examID)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        return responseReturn;
    }

    static async delete_Mark_Student_By_ExamID(examID) {
        await MarkStudentService
            .delete_Mark_Student_By_Mark_Student_ID(examID)
            .then(response => {
            }).catch(error => {
                console.error(error);
            })
    }

    static async get_List_Mark_Student_By_Student_ID(studentID) {
        let listMarkStudent = [];
        await MarkStudentService
            .fetch_List_Mark_Student_By_Student_ID(studentID)
            .then(response => {
                listMarkStudent = response.data;
            }).catch(error => {
                console.error(error);
            })
        return listMarkStudent;
    }

    static async startPolling_Get_List_Mark_Student_By_Student_ID(studentID, callback){
        let lastFetched = await this.get_List_Mark_Student_By_Student_ID(studentID)
        // Kiểm tra mỗi 5 giây
        // Trả về interval để có thể dọn dẹp sau này
        return setInterval(async () => {
            const data = await this.get_List_Mark_Student_By_Student_ID(studentID);
            if (JSON.stringify(data) !== JSON.stringify(lastFetched)) {
                lastFetched = data;
                callback(data);
                // Gọi lại callback để cập nhật dữ liệu
                //cho time 4 giay
            }
        }, 4000);
    }

    static async updateMark_Zero_If_No_Submitted(dataPut) {
        let responseReturn = null;
        let status = false;
        await MarkStudentService.put_MarkStudent_Zero_If_No_Submit(dataPut).then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
        })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async postFormSubmissionJavaClass(dataPost) {
        let status = false;
        let responseReturn = null;
        await MarkStudentService.post_Data_Result_Java_Class_From_FormSubmission(dataPost).then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
        })
        if(responseReturn) {
            status = true;
        }
        return status;
    }
}