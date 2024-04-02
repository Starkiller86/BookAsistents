package org.sbcm.Controller.Boards;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.sbcm.Dao.AdultRegisterdaoImp;
import org.sbcm.Dao.CRUD;
import org.sbcm.Model.Adult;
import org.sbcm.Model.SingletonModels.AdultSingleton;

import javax.swing.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.util.ResourceBundle;

public class MainBoard implements Initializable {
    //Cómo nueva ventana principal vamos a necesitar los daos
    CRUD<Adult> adultCRUD = new AdultRegisterdaoImp();
    @FXML
    Button homeButton;
    @FXML
    BorderPane principalBorder;
    @FXML
    BorderPane leftPanel;
    @FXML
    private TextField searchBar;

    @FXML
    public void action(ActionEvent event){


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Listener de ancho de ventana que se ejecuta 0.1 segundos despues de iniciar el stage
        new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            dinamicWidthMenu();
        })).play();
        //Listener de la tecla enter sobre el textfiel searchBat
        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    searchAction();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public void searchAction() throws Exception {
        AdultSingleton adultSingleton= AdultSingleton.getInstance();//Voy a utilizar el singleton solo para guardar el nombre
        adultSingleton.setNombre(searchBar.getText());//A pesar de que pedimos nombre completo del lado del servidor lo vamos a interpretar solo como texto
        //Aquí generamos la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasdeAsistencia/SelectAdultInterface.fxml"));
        Parent root= loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Seleccionar Usuario Registrado");
        stage.setScene(scene);
        stage.showAndWait();
        //ListAdult.setItems(adultCRUD.getAllResources()); vamos a dejar lo de la tabla para despues
        //Y una vez que la ventana se cierra, ya sea por x o y, vamos a regresar el singleton a null
        adultSingleton = null;//de esta manera en las demas partes de nuestro codigo donde lo usamos no se verá conflictuado
        try {
            searchBar.setText("");
        }catch (Exception ignored){

        }
    }


    /**
     * Este metodo es el que realiza la animación por frames para cuando la ventana cambie de tamaño extienda el panel lateral si es muy grande o lo encoja a solo
     * iconos si es muy pequeña la ventana
     *
     */
    public void dinamicWidthMenu(){
        Stage stage = (Stage) principalBorder.getScene().getWindow();
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {

            if (observable.getValue().doubleValue() >= 1650 && oldValue.doubleValue() <1650){
                Timeline timeline = new Timeline();
                timeline.setCycleCount(1);

                KeyValue keyValueStart = new KeyValue(leftPanel.prefWidthProperty(), 67, Interpolator.EASE_BOTH);
                KeyFrame startFrame = new KeyFrame(Duration.ZERO, keyValueStart);
                KeyValue keyValueEnd = new KeyValue(leftPanel.prefWidthProperty(), 267, Interpolator.EASE_BOTH);
                KeyFrame endFrame = new KeyFrame(Duration.seconds(0.5), keyValueEnd);
                timeline.getKeyFrames().addAll(startFrame, endFrame);
                timeline.play();
                //leftPanel.setPrefWidth(267);
                //homeButton.setPrefWidth(236);
            }else if (observable.getValue().doubleValue() <1650 && oldValue.doubleValue()>=1650)
            {
                Timeline timeline = new Timeline();
                timeline.setCycleCount(1);

                KeyValue keyValueStart = new KeyValue(leftPanel.prefWidthProperty(), 267, Interpolator.EASE_BOTH);
                KeyFrame startFrame = new KeyFrame(Duration.ZERO, keyValueStart);
                KeyValue keyValueEnd = new KeyValue(leftPanel.prefWidthProperty(), 67, Interpolator.EASE_BOTH);
                KeyFrame endFrame = new KeyFrame(Duration.seconds(0.5), keyValueEnd);
                timeline.getKeyFrames().addAll(startFrame, endFrame);
                timeline.play();
                //leftPanel.setPrefWidth(67);
                //homeButton.setPrefWidth(25);
            }
        });
    }
}
