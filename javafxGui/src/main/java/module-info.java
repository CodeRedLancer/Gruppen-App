module com.example.javafxgui {
  requires javafx.controls;
  requires javafx.fxml;

  opens com.example.javafxgui to javafx.fxml;
  exports com.example.javafxgui;
}