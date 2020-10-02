package main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final MongoDatabase MONGO_DATABASE =
            new MongoClient("127.0.0.1", 27017).getDatabase("local");

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            var cmd = reader.readLine();
            if(cmd.startsWith("ДОБАВИТЬ_ТОВАР"))
                addProduct(cmd);
            if(cmd.startsWith("ДОБАВИТЬ_МАГАЗИН"))
                addShop(cmd);
            if(cmd.startsWith("ВЫСТАВИТЬ_ТОВАР"))
                addProductInShop(cmd);
            if(cmd.startsWith("СТАТИСТИКА_ТОВАРОВ"))
                statistic();
        }
    }

    private static void addProduct(String cmd) {
        String[] data = cmd.split(" ");
    }

    private static void addShop(String cmd) {
        String[] data = cmd.split(" ");
    }

    private static void addProductInShop(String cmd) {
        String[] data = cmd.split(" ");
    }

    private static String statistic() {
        return null;
    }
}
