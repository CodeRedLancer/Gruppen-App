package org.abs.gruppenapp;

import org.abs.gruppenapp.gui.Gui;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    var builder = new SpringApplicationBuilder(Application.class);
    builder.headless(false);

    var context = builder.run(args);
    var gui = context.getBean(Gui.class);
    gui.initialize();
  }
}
