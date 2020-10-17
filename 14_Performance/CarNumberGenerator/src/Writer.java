import java.io.PrintWriter;

public class Writer extends Thread {
    int regionCode;
    PrintWriter writer;
    char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public Writer(int regionCode, String path) {
        this.regionCode = regionCode;
        try {
            writer = new PrintWriter(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int number = 1; number < 1000; number++) {
            StringBuilder builder = new StringBuilder();
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        builder.append(firstLetter)
                                .append(padNumber(number, 3))
                                .append(secondLetter)
                                .append(thirdLetter)
                                .append(padNumber(regionCode, 2))
                                .append("\n");
                    }
                }
            }
            writer.write(builder.toString());
        }

        writer.flush();
        writer.close();
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        if (padSize == 2)
            return "00" + numberStr;
        if (padSize == 1)
            return "0" + numberStr;
//        for (int i = 0; i < padSize; i++) {
//            numberStr = '0' + numberStr;
//        }
        return numberStr;
    }
}
