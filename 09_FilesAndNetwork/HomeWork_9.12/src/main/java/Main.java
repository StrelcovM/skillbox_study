import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static Elements elements;
    private static Map<String, String> images = new HashMap<>();

    public static void main(String[] args) throws IOException {
        try {
            Document document = Jsoup.connect("https://lenta.ru/").get();
            elements = document.select("img");
        } catch (Exception e) {
            e.printStackTrace();
        }

        AtomicInteger nonameCount = new AtomicInteger(1);

        elements.forEach(element -> {
            if (element.attr("alt").equals("") || !element.hasAttr("alt")) {
                images.put(String.valueOf(nonameCount.getAndIncrement()), element.attr("abs:src"));
            } else
                images.put(element.attr("alt"), element.attr("abs:src"));
        });
        savePic(images);
        images.forEach((key, value) -> System.out.println(key));
    }

    public static void savePic(Map<String, String> images) throws IOException {
        InputStream is = null;
        OutputStream os = null;

        for (Map.Entry<String, String> entry : images.entrySet()) {
            try {
                URL url = new URL(entry.getValue());
                URLConnection connection = url.openConnection();

                is = connection.getInputStream();
                os = new FileOutputStream("09_FilesAndNetwork/HomeWork_9.12/images/"
                        + entry.getKey() + ".jpg");

                int b;

                while ((b = is.read()) != -1) {
                    os.write(b);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                os.close();
                is.close();
            }
        }
    }
}
