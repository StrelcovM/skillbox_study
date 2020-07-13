package bank;

public abstract class Client {
    protected double balance;

    public abstract String getInfo();

    protected double getBalance() {
        return balance;
    }

    public void putMoney(double amount) {
        if (amount > 0)
            balance += amount;
        else
            System.out.println("the amount cannot be negative");
    }

    public void getMoney(double amount) {
        if (balance - amount > 0)
            balance -= amount;
        else
            System.out.println("insufficient funds on the account");
    }
}
