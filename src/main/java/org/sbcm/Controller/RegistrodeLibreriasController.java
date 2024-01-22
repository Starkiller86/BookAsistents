package org.sbcm.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.KidRegisterdaoImp;
import org.sbcm.Model.Adult;
import org.sbcm.Model.Kid;

import java.net.URL;
import java.nio.file.SecureDirectoryStream;
import java.util.ResourceBundle;

public class RegistrodeLibreriasController implements Initializable {
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
    @FXML private ComboBox generoRA;
    @FXML private TextField textInputgeneroRA;
    @FXML private ComboBox discapacidadRA;
    @FXML private TextField textInputdiscapacidadRA;
    @FXML private ComboBox escolaridadRA;
    @FXML private TextField textInputescolaridadRA;
    @FXML private ComboBox ocupacionRA;
    @FXML private TextField textInputocupacionRA;
    @FXML private TextField nVisitasRA;
    @FXML private Button buttonRA;

    @FXML private Button deleteRegisterRA;
    @FXML private Button upDateRegisterRA;
    @FXML private Button findRegisterRA;

    @FXML private void buttonAction(ActionEvent e){
        System.out.println("Hola mundo");
    }

    //Tab KidRegister
    //Aquí debajo vamos a declarar todos los nodos de esa tab o pestaña, en orden y las funciones de los botones
    @FXML private TextField nRegistroRI;
    @FXML private TextField edadRI;
    @FXML private ComboBox <String> generoRI;
    @FXML private TextField textInput;
    @FXML private ComboBox discapacidadRI;
    @FXML private TextField TextInput;
    @FXML private ComboBox escolaridadRI;
    @FXML private TextField TextInputRI;
    @FXML private ComboBox ocupacionRI;
    @FXML private TextField textInputRI;
    @FXML private TextField nVisitasRI;
    @FXML private Button buttonRI;
    @FXML private Button deleteRegisterRI;
    @FXML private Button upDateRegisterRI;
    @FXML private Button findRegisterRI;


    @FXML private  void buttonActionInfantil(ActionEvent e){
        System.out.println("hola");
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

    @FXML private void deleteRegisterRAAction(ActionEvent e){
        System.out.println("hi");
    }
    @FXML private void upDateRegisterRAaction(ActionEvent event){
        System.out.println("hii");
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

    @FXML private void buttonActionbuttonGA(ActionEvent event){
        long rand;
        do {
            rand = (long) (Math.random() * 20000);
        }while (rand % 2 != 1);
        this.numberAdult.setText(rand+"");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        edadC.setCellValueFactory(new PropertyValueFactory<>("edad"));
        generoC.setCellValueFactory(new PropertyValueFactory<>("genero"));
        generoRA.setItems(FXCollections.observableArrayList("Mujer", "Hombre"));
        discapacidadC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));
        discapacidadRA.setItems(FXCollections.observableArrayList("Si", "No"));
        escolaridadC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));
        escolaridadRA.setItems(FXCollections.observableArrayList("Prescolar", "Primaria", "Secundaria", "Bachillerato/Preparatoria", "Licenciatura", "Posgrado"));
        ocupacionC.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));
        ocupacionRA.setItems(FXCollections.observableArrayList("Hogar", "Estudiante", "Empleado o Trabajador", "Desocupado"));
        nVisitasC.setCellValueFactory(new PropertyValueFactory<>("nVisitas"));
        tipoDeVisitanteC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitas"));
        try{
            ListAdult.setItems(adultCRUD.getAllResource());
        }
        catch (Exception e) {
            throw new RuntimeException();
        }

        idKC.setCellValueFactory(new PropertyValueFactory<>("id"));
        edadKC.setCellValueFactory(new PropertyValueFactory<>("edad"));
        generoKC.setCellValueFactory(new PropertyValueFactory<>("genero"));
        generoRI.setItems(FXCollections.observableArrayList("Mujer", "Hombre"));
        discapacidadKC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));
        discapacidadRI.setItems(FXCollections.observableArrayList("Si", "No"));
        escolaridadKC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));
        escolaridadRI.setItems(FXCollections.observableArrayList("Prescolar", "Primaria", "Secundaria", "Bachillerato/Preparatoria", "Licenciatura", "Posgrado"));
        ocupacionKC.setCellValueFactory(new PropertyValueFactory<>("ocupacion"));
        ocupacionRI.setItems(FXCollections.observableArrayList("Hogar", "Estudiante", "Empleado o Trabajador", "Desocupado"));
        nVisitasKC.setCellValueFactory(new PropertyValueFactory<>("nVisitas"));
        tipoDeVisitanteKC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitante"));

        try {
            ListKid.setItems(kidCRUD.getAllResource());
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
    @FXML
    void addInputToComboBox(ActionEvent event){
        generoRI.getItems().add(textInput.getText());
        textInput.clear();
    }
    @FXML
    void addInputComboBoxdiscapacidad(ActionEvent event){
        discapacidadRI.getItems().add(TextInput.getText());
        TextInput.clear();
    }
    @FXML
    void addInputComboBoxescolaridad(ActionEvent event){
        escolaridadRI.getItems().add(TextInputRI.getText());
        TextInputRI.clear();
    }
    @FXML
    void addInputComboBoxocupacion(ActionEvent event){
        ocupacionRI.getItems().add(textInputRI.getText());
        textInputRI.clear();
    }
    @FXML
    void addInputToComboBoxgenero(ActionEvent event){
        generoRA.getItems().add(textInputgeneroRA.getText());
        textInputgeneroRA.clear();
    }
    @FXML
    void addInputComboBoxdiscapacidadRA(ActionEvent event){
        discapacidadRA.getItems().add(textInputdiscapacidadRA.getText());
        textInputdiscapacidadRA.clear();
    }
    @FXML
    void addInputComboBoxescolaridadRA(ActionEvent event){
        escolaridadRA.getItems().add(textInputescolaridadRA.getText());
        textInputescolaridadRA.clear();
    }
    @FXML
    void addInputComboBoxocupacionRA(ActionEvent event){
        ocupacionRA.getItems().add(textInputocupacionRA.getText());
        textInputocupacionRA.clear();
    }
    @FXML
    void getComboBox(ActionEvent event){
        System.out.println(generoRI.getValue());
    }

}
