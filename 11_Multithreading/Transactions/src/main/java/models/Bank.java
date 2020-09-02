package models;

import java.util.HashMap;
import java.util.Random;

public class Bank {
    private final HashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount) {
        System.out.println("Transfer from: " + fromAccountNum + " to: " +toAccountNum + " amount: " + amount);
        if (getBalance(fromAccountNum) < amount) {
            System.out.println("Недостаточно средств для совершения операции!");
            return;
        }

        if (accounts.get(fromAccountNum).isBlocked() || accounts.get(toAccountNum).isBlocked()) {
            System.out.println("Ошибка перевода!");
            return;
        }

        accounts.get(fromAccountNum).takeOfMoney(amount);
        accounts.get(toAccountNum).depositMoney(amount);


        if (amount > 50.000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accounts.get(fromAccountNum).setBlock();
                    accounts.get(toAccountNum).setBlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }
}
