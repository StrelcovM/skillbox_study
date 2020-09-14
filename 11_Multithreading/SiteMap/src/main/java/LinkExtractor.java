import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

public class LinkExtractor extends RecursiveTask<CopyOnWriteArraySet<String>> {
    private static final long serialVersionUID = -4221520778777621224L;
    private static String startUrl = "";
    private final String link;
    private final static CopyOnWriteArraySet<String> mapSite = new CopyOnWriteArraySet<>();

    public LinkExtractor(String url, String link) {
        this.link = link;
        startUrl = url;
    }

    private LinkExtractor(String link) {
        this.link = link;
    }

    @Override
    protected CopyOnWriteArraySet<String> compute() {
        List<LinkExtractor> extractors = getChildren();

        if (!extractors.isEmpty())
            for (LinkExtractor extractor : extractors)
                extractor.join();
        return mapSite;
    }

    private List<LinkExtractor> getChildren() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Document document = null;
        Elements elements;
        List<LinkExtractor> extractors = new ArrayList<>();

        try {
            document = Jsoup.connect(link).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document != null) {
            elements = document.getElementsByTag("a");

            elements.forEach(element -> {
                String currentLink = element.absUrl("href");

                if (currentLink.startsWith(startUrl) && !currentLink.contains("#") && !mapSite.contains(currentLink))
                    if (currentLink.endsWith("/")) {
                        mapSite.add(currentLink);
                        LinkExtractor extractor = new LinkExtractor(currentLink);
                        extractor.fork();
                        extractors.add(extractor);
                    } else mapSite.add(currentLink);
            });
        }
        return extractors;
    }
}