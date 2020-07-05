import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int sum = 0;
        Pattern p = Pattern.compile("\\d+\\D");
        Matcher m = p.matcher(text);

        while (m.find())
            sum += Integer.parseInt(text.substring(m.start(), m.end() - 1));
        System.out.println(sum);
    }
}