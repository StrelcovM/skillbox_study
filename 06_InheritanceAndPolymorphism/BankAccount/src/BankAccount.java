public class BankAccount {
    protected double moneyCount;

    protected void putMoney(double amount) {
        if (amount > 0)
            moneyCount += amount;
        else
            System.out.println("the amount cannot be negative");
    }

    protected void getMoney(double amount) {
        if(moneyCount - amount > 0)
            moneyCount -= amount;
        else
            System.out.println("insufficient funds on the account");
    }

    protected double getMoneyCount() {
        return moneyCount;
    }
}
