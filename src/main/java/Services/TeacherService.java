package Services;

import org.abs.gruppenapp.Entitys.Teacher;

public class TeacherService {

    public void addTeacher(Teacher teacher) {

        System.out.println("Add Teacher");
    }

    public void deleteTeacher(Integer teacherId) {

        System.out.println("delete teacher");
    }

    public void editTeacherLastName(Integer teacherId, String lastName) {

        System.out.println("edit teacher lastName");
    }

    public void editTeacherFirstName(Integer teacherId, String firstName) {

        System.out.println("edit teacher firstName");
    }
}
