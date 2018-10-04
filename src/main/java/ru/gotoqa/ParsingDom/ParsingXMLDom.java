package ru.gotoqa.ParsingDom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.w3c.dom.Element;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

/**
 * @author Muflikhunov Roman
 */

public class ParsingXMLDom {

    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();
        Document document = loader.parse("D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\act.xml");

        DocumentTraversal trav = (DocumentTraversal) document;

        MyFilter filter = new MyFilter();

        NodeIterator it = trav.createNodeIterator(document.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT, filter, true);

        for (Node node = it.nextNode(); node != null;
             node = it.nextNode()) {

            String name = node.getNodeName();
            String text = node.getTextContent().trim().replaceAll("\\s+", " ");
            System.out.printf("%s: %s%n", name, text);
            System.out.println("==========================================");
        }
    }

    static class MyFilter implements NodeFilter {

        @Override
        public short acceptNode(Node thisNode) {
            if (thisNode.getNodeType() == Node.ELEMENT_NODE) {

                Element e = (Element) thisNode;
                String nodeName = e.getNodeName();

                if ("FirstName".equals(nodeName) || "LastName".equals(nodeName) || "BirthDay".equals(nodeName)) {
                    return NodeFilter.FILTER_ACCEPT;
                }
            }
            return NodeFilter.FILTER_REJECT;
        }
    }
}
