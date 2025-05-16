package drawuml;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Generator {
    public static void main(String[] args) {
        String uml = StringUML.getStringUML();
        try {
            String outputFilePath = "./thesis-se-class.jpg";
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
}
