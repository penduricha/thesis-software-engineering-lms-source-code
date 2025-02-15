package uml_generate.map;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.*;

public class Generate {
    public static void main(String[] args) {
        String uml = "@startuml\n" +
                "\n" +
                "class Hospital {\n" +
                "    - hospitalCode: String\n" +
                "    - hospitalName: String\n" +
                "    + Hospital()\n" +
                "    + Hospital(hospitalCode: String, hospitalName: String)\n" +
                "    + getHospitalCode(): String\n" +
                "    + setHospitalCode(hospitalCode: String): void\n" +
                "    + getHospitalName(): String\n" +
                "    + setHospitalName(hospitalName: String): void\n" +
                "    + toString(): String\n" +
                "}\n" +
                "\n" +
                "class Patient {\n" +
                "    - name: String\n" +
                "    - age: int\n" +
                "    - medicalRecordNumber: String\n" +
                "    - hospital: Hospital\n" +
                "    + Patient()\n" +
                "    + Patient(name: String, age: int, medicalRecordNumber: String, hospital: Hospital)\n" +
                "    + getName(): String\n" +
                "    + setName(name: String): void\n" +
                "    + getAge(): int\n" +
                "    + setAge(age: int): void\n" +
                "    + getMedicalRecordNumber(): String\n" +
                "    + setMedicalRecordNumber(medicalRecordNumber: String): void\n" +
                "    + getHospital(): Hospital\n" +
                "    + setHospital(hospital: Hospital): void\n" +
                "    + toString(): String\n" +
                "}\n" +
                "\n" +
                "Patient \"0..*\" -- \"1\" Hospital\n" +
                "\n" +
                "@enduml";
        try {
            String outputFilePath = "./hospital_patient.jpg";
            OutputStream output = new FileOutputStream(outputFilePath);
            SourceStringReader reader = new SourceStringReader(uml);
            reader.outputImage(output, new FileFormatOption(FileFormat.PNG));
            output.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
        //System.out.println(uml);
    }

    public static String getStringFromTxtFile(String txtFile) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(txtFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return content.toString();
    }
}
