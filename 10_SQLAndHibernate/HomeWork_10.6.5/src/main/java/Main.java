import models.Course;
import models.LinkedPurchaseList;
import models.PurchaseList;
import models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
             SessionFactory sessionFactory = new MetadataSources(registry).getMetadataBuilder().build().getSessionFactoryBuilder().build();
             Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<PurchaseList> query = builder.createQuery(PurchaseList.class);
            Root<PurchaseList> root = query.from(PurchaseList.class);
            query.select(root);

            List<PurchaseList> list = session.createQuery(query).getResultList();

            list.forEach(purchaseList -> {
                String studentHql = "FROM " + Student.class.getSimpleName()
                        + " WHERE name ='" + purchaseList.getKey().getStudentName() + "'";

                String courseHql = "FROM " + Course.class.getSimpleName()
                        + " WHERE name ='" + purchaseList.getKey().getCourseName() + "'";

                Student student = (Student) session.createQuery(studentHql).getSingleResult();
                Course course = (Course) session.createQuery(courseHql).getSingleResult();

                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                linkedPurchaseList.setKey(new LinkedPurchaseList.Key(student.getId(), course.getId()));

                session.getTransaction().begin();
                session.save(linkedPurchaseList);
                session.getTransaction().commit();
            });
        }
    }
}
