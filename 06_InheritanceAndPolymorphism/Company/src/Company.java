import java.util.ArrayList;
import java.util.List;

public class Company {
    List<Employee> employees = new ArrayList<>();
    private long income;

    public long getIncome() {
        return income;
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {
        if (!employees.isEmpty())
            employees.remove(employee);
    }
}
