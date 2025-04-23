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
}