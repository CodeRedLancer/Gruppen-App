package org.abs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {


    @Override
    public void start(final Stage primaryStage) {
        final var customBorderPane = new CustomBorderPane();
        final var scene = new Scene(customBorderPane.getBorderPane(), 640, 480);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch();
    }

}
