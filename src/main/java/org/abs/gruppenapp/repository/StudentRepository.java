package org.abs.gruppenapp.repository;

import java.util.List;
import java.util.Optional;
import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.entities.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

  List<Student> findByCourse_Name(@Param("course") String course);

  List<Student> findByCourse_NameAndSubject_Name(String course, String subject);
  Optional<Student> findByStudentId(int id);
}
