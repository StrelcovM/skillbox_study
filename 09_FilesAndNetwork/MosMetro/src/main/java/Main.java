import utils.JsonParser;
import utils.SiteParser;

public class Main {
    public static void main(String[] args) {
        SiteParser siteParser = new SiteParser("https://www.moscowmap.ru/metro.html#lines");
        JsonParser jsonParser = new JsonParser();

        siteParser.write(siteParser.getStationIndexFromSite());
        jsonParser.printLinesSize();
    }
}