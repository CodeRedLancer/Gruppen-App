package org.abs.gruppenapp.repository;

import org.abs.gruppenapp.entities.LearningField;
import org.abs.gruppenapp.entities.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    Teacher findByUsername(@Param("username") String username);
}
