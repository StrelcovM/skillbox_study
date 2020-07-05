import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boxCount = scanner.nextInt();
        int containerCount;
        int truckCount;

        if (boxCount % 27 > 0)
            containerCount = (boxCount / 27) + 1;
        else
            containerCount = boxCount / 27;

        if (containerCount % 12 > 0)
            truckCount = (containerCount / 12) + 1;
        else
            truckCount = containerCount / 12;

        int i = 1;
        int j = 1;
        int k = 1;

        while (i <= truckCount) {
            System.out.println("Грузовик " + i + ":");
            i++;
            while (j <= containerCount) {
                System.out.println("\tКонтейнер " + j + ":");
                while (k <= boxCount) {
                    System.out.println("\t\tЯщик " + k);
                    if (k % 27 == 0) {
                        k++;
                        break;
                    }
                    k++;
                }
                if (j % 12 == 0) {
                    j++;
                    break;
                }
                j++;
            }
        }

        System.out.println(truckCount + " " + containerCount + " " + boxCount);
    }
}
