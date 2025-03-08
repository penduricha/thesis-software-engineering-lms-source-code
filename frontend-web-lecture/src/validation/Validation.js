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
        const regex = /^[a-zA-Z0-9.,-]+(?:\s[a-zA-Z0-9.,-]+)*$/;
        return regex.test(input);
    }

    // static validateString_Title(input) {
    //     return false;
    // }
    static validateNameFunction_Java(input) {
        // Define a regular expression for valid names
        const namePattern = /^[A-Za-z\s]+$/; // Only letters and spaces allowed
        const minLength = 2; // Minimum length for the name
        const maxLength = 50; // Maximum length for the name
        // Check if the input is a string
        if (typeof input !== 'string') {
            return false;
        }
        // Check length constraints
        if (input.length < minLength || input.length > maxLength) {
            return false;
        }
        // Check if the input matches the name pattern
        if (!namePattern.test(input)) {
            return false;
        }
        // If all checks passed, the name is valid
        return true;
    }

    static validateNameParameter_Java(input) {
        // Define a regular expression for valid names
        const namePattern = /^[A-Za-z\s]+$/; // Only letters and spaces allowed
        const minLength = 1; // Minimum length for the name
        const maxLength = 50; // Maximum length for the name
        // Check if the input is a string
        if (typeof input !== 'string') {
            return false;
        }
        // Check length constraints
        if (input.length < minLength || input.length > maxLength) {
            return false;
        }
        // Check if the input matches the name pattern
        if (!namePattern.test(input)) {
            return false;
        }
        // If all checks passed, the name is valid
        return true;
    }
}