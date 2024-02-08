package org.sbcm.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.KidRegisterdaoImp;
import org.sbcm.Model.Adult;
import org.sbcm.Model.Kid;
import org.sbcm.SingletonModels.AdultSingleton;
import org.sbcm.SingletonModels.KidSinglenton;
import org.sbcm.SingletonModelsAsistent.AdultAsistentSingleton;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegistrodeLibreriasController extends Component implements Initializable {
    //Vamos a declarar todos los nodos que tenemos en esa interfaz, de momento vamos a hacer un codigo spagueti donde solo nos enfoquemos en que funcione
    //Pero vamos a tratar de organizar lo mayor posible para despues dividorlo en varios controladores e interfaces,

    //Para poder acceder a las funcionalidades de la base de datos y el servidor necesitamos el DAO, lo voy a declarar en la parte superior, pero cuando dividamos
    //en varios archivos cada uno debe tener su propia instancia de Dao que necesite
    CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();
    CRUD<Kid> kidCRUD = new KidRegisterdaoImp();
    /**Tab AdultRegister**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones.**/
    //Para darle una accion a un botón es necesario indicar desde la interfaz a que funcion va a desencadenar la acción

    @FXML private TextField nRegistroRA;
    @FXML private TextField edadRA;
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
    @FXML private TextField textInputgeneroRA;
    @FXML private TextField textInputdiscapacidadRA;
    @FXML private TextField textInputescolaridadRA;
    @FXML private TextField textInputocupacionRA;
    @FXML private TextField nVisitasRA;
    @FXML private Button buttonRA;

    /**En esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.**/
    @FXML private void buttonRegisterAdult(ActionEvent event) throws Exception{
        //Primero vamos a crear el objeto en el que vamos a colocar toda la info de la interfaz
        Adult adulto = new Adult();
        //Le asigno el valor de sus atributos con base a lo que obtenga de la interfaz
        //adulto.setId(Integer.parseInt(nRegistroRA.getText()));
        adulto.setEdad(Integer.parseInt(edadRA.getText()));
        adulto.setGenero(((RadioButton) grupoGeneroRA.getSelectedToggle()).getText());
        adulto.setDiscapacidad(((RadioButton) grupodiscapacidad.getSelectedToggle()).getText());
        adulto.setEscolaridad(((RadioButton) grupoescolaridad.getSelectedToggle()).getText());
        adulto.setOcupacion(((RadioButton) grupoocupacion.getSelectedToggle()).getText());
        adulto.setTipoDeVisitante("Adulto");
        adulto.setNVisitas(Integer.parseInt(nVisitasRA.getText()));
        System.out.println(new ObjectMapper().writeValueAsString(adulto));
        //ahora solo llamaremos la función del crud que se encarga de subir datos mediante el servidor a la base de datos
        try{
            adultCRUD.postResourse(adulto);
            //Debemos actualizar la tabla tambien
            ListAdult.setItems(adultCRUD.getAllResources());
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    /**Tab KidRegister**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML private TextField nRegistroRI;
    @FXML private TextField edadRI;
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
    @FXML private  void buttonActionInfantil(ActionEvent e)throws Exception{
        Kid kid = new Kid();
        kid.setEdad(Integer.parseInt(edadRI.getText()));
        kid.setGenero(((RadioButton)grupogeneroK.getSelectedToggle()).getText());
        kid.setDiscapacidad(((RadioButton)grupodiscapacidadK.getSelectedToggle()).getText());
        kid.setEscolaridad(((RadioButton)grupoescolaridadK.getSelectedToggle()).getText());
        kid.setOcupacion(((RadioButton)grupoocupacionK.getSelectedToggle()).getText());
        kid.setTipoDeVisitante("Kid");
        kid.setNVisitas(Integer.parseInt(nVisitasRI.getText()));
        kid.setId(Integer.parseInt(nRegistroRI.getText()));
        System.out.println(new ObjectMapper().writeValueAsString(kid));
        try {
            kidCRUD.postResourse(kid);
            ListKid.setItems(kidCRUD.getAllResources());
        }catch (Exception exception){
            throw new Exception(exception);
        }
    }

    /**Tab AdultList**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML
    private TableView <Adult> ListAdult;
    @FXML
    private TableColumn<Adult, Integer> idC;
    @FXML
    private TableColumn<Adult, Integer> edadC;
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

    @FXML private Button deleteRegisterRA;
    @FXML private Button upDateRegisterRA;
    @FXML private Button findRegisterRA;

    /**En esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.**/
    @FXML private void deleteRegisterRAAction(ActionEvent event) throws Exception{
        Alert alert;
        Adult selectadult = ListAdult.getSelectionModel().getSelectedItem();
        if(selectadult!=null){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("¿Realmente desea eliminar el registro?");
            alert.setContentText("Registro: \n ID : " + selectadult.getId()+
                    "\n Edad: " + selectadult.getEdad() +
                    "\n Genero: " + selectadult.getGenero() +
                    "\n Escolaridad: " + selectadult.getEscolaridad() +
                    "\n Discapacidad: " + selectadult.getDiscapacidad() +
                    "\n Ocupación: " + selectadult.getOcupacion());
            ButtonType respuesta = alert.showAndWait().orElse(ButtonType.CANCEL);
            if(respuesta == ButtonType.OK){
                try {
                    adultCRUD.deleteResource(selectadult.getId());
                    ListAdult.setItems(adultCRUD.getAllResources());
                    alert.setTitle("operacion Exitosa");
                    alert.setHeaderText("El siguiente registro ha sido eliminado con exito");
                    alert.setContentText("Registro: \n ID : " + selectadult.getId() +
                            "\n Edad: " + selectadult.getEdad() +
                            "\n Genero: " + selectadult.getGenero() +
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
    @FXML private void upDateRegisterRAaction(ActionEvent event) throws Exception{
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
            adultSingleton.setDiscapacidad(adultoSelected.getDiscapacidad());
            adultSingleton.setnVisitas(adultoSelected.getNVisitas());
            adultSingleton.setEdad(adultoSelected.getEdad());
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
            //Una vez que la ventana se cierre vamos a actualizar la tabla para que se muestren los datos actualizados
            ListAdult.setItems(adultCRUD.getAllResources());

        }catch (Exception e){
            throw new Exception(e);
        }
    }
    @FXML private void findRegisterRAaction(ActionEvent event){

    }

    /**Tab KidList**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML
    private TableView <Kid> ListKid;
    @FXML
    private TableColumn<Kid, Integer> idKC;
    @FXML
    private TableColumn<Kid,Integer> edadKC;
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
                    "\n Edad: " + selectedKid.getEdad() +
                    "\n Genero: " + selectedKid.getGenero() +
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
                            "\n Edad: " + selectedKid.getEdad() +
                            "\n Genero: " + selectedKid.getGenero() +
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
            kidSinglenton.setEdad(kidSelected.getEdad());
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

    /**Tab NumberGenerateAdult**/
    /**Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones**/
    @FXML private TextField numberAdult;
    @FXML private Button buttonGA;
    /**Este botón muestra un número aleatorio los cuales se usaran para poder registrar a los usuarios en la base de datos.**/
    @FXML private void buttonActionbuttonGA(ActionEvent event){
        long rand;
        do {
            rand = (long) (Math.random() * 20000);
        }while (rand % 2 != 1);
        this.numberAdult.setText(rand+"");
    }
    /**En esta línea de código se van a determinar todas las variables que van a aparecer en la interfaz o estas son todas las variables que vera el usuario en el servidor.**/
    @FXML private void SumanVisitasC(ActionEvent event){

    }
    @FXML private void SumanVisitasKC(ActionEvent event){

    }
    @FXML private void VisitantetipoDeVisitanteC(ActionEvent event){

    }
    @FXML private void Visitante(ActionEvent event){

    }
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
        edadC.setCellValueFactory(new PropertyValueFactory<>("edad"));
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
        edadKC.setCellValueFactory(new PropertyValueFactory<>("edad"));
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
