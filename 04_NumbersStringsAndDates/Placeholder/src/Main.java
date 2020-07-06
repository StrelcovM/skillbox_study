import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        System.out.println(searchAndReplaceDiamonds(input, "***"));

    }
    private static String searchAndReplaceDiamonds(String text, String placeholder){
        return text.replaceAll("<.+>", placeholder);
    }
}
