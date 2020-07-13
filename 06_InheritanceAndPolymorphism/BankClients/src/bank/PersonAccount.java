package bank;

public class PersonAccount extends Client {

    @Override
    public String getInfo() {
        return "withdrawals and deposits is free. Balance: " + getBalance();
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
        super.getMoney(amount);
    }
}
