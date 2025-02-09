
export default class RouterDao {

    _variableRouterPathSession;
    _variableRouterPathLocalStorage;

    getVariableRouterPathSession() {
        return this._variableRouterPathSession;
    }

    setVariableRouterPathSession(value) {
        this._variableRouterPathSession = value;
    }

    getVariableRouterPathLocalStorage() {
        return this._variableRouterPathLocalStorage;
    }

    setVariableRouterPathLocalStorage(value) {
        this._variableRouterPathLocalStorage = value;
    }

    constructor(){
        this.setVariableRouterPathSession('routerPath_Session');
        this.setVariableRouterPathLocalStorage('routerPath_LocalStorage');
    }

    savePath_To_SessionStorage(routerPath){
        //Khi chuyển trang khác, path được save vào session.
        if(routerPath){
            sessionStorage.setItem(this.getVariableRouterPathSession(), routerPath);
        }
    }

    getPath_From_SessionStorage(){
        const routerPath = sessionStorage.getItem(this.getVariableRouterPathSession());
        return routerPath || null;
    }

    //other functions localstorage, session storages
    //save path to localstorage
    removePath_From_LocalStorage(routerPath){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.removeItem(this.getVariableRouterPathLocalStorage());
    }

    savePath_To_LocalStorage(routerPath){
        //Khi chuyển trang khác, path được save vào session.
        localStorage.setItem(this.getVariableRouterPathLocalStorage(), routerPath);
    }

    getPath_From_LocalStorage(){
        const routerPath = localStorage.getItem(this.getVariableRouterPathLocalStorage());
        return routerPath || null;
    }
}
