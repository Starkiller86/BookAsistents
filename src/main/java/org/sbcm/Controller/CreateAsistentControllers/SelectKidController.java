package org.sbcm.Controller.CreateAsistentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.KidRegisterdaoImp;
import org.sbcm.Model.Kid;
import org.sbcm.Model.SingletonModels.KidSinglenton;


import java.net.URL;
import java.util.ResourceBundle;

public class SelectKidController implements Initializable {
    @FXML
    private TableView<Kid> TablaResultKid;
    @FXML
    private TableColumn<Kid, Integer> idKC;
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

    CRUD<Kid> kidCRUD = new KidRegisterdaoImp();
    @FXML public void marcarButtonKidAction(ActionEvent event){
        KidSinglenton kidSinglenton = KidSinglenton.getInstance();
        kidSinglenton = null;
        kidSinglenton = KidSinglenton.getInstance();
        try {
            Kid kid = TablaResultKid.getSelectionModel().getSelectedItem();
            if (kid == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("No seleccionaste ninguna Usuario");
                alert.setContentText("Selecciona un Usuario");
                alert.showAndWait();
            }
            assert kid != null;
            if(kid.getNVisitas()+1>=6 && !kid.getTipoDeVisitante().equals("Con Credencial")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Tramite de Credencial");
                alert.setHeaderText("Ya puedes tramitar tu credencial!");
                alert.setContentText("¿Deseas tramitar tu credencial?");
                alert.showAndWait().ifPresent(response ->{
                    if(response == ButtonType.OK){
                        kid.setTipoDeVisitante("Con Credencial");
                    }else {
                        kid.setTipoDeVisitante("Frecuente");
                    }
                });
            }
            kidCRUD.putResource(kid);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Asistencia Registrada");
            alert.setContentText("La asistencia fue registrada correctamente");
            alert.showAndWait();
            Stage stage = (Stage) asistenciabuttonKC.getScene().getWindow();
            stage.close();
    }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error de conexión");
            alert.setContentText("Los cambios no fueron registrados");
        }
    }

    @FXML public void cancelarButtonKidAction(javafx.event.ActionEvent event){
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
        KidSinglenton kidSinglenton = KidSinglenton.getInstance();
        idKC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreKC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoKC.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        edadKC.setCellValueFactory(new PropertyValueFactory<>("simpleDate"));
        generoKC.setCellValueFactory(new PropertyValueFactory<>("genero"));
        escolaridadKC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));
        discapacidadKC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));
        ocupacionKC.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));
        nvisitasKC.setCellValueFactory(new PropertyValueFactory<>("nVisitas"));
        tipodevisitanteKC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitante"));
        try{
            TablaResultKid.setItems(kidCRUD.getAllResourcesByName(kidSinglenton.getNombre()));
            kidSinglenton = null;
        }catch (Exception e){
            throw  new RuntimeException();
        }




    }


}
