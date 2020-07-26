import java.io.*;
import java.util.Scanner;

public class Main {
    private static String pathFrom;
    private static String pathTo;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        for (; ; ) {
            System.out.println("Папка для копирования: ");
            pathFrom = sc.nextLine();

            File folderFrom = new File(pathFrom);

            if (!folderFrom.exists() || folderFrom.list().length == 0) {
                System.out.println("Папка пуста или не существует!");
                continue;
            }

            System.out.println("Папка назначения: ");
            pathTo = sc.nextLine();

            File folderTo = new File(pathTo);

            copyFolder(folderFrom, folderTo);

        }
    }

    public static void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            if (!dest.exists()) {
                dest.mkdir();
            }

            String[] files = src.list();

            for (String file : files) {

                File srcFile = new File(src, file);
                File destFile = new File(dest, file);

                copyFolder(srcFile, destFile);
            }

        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }
}
