export default class LecturerLocalStorage {
    removeLecturerID_From_LocalStorage(routerPath){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.removeItem("lecturerID");
    }

    saveLecturerID_To_LocalStorage(lecturerID){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.setItem("lecturerID", lecturerID);
    }

    getLecturerID_From_LocalStorage(){
        const lecturerID = localStorage.getItem("lecturerID");
        return lecturerID || null;
    }
}