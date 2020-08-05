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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    private static Document document;
    private static StationIndex stationIndex;

    public static void main(String[] args) {
        String mapJsonFile = "09_FilesAndNetwork/MosMetro/src/main/resources/map.json";

        createStationIndex();
        writeJsonObjectToFile(createJsonObject(), mapJsonFile);

        Map<String, Integer> map = parseLineFromJson(jsonFileAsString(mapJsonFile));
        map.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private static void parseLineFromSite() {
        Elements elements = document.getElementsByAttributeValueStarting("class",
                "js-metro-line");
        elements.forEach(element -> {
            Line line = new Line(element.text(), element.attr("data-line"));
            stationIndex.addLine(line);
        });
    }

    private static void parseStationFromSite() {
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

        parseLineFromSite();
        parseStationFromSite();
    }

    private static void writeJsonObjectToFile(JSONObject object, String path) {
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

    private static String jsonFileAsString(String filePath) {
        List<String> file = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        try {
            file = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.forEach(builder::append);

        return builder.toString();
    }

    private static Map<String, Integer> parseLineFromJson(String jsonString) {
        Map<String, Integer> lines = new HashMap<>();

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject stations = (JSONObject) jsonObject.get("stations");
        Set<String> linesName = stations.keySet();

        linesName.forEach(name -> {
            JSONArray stationsByLines = (JSONArray) stations.get(name);

            lines.put(name, stationsByLines.length());
        });

        return lines;
    }
}
