package uml_generate.nomap;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.*;

public class Generate {
    public static void main(String[] args) {
        String uml = "@startuml\n" +
                "\n" +
                "class Car {\n" +
                "    - licensePlate: String\n" +
                "    - manufacturer: String\n" +
                "    - manufactureYear: int\n" +
                "    - color: String\n" +
                "    - price: double\n" +
                "\n" +
                "    + Car(licensePlate: String, manufacturer: String, manufactureYear: int, color: String, price: double)\n" +
                "    + getLicensePlate(): String\n" +
                "    + getManufacturer(): String\n" +
                "    + getColor(): String\n" +
                "    + getPrice(): double\n" +
                "    + getManufactureYear(): int\n" +
                "    + getReducedPrice(): double\n" +
                "}\n" +
                "\n" +
                "@enduml";
        try {
            String outputFilePath = "./car.jpg";
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
