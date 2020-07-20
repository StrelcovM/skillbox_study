import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RoutCalculatorTest extends TestCase {
    List<Station> routOnLine;

    @Override
    protected void setUp() throws Exception {
        StationIndex stationIndex = new StationIndex();

        stationIndex.addLine(new Line(1, "first"));
        stationIndex.addLine(new Line(2, "second"));
        stationIndex.addLine(new Line(3, "third"));

        stationIndex.addStation(new Station("first_1",stationIndex.getLine(1)));
        stationIndex.addStation(new Station("first_2",stationIndex.getLine(1)));
        stationIndex.addStation(new Station("first_3",stationIndex.getLine(1)));

        stationIndex.addStation(new Station("second_1",stationIndex.getLine(2)));
        stationIndex.addStation(new Station("second_2",stationIndex.getLine(2)));
        stationIndex.addStation(new Station("second_3",stationIndex.getLine(2)));

        stationIndex.addStation(new Station("third_1",stationIndex.getLine(3)));
        stationIndex.addStation(new Station("third_2",stationIndex.getLine(3)));
        stationIndex.addStation(new Station("third_3",stationIndex.getLine(3)));
        stationIndex.addStation(new Station("third_4",stationIndex.getLine(3)));
        stationIndex.addStation(new Station("third_5",stationIndex.getLine(3)));

        stationIndex.getLine(1).addStation(stationIndex.getStation("first_1"));
        stationIndex.getLine(1).addStation(stationIndex.getStation("first_2"));
        stationIndex.getLine(1).addStation(stationIndex.getStation("first_3"));

        stationIndex.getLine(2).addStation(stationIndex.getStation("second_1"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("second_2"));
        stationIndex.getLine(2).addStation(stationIndex.getStation("second_3"));

        stationIndex.getLine(3).addStation(stationIndex.getStation("third_1"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("third_2"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("third_3"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("third_4"));
        stationIndex.getLine(3).addStation(stationIndex.getStation("third_5"));

        List<Station> connectionStations = new ArrayList<>();
        connectionStations.add(stationIndex.getStation("third_2", 3));
        connectionStations.add(stationIndex.getStation("first_2", 1));
        stationIndex.addConnection(connectionStations);

        connectionStations.clear();

        connectionStations.add(stationIndex.getStation("third_4", 3));
        connectionStations.add(stationIndex.getStation("second_2", 2));
        stationIndex.addConnection(connectionStations);
        /*
                          third_1
                            |
                            |
          first_1----third_2\first_2----first_3
                            |
                            |
                          third_3
                            |
                            |
         second_1----third_4\second_2----second_3
                            |
                            |
                          third_5
        */
//        routCalculator = new RouteCalculator(stationIndex);


        routOnLine = new ArrayList<>();
        routOnLine.add(stationIndex.getStation("third_1"));
        routOnLine.add(stationIndex.getStation("third_2"));
        routOnLine.add(stationIndex.getStation("third_3"));
        routOnLine.add(stationIndex.getStation("third_4"));
    }

    public void testCalculateDurationOnline() {

        double actual = RouteCalculator.calculateDuration(routOnLine);
        double expected = 7.5;

        assertEquals(actual, expected);
    }


}
