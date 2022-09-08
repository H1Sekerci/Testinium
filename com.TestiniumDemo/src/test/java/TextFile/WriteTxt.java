package TextFile;

import org.openqa.selenium.devtools.v85.io.IO;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class WriteTxt {

    public void  writeText(String urunBilgisi, String fiyat) throws IOException {

        String fileName = System.getProperty("user.dir");
        fileName +="\\src\\main\\resources\\TestiniumText.txt" ;
        try (FileOutputStream fos = new FileOutputStream(fileName)) {

            String text = urunBilgisi + " " + fiyat;
            byte[] mybytes = text.getBytes();

            fos.write(mybytes);
        }
    }

    public String readFiyatText() throws IOException {
        String fileName = System.getProperty("user.dir");
        fileName +="\\src\\main\\resources\\TestiniumText.txt" ;

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String currentLine = reader.readLine();
        reader.close();

        String[] array = currentLine.split(" ");
        return  array[array.length-2].toString();
    }
}
