package org.abs.gruppenapp.repository;

import java.util.List;
import org.abs.gruppenapp.entities.Course;
import org.abs.gruppenapp.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {


}
