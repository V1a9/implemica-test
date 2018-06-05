package ua.vgoryashko.findshortestpath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that searches a shortest path for a given pairs of cities.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5.06.18
 */
public class FindShortestPath {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindShortestPath.class);

    /**
     * Variable that stores a ref to a file name with input data.
     */
    private String fileName;

    /**
     * Variable that stores a number of tests.
     */
    private int numberOfTests;

    /**
     * Variable that stores a number of cities.
     */
    private int numberOfCities;

    /**
     * Variable that stores a number of paths to be found.
     */
    private int numberOfPathsToFind;

    /**
     * Variable that refers to a list of paths to be found.
     */
    private List<String[]> pathsToFind = new ArrayList<>();

    /**
     * Variable that refers to a list of all cities.
     */
    private List<City> cities = new ArrayList<>();

    /**
     * Variable that refers to a list of adjacent cities.
     */
    private List<City> adjacentCities = new ArrayList<>();

    /**
     * Variable that stores a flag that indicates that whether this iteration is first or not.
     */
    private boolean firstIteration = true;

    /**
     * Constructor for the class.
     * @param fileName file with input data
     */
    public FindShortestPath(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Method that invokes method that parses the input data and initializes variables.
     * @param xmlParser instance of the parser
     */
    protected void init(XmlParser xmlParser) {
        xmlParser.parseInput();
    }

    /**
     * Method that implements main logic of the application.
     * Initializes all variables invoking init();
     * For each path to be found invokes findShortestPath() and prints a result;
     * After each short path found, performs cleaning of all lists and initializes all variables again through init().
     */
    protected void start() {
        init(new XmlParser(fileName, this));
        for (int i = 0; i < pathsToFind.size(); i++) {
            int distance = findShortestPath(getCity(pathsToFind.get(i)[0]).getCityNumber(), getCity(pathsToFind.get(i)[1]).getCityNumber());
            System.out.println(String.format("Path: %s; Distance: %d", Arrays.toString(pathsToFind.get(i)), distance));
            cities.clear();
            pathsToFind.clear();
            firstIteration = true;
            init(new XmlParser(fileName, this));
        }
    }

    /**
     * Method that retrieves a city basing on the given name.
     * @param name of a city to be found.
     * @return City object
     */
    protected City getCity(String name) {
        City city = null;
        for (City c : cities) {
            if (name.equals(c.getCityName()))  {
                city = c;
            }
        }
        return city;
    }

    /**
     * Method that finds a shortest path between cities and returns a path's length.
     * @param srcNumber source city's number
     * @param dstNumber destination city's number
     * @return int distance between src and dst
     */
    protected int findShortestPath(int srcNumber, int dstNumber) {

        /*
         * Defining a source city and setting up its shortest distance to source to 0.
         */
        City current = cities.get(srcNumber - 1);
        if (firstIteration) {
            firstIteration = false;
            current.setShortestDistanceFromSource(0);
        }
        /*
         * Marking this city as visited.
         */
        if (!current.isVisited()) {
            current.setVisited(true);
        }
        /*
         * For each unvisited connected city we are defining distance to the source and if it less than
         * the current value stored in the adjacent city then we updating that value and
         * setting pointer of previous city to the current.
         * Adding adjacent city to the respective list.
         */
        for (int[] indexConnectedCity : current.getConnectedCities()) {
            City connectedCity = cities.get(indexConnectedCity[0] - 1);
            if (connectedCity.isVisited()) {
                continue;
            }
            int shortestDistance = current.getShortestDistanceFromSource() + indexConnectedCity[1];
            if (shortestDistance < connectedCity.getShortestDistanceFromSource()) {
                connectedCity.setShortestDistanceFromSource(shortestDistance);
                connectedCity.setPreviousCity(current);
            }
            adjacentCities.add(connectedCity);
        }

        /*
         * Checking if the list of adjacent cities isn't empty defining an one with shortest path weight and
         * setting it as a next city, all other cities removing from the list.
         * Recursively invoking this method with next city as parameter.
         */
        if (!adjacentCities.isEmpty()) {

            City next = null;
            for (City city : adjacentCities) {
                if (next == null) {
                    next = city;
                } else if (city.getShortestDistanceFromSource() <= next.getShortestDistanceFromSource()) {
                    next = city;
                }
            }

            adjacentCities.clear();
            assert next != null;
            findShortestPath(next.getCityNumber(), dstNumber);
        }

        /*
         * When all cities are visited and the list of adjacent cities is empty method returns
         * shortest path between the given source and destination.
         */
        return cities.get(dstNumber - 1).getShortestDistanceFromSource();
    }

    public String getFileName() {
        return fileName;
    }

    public void setNumberOfTests(int numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public void setNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
    }

    public void setNumberOfPathsToFind(int numberOfPathsToFind) {
        this.numberOfPathsToFind = numberOfPathsToFind;
    }

    public List<String[]> getPathsToFind() {
        return pathsToFind;
    }

    public List<City> getCities() {
        return cities;
    }
}
