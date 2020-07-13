public class BankAccount {
    double moneyCount;

    public void putMoney(double amount) {
        if (amount > 0)
            moneyCount += amount;
        else
            System.out.println("the amount cannot be negative");
    }

    public void getMoney(double amount) {
        if(moneyCount - amount > 0)
            moneyCount -= amount;
        else
            System.out.println("insufficient funds on the account");
    }

    public double getMoneyCount() {
        return moneyCount;
    }
}
