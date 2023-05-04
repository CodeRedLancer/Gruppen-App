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

  public Course getCourseByName(String courseName) {
    List<Course> courses = courseRepository.findByName(courseName);
    if (courses.size() > 1) {
      throw new IllegalStateException("Many courses were found");
    }
    return courses.get(0);
  }

  public void saveCourse(Course course) {
    courseRepository.save(course);
  }

  public List<String> getAllLearningFields() {
    List<LearningField> learningFields = (List<LearningField>) learningFieldRepository.findAll();
    return learningFields.stream().map(LearningField::getName).toList();
  }

  public void saveLearningField(LearningField lf){
    learningFieldRepository.save(lf);
  }

  public List<String> getLfByCourseName(String courseName) {
    List<LearningField> learningField = learningFieldRepository.findByCourses_Name(courseName);
    return learningField.stream().map(LearningField::getName).toList();
  }

  public List<String> getLfByName(String name) {
    List<LearningField> learningField = learningFieldRepository.findByName(name);
    return learningField.stream().map(LearningField::getName).toList();
  }

  public List<LearningField> getLfListByName(String name) {
    return learningFieldRepository.findByName(name);
  }

  public void deleteLF(String lf) {
    var lfFound = learningFieldRepository.findByName(lf);
    learningFieldRepository.delete(lfFound.get(0));
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

  public Optional<Student> findStudentByID(int id) {
    return studentRepository.findByStudentId(id);
  }

  public void saveStudent(Student student) {
    studentRepository.save(student);
  }

  public void removeStudent(int id) {
    var student = studentRepository.findByStudentId(id);
    if (student.isPresent()) {
      studentRepository.delete(student.get());
    } else {
      System.out.printf("Student with id %d was not found", id);
    }
  }

  public void removeCourse(String courseName) {
    var course = courseRepository.findByName(courseName);
    if (course.size() > 1) {
      System.out.println("Many courses were found");
    } else {
      courseRepository.delete(course.get(0));
    }
  }
}
