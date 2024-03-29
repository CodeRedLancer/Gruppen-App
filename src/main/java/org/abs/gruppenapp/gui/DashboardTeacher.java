package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DashboardTeacher extends JFrame {

  private final DatabaseService databaseService;

  public void initialize() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(500, 500);
    setLayout(new BorderLayout());
    setLocationRelativeTo(null);

    var loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    var mainPanel = new JPanel(new GridLayout(3, 1));
    mainPanel.setBorder(new EmptyBorder(10, 50, 10, 50));

    JButton logoutBtn = new JButton("Ausloggen");
    JButton groupBtn = new JButton("Gruppen erstellen");
    JButton studentBtn = new JButton("Schüler verwalten");

    loginPanel.add(logoutBtn);
    mainPanel.add(groupBtn);
    mainPanel.add(studentBtn);

    add(loginPanel, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);

    setVisible(true);

    logoutBtn.addActionListener(a -> logout());
    groupBtn.addActionListener(a -> openMainWindow());
    studentBtn.addActionListener(a -> openStudentManager());
  }

  private void logout() {
    Login login = new Login(databaseService);
    login.setVisible(true);
    login.initialize();
    setVisible(false);
  }

  private void openMainWindow() {
    StudentView gui = new StudentView(databaseService);
    gui.setVisible(true);
    gui.initialize();
    setVisible(false);
  }

  private void openStudentManager() {
    StudentManager studentManager = new StudentManager(databaseService);
    studentManager.setVisible(true);
    studentManager.initialize();
    setVisible(false);
  }
}
