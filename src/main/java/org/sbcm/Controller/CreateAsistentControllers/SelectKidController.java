package org.sbcm.Controller.CreateAsistentControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.sbcm.Model.Adult;
import org.sbcm.Model.Kid;
import org.sbcm.SingletonModels.AdultSingleton;
import org.sbcm.SingletonModels.KidSinglenton;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SelectKidController implements Initializable {
    @FXML
    private TableView<Kid> TablaResultKid;
    @FXML
    private TableColumn<Kid, String> idKC;
    @FXML
    private TableColumn<Kid,String> nombreKC;
    @FXML
    private TableColumn<Kid, String> apellidoKC;
    @FXML
    private TableColumn<Kid, Integer> edadKC;
    @FXML
    private TableColumn<Kid, String> generoKC;
    @FXML
    private TableColumn<Kid, String> escolaridadKC;
    @FXML
    private TableColumn<Kid, String> discapacidadKC;
    @FXML
    private TableColumn<Kid, String> ocupacionKC;
    @FXML
    private TableColumn<Kid, Integer> nvisitasKC;
    @FXML
    private TableColumn<Kid, String> tipodevisitanteKC;
    @FXML
    private Button cancelarbuttonKC;
    @FXML
    private Button asistenciabuttonKC;
    @FXML public void marcarButtonKidAction(ActionEvent event){
        KidSinglenton kidSinglenton = KidSinglenton.getInstance();
    }

    @FXML public void cancelarButtonKidAction(ActionEvent event){
        try {
            KidSinglenton kidSinglenton = KidSinglenton.getInstance();
            kidSinglenton = null;
            Stage stage = (Stage) cancelarbuttonKC.getScene().getWindow();
            stage.close();
        }catch (Exception e){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreKC.setCellValueFactory(new PropertyValueFactory<>(""));




    }
}
