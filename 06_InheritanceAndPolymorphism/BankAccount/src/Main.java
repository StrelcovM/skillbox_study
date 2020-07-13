public class Main {
    public static void main(String[] args) {
        DepositAccount depositAccount = new DepositAccount(5000);
        depositAccount.putMoney(200.0);
        depositAccount.getMoney(200.0);

    }
}
