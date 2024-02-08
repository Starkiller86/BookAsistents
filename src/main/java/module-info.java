module org.sbcm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens org.sbcm to javafx.fxml;
    opens org.sbcm.Controller.VentantasControllers to javafx.fxml;
    exports org.sbcm;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires org.json;
    requires java.sql;
    requires java.desktop;

    opens org.sbcm.Controller to javafx.fxml;
    opens org.sbcm.Dao to com.fasterxml.jackson.databind, com.fasterxml.jackson.annotation;
    opens org.sbcm.Model to com.fasterxml.jackson.annotation, com.fasterxml.jackson.databind;

    exports org.sbcm.Dao;
    exports org.sbcm.Model;
    exports org.sbcm.Controller;
    exports org.sbcm.SingletonModels;

}