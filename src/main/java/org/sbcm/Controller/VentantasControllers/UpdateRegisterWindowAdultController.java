package org.sbcm.Controller.VentantasControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Model.Adult;
import org.sbcm.Model.SingletonModels.AdultSingleton;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateRegisterWindowAdultController implements Initializable {
    @FXML
    private TextField idField;
    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML
    private DatePicker fechaNacimientoField;
    @FXML
    private TextField generoFIeld;
    @FXML
    private TextField discapacidadField;
    @FXML
    private TextField escolaridadField;
    @FXML
    private TextField ocupacionField;
    @FXML
    private TextField noVisitasField;
    @FXML
    private TextField tipoVisitanteField;
    @FXML
    private Button buttonActualizar;
    @FXML
    private Button buttonCancelar;

    //Declaramos la implementacion de crud correspondiente a esta ventana de actualizacion, que es la implementacion para adultos
    CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();
    AdultSingleton adultSingleton = AdultSingleton.getInstance();

    //Creamos la funcion que se va a ejecutar al momento de presionar el botón actualizar

    @FXML public void buttonActualizarAction(ActionEvent event){
        //Colocamos to-do dentro de un try
        try
        {
            //Primero vamos a colocar cada uno de los datos que se encuentren en la interfaz en un nuevo modelo de adult
            Adult adult = new Adult();
            adult.setNVisitas(Integer.parseInt(noVisitasField.getText())); //Colocamos como valor en cada uno de sus atributos el valor del campo de texto en la interfaz
            adult.setOcupacion(ocupacionField.getText());
            //adult.setFechaNacimiento(Date.from(fechaNacimientoField.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            adult.setEscolaridad(escolaridadField.getText());
            adult.setGenero(generoFIeld.getText());
            adult.setTipoDeVisitante(tipoVisitanteField.getText());
            adult.setDiscapacidad(discapacidadField.getText());
            adult.setId(Integer.parseInt(idField.getText()));

            adult.setNombre(nombreField.getText());
            adult.setApellido(apellidoField.getText());
            System.out.println(adult.getFechaNacimiento());
            //Una vez que tenemos nuestro modelo con todos los datos de la interfaz ya podemos mandarlo al servidor para que lo registre en la base de datos
            adultCRUD.putResource(adult);
            adultSingleton = null;
            //Ahora en caso de que haya hecho la función correctamente vamos a cerrar la ventana
            Stage stage = (Stage) buttonActualizar.getScene().getWindow();//Aqui tomamos la instancia de la ventana
            stage.close();
        }catch (Exception e){
            //En caso de que la función falle al actualizar mostraremos una ventana que nos muestre un mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al actualizar");
            alert.setHeaderText("Hubo un error al actualizar los datos");
            alert.setContentText("Comuniquese con soporte: " + e.getMessage());
            System.out.println(e.getMessage());
            adultSingleton = null;
        }
    }
    @FXML public void buttonCancelarAction(ActionEvent event){
        try {
            adultSingleton = null;
            Stage stage = (Stage) buttonCancelar.getScene().getWindow();
            stage.close();
        }catch (Exception e){

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();
        /**Creamos la instancia de AdultSingleton aqui tambien para que podamos acceder a los datos que tenga en ese momento**/

        /**Al momento de inicializar este controlador junto con su interfaz vamos a colcar los datos del singleton en cada uno de los campos para que los mpueda modificar**/
        nombreField.setText(adultSingleton.getNombre());
        apellidoField.setText(adultSingleton.getApellido());
        idField.setText(String.valueOf(adultSingleton.getId()));
        //LocalDate local = adultSingleton.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //fechaNacimientoField.setValue(adultSingleton.getFechaNacimiento());
        generoFIeld.setText(adultSingleton.getGenero());
        discapacidadField.setText(adultSingleton.getDiscapacidad());
        escolaridadField.setText(adultSingleton.getEscolaridad());
        ocupacionField.setText(adultSingleton.getOcupacion());
        noVisitasField.setText(String.valueOf(adultSingleton.getNVisitas()));
        tipoVisitanteField.setText(adultSingleton.getTipoDeVisitante());
    }


}
