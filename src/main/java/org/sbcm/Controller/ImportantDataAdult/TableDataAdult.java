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
    @FXML private TextField domicilioRAC;
    @FXML private TextField npersonalRAC;
    @FXML private TextField nemergenciaRAC;
    @FXML private TableView<Adult> dataTableAC;
    @FXML private Button dataadult;
    @FXML private void buttonActionRegisterData(ActionEvent event) throws Exception{
        Adult adult = new Adult();
        CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();
        try {
            Adult adulto = dataTableAC.getSelectionModel().getSelectedItem();
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
            adultSingleton.setNpersonal(adulto.getNumeropersonal());
            adultSingleton.setNemergencia(adulto.getNumeroemergencia());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasdeAsistencia/ImportantDataAdult.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Datos Importantes del Usuario");
            stage.setScene(scene);
            stage.showAndWait();
            adultSingleton = null;
            dataTableAC.setItems(adultCRUD.getAllResources());
        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
