export default class StringFormat {
    static normalizeSpaces(str) {
        return str.replace(/\s{2,}/g, ' ');
    }
}