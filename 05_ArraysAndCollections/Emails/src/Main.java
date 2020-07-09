import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static HashSet<String> emails = new HashSet<>();

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
