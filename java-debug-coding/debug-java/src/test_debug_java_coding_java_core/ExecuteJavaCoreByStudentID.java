package test_debug_java_coding_java_core;

import javax.tools.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ExecuteJavaCoreByStudentID {
    private String studentID;
    private Long examID;
    private Long questionJavaCoreExamID;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Long getExamID() {
        return examID;
    }

    public void setExamID(Long examID) {
        this.examID = examID;
    }

    public Long getQuestionJavaCoreExamID() {
        return questionJavaCoreExamID;
    }

    public void setQuestionJavaCoreExamID(Long questionJavaCoreExamID) {
        this.questionJavaCoreExamID = questionJavaCoreExamID;
    }

    public ExecuteJavaCoreByStudentID(String studentID, Long examID, Long questionJavaCoreExamID) {
        this.studentID = studentID;
        this.examID = examID;
        this.questionJavaCoreExamID = questionJavaCoreExamID;
    }

    public ExecuteJavaCoreByStudentID() {
    }

    public String executeJava(String code) {
        try {
            //thay đổi tùy theo cấu trúc
            String path = "service_compiler/"+studentID+"/"+examID+"/";
            String mainClassName = "Main_" + questionJavaCoreExamID;
            String mainFilePath =  path + mainClassName + ".java";

            // Create directory if it doesn't exist
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the source code to a file
            try (PrintWriter writer = new PrintWriter(mainFilePath)) {
                //writer.println("package " + packageName + ";");
                writer.println("import java.util.ArrayList;  // For using ArrayList\n" +
                        "import java.util.Random;     // For using Random\n" +
                        "import java.util.regex.*;    // For using regular expressions (Regex)\n" +
                        "import java.util.*;          // For importing all classes in java.util package\n" +
                        "import java.lang.StringBuilder; // For using StringBuilder\n" +
                        "import java.time.format.DateTimeFormatter; // For formatting dates\n" +
                        "import java.lang.*; // For exception\n" +
                        "import java.io.*; // For exception\n" +
                        "import java.text.*; // For exception\n" +
                        "import java.time.*; // For using LocalDateTime");
                writer.println(code);
            }

            // Compile the source code
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(mainFilePath);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);

            boolean success = task.call();

            if (!success) {
                //StringBuilder errorMessage = new StringBuilder("Compilation Error:\n");
                String errorMessage = "";
                for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                    //errorMessage.append((diagnostic.toString())).append("\n");
                    //errorMessage.append(diagnostic.getPosition()).append("\n");
                    errorMessage = diagnostic.toString();
                }
                //System.out.println(errorMessage);
                //return "Syntax failed";
                return errorMessage;
            }

            // Run the program
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", path, mainClassName);
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
            //delete
            Files.deleteIfExists(Paths.get(path + mainClassName + ".class"));

            return output.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
