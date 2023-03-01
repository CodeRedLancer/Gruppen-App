package org.abs.gruppenapp.services;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.abs.gruppenapp.entities.Course;
import org.abs.gruppenapp.entities.LearningField;
import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.entities.Subject;
import org.abs.gruppenapp.repository.CourseRepository;
import org.abs.gruppenapp.repository.LearningFieldRepository;
import org.abs.gruppenapp.repository.StudentRepository;
import org.abs.gruppenapp.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatabaseService {

  private final CourseRepository courseRepository;
  private final LearningFieldRepository learningFieldRepository;
  private final SubjectRepository subjectRepository;
  private final StudentRepository studentRepository;


  public List<String> getAllCourses() {
    List<Course> courses = (List<Course>) courseRepository.findAll();
    return courses.stream().map(Course::getName).toList();
  }

  public List<String> getLfByCourseName(String courseName) {
    List<LearningField> learningField = learningFieldRepository.findByCourses_Name(courseName);
    return learningField.stream().map(LearningField::getName).toList();
  }

  public List<String> getFachrichtungByLfName(String lfName) {
    List<Subject> subject = subjectRepository.findByLearningFields_name(lfName);
    return subject.stream().map(Subject::getName).toList();
  }

  public List<Student> getStudentsByCourseName(String courseName) {
    return studentRepository.findByCourse_Name(courseName);
  }

  public List<Student> getStudentsByCourseNameAndSubject(String courseName, String subject) {
    return studentRepository.findByCourse_NameAndSubject_Name(courseName, subject);
  }
}
