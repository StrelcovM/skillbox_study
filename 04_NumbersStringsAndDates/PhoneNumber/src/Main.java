import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringBuilder telNumber = new StringBuilder(
                new Scanner(System.in).nextLine().replaceAll("\\D", ""));

        if (telNumber.length() > 11 || telNumber.length() < 10) {
            System.out.println("Неверный формат номера");
            return;
        }
        if (telNumber.length() == 10)
            System.out.println('7' + telNumber.toString());
        else {
            if (telNumber.toString().charAt(0) == '7')
                System.out.println(telNumber);
            else if (telNumber.toString().charAt(0) == '8')
                System.out.println(telNumber.toString().replaceFirst("8", "7"));
            else
                System.out.println("Неверный формат номера");
        }
    }
}
