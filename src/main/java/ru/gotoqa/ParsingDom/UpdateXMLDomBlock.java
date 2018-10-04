package ru.gotoqa.ParsingDom;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * @author Muflikhunov Roman
 */

public class UpdateXMLDomBlock {

    public static final String xmlFilePath = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\act.xml";
    public static final String xmlFilePath2 = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\actOut.xml";

    public static void main(String args[]) {

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFilePath);

            Node employee2 = document.getElementsByTagName("ActorCast").item(0);
            Element address = document.createElement("Cast");
            address.setAttribute("id", "21");

            Element address2 = document.createElement("Character");
            address.appendChild(address2);

            address2.appendChild(getEmployeeElements(document, address, "FirstName", "Quor"));
            address2.appendChild(getEmployeeElements(document, address, "LastName", "Last"));
            employee2.appendChild(address);

            Element address3 = document.createElement("Actor");
            address.appendChild(address3);

            address3.appendChild(getEmployeeElements(document, address, "FirstName", "Quor"));
            address3.appendChild(getEmployeeElements(document, address, "LastName", "Last"));
            address3.appendChild(getEmployeeElements(document, address, "BirthDay", "11.11.11"));
            employee2.appendChild(address3);


            // DOM object to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File(xmlFilePath2));
            transformer.transform(domSource, streamResult);

            System.out.println("Done.");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException sae) {
            sae.printStackTrace();
        }
    }

    private static Node getEmployeeElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}