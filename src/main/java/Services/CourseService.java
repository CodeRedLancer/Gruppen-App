package Services;

import org.abs.gruppenapp.Entitys.Course;
import org.abs.gruppenapp.Entitys.Student;
import org.abs.gruppenapp.Entitys.Teacher;

public class CourseService {

    public void addCourse(Course course) {

        System.out.println("Add course");
    }

    public void deleteCourse(Integer courseId) {

        System.out.println("delete course");
    }

    public void addTeacher(Integer courseId, Teacher teacher) {

        System.out.println("add Teacher");
    }

    public void removeTeacher(Integer courseId, Integer teacherId) {

        System.out.println("remove Teacher");
    }

    public void addStudent(Integer courseId, Student student) {

        System.out.println("add Student");
    }

    public void removeStudent(Integer courseId, Student student) {

        System.out.println("remove Student");
    }

    public void editCourseName(Integer courseId, String name) {

        System.out.println("edit course name");
    }
}
