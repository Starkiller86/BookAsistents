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
import org.sbcm.Model.Adult;
import org.sbcm.Model.PersonalDataAdult;
import org.sbcm.Model.SingletonModels.AdultSingleton;

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
    public void dataAdultAction(ActionEvent actionEvent) throws Exception {
        if (domicilioDAC.getText().isEmpty() && npersoanlDAC.getText().isEmpty() && nemergenciaDAC.getText().isEmpty())
        {
            Stage stage = (Stage) dataadult.getScene().getWindow();
            stage.close();
            throw new Exception();
        }
        AdultSingleton singleton = AdultSingleton.getInstance();
        try{
            Adult adult = new Adult();
            adult.setId(singleton.getId());
            adult.setNombre(singleton.getNombre());
            adult.setApellido(singleton.getApellido());
            adult.setFechaNacimiento(singleton.getFechaNacimiento());
            adult.setGenero(singleton.getGenero());
            adult.setEscolaridad(singleton.getEscolaridad());
            adult.setDiscapacidad(singleton.getDiscapacidad());
            adult.setOcupacion(singleton.getOcupacion());
            adult.setNVisitas(singleton.getNVisitas());
            adult.setTipoDeVisitante(singleton.getTipoDeVisitante());

            PersonalDataAdult personal = new PersonalDataAdult();

            personal.setDomicilio(domicilioDAC.getText());
            personal.setIdAdulto(singleton);
            personal.setNumeroemergencia(nemergenciaDAC.getText());
            personal.setNumeropersonal(npersoanlDAC.getText());
            int idPersonal = personalCRUD.postResourse(personal);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Correcto");
            alert.setHeaderText("Se ha registrado la información personal con exito con el ID: "+ idPersonal);
            alert.showAndWait();
            //Hay que tomar en cuenta que si no tiene nada puede que no necesiteos registrar estos datos ni llamar al servidor, entonces si todos los datos están vacios no hacer nada
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro INCORRECTO");
            alert.setHeaderText("NO SE HA REGISTRADO LA INFORMACIÓN PERSONAL");
            alert.setContentText("Comunique con soporte con el siguiente mensaje: " + e.getMessage());
            alert.showAndWait();
            throw new Exception();
        }
        Stage stage = (Stage) dataadult.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
