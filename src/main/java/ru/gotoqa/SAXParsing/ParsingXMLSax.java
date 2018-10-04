package ru.gotoqa.SAXParsing;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Muflikhunov Roman
 */

public class ParsingXMLSax {
    public static void main(String argv[]) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean bfname = false;
                boolean blname = false;
                boolean birthDay = false;

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) {

                    System.out.println("Start Element: " + qName);

                    if (qName.equalsIgnoreCase("FIRSTNAME")) {
                        bfname = true;
                    }

                    if (qName.equalsIgnoreCase("LASTNAME")) {
                        blname = true;
                    }

                    if (qName.equalsIgnoreCase("BirthDay")) {
                        birthDay = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) {
                    System.out.println("End Element :" + qName);
                }

                public void characters(char ch[], int start, int length) {
                    if (bfname) {
                        System.out.println("First Name : " + new String(ch, start, length));
                        bfname = false;
                    }
                    if (blname) {
                        System.out.println("Last Name : " + new String(ch, start, length));
                        blname = false;
                    }
                    if (birthDay) {
                        System.out.println("BirthDay : " + new String(ch, start, length));
                        birthDay = false;
                    }
                }
            };

            saxParser.parse("D:\\JAVA\\Java_SRC\\XMLParsingProject\\src\\main\\resources\\act.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
