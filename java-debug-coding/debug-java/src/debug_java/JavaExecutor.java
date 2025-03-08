package debug_java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaExecutor {

    public String executeJavaProgram() {
        StringBuilder output = new StringBuilder();

        try {
            // Set up ProcessBuilder to run the TestEvent main class
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "src", "result_code_java.TestEvent");
            processBuilder.redirectErrorStream(true); // Merge error and output streams

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the process
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to finish and get the exit code
            int exitCode = process.waitFor();
            output.append("Process exited with code: ").append(exitCode);

        } catch (Exception e) {
            output.append("Error: ").append(e.getMessage());
        }

        return output.toString();
    }

    public static void main(String[] args) {
        JavaExecutor executor = new JavaExecutor();
        String result = executor.executeJavaProgram();
        System.out.println(result);
    }
}
