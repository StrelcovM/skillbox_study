import bank.Client;
import bank.OrganizationAccount;
import bank.PersonAccount;
import bank.SoleProprietor;

public class Main {
    public static void main(String[] args) {
        Client person = new PersonAccount();
        Client individualEntrepreneur = new SoleProprietor();
        Client organization = new OrganizationAccount();

        System.out.println(
                "Person conditions and Balance: " + person.getInfo() +
                        "\n"
        );

        System.out.println(
                "Individual entrepreneur conditions and Balance: " + individualEntrepreneur.getInfo() +
                        "\n"
        );

        System.out.println(
                "Organization conditions and Balance: " + organization.getInfo() +
                        "\n"
        );

        System.out.println("Top up all accounts for 200.0:");

        person.putMoney(200.0);
        individualEntrepreneur.putMoney(200.0);
        organization.putMoney(200.0);

        System.out.println(
                "Person conditions and Balance: " + person.getInfo() +
                        "\n"
        );

        System.out.println(
                "Individual entrepreneur conditions and Balance: " + individualEntrepreneur.getInfo() +
                        "\n"
        );

        System.out.println(
                "Organization conditions and Balance: " + organization.getInfo() +
                        "\n"
        );

        System.out.println("withdrawals from all accounts 100:");

        person.getMoney(100.0);
        individualEntrepreneur.getMoney(100.0);
        organization.getMoney(100.0);

        System.out.println(
                "Person conditions and Balance: " + person.getInfo() +
                        "\n"
        );

        System.out.println(
                "Individual entrepreneur conditions and Balance: " + individualEntrepreneur.getInfo() +
                        "\n"
        );

        System.out.println(
                "Organization conditions and Balance: " + organization.getInfo() +
                        "\n"
        );

    }
}
