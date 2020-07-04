
public class Cat {
    private static int catCount;

    private double originWeight;
    private double weight;
    private CatColor color;
    private double feedWeight;

    private static final int eyeCount = 2;
    private static final double minWeight = 1000.0;
    private static final double maxWeight = 9000.0;

    private boolean isAlive;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        catCount++;
        isAlive = true;
    }

    public Cat(double weight) {
        this();
        originWeight = weight;
    }

    public CatColor getColor() {
        return color;
    }

    public void setColor(CatColor color) {
        this.color = color;
    }

    public void meow() {
        if (isAlive) {
            weight = weight - 1;
            System.out.println("Meow");
            if (weight < minWeight) {
                catCount--;
                isAlive = false;
            }
        } else
            System.out.println("Cat can't say meow, because she died :(");
    }

    public void feed(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            feedWeight += amount;
            if (weight > maxWeight) {
                catCount--;
                isAlive = false;
            }
        } else
            System.out.println("Cat can't eat, because she died :(");
    }

    public void drink(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            if (weight > maxWeight) {
                catCount--;
                isAlive = false;
            }
        } else
            System.out.println("Cat can't drink, because she died :(");
    }

    public Double getWeight() {
        return weight;
    }

    public String getStatus() {
        if (weight < minWeight) {
            return "Dead";
        } else if (weight > maxWeight) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    public Double getFeedWeight() {
        return weight - originWeight;
    }

    public void pee() {
        if (isAlive) {
            weight -= 10 * Math.random();
            System.out.println("pee pee...");
            if (weight < minWeight) {
                catCount--;
                isAlive = false;
            }
        } else
            System.out.println("Cat can't pee, because she died :(");

    }

    public static int getCount() {
        return catCount;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Cat copyCat() {
        Cat cat = new Cat(this.originWeight);
        cat.weight = this.weight;
        cat.feedWeight = this.feedWeight;
        cat.color = this.getColor();
        cat.isAlive = this.isAlive();

        return cat;
    }
}