package ua.vgoryashko.findshortestpath;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FindShortestPathTest {

    private FindShortestPath findShortestPath;

    @Mock
    private XmlParser xmlParserMock;

    private XmlParser xmlParser;

    private City actual;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        findShortestPath = new FindShortestPath("input.xml");
        xmlParser = new XmlParser(findShortestPath.getFileName(), findShortestPath);
    }

    /**
     * Method that is used just to launch the application.
     */
    @Test
    public void findShortestPathTest() {
        findShortestPath.start();
    }


    @Test
    public void whenInitInvokedThenXmlParseInputInvoked() {
        findShortestPath.init(xmlParserMock);
        verify(xmlParserMock, times(1)).parseInput();
    }

    @Test
    public void whenGetCityInvokedThenCorrectCityReturned() {
        findShortestPath.init(xmlParser);
        actual = findShortestPath.getCity("gdansk");
        assertThat(actual.getCityNumber(), is(1));
    }

    @Test
    public void whenFindShortestPathInvokedThenCorrectShortestDistanceReturned() {
        findShortestPath.init(xmlParser);
        assertThat(findShortestPath.findShortestPath(1,  4), is(3));
    }
}