package org.abs.gruppenapp.Entitys;

import java.lang.reflect.Array;

public class Course {

    private int idTeacher;
    private String name;

    private Array students;

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

    public Array getStudents() {return students;}
}
