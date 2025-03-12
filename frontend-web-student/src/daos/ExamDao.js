import ExamService from "@/services/ExamService.js";

export default class ExamDao {
    static async getExams_By_CourseID(courseID) {
        let exams = [];
        await ExamService.fetchExams_By_CourseID(courseID).then(response => {
            exams = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exams;
    }

    static async startPolling_GetExams_By_CourseID(courseID, callback) {
        let lastFetchedExams = await this.getExams_By_CourseID(courseID);
         // Kiểm tra mỗi 5 giây
        // Trả về interval để có thể dọn dẹp sau này
        return setInterval(async () => {
            const currentExams = await this.getExams_By_CourseID(courseID);

            if (JSON.stringify(currentExams) !== JSON.stringify(lastFetchedExams)) {
                lastFetchedExams = currentExams;
                callback(currentExams);
                // Gọi lại callback để cập nhật dữ liệu
            }
        }, 5000);
    }

    static async getExam_By_CourseID_ExamID(examID, courseID) {
        let exam = null;
        await ExamService.fetchExam_By_CourseID_ExamID(examID, courseID).then(response => {
            exam = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exam;
    }

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
        }, 5000);
    }

    static async getExam_Information_Before_Exam(examID, courseID){
        let exam = null;
        await ExamService
            .fetchExam_Information_Before_Exam(examID, courseID)
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
}