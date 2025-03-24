package com.example.backend_service.debugs;

import com.example.backend_service.dto.CodeSubmitAndCodeMain;

import javax.tools.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExecuteJavaCoreResultQuestions implements Serializable {
    private String studentID;

    private CodeSubmitAndCodeMain codeSubmitAndCodeMain;

    private int sizeTestCases;

    public CodeSubmitAndCodeMain getCodeSubmitAndCodeMain() {
        return codeSubmitAndCodeMain;
    }

    public void setCodeSubmitAndCodeMain(CodeSubmitAndCodeMain codeSubmitAndCodeMain) {
        this.codeSubmitAndCodeMain = codeSubmitAndCodeMain;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getSizeTestCases() {
        return sizeTestCases;
    }

    public void setSizeTestCases(int sizeTestCases) {
        this.sizeTestCases = sizeTestCases;
    }

    public ExecuteJavaCoreResultQuestions(String studentID, CodeSubmitAndCodeMain codeSubmitAndCodeMain, int sizeTestCases) {
        this.studentID = studentID;
        this.codeSubmitAndCodeMain = codeSubmitAndCodeMain;
        this.sizeTestCases = sizeTestCases;
    }

    public ExecuteJavaCoreResultQuestions() {
    }

    private String getPathSaveCompiler() {
        return "service_compiler_debug_code_submitted/" + studentID + "/" + codeSubmitAndCodeMain.getResultQuestionJavaCoreID() + "/";
    }

    public List<String> getResultOutputCodeJava_From_CodeSubmitAndCodeMain() throws Exception {
        List<String> outputCodeJava_From_CodeSubmitAndCodeMain = new ArrayList<>();
        String folderPath = "service_compiler_debug_code_submitted/" + studentID;
        if(codeSubmitAndCodeMain != null) {
            String path = getPathSaveCompiler();
            String mainClassName = "Main_"+codeSubmitAndCodeMain.getResultQuestionJavaCoreID();
            String mainFilePath = getString(path, mainClassName);

            // Compile the source code
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(mainFilePath);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
            boolean success = task.call();
            if (!success) {
                //neu sai syntax
                //StringBuilder errorMessage = new StringBuilder("Compilation Error:\n");
                for(int i = 0; i < sizeTestCases; i++) {
                    outputCodeJava_From_CodeSubmitAndCodeMain.add("Syntax error");
                }
                deleteFolder(folderPath);
                return outputCodeJava_From_CodeSubmitAndCodeMain;
            }

            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", path, mainClassName);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            //StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                //output.append(line).append("\n");
                outputCodeJava_From_CodeSubmitAndCodeMain.add(line);
            }
            deleteFolder(folderPath);

            if(outputCodeJava_From_CodeSubmitAndCodeMain.size() == sizeTestCases) {
                return outputCodeJava_From_CodeSubmitAndCodeMain;
            } else {
                throw new Exception("List test cases is not match code run to output. Please edit.");
            }
        }
        return new ArrayList<>();
    }

    public String getString(String path, String mainClassName) throws FileNotFoundException {
        String mainFilePath =  path + mainClassName + ".java";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (PrintWriter writer = new PrintWriter(mainFilePath)) {
            //writer.println("package " + packageName + ";");
            writer.println("import java.util.ArrayList;  // For using ArrayList\n" +
                    "import java.util.Random;     // For using Random\n" +
                    "import java.util.regex.*;    // For using regular expressions (Regex)\n" +
                    "import java.util.*;          // For importing all classes in java.util package\n" +
                    "import java.lang.*; // For exception\n" +
                    "import java.io.*; // For exception\n" +
                    "import java.text.*; // For exception\n" +
                    "import java.time.format.*;" +
                    "import java.time.*; // For using LocalDateTime");
            writer.println("public class "+mainClassName+"{");
            writer.println(getCodeSubmitAndCodeMain().mergeToCode());
            writer.println("}");
        }
        return mainFilePath;
    }

    public void deleteFolder(String folderPath) {
        Path path = Paths.get(folderPath);
        try {
            // Delete the folder and its contents
            Files.walk(path).sorted((a, b) -> b.compareTo(a)).forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            System.err.println("Failed to delete " + p + ": " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error deleting folder: " + e.getMessage());
        }
    }
}
