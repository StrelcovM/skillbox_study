package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {
    @EmbeddedId
    private PurchaseKey key;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    public static class PurchaseKey implements Serializable {

        private static final long serialVersionUID = 2472129826645489974L;

        @Column(name = "student_name")
        private String studentName;

        @Column(name = "course_name")
        private String courseName;

        public PurchaseKey() {
        }

        public PurchaseKey(String student, String courseName) {
            this.studentName = student;
            this.courseName = courseName;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PurchaseKey that = (PurchaseKey) o;
            return Objects.equals(studentName, that.studentName) &&
                    Objects.equals(courseName, that.courseName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentName, courseName);
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public PurchaseKey getKey() {
        return key;
    }

    public void setKey(PurchaseKey key) {
        this.key = key;
    }
}
