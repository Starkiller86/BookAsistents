package org.sbcm.Controller.Boards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.sbcm.Dao.UserRequestDao;
import org.sbcm.MainApp;
import org.sbcm.Model.UserRequest;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private ComboBox<String> department;

    @FXML
    private PasswordField password;

    @FXML
    private TextField rPassword;

    @FXML
    private Button signButton;

    @FXML
    private TextField user;


    @FXML
    private Label login;





    private UserRequestDao userRCrud = new UserRequestDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signAction(ActionEvent event) throws Exception {
        UserRequest user = new UserRequest();
        try{
            try {
                user.setUser(this.user.getText());
                user.setPassword(this.password.getText());
                user.setDepartment(this.department.getValue());
                if (user.getPassword().equals(this.rPassword.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Corrige los datos");
                    alert.setHeaderText("Las contraseñas no coinciden");
                    alert.setContentText("Revisa tu contraseña");
                    alert.showAndWait();
                    rPassword.setText("");
                    throw new Exception();
                }
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Coloca correctamente los campos");
                alert.setTitle("Corrección");
                alert.setHeaderText("datos erroneos");
                throw new Exception();
            }
            int response = userRCrud.postResourse(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pre Registro");
            alert.setHeaderText("Pre-registro realizado correctamente");
            alert.setContentText("Espera a que tu coordinador de area confirme tu registro antes de poder utilizar tu usuario");
            alert.showAndWait();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/Login.fxml"));
            Stage stage = (Stage) signButton.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setMinWidth(1044);
            stage.setMinHeight(631);
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){

        }



    }

    @FXML
    public void loginClick(MouseEvent mouseEvent) throws Exception {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/Login.fxml"));
            Stage stage = (Stage) login.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setMinWidth(1044);
            stage.setMinHeight(631);
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}