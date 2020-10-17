public class Loader {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Writer thread_1 = new Writer(199, "14_Performance/CarNumberGenerator/res/numbers_1.txt");
        Writer thread_2 = new Writer(199, "14_Performance/CarNumberGenerator/res/numbers_2.txt");

        thread_1.start();
        thread_2.start();

        thread_1.join();
        thread_2.join();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
