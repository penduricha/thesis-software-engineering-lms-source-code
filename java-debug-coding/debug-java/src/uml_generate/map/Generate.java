package uml_generate.map;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.*;

public class Generate {
    public static void main(String[] args) {
        String uml = "@startuml\n" +
                "\n" +
                "class Library {\n" +
                "    - libraryCode: String\n" +
                "    - libraryName: String\n" +
                "\n" +
                "    + Library()  \n" +
                "    + Library(libraryCode: String, libraryName: String)  \n" +
                "    + getLibraryCode(): String\n" +
                "    + setLibraryCode(libraryCode: String): void\n" +
                "    + getLibraryName(): String\n" +
                "    + setLibraryName(libraryName: String): void\n" +
                "    + toString(): String\n" +
                "}\n" +
                "\n" +
                "class Book {\n" +
                "    - title: String\n" +
                "    - author: String\n" +
                "    - isbn: String\n" +
                "    - library: Library\n" +
                "\n" +
                "    + Book()  \n" +
                "    + Book(title: String, author: String, isbn: String, library: Library)  \n" +
                "    + getTitle(): String\n" +
                "    + setTitle(title: String): void\n" +
                "    + getAuthor(): String\n" +
                "    + setAuthor(author: String): void\n" +
                "    + getIsbn(): String\n" +
                "    + setIsbn(isbn: String): void\n" +
                "    + getLibrary(): Library\n" +
                "    + setLibrary(library: Library): void\n" +
                "    + toString(): String\n" +
                "}\n" +
                "\n" +

                "Book \"*\" -- \"1\" Library\n" +
                "\n" +
                "@enduml";
        try {
            String outputFilePath = "./lib-book.jpg";
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
