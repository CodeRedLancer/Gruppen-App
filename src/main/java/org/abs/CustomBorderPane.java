package org.abs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class CustomBorderPane {

    private BorderPane borderPane;

    public CustomBorderPane() {
        final var label = new Label("GroupBuilderApp");
        final var helloBtn = createHelloButton();
        final var firstNameTextField = new EnterStudentNameTextField().getFirstName();
        final var lastNameTextField = new EnterStudentNameTextField().getLastName();

        borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 0, 20, 0));

        borderPane.setTop(label);
        borderPane.setBottom(helloBtn);
        borderPane.setTop(firstNameTextField);
        borderPane.setTop(lastNameTextField);

        borderPane.setAlignment(helloBtn, Pos.BOTTOM_CENTER);
        borderPane.setAlignment(label, Pos.TOP_CENTER);
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    private Button createHelloButton() {
        // Construct the "Button" and attach an "EventHandler"
        final Button btnHello = new Button();
        btnHello.setText("Add Student");
        btnHello.setOnAction(evt -> {
            System.out.println("Adding student");
        });
        return btnHello;
    }
}
