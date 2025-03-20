package com.example.backend_service.debugs;

import javax.tools.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CompileJavaCodeByLectureID {
    private String lectureID;

    private String getPathSaveCompiler() {
        return "service_compiler_lecture/debug_code_add_and_update_question/" + lectureID + "/";
    }


    public String getLectureID() {
        return lectureID;
    }

    public void setLectureID(String lectureID) {
        this.lectureID = lectureID;
    }

    public CompileJavaCodeByLectureID(String lectureID) {
        this.lectureID = lectureID;
    }

    public boolean compileJavaCodeByLectureID(String code) {
        try {
            //thay đổi tùy theo cấu trúc
            /*
                Cấu trúc lưu file biên dịch sẽ là
                service_compiler/<studentID>/<examID./<questionJavaCoreExamID>
                vd: service_compiler/21107601/45/263
             */
            String path = getPathSaveCompiler();
            String mainClassName = "TestCompiler";
            String mainFilePath = getString(code, path, mainClassName);

            // Compile the source code
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(mainFilePath);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits);
            boolean success = task.call();
            if (!success) {
                return false;
            }

            // Delete files after execution
            Files.deleteIfExists(Paths.get(mainFilePath));
            //delete
            Files.deleteIfExists(Paths.get(path + mainClassName + ".class"));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String getString(String code, String path, String mainClassName) throws FileNotFoundException {
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
                    "import java.lang.*; // For exception\n" +
                    "import java.io.*; // For exception\n" +
                    "import java.text.*; // For exception\n" +
                    "import java.time.*; // For using LocalDateTime");
            writer.println("public class "+mainClassName+ "{");
            writer.println(code);
            writer.println("}");
        }
        return mainFilePath;
    }
}
