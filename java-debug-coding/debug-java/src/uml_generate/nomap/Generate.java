package uml_generate.nomap;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.*;

public class Generate {
    public static void main(String[] args) {
        String uml = "@startuml\n" +
                "class Product {\n" +
                "    - String productCode\n" +
                "    - String productName\n" +
                "    - double price\n" +
                "    - int quantity\n" +
                "    - LocalDate manufactureDate\n" +
                "    - LocalDate expirationDate\n" +
                "    + Product()\n" +
                "    + Product(String productCode, String productName, double price, int quantity, LocalDate manufactureDate, LocalDate expirationDate)\n" +
                "    + String validateProductCode(String code)\n" +
                "    + double validatePrice(double price)\n" +
                "    + int validateQuantity(int quantity)\n" +
                "    + LocalDate validateExpirationDate(LocalDate expirationDate, LocalDate manufactureDate)\n" +
                "    + String toString()\n" +
                "    + double getTotalValue()\n" +
                "    + String getProductCode()\n" +
                "    + String getProductName()\n" +
                "    + double getPrice()\n" +
                "    + int getQuantity()\n" +
                "    + LocalDate getManufactureDate()\n" +
                "    + LocalDate getExpirationDate()\n" +
                "    + void setProductCode(String productCode)\n" +
                "    + void setProductName(String productName)\n" +
                "    + void setPrice(double price)\n" +
                "    + void setQuantity(int quantity)\n" +
                "    + void setManufactureDate(LocalDate manufactureDate)\n" +
                "    + void setExpirationDate(LocalDate expirationDate)\n" +
                "}\n" +
                "@enduml";
        try {
            String outputFilePath = "./product-class.jpg";
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
