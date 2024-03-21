package org.sbcm.Controller.DatosUsuarioController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.PersonalDataKidDaolmp;
import org.sbcm.Model.Kid;
import org.sbcm.Model.PersonalDataKid;
import org.sbcm.Model.SingletonModels.KidSinglenton;

import javax.swing.text.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportantDataKidController implements Initializable {
    @FXML
    private TextField domicilioDKC;
    @FXML
    private TextField npersonalDKC;
    @FXML
    private TextField nemergenciaDKC;
    @FXML
    private Button datakid;
    CRUD<PersonalDataKid> personalDataKid = new PersonalDataKidDaolmp();
    @FXML
    public void dataKidAction(ActionEvent actionEvent) throws Exception{
        if (domicilioDKC.getText().isEmpty() && npersonalDKC.getText().isEmpty() && nemergenciaDKC.getText().isEmpty())
        {
            Stage stage = (Stage) datakid.getScene().getWindow();
            stage.close();
            throw new Exception();
        }
        //Esta instancia de singleton viene desde la primera interfaz donde registramos los primeros datos
        //Cómo tal no le tenemos que mover nada
        KidSinglenton singlenton = KidSinglenton.getInstance();
        try {
            //Aquí está la razon por la cual hicimos la herencia
            //Porque tenía que crear un nuevo kid y a sus atributos colocarle los valores que tenía en el singleton
            Kid kid = new Kid();
            kid.setId(singlenton.getId());
            kid.setNombre(singlenton.getNombre());
            kid.setApellido(singlenton.getApellido());
            kid.setFechaNacimiento(singlenton.getFechaNacimiento());
            kid.setGenero(singlenton.getDiscapacidad());
            kid.setEscolaridad(singlenton.getEscolaridad());
            kid.setOcupacion(singlenton.getOcupacion());
            //Ya una vez que le colocamos los datos que venian desde la otra interfaz
            //Teniamos que crear el modelo de la información personal
            PersonalDataKid personalDataKid1 = new PersonalDataKid();
            //Y tambien tenemos que tener el atributo kid
            //Aquí me pide que le mande un kid del modelo Kid
            //Al realizar herencia volvemos al principio de que un ejecutivo y un abogado son Personas, en to do caso de que heredan de la clase persona, como KidSingleton hereda de Kid, pues tambien es kid, entonces se lo podemos mandar tal cual
            personalDataKid1.setIdKid(singlenton);
            personalDataKid1.setDomicilio(domicilioDKC.getText());
            personalDataKid1.setNumeropersonal(npersonalDKC.getText());
            personalDataKid1.setNumeroemergencia(nemergenciaDKC.getText());
            int idPersonal = personalDataKid.postResourse(personalDataKid1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro correcto");
            alert.setHeaderText("se ha registrado la informacion personal con exito con el ID: " + idPersonal);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro incorrecto");
            alert.setHeaderText("NO SE HA REGISTRADO LA INFORMACION PERSONAL");
            alert.setContentText("comuniquese con soporte con el siguiente mensaje" +e.getMessage());
            alert.showAndWait();
            throw new Exception(e);
        }
        Stage stage = (Stage) datakid.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
