package com.example.dylan.manhunt;

import com.google.android.gms.games.Player;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;
import org.xml.sax.Attributes;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Reads the xml file that is passed to it and accepts the name, lat and lng coordinates of each user
 * @author Dylan Foster
 * @version 5/4/16
 */
public class ReadXml {
    /** Player object to hold the values */
    private Player player;
    /** temp variable to help send data to result */
    public static String temp = "";
    String[] playerArray = new String[temp.length()];
    /**
     * Parse the XML data that it receives with SAX
     */
    public void parse() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                //username boolean
                boolean name = false;
                //latitude boolean
                boolean lat = false;
                //longitude boolean
                boolean lng = false;

                /**
                 * Find all of the start tags
                 * @param uri - the location of where we are getting things
                 * @param localName - the local name we are getting
                 * @param attributes - the attributes of the player we are looking at (lat,lng)
                 * @throws SAXException
                 */
                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {
                    //System.out.println("Start Element :" + qName);
                    if (qName.equalsIgnoreCase("PLAYER")) {
                        //not quite fixed yet
                        //player = new Player();
                    }
                    if (qName.equalsIgnoreCase("NAME")) {
                        name = true;
                    }
                    if (qName.equalsIgnoreCase("LAT")) {
                        lat = true;
                    }
                    if (qName.equalsIgnoreCase("LNG")) {
                        lng = true;
                    }
                }

                /**
                 * Find the end tags
                 * @param uri - where it is getting it
                 * @param localName - the local name
                 * @param qName - the end tags
                 * @throws SAXException
                 */
                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("PLAYER")) {
                        //need to fix this as well
                        //playerArray.players.add(player);
                    }
                }

                /**
                 * Create the strings for each value
                 * @param ch - character array
                 * @param start - the start position
                 * @param length - the full length of what we are looking at
                 * @throws SAXException
                 */
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (name) {
                        String nam = new String(ch, start, length);
                        //player.setName(nam);
                        name = false;
                    }
                    if (lat) {
                        String lt = new String(ch, start, length);
                        double tmplat = Double.parseDouble(lt);
                        //player.setLat(tmplat);
                        lat = false;
                    }
                    if (lng) {
                        String lg = new String(ch, start, length);
                        double tmplng = Double.parseDouble(lg);
                        //player.setLng(tmplng);
                        lng = false;
                    }
                }
            };
            saxParser.parse(new InputSource(new StringReader(temp)), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

