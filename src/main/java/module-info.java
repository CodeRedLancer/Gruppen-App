module org.openjfx {
    requires javafx.controls;
    requires spring.boot.autoconfigure;
    requires java.persistence;
    requires lombok;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    exports org.abs.gruppenapp;
    exports org.abs.gruppenapp.repository;
}
