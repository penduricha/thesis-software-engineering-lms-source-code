export default class StudentLocalStorage {
    removeStudentID_From_LocalStorage(routerPath){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.removeItem("studentID");
    }

    saveStudentID_To_LocalStorage(studentID){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.setItem("studentID", studentID);
    }

    getStudentID_From_LocalStorage(){
        const studentID = localStorage.getItem("studentID");
        return studentID || null;
    }
}