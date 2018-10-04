package ru.gotoqa.ParsingDom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Muflikhunov Roman
 */

public class UpdateXMLDomCharacter {

    public static final String xmlFilePath = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\act.xml";
    public static final String xmlFilePath2 = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\actOut.xml";

    public static void main(String args[]) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFilePath);

            Node employee = document.getElementsByTagName("Cast").item(1);

            NamedNodeMap attribute = employee.getAttributes();
            Node nodeAttr = attribute.getNamedItem("id");
            nodeAttr.setTextContent("20");

            Node nodeAttr2 = document.getElementsByTagName("FirstName").item(0);
            nodeAttr2.setTextContent("FnameUPDATE");

            Node nodeAttr3 = document.getElementsByTagName("LastName").item(0);
            nodeAttr3.setTextContent("LnameUPDATE");

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