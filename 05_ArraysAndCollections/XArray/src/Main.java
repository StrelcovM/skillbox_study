public class Main {
    public static void main(String[] args) {
        char[][] arr = new char[7][7];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i + j == arr[i].length - 1 || i == j)
                    arr[i][j] = 'X';
                else
                    arr[i][j] = ' ';
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }
}
