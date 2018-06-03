package ua.vgoryashko.findshortestpath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that TODO
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 01.06.18
 */
public class FindShortestPath {

    //TODO add check of distances and cities names

    private static final Logger LOGGER = LoggerFactory.getLogger(FindShortestPath.class);

    private String fileName;

    private int numberOfTests;

    private int numberOfCities;

    private int numberOfPathsToFind;

    private List<String[]> pathsToFind = new ArrayList<>();

    private List<City> cities = new ArrayList<>();

    public FindShortestPath(String fileName) {
        this.fileName = fileName;
    }

    protected void initProgram(XmlParser xmlParser) {
        xmlParser.parseInput();
    }

    public void findPaths() {
        initProgram(new XmlParser(fileName, cities, pathsToFind, numberOfTests, numberOfCities, numberOfPathsToFind));

    }

}
