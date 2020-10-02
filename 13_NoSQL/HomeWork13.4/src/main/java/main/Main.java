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
            switch (reader.readLine()) {
                case () :
            }
        }
    }
}
