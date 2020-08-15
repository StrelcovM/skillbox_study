import com.fasterxml.jackson.databind.ObjectMapper;
import models.Line;
import models.StationIndex;
import utils.JsonParser;
import utils.SiteParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SiteParser siteParser = new SiteParser("https://www.moscowmap.ru/metro.html#lines");
        JsonParser jsonParser = new JsonParser();

        siteParser.write(siteParser.getStationIndexFromSite());
        jsonParser.printLinesSize();
    }
}