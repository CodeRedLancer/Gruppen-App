package org.abs.gruppenapp.entities;

import java.util.ArrayList;
import java.util.List;
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

@Entity(name = "Teacher")
@Table(name = "teacher_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "teacherId")
  private int teacherId;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "password")
  private String password;

  @Column(name = "salt")
  private String salt;

  @Column(name = "username")
  private String username;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "teachers_lfs",
      joinColumns = @JoinColumn(name = "teacher_Id", referencedColumnName = "teacherId"),
      inverseJoinColumns = @JoinColumn(name = "lf_Id", referencedColumnName = "lfId"))
  private List<LearningField> learningFields = new ArrayList<>();
}
