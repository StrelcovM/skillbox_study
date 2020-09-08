import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveAction;

public class LinkExtractor extends RecursiveAction {
    private static final long serialVersionUID = -4221520778777621224L;
    private final String link;
    private final Set<String> mapSite;

    public LinkExtractor(String link, Set<String> mapSite) {
        this.mapSite = mapSite;
        this.link = link;
    }

    @Override
    protected void compute() {

        List<LinkExtractor> extractors = getChildren();
        if (!extractors.isEmpty())
            for (LinkExtractor extractor : extractors)
                extractor.join();
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

                if (currentLink.startsWith(link) && !currentLink.contains("#") && !mapSite.contains(currentLink)) {
                    if (currentLink.endsWith("/")) {
                        mapSite.add(currentLink);
                        LinkExtractor extractor = new LinkExtractor(currentLink, mapSite);
                        extractor.fork();
                        extractors.add(extractor);
                    } else mapSite.add(currentLink);
                }
            });
        }
        return extractors;
    }
}