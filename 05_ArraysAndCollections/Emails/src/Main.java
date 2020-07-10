import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    private static Set<String> emails = new TreeSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String cmd;

        while (true) {
            cmd = sc.nextLine();
            if (cmd.matches("ADD\\s+.+"))
                addEmail(cmd);
            else if (cmd.equals("LIST"))
                for (String email : emails)
                    System.out.println(email);
            else
                System.out.println("Invalid command");
        }

    }

    private static void addEmail(String cmd) {
        String email = cmd.split("\\s+")[1].trim();

        if (email.matches(".+@.+\\..+"))
            emails.add(email);
        else
            System.out.println("Invalid email");
    }

}
