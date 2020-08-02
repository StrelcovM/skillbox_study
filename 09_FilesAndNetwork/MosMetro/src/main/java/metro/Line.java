package metro;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private int lineNumber;
    private List<Station> stationsList;

    public Line(String name, int lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
        stationsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLineNumber() {
        return lineNumber;
    }

}
