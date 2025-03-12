import java.io.BufferedReader;
import java.io.File;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.tools.*;
import java.util.*;

public class Main {
    public static String executeJava(String javaCode) throws Exception {
        try {
            String mainClassName = "App";
            String mainFilePath = "resultJava/21107601/" + mainClassName + ".java";

            // Create directory if it doesn't exist
            File directory = new File("resultJava/21107601");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the source code to a file
            try (PrintWriter writer = new PrintWriter(mainFilePath)) {
                writer.println(javaCode);
            }

            // Compile the source code
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(mainFilePath);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

            boolean success = task.call();

            if (!success) {
                StringBuilder errorMessage = new StringBuilder("Compilation Error:\n");
                for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                    errorMessage.append(diagnostic.toString()).append("\n");
                    //errorMessage.append(diagnostic.getPosition()).append("\n");
                }
                return errorMessage.toString();
            }

            // Run the program
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "resultJava/21107601", mainClassName);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Delete files after execution
            Files.deleteIfExists(Paths.get(mainFilePath));
            Files.deleteIfExists(Paths.get("resultJava/21107601/" + mainClassName + ".class"));

            return output.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        try {
            // Định nghĩa mã của lớp Person
//            String personClassCode = "class Person {\n" +
//                    "    private String name;\n" +
//                    "    private int age;\n" +
//                    "\n" +
//                    "    // Constructor\n" +
//                    "    public Person(String name, int age) {\n" +
//                    "        this.name = name;\n" +
//                    "        this.age = age;\n" +
//                    "    }\n" +
//                    "\n" +
//                    "    // Getter cho tên\n" +
//                    "    public String getName() {\n" +
//                    "        return name;\n" +
//                    "    }\n" +
//                    "\n" +
//                    "    // Getter cho tuổi\n" +
//                    "    public int getAge() {\n" +
//                    "        return age;\n" +
//                    "    }\n" +
//                    "\n" +
//                    "    // Phương thức để in thông tin\n" +
//                    "    public void displayInfo() {\n" +
//                    "        System.out.println(\"Name: \" + name + \", Age: \" + age);\n" +
//                    "    }\n" +
//                    "}\n";
//
//            // Định nghĩa mã của lớp App
//            String appClassCode =
//                    "import java.util.*;\n"+
//                    "public class App {\n" +
//
//                    "    public static void main(String[] args) {\n" +
//                    "        // Tạo đối tượng Person\n" +
//                    "        Person person1 = new Person(\"Alice\", 35);\n" +
//                    "        Person person2 = new Person(\"Bob\", 25);\n" +
//                    "\n" +
//                    "        // Hiển thị thông tin\n" +
//                    "        person1.displayInfo();\n" +
//                    "        person2.displayInfo();\n" +
//                    "    }\n" +
//                    "}\n";
            String javaCode =
                    "import java.util.*;\n" +
                    "public class App {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        List<String> list = new ArrayList<>();\n" +
                    "\n" +
                    "        // Adding elements to the list\n" +
                    "        list.add(\"Hello\");\n" +
                    "        list.add(\"World\");\n" +
                    "\n" +
                    "        // Printing the list\n" +
                    "        for (String item : list) {\n" +
                    "            System.out.println(item);\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            // Kết hợp mã lớp Person và App
            //String combinedCode = personClassCode + appClassCode;

            // Đường dẫn đến file output
            //String outputFilePath = "resultJava/output.txt";

            // Thực thi mã và ghi output vào file
            String result = executeJava(javaCode);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}