import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws IllegalArgumentException
    {
        String[] components = data.split("\\s+");
        if (components.length != 4)
            throw new IllegalArgumentException("Wrong argument length!");
        String name = components[0] + " " + components[1];
        try {
            storage.put(name, new Customer(name, components[3], components[2]));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}