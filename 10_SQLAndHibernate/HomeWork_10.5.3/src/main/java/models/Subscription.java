package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @EmbeddedId
    private SubscriptionKey key;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

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

        private static final long serialVersionUID = 21L;

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public SubscriptionKey(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        public SubscriptionKey() {
        }

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SubscriptionKey that = (SubscriptionKey) o;
            return studentId == that.studentId &&
                    courseId == that.courseId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}
