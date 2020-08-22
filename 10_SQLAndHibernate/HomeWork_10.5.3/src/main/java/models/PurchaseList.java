package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
@IdClass(PurchaseList.PurchaseKey.class)
public class PurchaseList {
    @EmbeddedId
    private PurchaseKey key;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private int price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @Embeddable
    public static class PurchaseKey implements Serializable {
        @Id
        @Column(name = "student_name", insertable = false, updatable = false)
        private String studentName;

        @Id
        @Column(name = "course_name", insertable = false, updatable = false)
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
}
