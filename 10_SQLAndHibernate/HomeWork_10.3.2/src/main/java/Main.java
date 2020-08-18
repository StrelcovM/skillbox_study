import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        BigInteger countCourses = (BigInteger) session
                .createSQLQuery("SELECT COUNT(*) FROM skillbox.courses")
                .uniqueResult();

        int count = countCourses.intValue();

        for(int i = 1; i < count; i++) {
            Course course = session.get(Course.class, i);
            System.out.println(course.getName() + " " + course.getStudentsCount());
        }
    }
}
