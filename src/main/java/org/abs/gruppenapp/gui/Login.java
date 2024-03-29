package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.security.NoSuchAlgorithmException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.services.DatabaseService;
import org.abs.gruppenapp.services.PasswordService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Login extends JFrame {

  private final DatabaseService databaseService;

  public void initialize() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(400, 250);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridLayout(3, 1));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new GridLayout(2, 1));
    panelForBtn.setBorder(new EmptyBorder(10, 50, 10, 50));

    JPanel panelLoginLabel = new JPanel(new FlowLayout());
    panelLoginLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelErrorLabel = new JPanel(new FlowLayout());
    panelErrorLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel nameLabel = new JLabel("Benutzername");
    JTextField nameTextField = new JTextField();
    JLabel passwordLabel = new JLabel("Kennwort");
    JPasswordField passwordTextField = new JPasswordField();
    JButton confirmBtn = new JButton("LOGIN");
    confirmBtn.setSize(20, 10);
    JLabel loginLabel = new JLabel("Melden Sie sich ein");
    JLabel errorLabel = new JLabel();
    errorLabel.setForeground(Color.red);

    panel.add(nameLabel);
    panel.add(nameTextField);
    panel.add(passwordLabel);
    panel.add(passwordTextField);

    panelLoginLabel.add(loginLabel);
    panelForBtn.add(confirmBtn);
    panelForBtn.add(errorLabel);

    add(panelLoginLabel, BorderLayout.NORTH);
    add(panel, BorderLayout.CENTER);
    add(panelForBtn, BorderLayout.SOUTH);

    setVisible(true);

    confirmBtn.addActionListener(e -> loginUser(e, confirmBtn, passwordTextField, errorLabel, nameTextField));
    passwordTextField.addActionListener(e -> loginUser(e, confirmBtn, passwordTextField, errorLabel, nameTextField));
  }

  private void loginUser(ActionEvent a, JButton confirmBtn, JPasswordField passwordTextField, JLabel errorLabel,
      JTextField nameTextField) {
    if (a.getSource() == confirmBtn || a.getSource() == passwordTextField) {
      errorLabel.setText(null);
      var username = nameTextField.getText();
      var password = new String(passwordTextField.getPassword());
      var teacher = databaseService.getTeacherByUsername(username);

      if (username.equals("admin") && password.equals("1234")) {
        openDashboardAdmin();
      }

      try {
        if (teacher.isPresent() && PasswordService.validatePassword(password, teacher.get().getSalt(),
            teacher.get().getPassword())) {
          System.out.println("username is present & password is valid");
          openDashboardTeacher();
        } else {
          errorLabel.setText("Der Benutzername oder das Kennwort ist falsch");
        }
      } catch (NoSuchAlgorithmException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  private void openDashboardAdmin() {
    DashboardAdmin dashboardAdmin = new DashboardAdmin(databaseService);
    dashboardAdmin.setVisible(true);
    dashboardAdmin.initialize();
    setVisible(false);
  }

  private void openDashboardTeacher() {
    DashboardTeacher dashboardTeacher = new DashboardTeacher(databaseService);
    dashboardTeacher.setVisible(true);
    dashboardTeacher.initialize();
    setVisible(false);
  }
}
