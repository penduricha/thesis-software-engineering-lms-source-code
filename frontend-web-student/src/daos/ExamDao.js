import ExamService from "@/services/ExamService.js";

export default class ExamDao {
    static async getExams_By_CourseID(courseID, studentID) {
        let exams = [];
        await ExamService.fetchExams_By_CourseID(courseID, studentID).then(response => {
            exams = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exams;
    }

    static async startPolling_GetExams_By_CourseID(courseID, studentID, callback) {
        let lastFetchedExams = await this.getExams_By_CourseID(courseID, studentID);
         // Kiểm tra mỗi 5 giây
        // Trả về interval để có thể dọn dẹp sau này
        return setInterval(async () => {
            const currentExams = await this.getExams_By_CourseID(courseID, studentID);

            if (JSON.stringify(currentExams) !== JSON.stringify(lastFetchedExams)) {
                lastFetchedExams = currentExams;
                callback(currentExams);
                // Gọi lại callback để cập nhật dữ liệu
            }
        }, 3000);
    }

    // static async getExam_By_CourseID_ExamID(examID, courseID, studentID) {
    //     let exam = null;
    //     await ExamService.fetchExam_By_CourseID_ExamID(examID, courseID, studentID).then(response => {
    //         exam = response.data;
    //     }).catch(error => {
    //         console.error(error);
    //     })
    //     return exam;
    // }

    static async getExams_By_StudentID_Student_Calendar(studentID, yearStartDate, monthStartDate, dateStartDate) {
        let exams = [];
        await ExamService
            .fetchExams_By_StudentID_Student_Calendar(studentID, yearStartDate, monthStartDate, dateStartDate)
            .then(response => {
            exams = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exams;
    }

    static async startPolling_GetExams_By_StudentID_Student_Calendar(studentID, yearStartDate,
                                                                     monthStartDate, dateStartDate, callback) {
        let lastFetchedExams = await this.getExams_By_StudentID_Student_Calendar(studentID,
            yearStartDate, monthStartDate, dateStartDate);
        // Kiểm tra mỗi 5 giây
        // Trả về interval để có thể dọn dẹp sau này
        return setInterval(async () => {
            const currentExams = await
                this.getExams_By_StudentID_Student_Calendar(studentID, yearStartDate, monthStartDate, dateStartDate);
            if (JSON.stringify(currentExams) !== JSON.stringify(lastFetchedExams)) {
                lastFetchedExams = currentExams;
                callback(currentExams);
                // Gọi lại callback để cập nhật dữ liệu
            }
        }, 3000);
    }

    static async getExam_Information_Before_Exam(examID, courseID, studentID){
        let exam = null;
        await ExamService
            .fetchExam_Information_Before_Exam(examID, courseID, studentID)
            .then(response => {
                exam = response.data;
            }).catch(error => {
                console.error(error);
            })
        return exam;
    }

    static async create_Access_Exam(studentID, examID) {
        let examIDResponse = null;
            await ExamService.post_Access_Exam(studentID, examID).then(response => {
                examIDResponse = response.data;
            }).catch(error => {
                console.error(error);
            })
        return examID === examIDResponse;
    }

    //suy nghi lai
    static async delete_Access_Exam(studentID, examID) {
        let studentIDResponse = null;
        await ExamService.delete_Access_Exam(studentID, examID).then(response => {
            studentIDResponse = response.data;
        }).catch(error => {
            console.error(error);
        })
        return Number(studentID) === Number(studentIDResponse);
    }

    static async get_View_Table_By_ExamID(examID){
        let result = true;
        await ExamService.fetch_View_Table_By_ExamID(examID).then(response => {
           result = response.data;
        }).catch(error => {
            console.error(error);
        })
        return result;
    }

    static async get_TopicExam_By_ExamID(examID){
        let result = null;
        await ExamService.fetch_TopicExam_By_ExamID(examID).then(response => {
            result = response.data;
        }).catch(error => {
            console.error(error);
        })
        return result;
    }
}