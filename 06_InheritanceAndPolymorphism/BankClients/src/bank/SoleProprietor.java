package bank;

public class SoleProprietor extends Client {

    @Override
    protected void getInfo() {

    }

    @Override
    public double getDeposit() {
        return super.getDeposit();
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
