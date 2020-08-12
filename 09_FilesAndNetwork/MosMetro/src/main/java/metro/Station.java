package metro;

public class Station {
    private String name;
    private Line line;

    public Station(String name, Line line) {
        this.name = name;
        this.line = line;
    }

    public Station() {}

    public String getName() {
        return name;
    }
}
