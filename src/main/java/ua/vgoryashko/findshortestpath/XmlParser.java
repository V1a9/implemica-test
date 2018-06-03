package ua.vgoryashko.findshortestpath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

/**
 * Class that parses input data from the input.xml and initializes all variables.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 03.06.18
 */
public class XmlParser extends DefaultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class);

    private String fileName;

    private City city = null;

    private List<City> cities;

    private List<String[]> pathsToFind;

    private int numberOfTests;

    private int numberOfCities;

    private int numberOfPathsToFind;

    private int counterOfConnectedCities = 0;

    private String[] pathToFind = null;

    private boolean bNumberOfTests = false;
    private boolean bNumberOfCities = false;
    private boolean bNumberOfPathsToFind = false;
    private boolean bName = false;
    private boolean bConnections = false;
    private boolean bNumber = false;
    private boolean bDistance = false;
    private boolean bFrom = false;
    private boolean bTo = false;

    public XmlParser(String fileName, List<City> cities, List<String[]> pathsToFind, int numberOfTests, int numberOfCities, int numberOfPathsToFind) {
        this.fileName = fileName;
        this.cities = cities;
        this.pathsToFind = pathsToFind;
        this.numberOfTests = numberOfTests;
        this.numberOfCities = numberOfCities;
        this.numberOfPathsToFind = numberOfPathsToFind;
    }

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

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("city")) {
            cities.add(city);
            counterOfConnectedCities = 0;
        } else if (qName.equals("path")) {
            pathsToFind.add(pathToFind);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (bNumberOfTests) {
            numberOfTests = Integer.valueOf(new String(ch, start, length));
            bNumberOfTests = false;
        } else if (bNumberOfCities) {
            numberOfCities = Integer.valueOf(new String(ch, start, length));
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
            }
            city.getConnectedCities()[counterOfConnectedCities] = new int[2];
            city.getConnectedCities()[counterOfConnectedCities][0] = Integer.valueOf(new String(ch, start, length));
            bNumber = false;
        } else if (bDistance) {
            city.getConnectedCities()[counterOfConnectedCities++][1] = Integer.valueOf(new String(ch, start, length));
            bDistance = false;
        } else if (bNumberOfPathsToFind) {
            numberOfPathsToFind = Integer.valueOf(new String(ch, start, length));
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

    protected void parseInput() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        ClassLoader loader = FindShortestPath.class.getClassLoader();
        spf.setNamespaceAware(true);
        try {
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new XmlParser(
                    this.fileName,
                    cities,
                    pathsToFind,
                    numberOfTests,
                    numberOfCities,
                    numberOfPathsToFind));
            xmlReader.parse(loader.getResource(this.fileName).getPath());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}