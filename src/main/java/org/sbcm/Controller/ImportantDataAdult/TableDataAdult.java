package org.sbcm.Controller.ImportantDataAdult;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Model.Adult;
import org.sbcm.SingletonModels.AdultSingleton;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TableDataAdult implements Initializable {
    @FXML private TextField domicilioDAC;
    @FXML private TextField npersonalDAC;
    @FXML private TextField nemergenciaDAC;
    @FXML private TableView<Adult> dataTableDAC;
    @FXML private Button dataadult;
    @FXML private void buttonActionRegisterData(ActionEvent event) throws Exception{
        Adult adult = new Adult();
        CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();
        try {
            adult.setDomicilio(domicilioDAC.getText());
            adult.setNpersonal(npersonalDAC.getText());
            adult.setNemergencia(nemergenciaDAC.getText());
            System.out.println(new ObjectMapper().writeValueAsString(adult));
            Adult adulto = dataTableDAC.getSelectionModel().getSelectedItem();
            if (adulto == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("No pusiste ningun dato");
                alert.setContentText("Coloca los datos");
                alert.showAndWait();
            }
            assert adulto != null : "Adulto es nulo";
            AdultSingleton adultSingleton = AdultSingleton.getInstance();
            adultSingleton.setDomicilio(adulto.getDomicilio());
            adultSingleton.setNpersonal(adulto.getNpersonal());
            adultSingleton.setNemergencia(adulto.getNemergencia());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasdeAsistencia/ImportantDataAdult.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Datos Importantes del Usuario");
            stage.setScene(scene);
            stage.showAndWait();
            adultSingleton = null;
            dataTableDAC.setItems(adultCRUD.getAllResources());
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("No pusiste ningun dato");
            alert.setContentText("Coloca los datos");
            alert.showAndWait();
            throw new Exception(e);
        }
        try {
            adultCRUD.postResourse(adult);
            dataTableDAC.setItems(adultCRUD.getAllResources());
            domicilioDAC.setText("");
            npersonalDAC.setText("");
            nemergenciaDAC.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro de Datos Correcto");
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            throw new Exception(exception);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
