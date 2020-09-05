import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    private static final String site = "https://skillbox.ru";
    private static final Set<String> siteMap = new TreeSet<>();

    public static void main(String[] args) {
        getLinksFromPage(site);
        siteMap.forEach(System.out::println);
    }

    private static boolean checkLink(String link) {
        return link.startsWith(site) && !link.contains("#") && !link.equals(site);
    }

    private static void getLinksFromPage(String url) {
        Document document = null;
        Elements elements = null;
        try {
            document = Jsoup.connect(url).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (document != null)
            elements = document.getElementsByTag("a");

        elements.forEach(element -> {
            String link = element.attr("href");

            if (link.matches("/.+/"))
                link = site + link;

            if (checkLink(link))
                siteMap.add(link);
        });
    }

}
