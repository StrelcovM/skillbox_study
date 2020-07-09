import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    private static Map<String, Integer> phoneBook = new TreeMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            String cmd = sc.nextLine();

            if (cmd.equals("LIST")) {
                for (String name : phoneBook.keySet())
                    System.out.println(name + " " + phoneBook.get(name));
            } else
                checkContact(cmd);
        }
    }

    private static void checkContact(String cmd) {
        if (cmd.matches("\\d+")) {
            Set<String> names = phoneBook.keySet();

            for (String name : names)
                if (phoneBook.get(name) == Integer.parseInt(cmd)) {
                    System.out.println(name + " " + cmd);
                    return;
                }
            System.out.print("Enter name: ");
            phoneBook.put(sc.nextLine(), Integer.parseInt(cmd));
        }

        if (cmd.matches("\\D+")) {
            if (phoneBook.containsKey(cmd))
                System.out.println(cmd + " " + phoneBook.get(cmd));
            else {
                System.out.print("Enter phone number: ");
                phoneBook.put(cmd, sc.nextInt());
            }
        }
    }
}
