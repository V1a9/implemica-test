package ua.vgoryashko.findshortestpath;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class XmlParserTest {

    private FindShortestPath findShortestPath;

    private XmlParser xmlParser;

    private City actual;

    private City expected;

    @Before
    public void setUp() {
        findShortestPath = new FindShortestPath("input.xml");
        xmlParser = new XmlParser(findShortestPath.getFileName(), findShortestPath);

        xmlParser.parseInput();

        actual = findShortestPath.getCities().get(0);

        expected = new City();
        expected.setCityName("gdansk");
        expected.setCityNumber(1);
        expected.setConnectedCities(new int[][]{{2, 1},{3, 3}});
    }

    @Test
    public void whenParseInputInvokedThenInputDataParsedAsExpected() {
        assertEquals(4, findShortestPath.getCities().size());
        assertNotNull(actual);
        assertThat(expected.getCityName(), is(actual.getCityName()));
        assertThat(expected.getCityNumber(), is(actual.getCityNumber()));
        assertThat(expected.getConnectedCities(), is(actual.getConnectedCities()));
    }
}