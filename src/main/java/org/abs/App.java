/*
 * App.java
 *
 * Created on 2022-09-22
 *
 * Copyright (C) 2022 Volkswagen AG, All rights reserved.
 */

package org.abs;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(final Stage primaryStage) {
        var ui = UI.getInstance();
        ui.createUI(primaryStage);
    }

    public static void main(final String[] args) {
        launch();
    }

}
