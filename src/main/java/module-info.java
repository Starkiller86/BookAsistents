module org.sbcm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens org.sbcm to javafx.fxml;
    opens org.sbcm.Controller.VentantasControllers to javafx.fxml;
    exports org.sbcm;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires org.apache.commons.collections4;
    requires org.apache.commons.codec;
    requires org.apache.commons.compress;

    requires org.json;
    requires java.sql;
    requires java.desktop;
    requires poi.ooxml.schemas;
    requires poi.ooxml;
    requires poi;


    opens org.sbcm.Controller to javafx.fxml;
    opens org.sbcm.Dao to com.fasterxml.jackson.databind, com.fasterxml.jackson.annotation, com.fasterxml.jackson.datatype.jsr310;
    opens org.sbcm.Model to com.fasterxml.jackson.annotation, com.fasterxml.jackson.databind, com.fasterxml.jackson.datatype.jsr310;
    opens org.sbcm.Controller.CreateAsistentControllers to javafx.fxml;
    opens org.sbcm.Controller.DatosUsuarioController to javafx.fxml;
    opens org.sbcm.Controller.Boards to javafx.fxml;

    exports org.sbcm.Dao;
    exports org.sbcm.Model;
    exports org.sbcm.Controller;

    exports org.sbcm.Model.SingletonModels;
    exports org.sbcm.Controller.CreateAsistentControllers to javafx.fxml;
    exports org.sbcm.Controller.DatosUsuarioController to javafx.fxml;
    exports org.sbcm.Controller.Boards to javafx.fxml;

}