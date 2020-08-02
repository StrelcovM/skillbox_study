import metro.Line;
import metro.Station;
import metro.StationIndex;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    private static Document document;
    private static StationIndex stationIndex;

    public static void main(String[] args) throws IOException {
        createStationIndex();
        writeJSONObjectToFile(createJsonObject(), "09_FilesAndNetwork/MosMetro/src/main/resources/map.json");
    }

    private static void parseLine() {
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

    private static void createStationIndex() {
        try {
            document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stationIndex = new StationIndex();

        parseLine();
        parseStation();
    }

    private static void writeJSONObjectToFile(JSONObject object, String path) {
        try (FileWriter writer = new FileWriter(path)) {

            writer.write(object.toString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject createJsonObject() {
        JSONObject map = new JSONObject();
        JSONObject stations = new JSONObject();
        JSONArray linesArray = new JSONArray();

        HashMap<String, Line> lineToNumbers = stationIndex.getLinesToNumber();

        lineToNumbers.forEach((key, value) -> {
            JSONArray stationsNames = new JSONArray();

            value.getStationsList().forEach(name -> stationsNames.put(name.getName()));

            stations.put(key, stationsNames);
        });

        lineToNumbers.forEach((key, value) -> {
            JSONObject line = new JSONObject();

            line.put("number", key);
            line.put("name", value.getName());

            linesArray.put(line);
        });

        map.put("stations", stations);
        map.put("lines", linesArray);

        return map;
    }
}
