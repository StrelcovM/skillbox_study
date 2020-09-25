package defpac;

public class RedisTest {
    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        storage.init();

        for (int i = 1; i <= 20; i++) {
            storage.singIn(i);
            Thread.sleep(200);
        }

        while (true) {
            if ((int) (Math.random() * 10) == 1)
                storage.pay((int) (Math.random() * 20) + 1);
            System.out.println(storage.popUser());
            Thread.sleep(900);
        }
    }
}
