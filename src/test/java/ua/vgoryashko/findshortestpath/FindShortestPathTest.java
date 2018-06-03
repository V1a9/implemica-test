package ua.vgoryashko.findshortestpath;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FindShortestPathTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindShortestPathTest.class);

    private int numberOfTests;

    private int numberOfCities;

    private int numberOfPathsToFind;

    private List<String[]> paths = new ArrayList<>();

    private List<City> cities = new ArrayList<>();

    private XmlParser xmlParser;

    @Before
    public void setUp() throws Exception {
        xmlParser = new XmlParser("input.xml", cities, paths, numberOfTests, numberOfCities, numberOfPathsToFind);
    }

    @Test
    public void parseInput() {
        xmlParser.parseInput();
    }


}