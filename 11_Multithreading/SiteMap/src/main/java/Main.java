import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final String site = "https://secure-headland-59304.herokuapp.com/";

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        CopyOnWriteArraySet<String> siteMap = fjp.invoke(new LinkExtractor(site, site));

        siteMap.forEach(System.out::println);

    }
}