package bank;

public class OrganizationAccount extends Client{

    @Override
    public String getInfo() {
        return "Withdrawals Commission 1%. " +
                "Deposits is free. " +
                "Balance: " + getBalance();
    }

    @Override
    protected double getBalance() {
        return super.getBalance();
    }

    @Override
    public void putMoney(double amount) {
        super.putMoney(amount);
    }

    @Override
    public void getMoney(double amount) {
        super.getMoney(amount + (amount / 100));
    }
}
