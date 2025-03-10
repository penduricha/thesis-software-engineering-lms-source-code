package test_debug_java_coding_java_core;

public class TestDemo {
    public static void main(String[] args) {
        Long examID = 41L;
        Long questionJavaCoreExamID = 226L;
        String studentID = "21107605";
        String code = "public class Main_"+questionJavaCoreExamID+" {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello world\");\n" +
                "    }\n" +
                "}";
        ExecuteJavaCoreByStudentID
                executeJavaCoreByStudentID = new ExecuteJavaCoreByStudentID(studentID, examID, questionJavaCoreExamID);
        String output = executeJavaCoreByStudentID.executeJava(code);
        System.out.println(output);
    }

}
