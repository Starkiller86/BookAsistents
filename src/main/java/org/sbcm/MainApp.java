package org.sbcm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**El main es el encargado de la inicializacion del código**/
public class MainApp extends Application {
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("");
        stage.setScene(scene);
        stage.setMinWidth(1044);
        stage.setMinHeight(631);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
