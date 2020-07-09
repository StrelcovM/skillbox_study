import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    private static Map<Integer, String> phoneBook = new TreeMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            String cmd = sc.nextLine();

            if (cmd.equals("LIST")) {
                for (Integer number : phoneBook.keySet())
                    System.out.println(number + " " + phoneBook.get(number));
            } else
                checkContact(cmd);
        }
    }

    private static void checkContact(String cmd) {
        boolean containsName = false;

        if (cmd.matches("\\d+")) {
            if (phoneBook.containsKey(Integer.parseInt(cmd)))
                System.out.println(phoneBook.get(Integer.parseInt(cmd)) + " " + cmd);
            else {
                System.out.print("Enter name: ");
                phoneBook.put(Integer.parseInt(cmd), sc.nextLine());
            }
        }

        if (cmd.matches("\\D+")) {
            Set<Integer> numbers = phoneBook.keySet();

            for (Integer number : numbers)
                if (phoneBook.get(number).equals(cmd)) {
                    System.out.println(cmd + " " + number);
                    containsName = true;
                }

            if(!containsName) {
                System.out.print("Enter number: ");
                phoneBook.put(sc.nextInt(), cmd);
            }
        }
    }
}
