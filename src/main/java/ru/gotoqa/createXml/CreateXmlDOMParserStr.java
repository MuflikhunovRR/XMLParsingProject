package ru.gotoqa.createXml;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

/**
 * @author Muflikhunov Roman
 */
public class CreateXmlDOMParserStr extends AdditionalMethod {

    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElementNS("https://www.gotoqa.ru/actors", "ActorCast");
        doc.appendChild(rootElement);

        rootElement.appendChild(getActor(doc, "1", "Daenerys", "Targaryen", "Emilia", "Clarke", "23 October 1986"));
        rootElement.appendChild(getActor(doc, "2", "Tyrion", "Lannister", "Peter", "Dinklage", "11 June 1969"));

        //file - console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        //for pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);

        //write to console or file
        StreamResult console = new StreamResult(System.out);
        StreamResult file = new StreamResult(new File(FILENAMEOUT));

        //write data
        transformer.transform(source, console);
        transformer.transform(source, file);

    }
}