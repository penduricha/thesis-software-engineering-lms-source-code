import java.io.BufferedReader;
//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCompiler {

    public static String executeCCompile(String codeC) {
        String output = "";
        String sourceFileName = "result-c/test_21107602.cpp"; // File tạm thời để lưu mã C
        String outputFileName = "result-c/test_21107602"; // File thực thi đầu ra
        String gccPath = "C:\\Program Files (x86)\\Embarcadero\\Dev-Cpp\\TDM-GCC-64\\bin\\gcc.exe";
        // Đường dẫn đến GCC

        // Viết mã C vào file tạm
        try (FileWriter writer = new FileWriter(sourceFileName)) {
            writer.write(codeC);
        } catch (IOException e) {
            return "Lỗi khi ghi file: " + e.getMessage();
        }

        // Tạo lệnh biên dịch
        ProcessBuilder compileProcessBuilder = new ProcessBuilder(gccPath, sourceFileName, "-o", outputFileName);

        // Chạy lệnh biên dịch
        try {
            Process compileProcess = compileProcessBuilder.start();
            int exitCode = compileProcess.waitFor();
            if (exitCode != 0) {
                output = "Lỗi trong quá trình biên dịch.";
                return output;
            }

            // Chạy file thực thi
            Process runProcess = new ProcessBuilder(outputFileName).start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()))) {
                String line;
                StringBuilder outputBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    outputBuilder.append(line).append("\n");
                }
                output = outputBuilder.toString();
            }

        } catch (IOException | InterruptedException e) {
            return "Lỗi khi chạy chương trình: " + e.getMessage();
        }
        return output.trim();
    }

    public static void main(String[] args) {
        String codeC = "#include <stdio.h>\nint main() { printf(\"Hello world\\n\"); return 0;}";

        String result = executeCCompile(codeC);
        System.out.println(result);
    }
}

//String gccPath = "C:\\Program Files (x86)\\Embarcadero\\Dev-Cpp\\TDM-GCC-64\\bin\\gcc.exe"; // Điều chỉnh nếu cần