import axios from "axios";

export default class AxiosConfig {
    constructor() {

    }

    getAPIClient(){
        return axios.create({
            baseURL: 'http://localhost:8080/api',
            headers: {
                'Content-Type': 'application/json',
                // Thiết lập header Authorization
            }
        });
    }
}