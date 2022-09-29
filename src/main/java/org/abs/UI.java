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
        var hbox = new HBox(createChoiceBox());
        hbox.setAlignment(Pos.TOP_CENTER);

        return hbox;
    }

    private ChoiceBox<String> createChoiceBox() {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("LF 10", "LF 11");
        choiceBox.setValue("Lernfelder");
        choiceBox.setOnAction(event -> {
            handleChoiceBoxEvent(choiceBox);
        });

        return choiceBox;
    }

    private void handleChoiceBoxEvent(ChoiceBox<String> choiceBox) {
        var selectedItem = choiceBox.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
    }
}
