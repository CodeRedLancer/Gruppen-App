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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Course")
@Table(name = "course_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int courseId;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<Teacher> teacherId = new HashSet<>();

  @Column(name = "name")
  private String name;
}
