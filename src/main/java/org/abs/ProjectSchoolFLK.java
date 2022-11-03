package org.abs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class ProjectSchoolFLK {

    public static Scene createContent() {
        final var label = new Label("Hello World");
        final var button = new Button("Neues Fenster öffnen");
        button.setPrefSize(150,50);
        label.setPrefSize(200,50);
        StackPane stack = new StackPane(label, button);
        final var scene = new Scene(stack);
        return scene;
    }
}
