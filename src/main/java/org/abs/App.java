/*
 * App.java
 *
 * Created on 2022-09-22
 *
 * Copyright (C) 2022 Volkswagen AG, All rights reserved.
 */

package org.abs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final char PERIOD = '.';

    private static JFrame frameMain;
    private static JTextField textFieldInput;

    public static void mainWindow(){
        frameMain = new JFrame("Main Window");
        frameMain.setSize(400,400);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setLayout(null);
    }

    public static void createTextFields(){
        textFieldInput = new JTextField();
        textFieldInput.setBounds(50,100, 200,30);
        frameMain.add(textFieldInput);
        frameMain.setVisible(true);
    }

    public static void createListener(){
        textFieldInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String test = textFieldInput.getText();
                System.out.println(test);
            }
        });
    }

    @Override
    public void start(final Stage primaryStage) {
        final var javaVersion = SystemInfo.javaVersion();
        final var javafxVersion = SystemInfo.javafxVersion();

        final var label = new Label("Hallo, JavaFX " + javafxVersion + ", läuft auf Java " + javaVersion + PERIOD);
        final var scene = new Scene(new StackPane(label), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        mainWindow();
        createTextFields();
        createListener();
    }

    public static void main(final String[] args) {
        launch();
    }

}


