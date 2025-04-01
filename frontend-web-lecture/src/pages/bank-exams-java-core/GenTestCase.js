import axios from "axios";

export default class GenTestCase {
    static async getTestCaseGenerate(content) {
        let listTestCases = [];
        try {
            const res = await axios.post("https://api.coze.com/v1/workflow/run", {
                "parameters": {
                    "input": this.content
                },
                "workflow_id": "7483910122122657799"
            }, {
                headers: {
                    'Authorization': 'Bearer pat_808iJDthzF5JN5t51ea6CyzEoaN8KjkIfQMi7BhUKC0nxcFQPArpIQwuc8BKuIRm',
                    'Content-Type': 'application/json'
                }
            });
            // Lấy dữ liệu từ API
            let data = res.data.data;
            console.log("data", data);

            let cleanedData = data.slice(10, -1);
            console.log("cleanedData", cleanedData);
            cleanedData = cleanedData.replace(/\\/g, '');

            console.log("cleanedData", cleanedData); // In ra mảng kết quả
            listTestCases = JSON.parse(cleanedData)
            return listTestCases;
        }catch(err) {
            alert(err);
            console.log("err Gen Testcase", err);
        }
    }
}