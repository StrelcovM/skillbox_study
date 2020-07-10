import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    private static Map<String, String> phoneBook = new TreeMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            String cmd = sc.nextLine();

            if (cmd.equals("LIST")) {
                for (String number : phoneBook.keySet())
                    System.out.println(number + " " + phoneBook.get(number));
            } else
                checkContact(cmd);
        }
    }

    private static void checkContact(String cmd) {
        boolean containsNumber = false;

        if (cmd.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3,4}\\)?[\\- ]?)?[\\d\\- ]{5,10}$")) {
            Set<String> names = phoneBook.keySet();

            for (String name : names)
                if (phoneBook.get(name).equals(cmd)) {
                    System.out.println(name + " " + cmd);
                    containsNumber = true;
                }

            if (!containsNumber) {
                System.out.print("Enter name: ");
                phoneBook.put(sc.nextLine(), cmd);
            }
        } else
//        if (cmd.matches("\\D+"))
        {
            if (phoneBook.containsKey(cmd))
                System.out.println(cmd + " " + phoneBook.get(cmd));
            else {
                System.out.print("Enter number: ");
                phoneBook.put(cmd, sc.nextLine());
            }
        }
    }
}
