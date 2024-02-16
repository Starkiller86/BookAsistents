package org.sbcm.Controller.CreateAsistentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sbcm.Model.Adult;
import org.sbcm.SingletonModels.AdultSingleton;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectAdultController implements Initializable {
    @FXML
    private TableView<Adult> TableResult;
    @FXML
    private TableColumn<Adult, String> apellidoC;
    @FXML
    private TableColumn<Adult, String> discapacidadC;
    @FXML
    private TableColumn<Adult, Integer> edadC;
    @FXML
    private TableColumn<Adult, String> escolaridadC;
    @FXML
    private TableColumn<Adult, String> generoC;
    @FXML
    private TableColumn<Adult, Integer> idC;
    @FXML
    private TableColumn<Adult, Integer> noVisitasC;
    @FXML
    private TableColumn<Adult, String> nombreC;
    @FXML
    private TableColumn<Adult, String> ocupacionC;
    @FXML
    private TableColumn<Adult, String> tipoVisitanteC;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button marcarButton;

    AdultSingleton adultSingleton = AdultSingleton.getInstance();

    @FXML public void marcarButtonAction(ActionEvent event){

    }
    @FXML public void cancelarButtonActioon(ActionEvent event){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        apellidoC.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        discapacidadC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));
        edadC.setCellValueFactory(new PropertyValueFactory<>("edad"));
        escolaridadC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));
        generoC.setCellValueFactory(new PropertyValueFactory<>("genero"));
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        noVisitasC.setCellValueFactory(new PropertyValueFactory<>("nVisitas"));
        nombreC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ocupacionC.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));
        tipoVisitanteC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitante"));
    }
}
