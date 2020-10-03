package main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    private static final MongoDatabase MONGO_DATABASE =
            new MongoClient("127.0.0.1", 27017).getDatabase("local");
    private static final MongoCollection<Document> PRODUCTS = MONGO_DATABASE.getCollection("products");
    private static final MongoCollection<Document> SHOPS = MONGO_DATABASE.getCollection("shops");

    static {
        PRODUCTS.drop();
        SHOPS.drop();
        SHOPS.insertOne(new Document(Map.of("name", "магазин_1", "prod", "[вафли]")));
        PRODUCTS.insertOne(new Document(Map.of("name", "вафли", "price", "50")));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            SHOPS.find().forEach((Consumer<Document>) System.out::println);
            PRODUCTS.find().forEach((Consumer<Document>) System.out::println);
            var cmd = reader.readLine();
            if (cmd.startsWith("ДОБАВИТЬ_ТОВАР"))
                addProduct(cmd);
            if (cmd.startsWith("ДОБАВИТЬ_МАГАЗИН"))
                addShop(cmd);
            if (cmd.startsWith("ВЫСТАВИТЬ_ТОВАР"))
                addProductInShop(cmd);
            if (cmd.startsWith("СТАТИСТИКА_ТОВАРОВ"))
                statistic();
        }
    }

    private static void addProduct(String cmd) {
        String[] data = cmd.split(" ");
        if (data.length != 3)
            throw new RuntimeException("invalid cmd");
        PRODUCTS.updateOne(
                new Document("name", data[1]),
                new Document("$set", new Document("price", data[2])),
                new UpdateOptions().upsert(true)
        );
    }

    private static void addShop(String cmd) {
        String[] data = cmd.split(" ");
        if (data.length != 2)
            throw new RuntimeException("invalid cmd");
        SHOPS.updateOne(new Document(Map.of("name", data[1])),
                new Document("$set", new Document("prod", "[]")),
                new UpdateOptions().upsert(true));
    }

    private static void addProductInShop(String cmd) {
        String[] data = cmd.split(" ");
    }

    private static String statistic() {
        return null;
    }
}
