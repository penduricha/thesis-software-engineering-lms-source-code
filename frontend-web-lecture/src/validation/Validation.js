export default class Validation {
    constructor() {
    }

    isNumeric (str){
        return /^\d+$/.test(str);
    }

    static removeSpaces(str) {
        return str.replace(/\s+/g, '');
        // Xóa tất cả khoảng trắng
    }

    static isFullOfSpaces(str) {
        for (let char of str) {
            if (char !== ' ') {
                return false;
            }
        }
        return true;
    }

    static validateString_Title(input) {
        const regex = /^[a-zA-Z0-9.,]+$/;
        return regex.test(input);
    }

    // static validateString_Title(input) {
    //     return false;
    // }
}