import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DepositAccount extends BankAccount {
    private Calendar lastDeposit;

    public DepositAccount(double amount) {
        if (amount > 0)
            this.moneyCount = amount;
        else
            System.out.println("the Deposit cannot be empty");
    }

    @Override
    public void putMoney(double amount) {
        super.putMoney(amount);
        lastDeposit.setTime(new Date());
//        lastDeposit = new Date();
    }

    @Override
    public void getMoney(double amount) {
//        if (new Date().getTime() - lastDeposit.getTime() < 2592000000L)
        lastDeposit.add(Calendar.MONTH, 1);

        if (lastDeposit.getTime().before(new Date()))
            System.out.println("it is not possible to withdraw money from the Deposit");
        else
            super.getMoney(amount);
    }

    @Override
    public double getMoneyCount() {
        return super.getMoneyCount();
    }
}
