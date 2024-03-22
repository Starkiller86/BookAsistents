package org.sbcm.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.KidRegisterdaoImp;
import org.sbcm.Model.Kid;
import org.sbcm.Model.SingletonModels.KidSinglenton;

import java.net.URL;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistroDeLibreriasControllerKid implements Initializable {
    CRUD<Kid> kidCRUD = new KidRegisterdaoImp();

    /**
     * Tab Busqueda
     */

    @FXML TextField nombreyapellidoField;
    @FXML Button buscarButton;

    @FXML private void buscarButtonAction(ActionEvent event) throws Exception{
        KidSinglenton kidSinglenton =  KidSinglenton.getInstance();
        kidSinglenton.setNombre(nombreyapellidoField.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/VentanasdeAsistencia/SelectKidInterface.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Seleccionar Usuario Registrado");
        stage.setScene(scene);
        stage.showAndWait();

        try
        {
            nombreyapellidoField.setText("");
            ListKid.setItems(kidCRUD.getAllResources());
        }
        catch (Exception e){
            throw new Exception();
        }
    }

    /**Tab KidRegister**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML
    private TextField nRegistroRI;
    @FXML private TextField nombreRI;
    @FXML private TextField apellidoRI;
    @FXML private DatePicker dateKid;
    @FXML private TextField textInput;
    @FXML private TextField TextInput;
    @FXML private TextField TextInputRI;
    @FXML private TextField textInputRI;
    @FXML private TextField nVisitasRI;
    @FXML private Button buttonRI;

    /**Grupo Género**/
    @FXML private RadioButton femRI;
    @FXML private RadioButton masRI;
    final ToggleGroup grupogeneroK = new ToggleGroup();
    /**Grupo Discapacidad**/
    @FXML private RadioButton siRI;
    @FXML private RadioButton noRI;
    final ToggleGroup grupodiscapacidadK = new ToggleGroup();
    /**Grupo Escolaridad**/
    @FXML private RadioButton preRI;
    @FXML private RadioButton priRI;
    @FXML private RadioButton secuRI;
    @FXML private RadioButton bachoRI;
    @FXML private RadioButton licRI;
    @FXML private RadioButton posRI;
    final ToggleGroup grupoescolaridadK = new ToggleGroup();
    /**Grupo Ocupación**/
    @FXML private RadioButton hogRI;
    @FXML private RadioButton estRI;
    @FXML private RadioButton emploRI;
    @FXML private RadioButton desRI;
    final  ToggleGroup grupoocupacionK = new ToggleGroup();



    /**En esta parte del código lo que realiza es determinar la función que el botón va a hacer en la interfaz y la base de datos.**/
    @FXML private  void buttonActionInfantil(ActionEvent ignoreEvent)throws Exception{
        Kid kid = new Kid();
        KidSinglenton kidSinglenton =KidSinglenton.getInstance();
        try {
            kid.setNombre(nombreRI.getText());
            kidSinglenton.setNombre(kid.getNombre());
            kid.setApellido(apellidoRI.getText());
            kidSinglenton.setApellido(kid.getApellido());
            kid.setFechaNacimiento(Date.from(dateKid.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            kidSinglenton.setFechaNacimiento(kid.getFechaNacimiento());
            kid.setGenero(((RadioButton) grupogeneroK.getSelectedToggle()).getText());
            kidSinglenton.setGenero(kid.getGenero());
            kid.setDiscapacidad(((RadioButton) grupodiscapacidadK.getSelectedToggle()).getText());
            kidSinglenton.setDiscapacidad(kid.getDiscapacidad());
            kid.setEscolaridad(((RadioButton) grupoescolaridadK.getSelectedToggle()).getText());
            kidSinglenton.setEscolaridad(kid.getEscolaridad());
            kid.setOcupacion(((RadioButton) grupoocupacionK.getSelectedToggle()).getText());
            kidSinglenton.setOcupacion(kid.getOcupacion());
            kid.setTipoDeVisitante("No Frecuente");
            kidSinglenton.setTipoDeVisitante(kid.getTipoDeVisitante());
            System.out.println(new ObjectMapper().writeValueAsString(kid));
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Hubo un error");
            alert.setContentText("Coloque los datos");
            alert.showAndWait();
            throw new Exception();
        }
        try {
            System.out.println("Fecha de nacimiento" + kidSinglenton.getFechaNacimiento().toString());
            int idkid = kidCRUD.postResourse(kidSinglenton);
            ListKid.setItems(kidCRUD.getAllResources());
            nombreRI.setText("");
            apellidoRI.setText("");
            dateKid.setValue(null);
            grupogeneroK.selectToggle(null);
            grupodiscapacidadK.selectToggle(null);
            grupoescolaridadK.selectToggle(null);
            grupoocupacionK.selectToggle(null);
            tipoDeVisitanteKC.setText("");

            kidSinglenton.setId(idkid);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalData/ImportantDataKid.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrar datos personales");
            stage.setScene(scene);
            stage.showAndWait();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Correcto");
            alert.setHeaderText("el registro se hico correctamente con el id: " + idkid);
            alert.showAndWait();
        }catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error de conexión");
            alert.setContentText("Comunique a soporte: "+exception);
            throw new Exception(exception);
        }
    }

    /**Tab KidList**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML
    private TableView<Kid> ListKid;
    @FXML
    private TableColumn<Kid, Integer> idKC;
    @FXML private TableColumn<Kid, String> nombreKC;
    @FXML private TableColumn<Kid, String> apellidoKC;
    @FXML
    private TableColumn<Kid,String> edadKC;
    @FXML
    private TableColumn<Kid, String> generoKC;
    @FXML
    private TableColumn<Kid, String> discapacidadKC;
    @FXML
    private TableColumn<Kid, String> escolaridadKC;
    @FXML
    private TableColumn<Kid, String> ocupacionKC;
    @FXML
    private TableColumn<Kid, Integer>nVisitasKC;
    @FXML
    private TableColumn<Kid, String> tipoDeVisitanteKC;
    @FXML private Button deleteRegisterRI;
    @FXML private Button upDateRegisterRI;
    @FXML private Button findRegisterRI;

    /**En esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.**/

    @FXML private void deleteRegisterRIaction(ActionEvent event) throws Exception{
        //Creamos un alert para brindar confirmación al usuario
        Alert alert;
        //Extraemos el modelo seleccionado de la tabla
        Kid selectedKid = ListKid.getSelectionModel().getSelectedItem();
        //Verificamos que el valor no sea nulo, si no es nulo mandamos el mensaje de confirmación
        if (selectedKid!=null){
            //Creamos el mensaje de confirmacion
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Realmente desea eliminar el registro?");
            alert.setContentText("Registro: \n ID: " + selectedKid.getId() +
                    "\n Nombre: " + selectedKid.getNombre() +
                    "\n Apellido: " + selectedKid.getApellido() +
                    "\n Fecha de Nacimiento: " + selectedKid.getFechaNacimiento() +
                    "\n Género: " + selectedKid.getGenero() +
                    "\n Escolaridad: " + selectedKid.getEscolaridad() +
                    "\n Discapacidad: " + selectedKid.getDiscapacidad() +
                    "\n Ocupación: " + selectedKid.getOcupacion());
            //Mostramos y Extraemos la respuesta y la guardamos en un ButtonType
            ButtonType respuesta = alert.showAndWait().orElse(ButtonType.CANCEL);
            //Verificamos que decidió el usuario
            if(respuesta == ButtonType.OK){
                //Si la respuesta es OK realizamos la operación delete y actualizamos la lista en la tabla
                //Para ello necesitamos un try catch para la comunicación en servidor y verificar si se realizó correctamente
                try {
                    //Colocamos el id del kid seleccionado en la tabla
                    kidCRUD.deleteResource(selectedKid.getId());
                    //Actualizamos la tabla
                    ListKid.setItems(kidCRUD.getAllResources());
                    //Si en este punto no ha pasado al catch, las operaciones fueron exitosas, por lo tanto se lo haremos saber al usuario con un alert
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Operación Exitosa");
                    alert.setHeaderText("El siguiente registro ha sido eliminado con exito!");
                    alert.setContentText("Registro: \n ID: " + selectedKid.getId() +
                            "\n Nombre: " + selectedKid.getNombre() +
                            "\n Apellido: " + selectedKid.getApellido() +
                            "\n Fecha de Nacimiento: " + selectedKid.getFechaNacimiento() +
                            "\n Género: " + selectedKid.getGenero() +
                            "\n Escolaridad: " + selectedKid.getEscolaridad() +
                            "\n Discapacidad: "+ selectedKid.getDiscapacidad() +
                            "\n Ocupación: " + selectedKid.getOcupacion());
                    alert.showAndWait();
                }catch (Exception e){
                    //En caso de surgir algún error durante el try se ejecutará el catch, de lo contrario solo se omite en la ejecución
                    //Mostraremos un mensaje al usuario indicando que hubo un error y debe comunicarse con soporte
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Ha ocurrido un error en el SISTEMA");
                    alert.setContentText("Favor de comunicarse con soporte al correo socialservice285@gmail.com y proporcionar la siguiente información \n " + e);
                    alert.showAndWait();
                    //Dado que es un error de sistema o conexión solo lo puede atender el de sistemas
                    throw new Exception(e);
                }
            }
        }
        else{
            //Esto va dentro de un else perdón
            //En caso de que no haya seleccionado nada tambien le daremos un mensaje de que debe seleccionar un fila antes de continuar
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No ha seleccionado ninguna FILA");
            alert.setContentText("Seleccione una fila para eliminar");
            //Ahora cada uno de los alerte debe pausar el programa para que no sea posible interactuar con el a menos de que el alert desaparezca, para ello usamos ShowAwait en todos
            alert.showAndWait();
        }

    }
    @FXML private void upDateRegisterRIaction(ActionEvent event) throws Exception{
        try{
            Kid kidSelected = ListKid.getSelectionModel().getSelectedItem();
            if(kidSelected == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("No seleccionaste ningun usuario");
                alert.setContentText("Selecciona un Usuario para continuar");
                alert.showAndWait();
            }
            assert kidSelected != null : "Kid es nulo";
            KidSinglenton kidSinglenton = KidSinglenton.getInstance();
            kidSinglenton.setId(kidSelected.getId());
            kidSinglenton.setNombre(kidSelected.getNombre());
            kidSinglenton.setApellido(kidSelected.getApellido());
            kidSinglenton.setFechaNacimiento(kidSelected.getFechaNacimiento());
            kidSinglenton.setDiscapacidad(kidSelected.getDiscapacidad());
            kidSinglenton.setEscolaridad(kidSelected.getEscolaridad());
            kidSinglenton.setGenero(kidSelected.getGenero());
            kidSinglenton.setOcupacion(kidSelected.getOcupacion());
            kidSinglenton.setnVisitas(kidSelected.getNVisitas());
            kidSinglenton.setTipoDeVisitante(kidSelected.getTipoDeVisitante());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ventanas/UpdateRegisterWindowKid.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Actualizar datos de Usuario");
            stage.setScene(scene);
            stage.showAndWait();
            kidSinglenton = null;
            ListKid.setItems(kidCRUD.getAllResources());
        }catch (Exception e){
            throw new Exception(e);

        }
    }
    @FXML private void findRegisterRIaction(ActionEvent event){
        System.out.println("hiiiii");
    }


    /**Tab NumberGenerateKid**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML private TextField numberKid;
    @FXML private Button buttonG;
    @FXML private void buttonActionbuttonG(ActionEvent event){
        long rand;
        do {
            rand = (long) (Math.random() * 30000);
        }while (rand % 2 != 0);
        this.numberKid.setText(rand+"");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        masRI.setToggleGroup(grupogeneroK);
        femRI.setToggleGroup(grupogeneroK);
        siRI.setToggleGroup(grupodiscapacidadK);
        noRI.setToggleGroup(grupodiscapacidadK);
        preRI.setToggleGroup(grupoescolaridadK);
        priRI.setToggleGroup(grupoescolaridadK);
        secuRI.setToggleGroup(grupoescolaridadK);
        bachoRI.setToggleGroup(grupoescolaridadK);
        licRI.setToggleGroup(grupoescolaridadK);
        posRI.setToggleGroup(grupoescolaridadK);
        hogRI.setToggleGroup(grupoocupacionK);
        estRI.setToggleGroup(grupoocupacionK);
        emploRI.setToggleGroup(grupoocupacionK);
        desRI.setToggleGroup(grupoocupacionK);
        idKC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreKC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoKC.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        edadKC.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        generoKC.setCellValueFactory(new PropertyValueFactory<>("genero"));

        discapacidadKC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));

        escolaridadKC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));

        ocupacionKC.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));

        nVisitasKC.setCellValueFactory(new PropertyValueFactory<>("nVisitas"));
        tipoDeVisitanteKC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitante"));

        try {
            ListKid.setItems(kidCRUD.getAllResources());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
