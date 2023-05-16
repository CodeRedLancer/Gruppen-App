package org.abs.gruppenapp.repository;

import java.util.List;
import java.util.Optional;
import org.abs.gruppenapp.entities.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findAllByOrderByLastNameAsc();

    Optional<Teacher> findByTeacherId(int id);

    Optional<Teacher> findByUsername(@Param("username") String username);
}
