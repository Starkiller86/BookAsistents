package org.sbcm.Controller.Boards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.sbcm.Dao.CRUD;
import org.sbcm.Dao.UserDaoImp;
import org.sbcm.MainApp;
import org.sbcm.Model.User;

import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private ComboBox<String> department;

    @FXML
    private PasswordField password;

    @FXML
    private Button signButton;

    @FXML
    private TextField user;


    @FXML
    private Label newUser;

    private UserDaoImp userCRUD = new UserDaoImp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void signAction(ActionEvent event) throws Exception {
        try{
            User user = new User();
            user.setUser(this.user.getText());
            user.setPassword(this.password.getText());
            user.setDepartment(this.department.getValue());
            int response = userCRUD.putConfirmResource(user);
            if (response>0){
                if (user.getDepartment().equals("Adultos")) {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/MainBoard.fxml"));
                    Stage stage = (Stage) signButton.getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.setMinWidth(1280);
                    stage.setMinHeight(695);
                    stage.centerOnScreen();
                    stage.show();
                }else if (user.getDepartment().equals("Infantil")){
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/MainBoardKid.fxml"));
                    Stage stage = (Stage) signButton.getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.setMinWidth(1280);
                    stage.setMinHeight(695);
                    stage.centerOnScreen();
                    stage.show();
                }else if (user.getDepartment().equals("Administrador")){
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/MainBoardAdmin.fxml"));
                    Stage stage = (Stage) signButton.getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.setMinWidth(1280);
                    stage.setMinHeight(695);
                    stage.centerOnScreen();
                    stage.show();
                }
            }else {
                Alert alert  = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Datos Incorrectos!");
                alert.setContentText("Verifica los datos para que puedas iniciar sesión");
                alert.showAndWait();

            }

        }catch (Exception e){
            Alert alert =  new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Connection Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new Exception(e);
        }



    }

    public void createNewUser(MouseEvent mouseEvent) throws Exception {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/SignUp.fxml"));
            Stage stage = (Stage) newUser.getScene().getWindow();
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
