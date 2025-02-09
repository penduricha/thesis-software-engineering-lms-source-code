package test;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

//import java.io.BufferedReader;
import java.io.FileOutputStream;
//import java.io.FileReader;
import java.io.OutputStream;

public class UMLGenerator {
    public static void main(String[] args) {

        // Chuyển đổi mã thành UML
        String uml = "@startuml\n" +
                "class Person {\n" +
                "    -String name\n" +
                "    -int age\n" +
                "    +Person(String name, int age)\n" +
                "    +String getName()\n" +
                "    +int getAge()\n" +
                "}\n" +
                "\n" +
                "class Student extends Person {\n" +
                "    -String studentId\n" +
                "    +Student(String name, int age, String studentId)\n" +
                "    +String getStudentId()\n" +
                "    +void study()\n" +
                "}\n" +
                "@enduml";

        // Tạo hình ảnh UML
        try {
            String outputFilePath = "student.jpg";
            OutputStream output = new FileOutputStream(outputFilePath);
            SourceStringReader reader = new SourceStringReader(uml);
            reader.outputImage(output, new FileFormatOption(FileFormat.PNG));
            output.close();
            System.out.println("Hình ảnh UML đã được tạo tại: " + outputFilePath);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}