import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String srcFolder = "C:\\Users\\Maxim\\Desktop\\img";
        String dstFolder = "C:\\Users\\Maxim\\Desktop\\dstImg";
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
        List<List<File>> images = getArraysOfImages(srcFolder, coreCount);

        for (int i = 0; i < coreCount; i++)
            executorService.submit(new ImgResizer(images.get(i), dstFolder));

        long start = System.currentTimeMillis();

        executorService.shutdown();

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

    private static List<List<File>> getArraysOfImages(String srcFolder, int arraysCount) {
        File[] allImages = new File(srcFolder).listFiles();
        List<List<File>> result = new ArrayList<>();
        int counter = 0;

        if (allImages == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < arraysCount; i++)
            result.add(new ArrayList<File>());

        while (counter < allImages.length)
            for (int i = 0; i < arraysCount; i++) {
                if (counter == allImages.length)
                    break;
                result.get(i).add(allImages[counter++]);
            }

        return result;
    }
}