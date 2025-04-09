import BankTestOopService from "@/services/BankTestOopService.js";


export default class BankTestJavaOopDao {
    static async get_List_Java_Test_Oop() {
        let listTest = [];
        await BankTestOopService.fetch_List_Java_Test_Oop()
            .then(response => {
                listTest = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
            })
        return listTest;
    }

    static async create_Java_Test_Oop(testToPost) {
        let status = false;
        let responseReturn = null;
        await BankTestOopService.post_Java_Test_Oop(testToPost)
            .then(response => {
                responseReturn = response.data;
            }).catch(error => {
                console.error(error);
                //alert(error)
                return status;
            })
        if(responseReturn) {
            status = true;
        }
        return status;
    }
}