export default class Password {
    _password_Input;
    //lop doi tuong password de xu ly mat khau

    constructor(password_Input) {
        this._password_Input = password_Input;
    }

    async sha512() {
        let buf = await crypto.subtle
            .digest("SHA-512",
            new TextEncoder("utf-8")
                .encode(this._password_Input));
        return Array.prototype.map.call(new Uint8Array(buf),
                x => (('00' + x.toString(16)).slice(-2)))
            .join('');
    }

    xorEncryptDecrypt() {
        let key = "passwordExam";
        let output = '';
        for (let i = 0; i < this._password_Input.length; i++) {
            // Mã hóa/giải mã bằng phép toán XOR
            output += String.fromCharCode(this._password_Input.charCodeAt(i) ^ key.charCodeAt(i % key.length));
        }
        return output;
    }
}