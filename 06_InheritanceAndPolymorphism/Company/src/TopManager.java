public class TopManager implements Employee {
    private Company company;
    private int fixSalary;

    public TopManager(Company company, int fixSalary) {
        this.company = company;
        this.fixSalary = fixSalary;
    }

    @Override
    public int getMonthSalary() {
        if (company.getIncome() > 10_000_000)
            return fixSalary + (int) Math.round(company.getIncome() * 1.5);
        else
            return fixSalary;
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
