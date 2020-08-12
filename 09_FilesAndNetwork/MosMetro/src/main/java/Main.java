import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import metro.Line;
import metro.Station;
import metro.StationIndex;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class Main {
    private static Document document;
    private static StationIndex stationIndex;

    public static void main(String[] args) {
        String mapJsonFile = "09_FilesAndNetwork/MosMetro/src/main/resources/map.json";

        createStationIndex();
        try {
            writeJsonObjectToFile(createJsonObject(stationIndex), mapJsonFile);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Map<String, Integer> map = parseLineFromJson(mapJsonFile);
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

    private static void writeJsonObjectToFile(String object, String path) {
        try (FileWriter writer = new FileWriter(path)) {

            writer.write(object);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createJsonObject(Object object) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private static Map<String, Integer> parseLineFromJson(String filePath) {
        StationIndex index = new StationIndex();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Integer> lines = new HashMap<>();
        Map<String, Line> linesToNumber;

        try {
            index = mapper.readValue(new FileReader(filePath), StationIndex.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        linesToNumber = index.getLinesToNumber();

        linesToNumber.forEach((key, value) -> lines.put(key, value.getStationsList().size()));

        return lines;
    }
}
