import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<String> todoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String cmd;

        while (true) {
            cmd = scanner.nextLine();

            if (cmd.matches("ADD.+"))
                addCase(cmd);
            else if (cmd.matches("DELETE.+"))
                deleteCase(cmd);
            else if (cmd.matches("EDIT.+"))
                editCase(cmd);
            else if (cmd.matches("LIST"))
                for (int i = 0; i < todoList.size(); i++)
                    System.out.println(i + " " + todoList.get(i));
            else
                System.out.println("Неверная команда");
        }
    }

    private static void addCase(String cmd) {
        String[] cmdParts = cmd.split("\\s+");

        if (cmdParts[1].trim().matches("\\d+")) {
            int numberCase = Integer.parseInt(cmdParts[1]);

            if (numberCase >= todoList.size())
                todoList.add(cmd.substring(cmd.indexOf(cmdParts[2])).trim());
            else
                todoList.add(numberCase, cmd.substring(cmd.indexOf(cmdParts[2])).trim());
        } else
            todoList.add(cmd.substring(cmd.indexOf(cmdParts[1])));
    }

    private static void deleteCase(String cmd) {
        String[] cmdParts = cmd.split("\\s+");

        if (cmdParts[1].trim().matches("\\d+")) {
            if (todoList.size() <= Integer.parseInt(cmdParts[1].trim()))
                return;
            else
                todoList.remove(Integer.parseInt(cmdParts[1].trim()));
        }
    }

    private static void editCase(String cmd) {
        String[] cmdParts = cmd.split("\\s+");

        if (Integer.parseInt(cmdParts[1].trim()) >= todoList.size())
            return;
        else
            todoList.set(Integer.parseInt(cmdParts[1].trim()), cmd.substring(cmd.indexOf(cmdParts[2])));
    }
}
