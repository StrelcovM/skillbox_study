import java.io.File;
import java.util.Scanner;

public class Main {
    private static double size;
    private static String path;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file;

        for (; ; ) {
            size = 0;

            System.out.println("Введите путь: ");
            path = sc.nextLine();

            file = new File(path);
            if (!file.exists() || !file.isDirectory()) {
                System.out.println("Folder not found!");
                continue;
            }

            calculateFolderSize(file);
            printCurrentSize(size);
        }
    }

    private static void calculateFolderSize(File file) {
        File[] listFiles = file.listFiles();

        if (listFiles.length == 0)
            return;


        for (File element : listFiles) {
            if (element.isDirectory())
                calculateFolderSize(element);
            else {
                size += element.length();
            }
        }
    }

    private static void printCurrentSize(double size) {
        if (size >= 0 && size < 1024)
            System.out.println("Размер папки " +  path + " cоставляет " + size + " байт");
        else if (size < 1024*1024)
            System.out.println("Размер папки " +  path + " cоставляет " + size/1024 + " кбайт");
        else if (size < 1024*1024*1024)
            System.out.println("Размер папки " +  path + " cоставляет " + (size/1024)/1024 + " Мбайт");
        else
            System.out.println("Размер папки " +  path + " cоставляет " + ((size/1024)/1024)/1024 + " Гбайт");
    }
}
