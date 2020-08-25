package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {
    @EmbeddedId
    private Key key;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public LinkedPurchaseList() {
    }

    @Embeddable
    public static class Key implements Serializable {

        private static final long serialVersionUID = -6834324831684893789L;

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public Key() {
        }

        public Key(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
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
            Key key = (Key) o;
            return studentId == key.studentId &&
                    courseId == key.courseId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}
