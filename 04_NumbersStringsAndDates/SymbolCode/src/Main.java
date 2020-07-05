public class Main {
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetRus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        for (int i = 0; i < alphabet.length(); i++) {
            System.out.println(alphabet.charAt(i) + ": " + String.format("%04X", (int) alphabet.charAt(i)));
        }

        System.out.println();

        for (int i = 0; i < alphabetRus.length(); i++){
            System.out.println(alphabetRus.charAt(i) + ": " + String.format("%04X", (int)alphabetRus.charAt(i)));
        }
    }
}
