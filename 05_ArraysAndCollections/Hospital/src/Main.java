public class Main {
    private static final int PATIENT_COUNT = 30;
    private static final double MIN_TEMP = 32.0;
    private static final double MAX_TEMP = 40.0;
    private static final double MIN_HEALTH_TEMP = 36.6;
    private static final double MAX_HEALTH_TEMP = 36.9;

    public static void main(String[] args) {
        int countHealthPatient = 0;
        double middleTemp = 0.0;
        double[] patients = new double[30];

        System.out.print("Температуры пациентов: ");
        for (int i = 0; i < PATIENT_COUNT; i++) {
            /*rounding the temperature to one decimal place
             *fill in the array and print it
             */
            double temp = (Math.random() * (MAX_TEMP - MIN_TEMP - 1)) + MIN_TEMP;
            patients[i] = Math.round(temp * 10) / 10.0;
            System.out.print(patients[i] + " ");
        }

        for (double temp : patients) {
            middleTemp += temp / PATIENT_COUNT;
            if (temp >= MIN_HEALTH_TEMP && temp <= MAX_HEALTH_TEMP)
                countHealthPatient++;
        }

        System.out.println("\nСредняя температура по больнице: " + Math.round(middleTemp * 100) / 100.0);
        System.out.println("Колличество здоровых пациентов: " + countHealthPatient);
    }
}
