package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
@IdClass(Subscription.SubscriptionKey.class)
public class Subscription {
    @EmbeddedId
    private SubscriptionKey key;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public SubscriptionKey getKey() {
        return key;
    }

    public void setKey(SubscriptionKey key) {
        this.key = key;
    }

    @Embeddable
    public static class SubscriptionKey implements Serializable {
        static final long serialVersionUID = 21L;

        @Id
        @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
        @JoinColumn(name = "student_id",referencedColumnName = "id", insertable = false, updatable = false)
        private Student student;

        @Id
        @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
        @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
        private Course course;

        public SubscriptionKey() {
        }

        public SubscriptionKey(Student student, Course course) {
            this.student = student;
            this.course = course;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubscriptionKey that = (SubscriptionKey) o;
            return Objects.equals(student, that.student) &&
                    Objects.equals(course, that.course);
        }

        @Override
        public int hashCode() {
            return Objects.hash(student, course);
        }
    }
}
