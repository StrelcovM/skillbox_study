package models;

public class Account {
    private volatile long money;
    private final String accNumber;
    private boolean isBlocked;

    public Account(String accNumber, long money) {
        this.money = money;
        this.accNumber = accNumber;
        isBlocked = false;
    }

    public long getMoney() {
        return money;
    }

    public void takeOfMoney(long amount) {
        money -= amount;
    }

    public void depositMoney(long amount) {
        money += amount;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlock() {
        isBlocked = true;
    }
}
