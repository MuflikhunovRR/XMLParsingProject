package ru.gotoqa.createXml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * @author Muflikhunov Roman
 */

public class AdditionalMethod {
    protected static final String FILENAME = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\actors.txt";
    protected static final String FILENAMEOUT = "D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\act.xml";
    public static DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    public static DocumentBuilder dBuilder;

    public static Node getActor(Document doc, String id, String fName, String lName, String afName, String alName, String age) {
        Element actor = doc.createElement("Cast");
        actor.setAttribute("id", id);

        Element character = doc.createElement("Character");
        character.appendChild(getActorsElements(doc, actor, "FirstName", fName));
        character.appendChild(getActorsElements(doc, actor, "LastName", lName));
        actor.appendChild(character);

        Element actors = doc.createElement("Actor");
        actors.appendChild(getActorsElements(doc, actor, "FirstName", afName));
        actors.appendChild(getActorsElements(doc, actor, "LastName", alName));
        actors.appendChild(getActorsElements(doc, actor, "BirthDay", age));
        actor.appendChild(actors);

        return actor;
    }

    private static Node getActorsElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
