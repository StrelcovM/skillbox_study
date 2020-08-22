import models.PurchaseList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
             SessionFactory sessionFactory = new MetadataSources(registry).getMetadataBuilder().build().getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {

            PurchaseList purchaseList = session.get(PurchaseList.class, new PurchaseList.PurchaseKey("Носов Макар", "Мобильный разработчик с нуля"));

            System.out.println(purchaseList.getPrice());
        }
    }
}