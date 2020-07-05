import static java.lang.Math.*;

public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        if (radius > 0)
            return PI * pow(radius, 2);
        return 0;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        if (radius > 0)
            return (4.0 / 3) * PI * pow(radius, 3);
        return 0;
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        return (a + b) > c && (b + c) > a && (a + c) > b;
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTriangleRightAngled, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        double p = (a + b + c) / 2;
        if (isTriangleRightAngled(a, b, c))
            return sqrt(p * (p - a) * (p - b) * (p - c));
        return 0;
    }
}
