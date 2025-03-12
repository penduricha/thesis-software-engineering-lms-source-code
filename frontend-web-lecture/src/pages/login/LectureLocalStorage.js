export default class LectureLocalStorage {
    removeLectureID_From_LocalStorage(routerPath){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.removeItem("lectureID");
    }

    saveLectureID_To_LocalStorage(lectureID){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.setItem("lectureID", lectureID);
    }

    getLectureID_From_LocalStorage(){
        const lectureID = localStorage.getItem("lectureID");
        return lectureID || null;
    }
}