module br.edu.femass.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires java.desktop;

    opens br.edu.femass.biblioteca to javafx.fxml;
    exports br.edu.femass.biblioteca;
    opens br.edu.femass.biblioteca.gui to javafx.fxml;
    exports br.edu.femass.biblioteca.gui;
    exports br.edu.femass.biblioteca.model;
    exports br.edu.femass.biblioteca.dao;

}