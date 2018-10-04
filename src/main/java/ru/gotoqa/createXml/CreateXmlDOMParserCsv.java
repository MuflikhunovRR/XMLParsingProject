package ru.gotoqa.createXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Muflikhunov Roman
 */

public class CreateXmlDOMParserCsv extends AdditionalMethod {

    public static void main(String[] args) {
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //Element rootElement = doc.createElementNS("https://www.gotoqa.ru/actors", "ActorCast");
            Element rootElement = doc.createElement("ActorCast");
            doc.appendChild(rootElement);

            List<String[]> content = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    content.add(sCurrentLine.split(","));
                }

                for (String[] con : content) {
                    rootElement.appendChild(getActor(doc, con[0], con[1], con[2], con[3], con[4], con[5]));
                }
            }

            //file - console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult logFile = new StreamResult(new File(FILENAMEOUT));

            //write data
            transformer.transform(source, console);
            transformer.transform(source, logFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}