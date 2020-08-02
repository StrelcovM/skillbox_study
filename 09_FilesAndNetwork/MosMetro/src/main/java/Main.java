import metro.Line;
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
        parseLine();
    }

    private static void parseLine() throws IOException {
        Elements elements = document.getElementsByAttributeValueStarting("class",
                "js-metro-line");
        elements.forEach(element -> {
            Line line = new Line(element.text(), element.attr("data-line"));
            stationIndex.addLine(line);
        });
    }
}
