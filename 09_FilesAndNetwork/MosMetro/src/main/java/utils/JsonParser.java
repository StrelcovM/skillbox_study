package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Line;
import models.StationIndex;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {
        private ObjectMapper mapper = new ObjectMapper();
        private Map<String, Integer> linesSize = new HashMap<>();
        private StationIndex index;

    public void printLinesSize() {
            Map<String, Integer> map = getLinesSize();
            map.forEach((key, value) -> System.out.println(key + " " + value));
        }

        private Map<String, Integer> getLinesSize() {
            try {
                String path = "09_FilesAndNetwork/MosMetro/src/main/resources/map.json";
                index = mapper.readValue(new FileReader(path), StationIndex.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Map<String, Line> linesToNumber = index.getLinesToNumber();

            linesToNumber.forEach((key, value) -> linesSize.put(key, value.getStationsList().size()));

            return linesSize;
        }
}