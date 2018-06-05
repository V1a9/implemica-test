package ua.vgoryashko.findshortestpath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Objects;

/**
 * Class that parses input data from the input.xml and initializes variables in FindShortestPath.class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 04.06.18
 */
public class XmlParser extends DefaultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class);

    /**
     * File with input data.
     */
    private String fileName;

    /**
     * City object that is created each time after respective data read from input file.
     */
    private City city = null;

    /**
     * Variable that is used for creation of an array that stores an info about adjacent cities.
     */
    private int counterOfConnectedCities = 0;

    /**
     * Array that represents a path for defining a shortest distance.
     */
    private String[] pathToFind = null;

    /**
     * Instance of FindShortestPath.class.
     */
    private FindShortestPath findShortestPath;

    /**
     * Boolean variables that are used for retrieving respective values from xml elements.
     */
    private boolean bNumberOfTests = false;
    private boolean bNumberOfCities = false;
    private boolean bNumberOfPathsToFind = false;
    private boolean bName = false;
    private boolean bConnections = false;
    private boolean bNumber = false;
    private boolean bDistance = false;
    private boolean bFrom = false;
    private boolean bTo = false;

    /**
     * Constructor for the class.
     * @param fileName file with input data.
     * @param findShortestPath instance of FindShortestPath.class
     */
    public XmlParser(final String fileName, final FindShortestPath findShortestPath) {
        this.fileName = fileName;
        this.findShortestPath = findShortestPath;
    }

    /**
     * Method that defines behavior of handler when parser starts reading a new xml element.
     * @param uri
     * @param localName
     * @param qName name of an element
     * @param attributes of an element
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "tests": bNumberOfTests = true;
                break;
            case "cities": bNumberOfCities = true;
                break;
            case "city":
                city = new City();
                city.setCityNumber(Integer.parseInt(attributes.getValue("id")));
                break;
            case "name": bName = true;
                break;
            case "connections": bConnections = true;
                break;
            case "number": bNumber = true;
                break;
            case "distance": bDistance = true;
                break;
            case "from": bFrom = true;
                break;
            case "to": bTo = true;
            break;
            default: break;
        }
    }

    /**
     * Method that defines a behavior of handler when parser reaches the end of a xml element.
     * Adds new city object to collection of cities.
     * Adds new String[] of paths to collection (e.g. [bydgoszcz, warszawa])
     * @param uri
     * @param localName
     * @param qName
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("city")) {
            findShortestPath.getCities().add(city);
            counterOfConnectedCities = 0;
        } else if (qName.equals("path") && findShortestPath.getPathsToFind() != null) {
            findShortestPath.getPathsToFind().add(pathToFind);
        }
    }

    /**
     * Method that reads values of xml elements and initializes respective variables.
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        if (bNumberOfTests) {
            findShortestPath.setNumberOfTests(Integer.valueOf(new String(ch, start, length)));
            bNumberOfTests = false;
        } else if (bNumberOfCities) {
            findShortestPath.setNumberOfCities(Integer.valueOf(new String(ch, start, length)));
            bNumberOfCities = false;
        } else if (bName) {
            city.setCityName(new String(ch, start, length));
            bName = false;
        } else if (bConnections) {
            city.setNumberOfConnections(Integer.valueOf(new String(ch, start, length)));
            bConnections = false;
        } else if (bNumber) {
            if (city.getConnectedCities() == null) {
                city.setConnectedCities(new int[city.getNumberOfConnections()][]);
                for (int i = 0; i < city.getNumberOfConnections(); i++) {
                    city.getConnectedCities()[i] = new int[2];
                }
            }
            city.getConnectedCities()[counterOfConnectedCities][0] = Integer.valueOf(new String(ch, start, length));
            bNumber = false;
        } else if (bDistance) {
            city.getConnectedCities()[counterOfConnectedCities++][1] = Integer.valueOf(new String(ch, start, length));
            bDistance = false;
        } else if (bNumberOfPathsToFind) {
            findShortestPath.setNumberOfPathsToFind(Integer.valueOf(new String(ch, start, length)));
            bNumberOfPathsToFind = false;
        } else if (bFrom) {
            pathToFind = new String[2];
            pathToFind[0] = new String(ch, start, length);
            bFrom = false;
        } else if (bTo) {
            pathToFind[1] = new String(ch, start, length);
            bTo = false;
        }
    }

    /**
     * Method that setting SAX parser and parses a file with input data.
     */
    protected void parseInput() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        ClassLoader loader = FindShortestPath.class.getClassLoader();
        spf.setNamespaceAware(true);
        try {
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new XmlParser(this.fileName, this.findShortestPath));
            xmlReader.parse(Objects.requireNonNull(loader.getResource(this.fileName)).getPath());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}