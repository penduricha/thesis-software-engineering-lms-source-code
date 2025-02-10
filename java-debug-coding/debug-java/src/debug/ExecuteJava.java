package debug;







import javax.tools.*;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;


public class ExecuteJava {
    private String javaCode;
    private String nameExam;
    private String codeStudent;

    public String getJavaCode() {
        return javaCode;
    }

    public void setJavaCode(String javaCode) {
        this.javaCode = javaCode;
    }

    public String getNameExam() {
        return nameExam;
    }

    public void setNameExam(String nameExam) {
        this.nameExam = nameExam;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public ExecuteJava(String javaCode, String nameExam, String codeStudent) {
        this.javaCode = javaCode;
        this.nameExam = nameExam;
        this.codeStudent = codeStudent;
    }
//    public String executeJava() {
//        try {
//            String path = "compile-java-code/" + nameExam + "/" + codeStudent + "/";
//            String mainClassName = "App";
//            String mainFilePath = path + mainClassName + ".java";
//
//            // Create directory if it doesn't exist
//            File directory = new File(path);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // Save the source code to a file
//            try (PrintWriter writer = new PrintWriter(mainFilePath)) {
//                writer.println(javaCode);
//            }
//
//            // Compile the source code
//            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
//            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
//            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(mainFilePath);
//            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
//
//            boolean success = task.call();
//
//            if (!success) {
//                StringBuilder errorMessage = new StringBuilder("Compilation Error:\n");
//                for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
//                    errorMessage.append(diagnostic.toString()).append("\n");
//                }
//                return errorMessage.toString();
//            }
//
//            // Create URLClassLoader with the directory containing the class files
//            URL[] urls = { new File(path).toURI().toURL() };
//            RestrictedClassLoader classLoader = new RestrictedClassLoader(urls);
//
//            // Load and run the class
//            Class<?> mainClass = classLoader.loadClass(mainFilePath);
//            Method mainMethod = mainClass.getMethod("main", String[].class);
//            String[] args = {}; // Pass any needed arguments
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            PrintStream printStream = new PrintStream(outputStream);
//
//            // Redirect System.out to capture output
//            PrintStream originalOut = System.out;
//            System.setOut(printStream);
//
//            // Invoke the main method
//            mainMethod.invoke(null, (Object) args);
//
//            // Reset System.out
//            System.setOut(originalOut);
//
//            // Get the output
//            String output = outputStream.toString();
//
//            // Clean up
//            Files.deleteIfExists(Paths.get(mainFilePath));
//            Files.deleteIfExists(Paths.get(path + mainClassName + ".class"));
//
//            return output;
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }

    public String executeJava() {
        try {
            //thay đổi tùy theo cấu trúc
            String path = "compile-java-code/"+nameExam+"/"+codeStudent+"/";
            String mainClassName = "App";
            String mainFilePath =  path + mainClassName + ".java";

            // Create directory if it doesn't exist
            File directory = new File(path);
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
                    errorMessage.append((diagnostic.toString())).append("\n");
                    //errorMessage.append(diagnostic.getPosition()).append("\n");
                }
                return errorMessage.toString();
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
