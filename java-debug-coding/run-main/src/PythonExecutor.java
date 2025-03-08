import java.io.*;

public class PythonExecutor {

    public static void main(String[] args) {
        // Mã Python cần thực thi
        String pythonCode = "import pandas as pd\n" +
                "import seaborn as sns\n" +
                "import matplotlib.pyplot as plt\n" +
                "import numpy as np\n" +
                "from scipy.stats import shapiro";

        // Tạo tệp Python
        String sourceFile = "compile-python/helloWorld.py";

        // Ghi mã Python vào tệp
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sourceFile))) {
            writer.write(pythonCode);
        } catch (IOException e) {
            //e.printStackTrace();
            return;
        }

        // Thực thi mã Python và nhận đầu ra
        String output = executePythonCode(sourceFile);
        System.out.println(output);
    }

    private static String executePythonCode(String sourceFile) {
        StringBuilder output = new StringBuilder();
        try {
            // Sử dụng ProcessBuilder để gọi trình thông dịch Python
            ProcessBuilder processBuilder = new ProcessBuilder("python", sourceFile);
            Process process = processBuilder.start();

            // Đọc đầu ra từ quá trình
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Đợi quá trình thực thi hoàn tất
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            //e.printStackTrace();
        }
        return output.toString().trim(); // Trả về đầu ra
    }
}