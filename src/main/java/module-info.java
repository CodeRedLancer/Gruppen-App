module org.abs.gruppenapp {
  requires javafx.controls;
  requires javafx.fxml;
  requires spring.boot.autoconfigure;
  requires spring.boot;
  requires spring.context;
  requires lombok;
  requires java.persistence;
  requires spring.data.commons;
  requires spring.beans;
  requires ignite.spring;

  opens org.abs.gruppenapp to javafx.fxml;
  exports org.abs.gruppenapp;
}