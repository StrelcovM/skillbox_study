import java.util.Date;

public class DepositAccount extends BankAccount {
    public Date lastDeposit;

    public DepositAccount(double amount) {
        if (amount > 0)
            this.moneyCount = amount;
        else
            System.out.println("the Deposit cannot be empty");
    }

    @Override
    public void putMoney(double amount) {
        super.putMoney(amount);
        lastDeposit = new Date();
    }

    @Override
    public void getMoney(double amount) {
        if (new Date().getTime() - lastDeposit.getTime() < 2592000000L)
            System.out.println("it is not possible to withdraw money from the Deposit");
        else
            super.getMoney(amount);
    }

    @Override
    public double getMoneyCount() {
        return super.getMoneyCount();
    }
}
