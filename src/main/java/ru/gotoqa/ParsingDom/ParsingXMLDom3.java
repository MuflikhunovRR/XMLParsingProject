package ru.gotoqa.ParsingDom;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Muflikhunov Roman
 */

public class ParsingXMLDom3 {
    public static void main(String[] args) {
        String filePath = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\act.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Actor");
            List<ActorsModel> actorsList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                actorsList.add(getActors(nodeList.item(i)));
            }
            for (ActorsModel act : actorsList) {
                System.out.println(act.getFirstName().toString());
            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }

    }


    private static ActorsModel getActors(Node node) {
        ActorsModel act = new ActorsModel();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            act.setFirstName(getTagValue("FirstName", element));
            act.setLastName(getTagValue("LastName", element));
            act.setBirthDay(getTagValue("BirthDay", element));
        }

        return act;
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}