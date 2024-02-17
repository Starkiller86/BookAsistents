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
import org.sbcm.SingletonModels.AdultSingleton;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectAdultController implements Initializable {
    @FXML
    private TableView<Adult> TableResult;
    @FXML
    private TableColumn<Adult, String> apellidoC;
    @FXML
    private TableColumn<Adult, String> discapacidadC;
    @FXML
    private TableColumn<Adult, Integer> edadC;
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
        edadC.setCellValueFactory(new PropertyValueFactory<>("edad"));
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
