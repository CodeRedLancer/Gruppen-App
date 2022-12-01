package org.abs.gruppenapp;

import org.abs.gruppenapp.gui.SwingTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    var builder = new SpringApplicationBuilder(Application.class);
    builder.headless(false);

    var context = builder.run(args);
    var swingApp = context.getBean(SwingTest.class);
    swingApp.initUI();
  }
}
