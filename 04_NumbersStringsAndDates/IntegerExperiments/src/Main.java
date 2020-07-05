public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        Main main = new Main();
        container.count += 7843;
        System.out.println(main.sumDigits(12345));
        System.out.println(main.sumDigits(10));
        System.out.println(main.sumDigits(5059191));
    }

    public Integer sumDigits(Integer number) {
        //@TODO: write code here
        Integer res = 0;
        String num = number.toString();
        for (int i = 0; i < num.length(); i++) {
            res += Character.getNumericValue(num.charAt(i));
        }
        return res;
    }
}
