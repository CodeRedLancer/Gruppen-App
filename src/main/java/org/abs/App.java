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

    @Override
    public void start(final Stage primaryStage) {
        final var javaVersion = SystemInfo.javaVersion();
        final var javafxVersion = SystemInfo.javafxVersion();

        final var label = new Label("Hallo, JavaFX " + javafxVersion + ", running on Java " + javaVersion + PERIOD);
        final var scene = new Scene(new StackPane(label), 640, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(final String[] args) {
        launch();
    }

}
