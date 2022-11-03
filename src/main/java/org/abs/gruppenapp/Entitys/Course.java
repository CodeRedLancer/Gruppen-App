package org.abs.gruppenapp.Entitys;

public class Course {

    private int idTeacher;
    private String name;

    public Course(int idTeacher, String name) {
        this.idTeacher = idTeacher;
        this.name = name;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public String getName() {
        return name;
    }
}
