import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final String site = "https://skillbox.ru";
    private static final Set<String> siteMap = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.invoke(new LinkExtractor(site, siteMap));

    }
}