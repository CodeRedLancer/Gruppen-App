package org.abs.gruppenapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Student")
@Table(name = "student_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "studentId")
  private int studentId;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "evaluation")
  private int evaluation;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "subject_Id")
  private Subject subject;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "course_Id")
  private Course course;
}
