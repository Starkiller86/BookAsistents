package org.sbcm.Controller.DatosUsuarioController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.PersonalDataAdultDaoImp;
import org.sbcm.Model.PersonalDataAdult;
import org.sbcm.SingletonModels.AdultSingleton;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportantDataAdultController implements Initializable {

    @FXML
    private TextField domicilioDAC;
    @FXML
    private TextField npersoanlDAC;
    @FXML
    private TextField nemergenciaDAC;
    @FXML
    private Button dataadult;
    CRUD<PersonalDataAdult> personalCRUD = new PersonalDataAdultDaoImp();
    @FXML
    public void dataAdultAction(ActionEvent actionEvent) {
        if (domicilioDAC.getText().equals("") && npersoanlDAC.getText().equals("") && nemergenciaDAC.getText().equals(""))
        {
            Stage stage = (Stage) dataadult.getScene().getWindow();
            stage.close();
        }
        AdultSingleton singleton = AdultSingleton.getInstance();
        try{
            PersonalDataAdult personal = new PersonalDataAdult();
            personal.setDomicilio(domicilioDAC.getText());
            personal.setIdAdulto(singleton.getId());
            personal.setNumeroemergencia(nemergenciaDAC.getText());
            personal.setNumeropersonal(npersoanlDAC.getText());
            int idPersonal = personalCRUD.postResourse(personal);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Correcto");
            alert.setHeaderText("Se ha registrado la información personal con exito");
            alert.showAndWait();
            //Hay que tomar en cuenta que si no tiene nada puede que no necesiteos registrar estos datos ni llamar al servidor, entonces si todos los datos están vacios no hacer nada
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro INCORRECTO");
            alert.setHeaderText("NO SE HA REGISTRADO LA INFORMACIÓN PERSONAL");
            alert.setContentText("Comunique con soporte con el siguiente mensaje: " + e.getMessage());
            alert.showAndWait();
        }
        Stage stage = (Stage) dataadult.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
