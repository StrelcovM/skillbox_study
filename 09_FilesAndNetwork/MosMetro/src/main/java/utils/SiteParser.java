package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Line;
import models.Station;
import models.StationIndex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class SiteParser {
    Document document;
    StationIndex stationIndex;

    public SiteParser() {
        stationIndex = new StationIndex();
    }

    public StationIndex getStationIndexFromSite(String url) {
        try {
            document = Jsoup.connect(url).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseStationIndexFromSite();
    }

    public void write(Object object, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(new ObjectMapper().writeValueAsString(object));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StationIndex parseStationIndexFromSite() {
        //parsing line from site
        Elements elements = document.getElementsByAttributeValueStarting("class",
                "js-metro-line");
        elements.forEach(element -> {
            Line line = new Line(element.text(), element.attr("data-line"));
            stationIndex.addLine(line);
        });

        //parsing stations from site
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

        return stationIndex;
    }
}