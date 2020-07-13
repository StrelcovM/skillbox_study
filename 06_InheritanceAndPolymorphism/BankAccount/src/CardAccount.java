public class CardAccount extends BankAccount {
    @Override
    public void putMoney(double amount) {
        super.putMoney(amount);
    }

    @Override
    public void getMoney(double amount) {
        super.getMoney(amount + (amount / 100));
    }

    @Override
    public double getMoneyCount() {
        return super.getMoneyCount();
    }
}
