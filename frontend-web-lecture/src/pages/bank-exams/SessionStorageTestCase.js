export class SessionStorageTestCase {
    constructor() {
        // Khởi tạo mảng test cases và tải từ session storage nếu có
        this.testCases = [];
        this.loadFromSessionStorage();
    }

    // Hàm thêm test case
    addTestCase(input, output, note) {
        const testCase = {
            inputTest: input,
            outputExpect: output,
            note: note,
        };
        this.testCases.push(testCase);
        this.saveToSessionStorage();
    }

    // Hàm xóa test case theo index
    removeTestCase(index) {
        if (index >= 0 && index < this.testCases.length) {
            this.testCases.splice(index, 1);
            this.saveToSessionStorage();
        } else {
            console.error('Index out of bounds');
        }
    }

    // Lưu test cases vào session storage
    saveToSessionStorage() {
        sessionStorage.setItem('testCases', JSON.stringify(this.testCases));
    }

    // Tải test cases từ session storage
    loadFromSessionStorage() {
        const storedTestCases = sessionStorage.getItem('testCases');
        if (storedTestCases) {
            this.testCases = JSON.parse(storedTestCases);
        }
        return this.testCases;
    }

    // Hàm lấy tất cả test cases
    getAllTestCases() {
        return this.testCases;
    }
}