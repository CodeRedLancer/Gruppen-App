package org.abs;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

public class EnterStudentNameTextField {

    private TextField firstName;
    private TextField lastName;

    public EnterStudentNameTextField() {
        createFirstNameTextField();
        createLastNameTextField();
    }

    private void createFirstNameTextField() {
        firstName = new TextField("first name");
        firstName.setAlignment(Pos.CENTER);
        firstName.setEditable(true);
        firstName.setMaxWidth(200.0);
        firstName.setOnMouseClicked(mouseEvent -> firstName.setText(""));
        firstName.setOnAction(actionEvent -> System.out.println(firstName.getText()));

    }

    private void createLastNameTextField() {
        lastName = new TextField("last name");
        lastName.setAlignment(Pos.CENTER);
        lastName.setEditable(true);
        lastName.setMaxWidth(200.0);
        lastName.setOnMouseClicked(mouseEvent -> lastName.setText(""));
        lastName.setOnAction(actionEvent -> System.out.println(lastName.getText()));
    }

    public TextField getFirstName() {
        return firstName;
    }

    public TextField getLastName() {
        return lastName;
    }
}
