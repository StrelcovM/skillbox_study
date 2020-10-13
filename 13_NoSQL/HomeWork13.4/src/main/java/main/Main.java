package main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    private static final MongoDatabase MONGO_DATABASE =
            new MongoClient("127.0.0.1", 27017)
                    .getDatabase("local");
    private static final MongoCollection<Document> PRODUCTS = MONGO_DATABASE.getCollection("products");
    private static final MongoCollection<Document> SHOPS = MONGO_DATABASE.getCollection("shops");

    static {
        PRODUCTS.drop();
        SHOPS.drop();
        SHOPS.insertOne(new Document(Map.of("name", "магазин_1", "prod", Collections.singletonList("молоко"))));
        SHOPS.insertOne(new Document(Map.of("name", "магазин_2", "prod", Arrays.asList("хлеб", "молоко"))));
        SHOPS.insertOne(new Document(Map.of("name", "магазин_3", "prod", Arrays.asList("вафли", "хлеб", "молоко"))));
        PRODUCTS.insertOne(new Document(Map.of("name", "вафли", "price", 50)));
        PRODUCTS.insertOne(new Document(Map.of("name", "молоко", "price", 20)));
        PRODUCTS.insertOne(new Document(Map.of("name", "хлеб", "price", 10)));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            var cmd = reader.readLine();
            if (cmd.startsWith("ДОБАВИТЬ_ТОВАР"))
                addProduct(cmd);
            if (cmd.startsWith("ДОБАВИТЬ_МАГАЗИН"))
                addShop(cmd);
            if (cmd.startsWith("ВЫСТАВИТЬ_ТОВАР"))
                addProductInShop(cmd);
            if (cmd.startsWith("1"))
                System.out.println(statistic());
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
        SHOPS.updateOne(
                new Document("name", data[1]),
                new Document("$set", new Document("prod", Collections.emptyList())),
                new UpdateOptions().upsert(true)
        );
    }

    private static void addProductInShop(String cmd) {
        String[] data = cmd.split(" ");
        if (data.length != 3)
            throw new RuntimeException("invalid cmd");
        SHOPS.findOneAndUpdate(
                new Document("name", data[1]),
                new Document("$push", new Document("prod", data[2]))
        );
    }

    private static String statistic() {
        StringBuilder builder = new StringBuilder();
        BsonDocument query2 = BsonDocument
                .parse("{$lookup: {from: \"products\"," +
                        "localField: \"prod\"," +
                        "foreignField: \"name\"," +
                        " as: \"prods\"}}");

        BsonDocument query = BsonDocument.parse("{ $unwind: \"$prods\"}");
        BsonDocument query1 = BsonDocument
                .parse("{ $group: {_id: \"$name\"," +
                        "products_count: {$sum :1}," +
                        "avg_products_price: {$avg:\"$prods.price\"}," +
                        "min_price: {$min:\"$prods.price\"}," +
                        "max_price: {$max:\"$prods.price\"}," +
                        "count_less_100: {$lt:[\"$prods.price\", 100]} " +
                        "}}");

        SHOPS.aggregate(Arrays.asList(query2, query, query1)).forEach((Consumer<Document>) doc -> {
            builder.append(doc);
            builder.append("\n\n");
        });

        return builder.toString();
    }
}