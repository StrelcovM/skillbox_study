import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Main {
    private static Document document;
    public static void main(String[] args) {
        try {
            document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine() throws IOException {


    }
}
