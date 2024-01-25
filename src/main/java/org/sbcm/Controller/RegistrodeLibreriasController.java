package org.sbcm.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.KidRegisterdaoImp;
import org.sbcm.Model.Adult;
import org.sbcm.Model.Kid;

import java.awt.*;
import java.net.URL;
import java.nio.file.SecureDirectoryStream;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RegistrodeLibreriasController extends Component implements Initializable {
    //Vamos a declarar todos los nodos que tenemos en esa interfaz, de momento vamos a hacer un codigo spagueti donde solo nos enfoquemos en que funcione
    //Pero vamos a tratar de organizar lo mayor posible para despues dividorlo en varios controladores e interfaces,

    //Para poder acceder a las funcionalidades de la base de datos y el servidor necesitamos el DAO, lo voy a declarar en la parte superior, pero cuando dividamos
    //en varios archivos cada uno debe tener su propia instancia de Dao que necesite
    CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();
    /**
     * Prueba de GitHub
     */
    CRUD<Kid> kidCRUD = new KidRegisterdaoImp();
    //Tab AdultRegister
    //Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones.
    //Para darle una accion a un botón es necesario indicar desde la interfaz a que funcion va a desencadenar la acción

    @FXML private TextField nRegistroRA;
    @FXML private TextField edadRA;
    //Grupo Genero
    final ToggleGroup grupoGeneroRA = new ToggleGroup();
    @FXML private RadioButton femRA;
    @FXML private RadioButton masRA;
    //Grupo discapacidad
    @FXML private RadioButton siRA;
    @FXML private RadioButton noRA;
    final ToggleGroup grupodiscapacidad = new ToggleGroup();
    //grupo escolaridad
    @FXML private RadioButton preRA;
    @FXML private RadioButton priRA;
    @FXML private RadioButton secuRA;
    @FXML private RadioButton bachoRA;
    @FXML private RadioButton licRA;
    @FXML private RadioButton posRA;
    final ToggleGroup grupoescolaridad = new ToggleGroup();
    //grupo ocupación
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

    @FXML private Button deleteRegisterRA;
    @FXML private Button upDateRegisterRA;
    @FXML private Button findRegisterRA;
    //en esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.

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

        //ahora solo llamaremos la función del crud que se encarga de subir datos mediante el servidor a la base de datos

        try{
            adultCRUD.postResourse(adulto);
        }catch (Exception e){
            throw new Exception(e);
        }




        /*CODIGO LEGACY
        try{
            String nRegistro = nRegistroRA.getText();
            String edad = edadRA.getText();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/registrolibrerias","root","");
            PreparedStatement ps=con.prepareStatement("insert into registroaduls(id,edad,genero,discapacidad,escolaridad,ocupacion)values(?,?,?,?,?,?)");
            ps.setString(1, nRegistro);
            ps.setString(2, edad);
            /**
             * Genero
             *//*
            if (femRA.isSelected()){
                ps.setString(3,femRA.getText());
            }
            else
                ps.setString(3,masRA.getText());
            /**
             * Discapacidad
             *//*
            if (siRA.isSelected()){
                ps.setString(4,siRA.getText());
            }
            else
                ps.setString(4,noRA.getText());
            /**
             * Escolaridad
             *//*

            if (preRA.isSelected()){
                ps.setString(5,preRA.getText());
            } else if (priRA.isSelected()) {
                ps.setString(5,priRA.getText());
            } else if (secuRA.isSelected()) {
                ps.setString(5,secuRA.getText());
            } else if (bachoRA.isSelected()) {
                ps.setString(5,bachoRA.getText());
            } else if (licRA.isSelected()) {
                ps.setString(5,licRA.getText());
            }
            else
                ps.setString(5,posRA.getText());
            /**
             * Ocupación
             *//*
            if (hogRA.isSelected()){
                ps.setString(6,hogRA.getText());
            } else if (estRA.isSelected()) {
                ps.setString(6,estRA.getText());
            } else if (emploRA.isSelected()) {
                ps.setString(6, emploRA.getText());
            }else
                ps.setString(6,desRA.getText());
            ps.executeUpdate();
        }catch (Exception ex){
            Logger.getLogger(RegistrodeLibreriasController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    //Tab KidRegister
    //Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones
    @FXML private TextField nRegistroRI;
    @FXML private TextField edadRI;
    @FXML private TextField textInput;
    @FXML private TextField TextInput;
    @FXML private TextField TextInputRI;
    @FXML private TextField textInputRI;
    @FXML private TextField nVisitasRI;
    @FXML private Button buttonRI;
    @FXML private Button deleteRegisterRI;
    @FXML private Button upDateRegisterRI;
    @FXML private Button findRegisterRI;
    //grupo genero
    @FXML private RadioButton femRI;
    @FXML private RadioButton masRI;
    final ToggleGroup grupogeneroK = new ToggleGroup();
    //grupo discapacidad
    @FXML private RadioButton siRI;
    @FXML private RadioButton noRI;
    final ToggleGroup grupodiscapacidadK = new ToggleGroup();
    //frupo escolaridad
    @FXML private RadioButton preRI;
    @FXML private RadioButton priRI;
    @FXML private RadioButton secuRI;
    @FXML private RadioButton bachoRI;
    @FXML private RadioButton licRI;
    @FXML private RadioButton posRI;
    final ToggleGroup grupoescolaridadK = new ToggleGroup();
    //grupo ocupación
    @FXML private RadioButton hogRI;
    @FXML private RadioButton estRI;
    @FXML private RadioButton emploRI;
    @FXML private RadioButton desRI;
    final  ToggleGroup grupoocupacionK = new ToggleGroup();

    //en esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.
    @FXML private  void buttonActionInfantil(ActionEvent e){
        try {
            String nRegistro = nRegistroRI.getText();
            String edad = edadRI.getText();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/registrolibrerias", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into kidsregister(id,edad,genero,escolaridad,discapacidad,ocupacion)values(?,?,?,?,?,?))");
            ps.setString(1, nRegistro);
            ps.setString(2, edad);
            /**Genero**/
            if (femRI.isSelected()) {
                ps.setString(3, femRI.getText());
            } else
                ps.setString(3, masRI.getText());

            /**Escolaridad**/
            if (preRI.isSelected()) {
                ps.setString(4, preRI.getText());
            } else if (priRI.isSelected()) {
                ps.setString(4, priRI.getText());
            } else if (secuRI.isSelected()) {
                ps.setString(4, secuRI.getText());
            } else if (bachoRI.isSelected()) {
                ps.setString(4, bachoRI.getText());
            } else if (licRI.isSelected()) {
                ps.setString(4, licRI.getText());
            } else
                ps.setString(4, posRI.getText());
            /**
             * Discapacidad
             */
            if (siRI.isSelected()) {
                ps.setString(5, siRI.getText());
            } else
                ps.setString(5, noRI.getText());
            /**Ocupacion**/
            if (hogRI.isSelected()) {
                ps.setString(6, hogRI.getText());
            } else if (estRI.isSelected()) {
                ps.setString(6, estRI.getText());
            } else if (emploRI.isSelected()) {
                ps.setString(6, emploRI.getText());
            } else
                ps.setString(6,desRI.getText());
            ps.executeUpdate();
        } catch (Exception exception){
            Logger.getLogger(RegistrodeLibreriasController.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    //Tab AdultList
    //Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones
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
    //en esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.

    @FXML private void deleteRegisterRAAction(ActionEvent e){
        String nRegistroRA = JOptionPane.showInputDialog(null,"Ingrese el ID del registro que desea eliminar: ");
    }
    @FXML private void upDateRegisterRAaction(ActionEvent event){

    }
    @FXML private void findRegisterRAaction(ActionEvent event){
        System.out.println("hiii");
    }

    //Tab KidList
    //Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones
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
    //en esta parte del código lo que realiza es determinar la funcion que el botón va a hacer en la interfaz y la base de datos.

    @FXML private void deleteRegisterRIaction(ActionEvent event){
        System.out.println("hiiii");
    }
    @FXML private void upDateRegisterRIaction(ActionEvent event){
        System.out.println("hi");
    }
    @FXML private void findRegisterRIaction(ActionEvent event){
        System.out.println("hiiiii");
    }


    //Tab NumberGenerateKid
    //Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones
    @FXML private TextField numberKid;
    @FXML private Button buttonG;
    @FXML private void buttonActionbuttonG(ActionEvent event){
        long rand;
        do {
            rand = (long) (Math.random() * 30000);
        }while (rand % 2 != 0);
        this.numberKid.setText(rand+"");
    }

    //Tab NumberGenerateAdult
    //Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones
    @FXML private TextField numberAdult;
    @FXML private Button buttonGA;
    //este botón muestra un número aleatorio los cuales se usaran para poder registrar a los usuarios en la base de datos.

    @FXML private void buttonActionbuttonGA(ActionEvent event){
        long rand;
        do {
            rand = (long) (Math.random() * 20000);
        }while (rand % 2 != 1);
        this.numberAdult.setText(rand+"");
    }
    //En esta línea de código se van a determinar todas las variables que van a aparecer en la interfaz o estas son todas las variables que vera el usuario en el servidor.

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
            ListAdult.setItems(adultCRUD.getAllResource());
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
            ListKid.setItems(kidCRUD.getAllResource());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
