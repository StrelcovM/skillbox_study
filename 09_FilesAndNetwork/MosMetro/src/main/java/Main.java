import metro.Line;
import metro.Station;
import metro.StationIndex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    private static Document document;
    private static StationIndex stationIndex;
    public static void main(String[] args) throws IOException {
        try {
            document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stationIndex = new StationIndex();
        parseLine();
        parseStation();
        System.out.println();
    }

    private static void parseLine() throws IOException {
        Elements elements = document.getElementsByAttributeValueStarting("class",
                "js-metro-line");
        elements.forEach(element -> {
            Line line = new Line(element.text(), element.attr("data-line"));
            stationIndex.addLine(line);
        });
    }

    private static void parseStation() {
        Elements linesWithStations = document.getElementsByClass("js-metro-stations t-metrostation-list-table");

        linesWithStations.forEach(parseLine -> {
            Line line = stationIndex.getLineByNumber(parseLine.attr("data-line"));

            Elements stationsOnParseLine = parseLine.select("span.name");

            stationsOnParseLine.forEach(nameStation -> {
                Station station = new Station(nameStation.text(), line);
                stationIndex.addStation(station);
                line.addStation(station);
            });
        });
    }
}
