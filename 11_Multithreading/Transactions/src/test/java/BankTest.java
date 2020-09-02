import junit.framework.TestCase;
import models.Account;
import models.Bank;

import java.util.HashMap;
import java.util.Random;

public class BankTest extends TestCase {
    private Bank bank;
    private HashMap<String, Account> accounts = new HashMap<>();
    private Random random = new Random();
    private long sumBeforeTransactions = 0;

    @Override
    public void setUp() {
        for (int i = 0; i < 50; i++) {
            Account account = new Account(String.valueOf(i), (int) (Math.random() * 100001));
            accounts.put(String.valueOf(i), account);

            sumBeforeTransactions += account.getMoney();
        }
        bank = new Bank(accounts);
    }

    public void testTransaction() {

        for (int i = 0; i < 100; i++) {
//            bank.transfer(String.valueOf((int) (Math.random() * 51)),
//                    String.valueOf((int) (Math.random() * 51)),
//                    (int) (Math.random() * 100001));
            Thread thread = new Thread(() -> bank.transfer(String.valueOf((int) (Math.random() * 50)),
                    String.valueOf((int) (Math.random() * 50)),
                    (int) (Math.random() * 100001)));
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long sumAfterTransactions = accounts.values().stream().mapToLong(Account::getMoney).sum();
        assertEquals(sumBeforeTransactions, sumAfterTransactions);
    }
}
