export default class RouterDao {

    _variableRouterPathSession;

    getVariableRouterPathSession() {
        return this._variableRouterPathSession;
    }

    setVariableRouterPathSession(value) {
        this._variableRouterPathSession = value;
    }

    constructor(){
        this.setVariableRouterPathSession('routerPathSession');
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
}