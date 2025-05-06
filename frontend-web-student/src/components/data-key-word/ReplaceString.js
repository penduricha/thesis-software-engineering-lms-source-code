export default class ReplaceString {
    static escapeString (str){
        return str.replace(/\\/g, '\\\\').replace(/"/g, '\\"');
    };
}