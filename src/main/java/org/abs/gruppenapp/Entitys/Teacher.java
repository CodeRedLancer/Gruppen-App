package org.abs.gruppenapp.Entitys;

public class Teacher {
    private String firstName;
    private String lastName;

    private String password;
    private Integer id;
    public Teacher(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword(){
        return password;
    }

    public Integer getId(){
        return id;
    }
}
