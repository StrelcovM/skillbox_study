package bank;

public abstract class Client {
    protected double deposit;

    protected abstract void getInfo();

    protected double getDeposit() {
        return deposit;
    }

    protected void putMoney(double amount) {
        if (amount > 0)
            deposit += amount;
        else
            System.out.println("the amount cannot be negative");
    }

    protected void getMoney(double amount) {
        if (deposit - amount > 0)
            deposit -= amount;
        else
            System.out.println("insufficient funds on the account");
    }
}
