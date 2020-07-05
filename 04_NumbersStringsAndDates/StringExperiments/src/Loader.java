
public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int sum = 0;

        for (int i = 0; i < text.length(); i++) {
            String salary = "";

            while (Character.isDigit(text.charAt(i))) {
                salary += text.charAt(i);
                i++;
            }

            if (salary.length() > 0)
                sum += Integer.parseInt(salary);
            salary = "";
        }
        System.out.println(sum);
    }
}