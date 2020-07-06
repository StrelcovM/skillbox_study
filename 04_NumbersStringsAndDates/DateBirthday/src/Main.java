import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        int day = 31;
        int month = 12;
        int year = 1990;

        Calendar calendar = Calendar.getInstance();
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTimeInMillis(System.currentTimeMillis());
        calendar.set(year, month - 1, day);

        int age = currentTime.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy - E", Locale.ENGLISH);

        for (int i = 0; i < age; i++) {
            calendar.set(year + i, month - 1, day);
            System.out.println(i + " - " + format.format(calendar.getTime()) );
        }
    }
}
