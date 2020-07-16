public class Customer
{
    private String name;
    private String phone;
    private String eMail;

    public Customer(String name, String phone, String eMail)
    {
        if (!phone.matches("\\+\\d{11}"))
            throw new IllegalArgumentException("Invalid phone number!");
        if (!eMail.matches(".+@.+\\..+"))
            throw new IllegalArgumentException("Invalid email!");
        this.name = name;
        this.phone = phone;
        this.eMail = eMail;
    }

    public String toString()
    {
        return name + " - " + eMail + " - " + phone;
    }
}
