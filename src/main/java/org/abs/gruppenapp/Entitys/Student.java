package org.abs.gruppenapp.Entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private String firstName;
    private String lastName;
    private Integer evaluation;
    private Integer id;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int studentId;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "evaluation")
  private int evaluation;
}

    public String getLastName() {
        return lastName;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public Integer getId() {
        return id;
    }
}