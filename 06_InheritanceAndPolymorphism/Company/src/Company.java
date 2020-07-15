import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {
    List<Employee> employees = new ArrayList<>();
    private final int income;

    public Company(int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    private List<Employee> getTopSalaryStaff(int count) {
        if (count < 0 || count > employees.size())
            return Collections.emptyList();
        else {
            employees.sort(new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return Integer.compare(o2.getMonthSalary(), o1.getMonthSalary());
                }
            });

            List<Employee> result = new ArrayList<>();
            for (int i = 0; i < count; i++)
                result.add(employees.get(i));

            return result;

        }
    }

    private List<Employee> getLowestSalaryStaff(int count) {
        if (count < 0 || count > employees.size())
            return Collections.emptyList();
        else {
            employees.sort(new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return Integer.compare(o1.getMonthSalary(), o2.getMonthSalary());
                }
            });

            List<Employee> result = new ArrayList<>();
            for (int i = 0; i < count; i++)
                result.add(employees.get(i));

            return result;

        }
    }

    public void fire(Employee employee) {
        if (!employees.isEmpty())
            employees.remove(employee);
    }

    public static void main(String[] args) {
        int income = (int) (Math.random() * 26000 + 115000);
        int fixSalaryManager = (int) (Math.random() * 20000 + 30000);
        int fixSalaryOperator = (int) (Math.random() * 2000 + 50000);
        int fixSalaryTopManager = (int) (Math.random() * 50000 + 60000);
        List<Employee> operators = new ArrayList<>();

        Company company = new Company(income);

        for (int i = 0; i < 180; i++)
            operators.add(new Operator(company, fixSalaryOperator));
        company.hireAll(operators);

        for (int i = 0; i < 80; i++)
            company.hire(new Manager(company, fixSalaryManager));

        for (int i = 0; i < 10; i++)
            company.hire(new TopManager(company, fixSalaryTopManager));

        for (Employee employee : company.getTopSalaryStaff(10))
            System.out.println(employee);

        System.out.println();

        for (Employee employee : company.getLowestSalaryStaff(30))
            System.out.println(employee);

        for (int i = 0; i < company.employees.size(); i++)
            if (i % 2 == 0)
                company.fire(company.employees.get(i));

        System.out.println("Зарплаты после сокращения: ");

        for (Employee employee : company.getTopSalaryStaff(10))
            System.out.println(employee);

        System.out.println();

        for (Employee employee : company.getLowestSalaryStaff(30))
            System.out.println(employee);
    }
}
