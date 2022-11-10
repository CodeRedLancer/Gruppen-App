package Services;

import org.abs.gruppenapp.Entitys.Student;

public class StudentService {

    public void addStudent(Student student) {

        System.out.println("Add User");
    }

    public void deleteStudent(Integer studentId) {

        System.out.println("delete User");
    }

    public void editStudentName(Integer studentId, String lastName) {

        System.out.println("edit Student lastName");
    }

    public void editStudentName(Integer studentId, String lastName, String firstName) {

        System.out.println("edit Student lastName and firstName");
    }
}
