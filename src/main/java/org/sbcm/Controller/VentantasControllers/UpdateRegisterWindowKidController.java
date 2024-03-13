package org.sbcm.Controller.VentantasControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.KidRegisterdaoImp;
import org.sbcm.Model.Kid;
import org.sbcm.SingletonModels.KidSinglenton;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateRegisterWindowKidController implements Initializable {

    @FXML
    private TextField idFieldKid;

    @FXML
    private TextField nombreFieldKid;

    @FXML
    private  TextField apellidoFieldKid;

    @FXML
    private TextField edadFieldKid;

    @FXML
    private TextField generoFieldKid;

    @FXML
    private TextField discapacidadFieldKid;

    @FXML
    private TextField escolaridadFieldKid;

    @FXML
    private TextField ocupacionFieldKid;

    @FXML
    private TextField nVisitasFieldKid;

    @FXML
    private TextField tipoDeVisitanteFieldKid;

    @FXML
    private Button buttonActualizarK;

    @FXML
    private Button buttonCancelarK;
    CRUD<Kid> kidCRUD = new KidRegisterdaoImp();
    /**Creamos la instancia de KidSingleton aquí tambien para que podamos acceder a los datos**/
    KidSinglenton kidSinglenton = KidSinglenton.getInstance();
    @FXML public void buttonActualizarKidAction(ActionEvent event){
        try {
            Kid kid = new Kid();
            kid.setNVisitas(Integer.parseInt(nVisitasFieldKid.getText()));
            kid.setNombre(nombreFieldKid.getText());
            kid.setApellido(apellidoFieldKid.getText());
            kid.setOcupacion(ocupacionFieldKid.getText());
            kid.setFechaNacimiento(edadFieldKid.getText());
            kid.setEscolaridad(escolaridadFieldKid.getText());
            kid.setGenero(generoFieldKid.getText());
            kid.setTipoDeVisitante(tipoDeVisitanteFieldKid.getText());
            kid.setDiscapacidad(discapacidadFieldKid.getText());
            kid.setId(Integer.parseInt(idFieldKid.getText()));
            kidCRUD.putResource(kid);
            kidSinglenton = null;
            Stage stage = (Stage) buttonActualizarK.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al Actualizar");
            alert.setHeaderText("Hubo un error al actualizar los datos");
            alert.setContentText("Comuniquese con soporte: " + e.getMessage());
            System.out.println(e.getMessage());
            kidSinglenton=null;
        }
    }
    @FXML public void buttonCancelarKAction(ActionEvent event){
        try {
            kidSinglenton = null;
            Stage stage = (Stage) buttonCancelarK.getScene().getWindow();
            stage.close();
        }catch (Exception e){

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /**Al momento de inicializar este controlador junto con su interfaz vamos a colocar los datos del singlenton en cada uno de los campos para poder modificar los datos**/
        idFieldKid.setText(String.valueOf(kidSinglenton.getId()));
        nombreFieldKid.setText(kidSinglenton.getNombre());
        apellidoFieldKid.setText(kidSinglenton.getApellido());
        edadFieldKid.setText(String.valueOf(kidSinglenton.getFechadenacimiento()));
        generoFieldKid.setText(kidSinglenton.getGenero());
        discapacidadFieldKid.setText(kidSinglenton.getDiscapacidad());
        escolaridadFieldKid.setText(kidSinglenton.getEscolaridad());
        ocupacionFieldKid.setText(kidSinglenton.getOcupacion());
        nVisitasFieldKid.setText(String.valueOf(kidSinglenton.getnVisitas()));
        tipoDeVisitanteFieldKid.setText(kidSinglenton.getTipoDeVisitante());
    }
}
