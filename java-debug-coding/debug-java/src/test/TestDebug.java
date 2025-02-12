package test;

import debug.ExecuteJava;
import source.SourceCode;


public class TestDebug {
    public static void main(String[] args) {
        String codeStudent = "21107608";
        String nameExam = "test-week1";
        SourceCode sourceCode = new SourceCode();
        ExecuteJava executeJava = new ExecuteJava(sourceCode.getSource(), nameExam,codeStudent);
        System.out.println(executeJava.executeJava());
    }
}
