package test;

import javax.tools.*;
import java.io.*;

public class DebugJavaExecution {
    public static void main(String[] args) {
        String nameMainClass = "ResultQuestion_JavaCore_ID_1"; // Name of the main class
        String packageName = "result_question_java_core"; // Package name
        String javaCoreCode = "public class "+nameMainClass+" {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}"; // Sample Java code

        DebugJavaExecution executor = new DebugJavaExecution();
        String result = executor.executeJavaCore(nameMainClass, packageName, javaCoreCode);
        System.out.println(result);
    }

    public String executeJavaCore(String nameMainClass, String packageName, String javaCoreCode) {
        StringBuilder output = new StringBuilder();
        try {
            // Change according to structure
            String baseDir = "src";
            String packageDir = baseDir + "/" + packageName.replace('.', '/');

            String mainFilePath = packageDir + "/" + nameMainClass + ".java";
            String mainFilePathToExecute = packageName + "." + nameMainClass;

            // Create directory if it doesn't exist
            File directory = new File(packageDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the source code to a file
            try (PrintWriter writer = new PrintWriter(mainFilePath)) {
                writer.println("package " + packageName + ";");
                writer.println("import java.util.ArrayList;  // For using ArrayList\n" +
                        "import java.util.Random;     // For using Random\n" +
                        "import java.util.regex.*;    // For using regular expressions (Regex)\n" +
                        "import java.util.*;          // For importing all classes in java.util package\n" +
                        "import java.lang.StringBuilder; // For using StringBuilder\n" +
                        "import java.time.format.DateTimeFormatter; // For formatting dates\n" +
                        "import java.time.LocalDate; // For using LocalDateTime");
                writer.println(javaCoreCode);
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
                }
                return errorMessage.toString();
            }

            // Run the program
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "src", mainFilePathToExecute);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            return output.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}