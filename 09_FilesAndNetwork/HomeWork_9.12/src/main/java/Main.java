import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Elements elements;
    private static final Map<String, String> images = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try {
            Document document = Jsoup.connect("https://lenta.ru/").maxBodySize(0).get();
            elements = document.select("img");
        } catch (Exception e) {
            e.printStackTrace();
        }

        elements.forEach(element -> {
            String name = element.attr("src")
                    .substring(element.attr("src")
                            .lastIndexOf("/") + 1)
                    .replaceAll("\\?", "");

            images.put(name, element.attr("abs:src"));
        });
        savePic(images);
        images.forEach((key, value) -> System.out.println(key));
    }

    public static void savePic(Map<String, String> images) throws IOException {
        for (Map.Entry<String, String> entry : images.entrySet()) {
            URL url = new URL(entry.getValue());
            URLConnection connection = url.openConnection();

            try (InputStream is = connection.getInputStream();
                 OutputStream os =
                         new FileOutputStream("09_FilesAndNetwork/HomeWork_9.12/images/"
                                 + entry.getKey())) {
                is.transferTo(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
