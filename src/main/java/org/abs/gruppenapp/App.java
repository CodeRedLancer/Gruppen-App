/*
 * App.java
 *
 * Created on 2022-09-22
 *
 * Copyright (C) 2022 Volkswagen AG, All rights reserved.
 */

package org.abs.gruppenapp;

import javafx.application.Application;
import javafx.stage.Stage;


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
