package org.abs.gruppenapp.services;

import org.abs.gruppenapp.entities.Student;
import org.abs.gruppenapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;


    public void addStudent(Student student) {
        studentRepository.save(student);
        System.out.println("Add User");
    }

    public void deleteStudent(Integer studentId) {

        System.out.println("delete User");
    }

    public void editStudentLastName(Integer studentId, String lastName) {

        System.out.println("edit Student lastName");
    }

    public void editStudentFirstName(Integer studentId, String firstName) {

        System.out.println("edit Student firstName");
    }
}
