public class Manager implements Employee {
    private Company company;
    private int fixSalary;

    public Manager(Company company, int fixSalary) {
        this.company = company;
        this.fixSalary = fixSalary;
    }

    @Override
    public int getMonthSalary() {
        return fixSalary + (int) Math.round(company.getIncome() * 0.05);
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
