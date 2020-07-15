import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static String staffFile = "C:\\Users\\Maxim\\Desktop\\hw_skillbox\\gitlab\\java_basics\\07_AdvancedOOPFeatures\\LambdaExpressions\\data\\staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        staff.sort((o1, o2) -> {
            if (o2.getSalary() < o1.getSalary() )
                    return 1;
            if (o2.getSalary() > o1.getSalary())
                return -1;
            if (o1.getSalary().equals(o2.getSalary()))
                return o1.getName().compareTo(o2.getName());

            return 0;

        });

        for (Employee employee : staff)
            System.out.println(employee);
    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return staff;
    }
}