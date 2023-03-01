package org.abs.gruppenapp.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Subject")
@Table(name = "subject_table")
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subjectId")
  private int subjectId;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "subject")
  private Set<Student> students = new HashSet<>();

  @ManyToMany(mappedBy = "subjects")
  private Set<LearningField> learningFields = new HashSet<>();

  public int getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(int subjectId) {
    this.subjectId = subjectId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

  public Set<LearningField> getLearningFields() {
    return learningFields;
  }

  public void setLearningFields(Set<LearningField> learningFields) {
    this.learningFields = learningFields;
  }
}
