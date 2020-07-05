import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        String[] words = text.split("\\W");

        for (String s : words) {
            if (!s.equals(""))
                System.out.println(s);
        }
    }
}
