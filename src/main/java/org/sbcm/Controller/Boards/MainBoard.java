package org.sbcm.Controller.Boards;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.net.Inet4Address;
import java.net.URL;
import java.util.ResourceBundle;

public class MainBoard implements Initializable {
    @FXML
    Button homeButton;
    @FXML
    BorderPane principalBorder;
    @FXML
    BorderPane leftPanel;

    @FXML
    public void action(ActionEvent event){
        System.out.println("Play");




    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Listener de ancho de ventana que se ejecuta 0.1 segundos despues de iniciar el stage
        new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            dinamicWidthMenu();
        })).play();
    }

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
