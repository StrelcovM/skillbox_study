package metro;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class StationIndex {
    private HashMap<Integer, Line> linesToNumber;
    private Set<Station> stations;

    public StationIndex() {
        linesToNumber = new HashMap<>();
        stations = new TreeSet<>();
    }

    public void addLine(Line line) {
        linesToNumber.put(line.getLineNumber(), line);
    }

    public void addStation(Station station) {
        stations.add(station);
    }
}
