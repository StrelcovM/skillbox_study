import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        String[] text = name.split(" ");

        System.out.println("Фамилия: " + text[0]);
        System.out.println("Имя: " + text[1]);
        System.out.println("Отчество: " + text[2]);
    }
}
