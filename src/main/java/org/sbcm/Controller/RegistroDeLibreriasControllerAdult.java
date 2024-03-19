package org.sbcm.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Model.Adult;
import org.sbcm.Model.SingletonModels.AdultSingleton;

import java.awt.*;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistroDeLibreriasControllerAdult extends Component implements Initializable {
    //Vamos a declarar todos los nodos que tenemos en esa interfaz, de momento vamos a hacer un codigo spagueti donde solo nos enfoquemos en que funcione
    //Pero vamos a tratar de organizar lo mayor posible para despues dividorlo en varios controladores e interfaces,

    //Para poder acceder a las funcionalidades de la base de datos y el servidor necesitamos el DAO, lo voy a declarar en la parte superior, pero cuando dividamos
    //en varios archivos cada uno debe tener su propia instancia de Dao que necesite
    CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();


    /**Tab registrar asistencia*/

    @FXML private TextField nombreyapellidoField;

    @FXML private void buscarButtonAction(ActionEvent ignoreEvent) throws Exception {
        AdultSingleton adultSingleton= AdultSingleton.getInstance();//Voy a utilizar el singleton solo para guardar el nombre
        adultSingleton.setNombre(nombreyapellidoField.getText());//A pesar de que pedimos nombre completo del lado del servidor lo vamos a interpretar solo como texto
        //Aquí generamos la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasdeAsistencia/SelectAdultInterface.fxml"));
        Parent root= loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Seleccionar Usuario Registrado");
        stage.setScene(scene);
        stage.showAndWait();
        ListAdult.setItems(adultCRUD.getAllResources());
        //Y una vez que la ventana se cierra, ya sea por x o y, vamos a regresar el singleton a null
        adultSingleton = null;//de esta manera en las demas partes de nuestro codigo donde lo usamos no se verá conflictuado
        try {
            nombreyapellidoField.setText("");
        }catch (Exception e){
            throw new Exception(e);
        }
    }



    /*Tab AdultRegister**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones.**/
    //Para darle una accion a un botón es necesario indicar desde la interfaz a que funcion va a desencadenar la acción


    @FXML private TextField nombreRA;
    @FXML private TextField apellidoRA;
    @FXML private DatePicker fechaNacimientoP;
    /**Grupo Género**/
    final ToggleGroup grupoGeneroRA = new ToggleGroup();
    @FXML private RadioButton femRA;
    @FXML private RadioButton masRA;
    /**Grupo Discapacidad**/
    @FXML private RadioButton siRA;
    @FXML private RadioButton noRA;
    final ToggleGroup grupodiscapacidad = new ToggleGroup();
    /**Grupo Escolaridad**/
    @FXML private RadioButton preRA;
    @FXML private RadioButton priRA;
    @FXML private RadioButton secuRA;
    @FXML private RadioButton bachoRA;
    @FXML private RadioButton licRA;
    @FXML private RadioButton posRA;
    final ToggleGroup grupoescolaridad = new ToggleGroup();
    /**Grupo Ocupación**/
    @FXML private RadioButton hogRA;
    @FXML private RadioButton estRA;
    @FXML private RadioButton emploRA;
    @FXML private RadioButton desRA;

    final ToggleGroup grupoocupacion = new ToggleGroup();



    /**En esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.**/
    @FXML private void buttonRegisterAdult(ActionEvent ignoreEvent) throws Exception{
        //Primero vamos a crear el objeto en el que vamos a colocar toda la info de la interfaz
        Adult adulto = new Adult();
        AdultSingleton singleton = AdultSingleton.getInstance();
        //Le asigno el valor de sus atributos con base a lo que obtenga de la interfaz
        //adulto.setId(Integer.parseInt(nRegistroRA.getText()));
        try {
            adulto.setFechaNacimiento(Date.from(fechaNacimientoP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            singleton.setFechaNacimiento(adulto.getFechaNacimiento());
            adulto.setNombre(nombreRA.getText());
            singleton.setNombre(adulto.getNombre());
            adulto.setApellido(apellidoRA.getText());
            singleton.setApellido(adulto.getApellido());
            adulto.setGenero(((RadioButton) grupoGeneroRA.getSelectedToggle()).getText());
            singleton.setGenero(adulto.getGenero());
            adulto.setDiscapacidad(((RadioButton) grupodiscapacidad.getSelectedToggle()).getText());
            singleton.setDiscapacidad(adulto.getDiscapacidad());
            adulto.setEscolaridad(((RadioButton) grupoescolaridad.getSelectedToggle()).getText());
            singleton.setEscolaridad(adulto.getEscolaridad());
            adulto.setOcupacion(((RadioButton) grupoocupacion.getSelectedToggle()).getText());
            singleton.setOcupacion(adulto.getOcupacion());
            adulto.setNVisitas(1);
            singleton.setNVisitas(adulto.getNVisitas());
            adulto.setTipoDeVisitante("No Frecuente");
            singleton.setTipoDeVisitante(adulto.getTipoDeVisitante());
            System.out.println(new ObjectMapper().writeValueAsString(adulto));
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Hubo un error al capturar los datos");
            alert.setContentText("Coloque los datos correctamente");
            alert.showAndWait();
            throw new Exception();
        }
        //ahora solo llamaremos la función del crud que se encarga de subir datos mediante el servidor a la base de datos
        try{
            //obtenemos el id del adulto que vamos a registrar
            System.out.println("Fecha de nacimiento" + singleton.getFechaNacimiento().toString());
            int idadulto = adultCRUD.postResourse(singleton);
            //Debemos actualizar la tabla tambien
            ListAdult.setItems(adultCRUD.getAllResources());
            fechaNacimientoP.setValue(null);
            nombreRA.setText("");
            apellidoRA.setText("");
            grupoGeneroRA.selectToggle(null);
            grupodiscapacidad.selectToggle(null);
            grupoescolaridad.selectToggle(null);
            grupoocupacion.selectToggle(null);
            tipoDeVisitanteC.setText("");
            //Usamos a singleton para usar ese id en otra parte

            singleton.setId(idadulto);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ImportantDataAdult.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrar datos personales");
            stage.setScene(scene);
            stage.showAndWait();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Correcto");
            alert.setHeaderText("El registro se ha realizado correctamente con el ID: " + idadulto);
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error de conexión");
            alert.setContentText("Comunique a soporte : " + e);
            throw new Exception(e);
        }
    }

    /*Tab AdultList**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML
    private TableView <Adult> ListAdult;
    @FXML
    private TableColumn<Adult, Integer> idC;
    @FXML TableColumn<Adult, String> nombreC;
    @FXML TableColumn<Adult, String> apellidoC;
    @FXML
    private TableColumn<Adult, String> fechaNacimientoC;
    @FXML
    private TableColumn<Adult, String > generoC;
    @FXML
    private TableColumn<Adult, String > discapacidadC;
    @FXML
    private TableColumn<Adult, String > escolaridadC;
    @FXML
    private TableColumn<Adult, String > ocupacionC;
    @FXML
    private TableColumn<Adult, Integer>nVisitasC;
    @FXML
    private TableColumn<Adult, String> tipoDeVisitanteC;



    /**En esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.**/
    @FXML private void deleteRegisterRAAction(ActionEvent ignoreEvent) throws Exception{
        Alert alert;
        Adult selectadult = ListAdult.getSelectionModel().getSelectedItem();
        if(selectadult!=null){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Realmente desea eliminar el registro?");
            alert.setContentText("Registro: \n ID : " + selectadult.getId()+
                    "\n Nombre: " + selectadult.getNombre() +
                    "\n Apellido: " +  selectadult.getApellido() +
                    "\n Fecha de Nacimiento: " + selectadult.getFechaNacimiento() +
                    "\n Género: " + selectadult.getGenero() +
                    "\n Escolaridad: " + selectadult.getEscolaridad() +
                    "\n Discapacidad: " + selectadult.getDiscapacidad() +
                    "\n Ocupación: " + selectadult.getOcupacion());
            ButtonType respuesta = alert.showAndWait().orElse(ButtonType.CANCEL);
            if(respuesta == ButtonType.OK){
                try{
                    adultCRUD.deleteResource(selectadult.getId());
                    ListAdult.setItems(adultCRUD.getAllResources());
                    alert.setTitle("operacion Exitosa");
                    alert.setHeaderText("El siguiente registro ha sido eliminado con exito");
                    alert.setContentText("Registro: \n ID : " + selectadult.getId() +
                            "\n Nombre: " + selectadult.getNombre() +
                            "\n Apellido: " + selectadult.getApellido() +
                            "\n Fecha de Nacimiento: " + selectadult.getFechaNacimiento() +
                            "\n Género: " + selectadult.getGenero() +
                            "\n Escolaridad: " + selectadult.getEscolaridad() +
                            "\n Discapacidad: " + selectadult.getDiscapacidad() +
                            "\n Ocupación: " + selectadult.getOcupacion());
                    alert.showAndWait();
                }catch (Exception e){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Ha ocurrido un error en el sistema");
                    alert.setContentText("Favor de comunicarse a soporte al correo socialservice285@gmail.com y proporcionar la siguiente información \n" + e);
                    alert.showAndWait();
                    throw new Exception(e);
                }
            }
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No ha seleccionado ninguna FILA");
            alert.setContentText("Seleccione una fila para eliminar");
            alert.showAndWait();
        }
    }
    @FXML private void upDateRegisterRAaction(ActionEvent ignoreEvent) throws Exception{
        //Voy a verificar que el botón funciona correctamente con un sout
        //funciona bien
        //System.out.println("update");
        //Como es posible que exitan errores con comunicacion del servidor o en la seleccion del elemeto voy a programar dentro de un try
        try {
            //Como se supone que debe seleccionar algo de la tabla para poderlo actualizar voy a verificar si tiene seleccionado algo
            Adult adultoSelected = ListAdult.getSelectionModel().getSelectedItem();
            //Si adultoSelected es nulo o no existe quiere decir que no selecciono nada entonces le mostrare un alert que le indique que debe seleccionar algo
            if(adultoSelected == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("No seleccionaste ningun Usuario");
                alert.setContentText("Selecciona un usuario para continuar");
                //Configuramos la ventana como ShowandAwait para que no nos deje interactuar con la interfaz mientras está abierta
                alert.showAndWait();

            }
            //En el caso de que adulto sea nulo y no haya nada seleccionado usaremos un assert para cortar la ejecucion en este punto y pase al catch
            assert adultoSelected != null : "Adulto es nulo";
            //Si no sucede lo anterior  y si tenemos algo seleccionado vamos a pasar la información de un controlador a otro para que se pueda mostrar y editar
            //Como tal javafx no tiene un funcionalidad que permita esta comunicación, pero java como tal mediante un patron de diseño: "Singleton"
            //Nuestra unica instancia va a guardar la informacion de nuestro usuario para que podamos leer la misma información desde cualquier parte de nuestro codigo
            //Creamos nuestro singleton
            AdultSingleton adultSingleton = AdultSingleton.getInstance(); //No usamos new, en su lugar colocamos el metodo estatico getInstance() de AdultSingleton
            //Colocamos datos del adultoSelected a adultoSingleton
            adultSingleton.setId(adultoSelected.getId());
            adultSingleton.setNombre(adultoSelected.getNombre());
            adultSingleton.setApellido(adultoSelected.getApellido());
            adultSingleton.setDiscapacidad(adultoSelected.getDiscapacidad());
            adultSingleton.setNVisitas(adultoSelected.getNVisitas());
            adultSingleton.setFechaNacimiento(adultoSelected.getFechaNacimiento());
            adultSingleton.setEscolaridad(adultoSelected.getEscolaridad());
            adultSingleton.setGenero(adultoSelected.getGenero());
            adultSingleton.setOcupacion(adultoSelected.getOcupacion());
            adultSingleton.setTipoDeVisitante(adultoSelected.getTipoDeVisitante());
            //Ahora tenemos que crear una nueva ventana donde cargaremos la interfaz que creamos para actualizar los datos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ventanas/UpdateRegisterWindow.fxml"));
            Parent root= loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Actualizar datos de Usuario");
            stage.setScene(scene);
            stage.showAndWait();
            adultSingleton = null;
            //Una vez que la ventana se cierre vamos a actualizar la tabla para que se muestren los datos actualizados
            ListAdult.setItems(adultCRUD.getAllResources());

        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @FXML private void findRegisterRAaction(ActionEvent ignoredEvent){

    }

    /*Tab NumberGenerateAdult**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML private TextField numberAdult;

    /**Este botón muestra un número aleatorio los cuales se usaran para poder registrar a los usuarios en la base de datos.**/
    @FXML private void buttonActionbuttonGA(ActionEvent ignoreEvent){
        long rand;
        do {
            rand = (long) (Math.random() * 20000);
        }while (rand % 2 != 1);
        this.numberAdult.setText(rand+"");
    }
    /**En esta línea de código se van a determinar todas las variables que van a aparecer en la interfaz o estas son todas las variables que vera el usuario en el servidor.**/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        masRA.setToggleGroup(grupoGeneroRA);
        femRA.setToggleGroup(grupoGeneroRA);
        siRA.setToggleGroup(grupodiscapacidad);
        noRA.setToggleGroup(grupodiscapacidad);
        preRA.setToggleGroup(grupoescolaridad);
        priRA.setToggleGroup(grupoescolaridad);
        secuRA.setToggleGroup(grupoescolaridad);
        bachoRA.setToggleGroup(grupoescolaridad);
        licRA.setToggleGroup(grupoescolaridad);
        posRA.setToggleGroup(grupoescolaridad);
        hogRA.setToggleGroup(grupoocupacion);
        estRA.setToggleGroup(grupoocupacion);
        emploRA.setToggleGroup(grupoocupacion);
        desRA.setToggleGroup(grupoocupacion);
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoC.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        fechaNacimientoC.setCellValueFactory(new PropertyValueFactory<>("simmpleDate"));
        generoC.setCellValueFactory(new PropertyValueFactory<>("genero"));
        //genero
        discapacidadC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));

        escolaridadC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));

        ocupacionC.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));

        nVisitasC.setCellValueFactory(new PropertyValueFactory<>("nVisitas"));
        tipoDeVisitanteC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitante"));
        try {
            ListAdult.setItems(adultCRUD.getAllResources());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
