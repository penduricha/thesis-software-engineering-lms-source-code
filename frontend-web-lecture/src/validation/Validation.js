export default class Validation {
    isNumeric (str){
        return /^\d+$/.test(str);
    }

    isFullOfSpaces(str) {
        for (let char of str) {
            if (char !== ' ') {
                return false;
            }
        }
        return true;
    }


}