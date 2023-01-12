package org.abs.gruppenapp.repository;

import java.util.List;
import org.abs.gruppenapp.entities.LearningField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LearningFieldRepository extends CrudRepository<LearningField, Long> {

  List<LearningField> findByCourses_Name(@Param("name") String name);
}
