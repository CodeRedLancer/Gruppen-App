package com.example.javafxgui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

  @FXML
  private TextField usernameTextField;
  @FXML
  private PasswordField passwordField;
  @FXML
  private Label errorLabel;

  @FXML
  private Button loginBtn;


  public void initialize() {}

  public void initManager() {
    errorLabel.setText(null);
    if (loginCorrect()) {
      try {
        openMainView();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      errorLabel.setText("Wrong username or password");
    }
  }

  private boolean loginCorrect() {
    return "admin".equals(usernameTextField.getText())
        && "1234".equals(passwordField.getText());
  }

  private void openMainView() throws IOException {
    Stage newWindow = new Stage();
    newWindow.setTitle("New Scene");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
    newWindow.setScene(new Scene(loader.load()));
    newWindow.show();

    closeLoginWindow();
  }

  private void closeLoginWindow() {
    Stage stage = (Stage) loginBtn.getScene().getWindow();
    stage.close();
  }
}
