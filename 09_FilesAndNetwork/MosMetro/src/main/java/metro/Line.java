package metro;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private String lineNumber;
    private List<Station> stationsList;

    public Line(String name, String lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
        stationsList = new ArrayList<>();
    }

    public Line() {
    }

    public String getName() {
        return name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void addStation(Station station) {
        stationsList.add(station);
    }

    public List<Station> getStationsList() {
        return stationsList;
    }

}
