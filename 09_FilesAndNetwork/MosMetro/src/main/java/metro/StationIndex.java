package metro;

import java.util.*;

public class StationIndex {
    private HashMap<String, Line> linesToNumber;
    private List<Station> stations;

    public StationIndex() {
        linesToNumber = new HashMap<>();
        stations = new ArrayList<>();
    }

    public void addLine(Line line) {
        linesToNumber.put(line.getLineNumber(), line);
    }

    public Line getLineByNumber(String number) {
        if (linesToNumber.containsKey(number))
            return linesToNumber.get(number);
        return null;
    }

    public void addStation(Station station) {
        stations.add(station);
    }
}
