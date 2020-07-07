import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int sum = 0;

        sum += Integer.parseInt(text.substring(text.indexOf("5"), text.indexOf("5") + 4));
        sum += Integer.parseInt(text.substring(text.lastIndexOf("3"), text.lastIndexOf("3") + 5));
        System.out.println(sum);
    }
}