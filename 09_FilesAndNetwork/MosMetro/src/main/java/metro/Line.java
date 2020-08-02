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

    public String getName() {
        return name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

}
