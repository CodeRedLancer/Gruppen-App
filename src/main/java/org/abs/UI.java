package org.abs;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UI {

    private static UI instance;

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }

        return instance;
    }

    public void createUI(Stage primaryStage) {
        var dropDownVBox = createDropDownMenu();
        var tableVBox = createTableBox();

        var button = new Button("Create groups");
        var buttonVbox = new VBox(button);
        buttonVbox.setAlignment(Pos.BOTTOM_CENTER);

        var rootPane = new Pane();
        //dropDownVBox.getChildren().add(rootPane);
        //tableVBox.getChildren().add(rootPane);
        //buttonVbox.getChildren().add(rootPane);

        rootPane.getChildren().addAll(dropDownVBox, tableVBox, buttonVbox);
        var vbox = new VBox();
        vbox.getChildren().addAll(rootPane);

        var scene = new Scene(vbox, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createDropDownMenu() {
        var vBox = new VBox(createChoiceBox());
        vBox.setAlignment(Pos.TOP_CENTER);

        return vBox;
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

    private VBox createTableBox() {
        var vbox = new VBox(createAndFillTable());
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(5);

        return vbox;
    }

    private TableView<Student> createAndFillTable() {
        TableView<Student> studentTable = new TableView<>();

        TableColumn<Student, String> firstNameColumn = new TableColumn<>("firstName");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Student, String> lastNameColumn = new TableColumn<>("lastName");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Student, Integer> evaluation = new TableColumn<>("evaluation");
        evaluation.setCellValueFactory(new PropertyValueFactory<>("evaluation"));

        studentTable.getColumns().addAll(firstNameColumn, lastNameColumn, evaluation);

        studentTable.getItems().addAll(getStudents());

        return studentTable;
    }

    private List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Markus", "Knecht", 15));
        students.add(new Student("Robert", "Mager", 11));

        return students;
    }

    private void handleChoiceBoxEvent(ChoiceBox<String> choiceBox) {
        var selectedItem = choiceBox.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
    }
}
