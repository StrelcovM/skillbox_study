package main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> students = Files.readAllLines(Paths.get("13_NoSQL/MongoStudents/src/main/resources/mongo.csv"));

        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

        MongoDatabase database = mongoClient.getDatabase("local");

        MongoCollection<Document> collection = database.getCollection("skillbox");

        collection.drop();

        students.forEach(student -> {
            String[] data = student.split(",");
            Document document = new Document()
                    .append("name", data[0])
                    .append("age", data[1]);
            StringBuilder builder = new StringBuilder();
            for (int i = 2; i < data.length; i++)
                builder.append(data[i]).append(" ");
            document.append("courses", builder.toString().replaceAll("\"", "").trim());
            collection.insertOne(document);
        });
        //1
        System.out.println(collection.countDocuments());
        //2
        BsonDocument query = BsonDocument.parse("{age: {$gt: '40'}}");
        System.out.println(collection.countDocuments(query));
        //3
        query = BsonDocument.parse("{age: 1}");
        collection
                .find()
                .sort(query)
                .limit(1)
                .forEach((Consumer<Document>) document -> System.out.println(document.get("name")));
        //4
        query = BsonDocument.parse("{age: -1}");
        collection
                .find()
                .sort(query)
                .limit(1)
                .forEach((Consumer<Document>) document -> System.out.println(document.get("courses")));
    }
}
