module org.sbcm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens org.sbcm to javafx.fxml;
    exports org.sbcm;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires org.json;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.java;
    opens org.sbcm.Controller to javafx.fxml;
    opens org.sbcm.Dao to com.fasterxml.jackson.databind, com.fasterxml.jackson.annotation;
    opens org.sbcm.Model to com.fasterxml.jackson.annotation;

    exports org.sbcm.Dao;
    exports org.sbcm.Model;
    exports org.sbcm.Controller;

}