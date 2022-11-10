package org.abs.gruppenapp.Entitys;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Course")
@Table(name = "course_table")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Integer> teacherId = new HashSet<>();

    @Column(name = "name")
    private String name;

    public Course() {
    }

    public Course(int courseId, Set<Integer> teacherId, String name) {
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public Set<Integer> getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Set<Integer> teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
