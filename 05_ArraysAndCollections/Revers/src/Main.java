public class Main {
    public static void main(String[] args) {
        String[] text = {"Каждый", "охотник", "желает", "знать,", "где", "сидит", " фазан."};

        for (int i = 0; i < text.length / 2; i++) {
            String rev = "";
            rev = text[i];
            text[i] = text[text.length - i - 1];
            text[text.length - i - 1] = rev;
        }

        for(String s : text)
            System.out.print(s + " ");
    }
}
