import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final String site = "https://secure-headland-59304.herokuapp.com/";
    private static final Set<String> siteMap = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.invoke(new LinkExtractor(site, siteMap));

        siteMap.forEach(System.out:: println);

//        siteMap.forEach(link -> {
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(link.substring(site.length()));
//            System.out.println(stringBuilder.toString());
//        });
    }
}