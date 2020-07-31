import model.Transaction;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "C://Users/Maxim/Desktop/hw_skillbox/gitlab/java_basics/out/production/HomeWork_9.9/movementList.csv";
        List<Transaction> transactions = parseTransaction(path);

        System.out.println("Сумма расходов: " + getAllExpenditure(transactions));
        System.out.println("Сумма доходов: " + getAllIncome(transactions));

        System.out.println("Сумма расходов по организациям: ");
        for (Map.Entry<String, Double> entry : getExpenditureList(transactions).entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

    private static List<Transaction> parseTransaction(String path) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(path));
        lines.remove(lines.get(0));

        for (String line : lines) {

            String[] fields = line.split(",");

            for (int i = 0; i < fields.length; i++) {
                if (fields[i].contains("\"")) {
                    fields[i] += "." + fields[i + 1];
                    fields[i] = fields[i].replaceAll("\"", "");
                    fields[i + 1] = "";
                }
            }

            Transaction transaction = new Transaction();

            transaction.setAccountType(fields[0]);
            transaction.setAccountNumber(new BigInteger(fields[1]));
            transaction.setAccountCurrency(fields[2]);
            transaction.setOperationDate(fields[3]);
            transaction.setTransactionReference(fields[4]);
            transaction.setOperationDescription(fields[5]);
            transaction.setIncome(Double.parseDouble(fields[6]));
            transaction.setExpenditure(Double.parseDouble(fields[7]));

            transactions.add(transaction);
        }
        return transactions;
    }

    private static double getAllExpenditure(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getExpenditure() > 0)
                .mapToDouble(Transaction::getExpenditure)
                .sum();
    }

    private static double getAllIncome(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getIncome() > 0)
                .mapToDouble(Transaction::getIncome)
                .sum();
    }

    private static Map<String, Double> getExpenditureList(List<Transaction> transactions) {
        Map<String, Double> result = new HashMap<>();

        for (Transaction transaction : transactions) {
            String description = transaction.getOperationDescription();
            description = description.substring(description.indexOf(" "), description.indexOf('.') - 2)
                    .trim();

            if (result.containsKey(description))
                result.put(description, result.get(description) + transaction.getExpenditure());
            else
                result.put(description, transaction.getExpenditure());
        }
        return result;
    }
}
