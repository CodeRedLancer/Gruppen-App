package org.abs.gruppenapp.repository;

import java.util.List;
import org.abs.gruppenapp.entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

  List<Subject> findByLearningFields_name(@Param("name") String name);
}
