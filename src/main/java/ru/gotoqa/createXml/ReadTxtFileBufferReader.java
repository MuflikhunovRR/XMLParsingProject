package ru.gotoqa.createXml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Muflikhunov Roman
 */
public class ReadTxtFileBufferReader {
    private static final String FILENAME = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\actors.txt";

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String sCurrentLine;
            List<String[]> content = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                content.add(sCurrentLine.split(","));
                //System.out.println(sCurrentLine);
                //System.out.println("-----------------------------");
            }

            for (String[] con : content) {
                System.out.println("Id: " + con[0] + ", First Name: " + con[1] + ", Secon Name: " + con[2]);
            }


        }   }
}
