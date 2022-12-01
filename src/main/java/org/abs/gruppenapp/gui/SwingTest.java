package org.abs.gruppenapp.gui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Component
public class SwingTest extends JFrame {

  public SwingTest() {
  }

  public void initUI() {

    var quitButton = new JButton("Quit");

    quitButton.addActionListener((ActionEvent event) -> {
      System.exit(0);
    });

    createLayout(quitButton);

    setTitle("Quit button");
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  private void createLayout(JComponent... arg) {

    var pane = getContentPane();
    var gl = new GroupLayout(pane);
    pane.setLayout(gl);

    gl.setAutoCreateContainerGaps(true);

    gl.setHorizontalGroup(gl.createSequentialGroup()
        .addComponent(arg[0])
    );

    gl.setVerticalGroup(gl.createSequentialGroup()
        .addComponent(arg[0])
    );
  }
}
