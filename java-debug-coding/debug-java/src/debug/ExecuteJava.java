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
                //StringBuilder errorMessage = new StringBuilder("Compilation Error:\n");
                String errorMessage = "";
                for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                    //errorMessage.append((diagnostic.toString())).append("\n");
                    //errorMessage.append(diagnostic.getPosition()).append("\n");
                    errorMessage = diagnostic.toString();
                }
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

    public ExecuteJava() {
    }

    public String executeJava_With_Class(String codeClass, String codeMain, String nameClass, String nameMainClass, String packageName) {
        StringBuilder output = new StringBuilder();
        try {
            String baseDir = "src";
            String packageDir = baseDir + "/" + packageName.replace('.', '/');
            // Convert package name to directory structure

            // Create the full paths for the class and main files
            String classFilePath = packageDir + "/" + nameClass + ".java";
            String mainFilePath = packageDir + "/" + nameMainClass + ".java";

            String mainFilePathToExecute = packageName + "." + nameMainClass;

            // Create the directory if it doesn't exist
            File directory = new File(packageDir);
            if (!directory.exists()) {
                directory.mkdirs(); // Create the package directory structure
            }

            // Save the class code to a file
            try (PrintWriter writer = new PrintWriter(classFilePath)) {
                writer.println("package " + packageName + ";"); // Add package declaration
                writer.println(codeClass); // Save the provided class code
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Save the main code to a separate file
            try (PrintWriter writer = new PrintWriter(mainFilePath)) {
                writer.println("package " + packageName + ";"); // Add package declaration
                writer.println(codeMain); // Save the main class code
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Compile both the class code and main code
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            // Compile the class file
            Iterable<? extends JavaFileObject> classCompilationUnits = fileManager.getJavaFileObjects(classFilePath);
            JavaCompiler.CompilationTask classTask = compiler.getTask(null, fileManager, diagnostics, null, null, classCompilationUnits);
            boolean classSuccess = classTask.call();

            // Compile the main file
            Iterable<? extends JavaFileObject> mainCompilationUnits = fileManager.getJavaFileObjects(mainFilePath);
            JavaCompiler.CompilationTask mainTask = compiler.getTask(null, fileManager, diagnostics, null, null, mainCompilationUnits);
            boolean mainSuccess = mainTask.call();

            if (!classSuccess || !mainSuccess) {
                StringBuilder errorMessage = new StringBuilder("Compilation Error:\n");
                for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                    errorMessage.append(diagnostic.toString()).append("\n");
                }
                return errorMessage.toString();
            }

            // Run the program
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "src", mainFilePathToExecute);
            processBuilder.redirectErrorStream(true);
            // Merge error and output streams

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the process
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Delete files after execution
//            Files.deleteIfExists(Paths.get(classFilePath));
//            Files.deleteIfExists(Paths.get(mainFilePath));
//            Files.deleteIfExists(Paths.get(packageDir + "/" + nameClass + ".class"));
//            Files.deleteIfExists(Paths.get(packageDir + "/" + nameMainClass + ".class"));

           // return output.toString();
            return output.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}