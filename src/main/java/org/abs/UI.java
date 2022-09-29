package org.abs;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class UI {

    private static UI instance;

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }

        return instance;
    }

    public void createUI(Stage primaryStage) {
        var hbox = createDropDownMenu();

        var scene = new Scene(new StackPane(hbox), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createDropDownMenu() {
        ChoiceBox<String> assetsClass = new ChoiceBox<>();
        assetsClass.getItems().addAll("LF 10");
        assetsClass.getItems().addAll("LF 11");
        assetsClass.setValue("Lernfelder");
        var hbox = new HBox(assetsClass);
        hbox.setAlignment(Pos.TOP_CENTER);

        return hbox;
    }
}
