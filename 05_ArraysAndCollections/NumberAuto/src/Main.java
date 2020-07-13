import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> numbersArray = arrayListGenerate();
        List<String> sortedNumbersArrayList = arrayListGenerate();
        Collections.sort(sortedNumbersArrayList);
        HashSet<String> numbersHashSet = new HashSet<>(numbersArray);
        TreeSet<String> numbersTreeSet = new TreeSet<>(numbersArray);
        String number = new Scanner(System.in).nextLine();
        boolean flag = false;

        long start = System.nanoTime();
        for (String s : numbersArray)
            if (s.equals(number)) {
                flag = true;
                break;
            }
        long time = System.nanoTime() - start;

        System.out.println("Поиск перебором: номер " + (flag ? "найден " : "не найден ") +
                "поиск занял: " + time + " нс");

        flag = false;

        start = System.nanoTime();
        if (Collections.binarySearch(sortedNumbersArrayList, number) != -1)
            flag = true;
        time = System.nanoTime() - start;

        System.out.println("Бинарный поиск: номер " + (flag ? "найден " : "не найден ") +
                "поиск занял: " + time + " нс");

        flag = false;

        start = System.nanoTime();
        if (numbersHashSet.contains(number))
            flag = true;
        time = System.nanoTime() - start;

        System.out.println("Поиск в HashSet: номер " + (flag ? "найден " : "не найден ") +
                "поиск занял: " + time + " нс");

        flag = false;

        start = System.nanoTime();
        if (numbersTreeSet.contains(number))
            flag = true;
        time = System.nanoTime() - start;

        System.out.println("Поиск в TreeSet: номер " + (flag ? "найден " : "не найден ") +
                "поиск занял: " + time + " нс");
    }


    private static ArrayList<String> arrayListGenerate() {
        char[] charArray = new char[]{'С', 'М', 'Т', 'В', 'А', 'Р', 'О', 'Н', 'Е', 'У', 'Х', 'К'};
        ArrayList<String> numbers = new ArrayList<>();

        for (char c : charArray) {
            for (int j = 0; j <= 999; j += 111) {
                for (int k = 1; k <= 197; k++) {
                    numbers.add(String.format("%c%03d%c%c%02d",
                            c, j, c, c, k));
                }
            }
        }
        return numbers;
    }
}
