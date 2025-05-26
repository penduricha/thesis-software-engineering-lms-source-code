import MarkStudentService from "@/services/MarkStudentService.js";


export default class MarkStudentDao {
    constructor() {
    }

    static async get_List_Mark_Student_By_ExamID(examID) {
        let listStudents = [];
        await MarkStudentService.fetch_List_Mark_Student_By_ExamID(examID)
            .then(response => {
            listStudents = response.data;
        }).catch(error => {
            console.error(error);
        })
        return listStudents;
    }

    static async startPolling_Get_List_Mark_Student_By_ExamID(examID, callback){
        let lastFetchedStudents = await this.get_List_Mark_Student_By_ExamID(examID);
        return setInterval(async () => {
            const students = await
                this.get_List_Mark_Student_By_ExamID(examID);
            if (JSON.stringify(students) !== JSON.stringify(lastFetchedStudents)) {
                lastFetchedStudents = students;
                callback(students);
            }
        }, 3000);
    }

    static async get_Average_MarkExam_By_ExamID(examID){
        let average = 0;
        await MarkStudentService.fetch_Average_MarkExam_By_ExamID(examID)
            .then(response => {
                average = response.data;
            }).catch(error => {
                console.error(error);
            })
        return average;
    }

    static async startPolling_Get_Average_MarkExam_By_ExamID(examID, callback){
        let lastFetchedAverage = await this.get_Average_MarkExam_By_ExamID(examID);
        return setInterval(async () => {
            const newAverage = await
                this.get_Average_MarkExam_By_ExamID(examID);
            if (newAverage !== lastFetchedAverage) {
                lastFetchedAverage = newAverage;
                callback(newAverage);
            }
        }, 2000);
    }

    static async get_GroupBy_MarkStudent_MarkExam_By_ExamID(examID) {
        let groupByMarkStudent = [];
        await MarkStudentService.fetch_GroupBy_MarkStudent_MarkExam_By_ExamID(examID)
            .then(response => {
                groupByMarkStudent = response.data;
            }).catch(error => {
                console.error(error);
            })
        return groupByMarkStudent;
    }

    static async startPolling_Get_GroupBy_MarkStudent_MarkExam_By_ExamID(examID, callback) {
        let lastFetchedGroupByMarkStudent = await this.get_GroupBy_MarkStudent_MarkExam_By_ExamID(examID);
        return setInterval(async () => {
            const newGroupByMarkStudent = await
                this.get_GroupBy_MarkStudent_MarkExam_By_ExamID(examID);
            if (newGroupByMarkStudent !== lastFetchedGroupByMarkStudent) {
                lastFetchedGroupByMarkStudent = newGroupByMarkStudent;
                callback(newGroupByMarkStudent);
            }
        }, 2000);
    }

    static async get_GroupBy_ZeroToTen_MarkStudent_MarkExam_By_ExamID(examID){
        let groupByMarkStudent = [];
        await MarkStudentService.fetch_GroupBy_ZeroToTen__MarkExam_By_ExamID(examID)
            .then(response => {
                groupByMarkStudent = response.data;
            }).catch(error => {
                console.error(error);
            })
        return groupByMarkStudent;
    }

    static async startPolling_Get_GroupBy_ZeroToTen_MarkStudent_MarkExam_By_ExamID(examID, callback){
        let lastFetchedGroupByMarkStudent = await this.get_GroupBy_ZeroToTen_MarkStudent_MarkExam_By_ExamID(examID);
        return setInterval(async () => {
            const newGroupByMarkStudent = await
                this.get_GroupBy_ZeroToTen_MarkStudent_MarkExam_By_ExamID(examID);
            if (newGroupByMarkStudent !== lastFetchedGroupByMarkStudent) {
                lastFetchedGroupByMarkStudent = newGroupByMarkStudent;
                callback(newGroupByMarkStudent);
            }
        }, 2000);
    }
}