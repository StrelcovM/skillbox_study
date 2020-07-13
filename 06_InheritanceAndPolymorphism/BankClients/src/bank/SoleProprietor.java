package bank;

public class SoleProprietor extends Client {

    @Override
    public String getInfo() {
        return "Deposits up to one thousand Commission 1%. " +
                "Deposits over to one thousand Commission 0,5%. " +
                "Withdrawals is free. Balance: "
                + getBalance();
    }

    @Override
    protected double getBalance() {
        return super.getBalance();
    }

    @Override
    public void putMoney(double amount) {
        if (amount < 1000)
            super.putMoney(amount - (amount / 100));
        else
            super.putMoney(amount - (amount / 200));
    }

    @Override
    public void getMoney(double amount) {
        super.getMoney(amount);
    }
}
