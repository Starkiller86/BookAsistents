package org.sbcm.Controller.CreateAsistentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Model.Adult;
import org.sbcm.Model.SingletonModels.AdultSingleton;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SelectAdultController implements Initializable {
    @FXML
    private TableView<Adult> TableResult;
    @FXML
    private TableColumn<Adult, String> apellidoC;
    @FXML
    private TableColumn<Adult, String> discapacidadC;
    @FXML
    private TableColumn<Adult, Date> fechaNacimientoC;
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
    CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();


    @FXML public void marcarButtonAction(ActionEvent event){
        //Del lado de la ventana vamos a hacer la consulta de busqueda de coincidencias con el nombre, pero para ello ocupamos el singleton
        AdultSingleton adultSingleton = AdultSingleton.getInstance();
        adultSingleton = null;
        adultSingleton = AdultSingleton.getInstance();
        try{

            Adult adult = TableResult.getSelectionModel().getSelectedItem();
            if (adult == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("No seleccionaste ningun Usuario");
                alert.setContentText("Selecciona un usuario para continuar");
                //Configuramos la ventana como ShowandAwait para que no nos deje interactuar con la interfaz mientras está abierta
                alert.showAndWait();
            }
            assert adult != null;

            if(adult.getNVisitas()+1 >= 6 && !adult.getTipoDeVisitante().equals("Con Credencial")){
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setTitle("Tramite de Credencial");
                 alert.setHeaderText("Ya puedes tramitar tu credencial!");
                 alert.setContentText("¿Deseas tramitar tu crendencial?");
                 alert.showAndWait().ifPresent(response ->{
                     if (response == ButtonType.OK)
                     {
                         //Aqui va la logica de cambiar el valor de tipo de usuario  si solo es frecuente, no frecuente, con credencial etc
                         adult.setTipoDeVisitante("Con Credencial");
                     }
                     else {
                        adult.setTipoDeVisitante("Frecuente");
                     }
                 });

            }

            adultCRUD.putAssistance(adult);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Asistencia Registrada");
            alert.setContentText("La asistencia fue registrada correctamente");
            alert.showAndWait();
            Stage stage = (Stage) marcarButton.getScene().getWindow();
            stage.close();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR DE CONEXIÓN");
            alert.setContentText("Los cambios no fueron registrados");

        }
        //el nombre completo con los apeidos esta guardado el propiedad nombre, pero necesitamos analizar un momento el servidor
        //Aquí vamos a darle funcionalidad al marcar asistencia, que solo será buscar su id y aumentar 1 a noAsistencias, como una consulta actualizar pero sin ningún cambio mas que al numero de visitas

    }
    @FXML public void cancelarButtonActioon(ActionEvent event){
        try{
            AdultSingleton adultSingleton = AdultSingleton.getInstance();
            adultSingleton = null;
            Stage stage = (Stage) cancelarButton.getScene().getWindow();
            stage.close();
        }catch (Exception e){

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        apellidoC.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        discapacidadC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));
        fechaNacimientoC.setCellValueFactory(new PropertyValueFactory<>("simpleDate"));
        escolaridadC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));
        generoC.setCellValueFactory(new PropertyValueFactory<>("genero"));
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        noVisitasC.setCellValueFactory(new PropertyValueFactory<>("nVisitas"));
        nombreC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ocupacionC.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));
        tipoVisitanteC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitante"));
        //Al momento de inicializar vamos a obtener el nombre que colocamas en el singleton en el atributo nombre
        AdultSingleton adultSingleton = AdultSingleton.getInstance();
        //Y lo vamos a utilizar para mandarlo por el crud al servidor
        try{
            TableResult.setItems(adultCRUD.getAllResourcesByName(adultSingleton.getNombre()));
            //Una vez que terminemos ya la consulta ya no es necesario guardar el valor de adultSingleton
            adultSingleton = null;
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }


    }
}
