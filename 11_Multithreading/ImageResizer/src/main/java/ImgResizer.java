import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImgResizer implements Runnable{
    private final List<File> images;
    private final String dstFolder;

    public ImgResizer(List<File> images, String dstFolder) {
        this.images = images;
        this.dstFolder = dstFolder;
    }

    public void run() {
        for (File image : images) {
            try {
                BufferedImage newImg = Scalr.resize(ImageIO.read(image), 300);
                File newFile = new File(dstFolder + "/" + image.getName());
                ImageIO.write(newImg, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
