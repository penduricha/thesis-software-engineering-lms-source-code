const javaKeyWordsBasic = [
    "public", "private", "protected", "static", "final", "void", "class", "interface", "enum",
    "extends", "implements", "abstract", "new", "this", "super", "return", "import", "package",
    "if", "else", "switch", "case", "default", "break", "continue", "while", "do", "for",
    "foreach", "try", "catch", "finally", "throw", "throws", "synchronized", "volatile",
    "transient", "native", "strictfp", "assert", "instanceof", "Array", "List", "Map",
    "null", "true", "false", "LocalDate", "Array", "matches", "toString", "DateTimeParseException",
    "IllegalArgumentException", "System.out.println", "System.out.printf", "System.out.print"
];

// const javaKeyWords = [
//     "public", "private", "protected", "static", "final", "void", "class", "interface", "enum",
//     "extends", "implements", "abstract", "new", "this", "super", "return", "import", "package",
//     "if", "else", "switch", "case", "default", "break", "continue", "while", "do", "for",
//     "foreach", "try", "catch", "finally", "throw", "throws", "synchronized", "volatile",
//     "transient", "native", "strictfp", "assert", "instanceof", "Array", "List", "Map",
//     "null", "true", "false", "LocalDate", "Array", "matches", "toString", "DateTimeParseException",
//     "IllegalArgumentException", "System.out.println", "System.out.printf", "System.out.print"
// ];

// export default javaKeyWords;

// keyword chị đã tìm hiểu được:
// Từ khóa kiểm soát truy cập và sửa đổi thành phần
const javaAccessModifiers = [
    "public", "private", "protected"
];

// Các từ khóa sửa đổi
const javaOtherModifiers = [
    "static", "final", "transient", "volatile", "abstract", "native", "strictfp"
];

// Từ khóa kiểm soát luồng điều khiển
const javaControlFlow = [
    "if", "else", "switch", "case", "default", "break", "continue", "while", "do", "for",
    "foreach", "try", "catch", "finally", "throw", "throws"
];

// Từ khóa liên quan đến đối tượng và lớp
const javaObjectKeywords = [
    "class", "interface", "enum", "extends", "implements", "new", "this", "super", "return"
];

// Các từ khóa dành cho kiểu dữ liệu và hằng số
const javaPrimitiveTypes = [
    "boolean", "byte", "char", "short", "int", "long", "float", "double"
];

const javaLiteralKeywords = [
    "null", "true", "false"
];

// Từ khóa xử lý ngoại lệ và kiểm tra
const javaExceptionKeywords = [
    "instanceof", "throw", "throws", "try", "catch", "finally"
];

// Các từ khóa bổ sung và đặc biệt
const javaSpecialKeywords = [
    "package", "import", "assert", "synchronized", "volatile", "native", "strictfp"
];

// Các từ khóa khác
const javaOtherKeywords = [
    "this", "super", "extends", "implements", "new", "return", "default", "null"
];

// **Các từ khóa bổ sung (cần có cho kỳ thi hoặc công việc)**:
const javaAdvancedKeywords = [
    "continue", "final", "finalize", "native", "instanceof", "super", "this", "volatile", "strictfp"
];

const javaTypeKeywords = [
    "boolean", "byte", "char", "short", "int", "long", "float", "double"
];

const javaGenericKeywords = [
    "extends", "super", "T", "E", "K", "V", "List", "Map", "Set", "HashMap", "ArrayList"
];

const javaNullKeywords = [
    "null", "true", "false"
];

// **Phương thức cơ bản trong Java**:

// Phương thức của lớp String
const stringMethods = [
    "length()", "charAt()", "substring()", "indexOf()", "toLowerCase()", "toUpperCase()", "equals()",
    "contains()", "replace()", "split()", "matches()", "startsWith()", "endsWith()", "trim()", "valueOf()",
    "equalsIgnoreCase()"
];

// Phương thức của mảng (Array)
const arrayMethods = [
    "length", "clone()", "copyOf()", "sort()", "equals()", "fill()", "toString()", "stream()", "forEach()"
];

// Phương thức của lớp List (Java Collections)
const listMethods = [
    "add()", "remove()", "get()", "set()", "contains()", "isEmpty()", "size()", "clear()", "indexOf()"
];

// Phương thức của lớp Map
const mapMethods = [
    "put()", "get()", "remove()", "keySet()", "values()", "containsKey()", "containsValue()", "size()", "clear()"
];

// Phương thức của LocalDate (Java 8+ Date API)
const localDateMethods = [
    "now()", "parse()", "format()", "plusDays()", "minusDays()", "getDayOfWeek()", "getYear()", "getMonthValue()"
];

// **Phương thức của Math**
const mathMethods = [
    "abs()", "max()", "min()", "sqrt()", "pow()", "random()", "sin()", "cos()", "tan()", "round()"
];

// **Phương thức của System.out**
const systemOutMethods = [
    "println()", "print()", "printf()"
];

// **Phương thức của Arrays**
const arraysMethods = [
    "copyOf()", "copyOfRange()", "sort()", "binarySearch()", "equals()", "toString()", "fill()"
];

// **Phương thức của ArrayList**
const arrayListMethods = [
    "add()", "remove()", "get()", "set()", "size()", "contains()", "clear()", "isEmpty()"
];

// **Phương thức của LinkedList**
const linkedListMethods = [
    "add()", "remove()", "get()", "set()", "addFirst()", "addLast()", "removeFirst()", "removeLast()", "contains()"
];

// **Phương thức của HashMap**
const hashMapMethods = [
    "put()", "get()", "containsKey()", "containsValue()", "remove()", "clear()", "size()", "keySet()", "values()"
];

// **Phương thức của Scanner**
const scannerMethods = [
    "next()", "nextLine()", "nextInt()", "nextDouble()", "hasNext()", "hasNextLine()"
];

// **Phương thức của Iterator**
const iteratorMethods = [
    "next()", "hasNext()", "remove()"
];

// **Các loại lỗi và ngoại lệ trong Java**
const javaErrors = [
    "NullPointerException", "ArrayIndexOutOfBoundsException", "ArithmeticException",
    "IllegalArgumentException", "NumberFormatException"
];

// **Kết hợp các mảng từ khóa và phương thức**:

const javaKeyWords = javaAccessModifiers.concat(
    javaOtherModifiers, javaControlFlow, javaObjectKeywords, javaPrimitiveTypes,
    javaLiteralKeywords, javaExceptionKeywords, javaSpecialKeywords, javaOtherKeywords,
    javaAdvancedKeywords, javaTypeKeywords, javaGenericKeywords, javaNullKeywords, javaKeyWordsBasic, javaErrors
);

const javaMethods = stringMethods.concat(
    arrayMethods, listMethods, mapMethods, localDateMethods, mathMethods, systemOutMethods,
    arraysMethods, arrayListMethods, linkedListMethods, hashMapMethods, scannerMethods,
    iteratorMethods
);


export default javaKeyWords.concat(javaMethods);