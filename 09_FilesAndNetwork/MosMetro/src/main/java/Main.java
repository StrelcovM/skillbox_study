import com.fasterxml.jackson.databind.ObjectMapper;
import models.Line;
import models.StationIndex;
import utils.SiteParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String mapJsonFile = "09_FilesAndNetwork/MosMetro/src/main/resources/map.json";
        String siteURL = "https://www.moscowmap.ru/metro.html#lines";
        StationIndex stationIndex;
        SiteParser parser = new SiteParser();

        stationIndex = parser.getStationIndexFromSite(siteURL);
        parser.write(stationIndex, mapJsonFile);

        Map<String, Integer> map = parseLineFromJson(mapJsonFile);
        map.forEach((key, value) -> System.out.println(key + " " + value));
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
