
public class Loader
{
    public static void main(String[] args)
    {
        Cat tima = new Cat();
        Cat oleg = new Cat();
        Cat vasya = new Cat();
        Cat anton = new Cat();
        Cat murka = new Cat();

        Cat kitten_1 = getKitten();
        Cat kitten_2 = getKitten();
        Cat kitten_3 = getKitten();

        System.out.println("Tima weight: " + tima.getWeight());
        System.out.println("Oleg weight: " + oleg.getWeight());
        System.out.println("Vasya weight: " + vasya.getWeight());
        System.out.println("Anton weight: " + anton.getWeight());
        System.out.println("Murka weight: " + murka.getWeight());

        murka.feed(3.0);
        tima.feed(1.0);

        System.out.println();

        System.out.println("Murka weight: " + murka.getWeight());
        System.out.println("Tima weight: " + tima.getWeight());

        while (!murka.getStatus().equals("Exploded"))
            murka.feed(1.0);

        System.out.println("\nMurka status: " + murka.getStatus());

        while (!tima.getStatus().equals("Dead"))
            tima.meow();

        System.out.println("\nTima status: " + tima.getStatus());

        System.out.println("\nOleg weight: " + oleg.getWeight());
        oleg.feed(150.0);
        System.out.println("\nOleg feeds: " + oleg.getFeedWeight());
        System.out.println("Oleg weight: " + oleg.getWeight());
        oleg.pee();
        System.out.println("Oleg weight after pee: " + oleg.getWeight());
    }

    private static Cat getKitten(){
        return new Cat(1100.0);
    }
}