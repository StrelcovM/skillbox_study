package defpac;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

public class Storage {

    private RedissonClient client;
    private RScoredSortedSet<String> onlineUsers;
    private final static String KEY = "ONLINE_USERS";

    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            client = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            System.out.println("Не удалось подключиться к Redis");
            System.out.println(Exc.getMessage());
        }
        RKeys rKeys = client.getKeys();
        onlineUsers = client.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    private long getTime() {
        return System.nanoTime();
    }

    public void shutDown() {
        client.shutdown();
    }

    public void singIn(int userId) {
        onlineUsers.add(getTime(), String.valueOf(userId));
    }

    public int popUser() {
        int userId = Integer.parseInt(onlineUsers.pollFirst());
        singIn(userId);
        return userId;
    }

    public void pay(int userId) {
        onlineUsers.add(0, String.valueOf(userId));
    }
}
