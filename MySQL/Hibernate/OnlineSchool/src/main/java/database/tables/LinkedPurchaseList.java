package database.tables;

import database.data.LinkedPurchaseListKey;
import jakarta.persistence.*;

@Entity
@Table(name = "Linked_Purchase_list")
public class LinkedPurchaseList {


    @EmbeddedId
    private LinkedPurchaseListKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Course course;

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
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
}
