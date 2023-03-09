package org.abs.gruppenapp.services;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.entities.Course;
import org.abs.gruppenapp.entities.LearningField;
import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.entities.Subject;
import org.abs.gruppenapp.entities.Teacher;
import org.abs.gruppenapp.repository.CourseRepository;
import org.abs.gruppenapp.repository.LearningFieldRepository;
import org.abs.gruppenapp.repository.StudentRepository;
import org.abs.gruppenapp.repository.SubjectRepository;
import org.abs.gruppenapp.repository.TeacherRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DatabaseService {

  private final CourseRepository courseRepository;
  private final LearningFieldRepository learningFieldRepository;
  private final SubjectRepository subjectRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;


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

  public List<Teacher> getAllTeachers() {
    return teacherRepository.findAllByOrderByLastNameAsc();
  }

  public Optional<Teacher> findTeacherByID(int id) {
    return teacherRepository.findByTeacherId(id);
  }

  public void saveTeacher(Teacher teacher) {
    teacherRepository.save(teacher);
  }

  public void removeTeacher(int id) {
    var teacher = teacherRepository.findByTeacherId(id);
    if (teacher.isPresent()) {
      teacherRepository.delete(teacher.get());
    } else {
      System.out.printf("Teacher with id %d was not found", id);
    }
  }
}
