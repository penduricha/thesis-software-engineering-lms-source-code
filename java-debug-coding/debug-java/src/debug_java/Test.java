package debug_java;

import debug.ExecuteJava;


public class Test {
    public static void main(String[] args) {
        String codeStudent = "21107801";
        String nameExam = "java-class";
        GetCodeJava getCodeJava = new GetCodeJava();
        ExecuteJava executeJava = new ExecuteJava();
        System.out.println(executeJava.executeJava_With_Class(
                getCodeJava.getCodeClass_Simple(),
                getCodeJava.getCode_Main(),
                "Event",
                "TestEvent",
                "result_code_java"
        ));
    }
}
