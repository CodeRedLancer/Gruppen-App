package org.abs.gruppenapp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Login extends JFrame {

  public static void main(String[] args) {

  }


  public void initialize() {
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setTitle("GroupMaker 8");
    setSize(300, 250);

    JPanel panel = new JPanel(new GridLayout(3, 1));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForBtn = new JPanel(new FlowLayout());
    panelForBtn.setBorder(new EmptyBorder(10, 10, 10, 10));

    JPanel panelForLabel = new JPanel(new FlowLayout());
    panelForLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel nameLabel = new JLabel("Username");
    JTextField nameTextField = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordTextField = new JPasswordField();
    JButton confirmBtn = new JButton("LOGIN");
    confirmBtn.setSize(20, 10);
    JLabel loginLabel = new JLabel("Melden Sie sich ein");

    panel.add(nameLabel);
    panel.add(nameTextField);
    panel.add(passwordLabel);
    panel.add(passwordTextField);

    panelForBtn.add(confirmBtn);
    panelForLabel.add(loginLabel);

    add(panel, BorderLayout.CENTER);
    add(panelForBtn, BorderLayout.SOUTH);
    add(panelForLabel, BorderLayout.NORTH);

    setVisible(true);

    confirmBtn.addActionListener(e -> {
      var username = nameTextField.getText();
      var password = new String(passwordTextField.getPassword());

      if (username.equals("admin") && password.equals("1234")) {
        System.out.println("Login successful!");

   
      } else {
        System.out.println("Wrong username or password");
      }


    });

  }

}
