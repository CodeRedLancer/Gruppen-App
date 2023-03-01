package org.abs.gruppenapp.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "LearningField")
@Table(name = "lf_table")
@AllArgsConstructor
@NoArgsConstructor
public class LearningField {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "lfId")
  private int lfId;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "lf")
  private Set<Course> courses = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "lfs_subjects",
      joinColumns = @JoinColumn(name = "lf_Id", referencedColumnName = "lfId"),
      inverseJoinColumns = @JoinColumn(name = "subject_Id", referencedColumnName = "subjectId"))
  private Set<Subject> subjects = new HashSet<>();

  public int getLfId() {
    return lfId;
  }

  public void setLfId(int lfId) {
    this.lfId = lfId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Course> getCourses() {
    return courses;
  }

  public void setCourses(Set<Course> courses) {
    this.courses = courses;
  }

  public Set<Subject> getSubjects() {
    return subjects;
  }

  public void setSubjects(Set<Subject> subjects) {
    this.subjects = subjects;
  }
}
