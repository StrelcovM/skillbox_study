public class Operator implements Employee {
    private Company company;
    private int fixSalary;

    public Operator(Company company, int fixSalary) {
        this.company = company;
        this.fixSalary = fixSalary;
    }

    @Override
    public int getMonthSalary() {
        return fixSalary;
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
