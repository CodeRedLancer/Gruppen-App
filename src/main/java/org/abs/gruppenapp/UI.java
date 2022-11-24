package org.abs.gruppenapp;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.abs.gruppenapp.Entitys.Student;

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

    public void switchToClassSelection(Stage primaryStage) {
        var choiceBox = createChoiceBox(List.of("2402", "2403"), "Klassen");
        var vBox = createVBox(choiceBox, Pos.TOP_LEFT);

        var borderPane = new BorderPane();
        borderPane.setTop(vBox);

        choiceBox.setOnAction(event -> {
            switchToLFSelection(primaryStage, vBox);
        });

        setScene(primaryStage, borderPane);
    }

    private void switchToLFSelection(Stage primaryStage, VBox vBox) {
        System.out.println("Calling service to get LFs");
        List<String> items = new ArrayList<>(List.of("LF1", "LF2"));
        var choiceBox = createChoiceBox(items, "Lernfelder");
        var lfVBox = createVBox(choiceBox, Pos.TOP_LEFT);

        var gridPane = new GridPane();
        gridPane.addRow(0, vBox, lfVBox);

        var borderPane = new BorderPane();
        borderPane.setTop(gridPane);

        choiceBox.setOnAction(event -> {
            System.out.println("Calling service to get students");
            switchToStudentMenu(primaryStage, borderPane);
        });

        setScene(primaryStage, borderPane);
    }

    private void switchToStudentMenu(Stage primaryStage, BorderPane borderPane) {
        var createGroupsButton = new Button("Gruppen erstellen");
        var editClassButton = new Button("Klasse bearbeiten");
        var buttonVBox = new VBox(createGroupsButton, editClassButton);

        borderPane.setCenter(createTableBox());
        borderPane.setBottom(buttonVBox);
        setScene(primaryStage, borderPane);
    }

    private ChoiceBox<String> createChoiceBox(List<String> items, String value) {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(items);
        choiceBox.setValue(value);

        return choiceBox;
    }

    private VBox createVBox(ChoiceBox choiceBox, Pos pos) {
        var vBox = new VBox(choiceBox);
        vBox.setAlignment(pos);

        return vBox;
    }

    private void setScene(Stage primaryStage, Parent parent) {
        var scene = new Scene(parent, 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
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
