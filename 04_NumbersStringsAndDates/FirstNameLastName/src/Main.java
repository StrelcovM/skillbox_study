import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        System.out.println("Фамилия: " + name.substring(0, name.indexOf(" ")));
        System.out.println("Имя: " + name.substring(name.indexOf(" ") + 1, name.lastIndexOf(" ")));
        System.out.println("Отчество: " + name.substring(name.lastIndexOf(" ") + 1));
    }
}
