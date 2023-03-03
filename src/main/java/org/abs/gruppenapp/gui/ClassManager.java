package org.abs.gruppenapp.gui;

import javax.swing.JFrame;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClassManager extends JFrame {

  private final DatabaseService databaseService;

  public void initialize(){

  }

}
