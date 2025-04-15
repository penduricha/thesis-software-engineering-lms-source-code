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

    static async getExam_By_CourseID_ExamID(examID, courseID) {
        let exam = null;
        await ExamService.fetchExam_By_CourseID_ExamID(examID, courseID).then(response => {
            exam = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exam;
    }

    static async create_Exam_By_CourseID(courseID, exam) {
        let status = false;
        let examResponse = null
        await ExamService.post_Exam_By_CourseID(courseID, exam).then(response => {
            examResponse = response.data;
        }).catch(error => {
            console.error(error);
        })

        if (examResponse) {
            status = true;
        }
        return status;
    }

    static async update_Exam_By_ExamID(exam, examID) {
        let status = false;
        let examResponse = null
        await ExamService.put_Exam_By_ExamID(exam, examID).then(response => {
            examResponse = response.data;
        }).catch(error => {
            console.error(error);
        })

        if (examResponse) {
            status = true;
        }
        return status;
    }

    static async delete_Exam_By_ExamID(examID) {
        let status = false;
        let examResponse = null
        await ExamService.delete_Exam_By_ExamID(examID).then(response => {
            examResponse = response.data;
        }).catch(error => {
            console.error(error);
        })

        if (examResponse) {
            status = true;
        }
        return status;
    }

    static async getExams_By_LectureID_Lecture_Calendar(lectureID, yearStartDate, monthStartDate, dateStartDate) {
        let exams = [];
        await ExamService.fetchExams_By_LectureID_Lecture_Calendar
        (lectureID,
            yearStartDate,
            monthStartDate,
            dateStartDate).then(response => {
            exams = response.data;
        }).catch(error => {
            console.error(error);
        })
        return exams;
    }

    static async getStatus_Access_Student_To_Exam_By_ExamID(examID) {
        let status = false;
         await ExamService
            .fetchStatus_Access_Student_To_Exam_By_ExamID(examID).then(response => {
                 status = response.data;
             }).catch(error => {
                 console.error(error);
             })
        return status;
    }

    static async get_Exist_Title_Exam_By_TitleExam_CourseID(courseID, titleExam) {
        let responseReturn = null;
        await ExamService
            .fetch_Title_Exam_By_TitleExam_CourseID(courseID, titleExam).then(response => {
               responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        return responseReturn;
    }

    static async create_Exam_Java_Core_With_Choose_Question(dataPost ,courseID) {
        let responseReturn = null;
        let status = false;
        await ExamService
            .post_Exam_Java_Core_With_Choose_Question(dataPost ,courseID).then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async create_Exam_Java_Core_With_Random_Question(dataPost, courseID, numberOfQuestionsToRandom) {
        let responseReturn = null;
        let status = false;
        await ExamService
            .post_Exam_Java_Core_With_Random_Question(dataPost, courseID, numberOfQuestionsToRandom)
            .then(response => {
            responseReturn = response.data;
        }).catch(error => {
            console.error(error);
        })
        if(responseReturn) {
            status = true;
        }
        return status;
    }

    static async getListTitleExam() {
        let listTitleExam = [];
        await ExamService
            .fetchListTitleExam()
            .then(response => {
                listTitleExam = response.data;
            }).catch(error => {
                console.error(error);
            })
        return listTitleExam;
    }

    static async create_Exam_Java_Class_With_ChooseTest(dataPost, courseID){
        let responseReturn = null;
        let status = false;
        // const dataPost2 = {
        //     "titleExam": "java class demo 2",
        //     "typeExam" : "Practice 1",
        //     "topicExam" : "Java class",
        //     "retakeExam" : "Yes",
        //     "scoringMethod" : "Max",
        //     "duration" : 30,
        //     "startDateDay" : 15,
        //     "startDateMonth" : 4,
        //     "startDateYear" : 2025,
        //     "startDateHour" : 15,
        //     "startDateMinute" : 15,
        //     "endDateDay" : 19,
        //     "endDateMonth" : 5,
        //     "endDateYear" : 2025,
        //     "endDateHour" : 15,
        //     "endDateMinute" : 15,
        //     "viewTable" : true,
        //     "linkExamPaper" : null,
        //     "password" : null,
        //     "bankTestJavaOopID" : 1
        // }
        await ExamService
            .post_Exam_Java_Class_With_ChooseTest(dataPost, courseID)
            .then(response => {
                responseReturn = response.data;
                console.log('Response return: ',responseReturn);
            }).catch(error => {
                console.error(error);
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }
}