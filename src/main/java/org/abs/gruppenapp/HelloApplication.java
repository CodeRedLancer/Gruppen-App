package org.abs.gruppenapp;

import static javafx.application.Application.launch;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import com.gluonhq.ignite.spring.SpringContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HelloApplication extends Application {


  public static void main(String[] args) {
    javafx.application.Application.launch(HelloApplication.class, args);
  }

  @Autowired
  private FXMLLoader loader;

  private final SpringContext context = new SpringContext(this);
  private ConfigurableApplicationContext ctx;
  private static Scene scene;


  @Override
  public void start(Stage stage) throws IOException {
    ctx = SpringApplication.run(HelloApplication.class);

    loader.setLocation(getClass().getResource("login.fxml"));
    Parent primaryView = loader.load();
    stage.setTitle("Gruppen-App!");
    stage.setScene(scene = new Scene(primaryView, 300,150));
    stage.show();


//    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
//    Scene scene = new Scene(fxmlLoader.load(), 300, 150);
//    stage.setTitle("Gruppen-App!");
//    stage.setScene(scene);
//    stage.show();
  }

  @Override
  public void stop() throws Exception {
    Platform.exit();
    ctx.close();
  }
}