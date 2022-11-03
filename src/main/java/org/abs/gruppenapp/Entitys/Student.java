package org.abs.gruppenapp.Entitys;

public class Student {

    private String firstName;
    private String lastName;
    private Integer evaluation;

    public Student(String firstName, String lastName, Integer evaluation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.evaluation = evaluation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getEvaluation() {
        return evaluation;
    }
}