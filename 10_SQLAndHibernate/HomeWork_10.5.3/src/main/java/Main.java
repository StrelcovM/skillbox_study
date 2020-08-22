import models.Student;
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

            Student student = session.get(Student.class, 8);

            student.getSubscriptions().forEach(el -> System.out.println(el.getKey().getCourse().getName()));
        }
    }
}