package org.sbcm.Controller.CreateAsistentControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAsistentAdultController implements Initializable {
    @FXML private TextField nRegistroAsistenciaRA;
    @FXML private TextField nVisitasAsistenciaRA;
    @FXML private DatePicker fechasVisitasAsistenciaRA;
    @FXML private Button agregarAsistenciaRA;
    @FXML private Button cancelarRA;

    public TextField getnRegistroAsistenciaRA() {
        return nRegistroAsistenciaRA;
    }

    public void setnRegistroAsistenciaRA(TextField nRegistroAsistenciaRA) {
        this.nRegistroAsistenciaRA = nRegistroAsistenciaRA;
    }

    public TextField getnVisitasAsistenciaRA() {
        return nVisitasAsistenciaRA;
    }

    public void setnVisitasAsistenciaRA(TextField nVisitasAsistenciaRA) {
        this.nVisitasAsistenciaRA = nVisitasAsistenciaRA;
    }

    public DatePicker getFechasVisitasAsistenciaRA() {
        return fechasVisitasAsistenciaRA;
    }

    public void setFechasVisitasAsistenciaRA(DatePicker fechasVisitasAsistenciaRA) {
        this.fechasVisitasAsistenciaRA = fechasVisitasAsistenciaRA;
    }

    public Button getAgregarAsistenciaRA() {
        return agregarAsistenciaRA;
    }

    public void setAgregarAsistenciaRA(Button agregarAsistenciaRA) {
        this.agregarAsistenciaRA = agregarAsistenciaRA;
    }

    public Button getCancelarRA() {
        return cancelarRA;
    }

    public void setCancelarRA(Button cancelarRA) {
        this.cancelarRA = cancelarRA;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
