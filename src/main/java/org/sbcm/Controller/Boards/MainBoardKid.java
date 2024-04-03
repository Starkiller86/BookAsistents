package org.sbcm.Controller.Boards;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sbcm.Dao.*;
import org.sbcm.MainApp;
import org.sbcm.Model.Adult;
import org.sbcm.Model.Kid;
import org.sbcm.Model.PersonalDataAdult;
import org.sbcm.Model.PersonalDataKid;
import org.sbcm.Model.SingletonModels.AdultSingleton;
import org.sbcm.Model.SingletonModels.KidSinglenton;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainBoardKid implements Initializable {
    public ComboBox<String> schoolLevel;
    //Cómo nueva ventana principal vamos a necesitar los daos
    CRUD<Kid> kidCRUD = new KidRegisterdaoImp();
    CRUD<PersonalDataKid> personalCRUD = new PersonalDataKidDaolmp();
    @FXML
    private Button addButton;
    @FXML
    private TextField address;
    @FXML
    private ComboBox<String> day;
    @FXML
    private ComboBox<String> disability;
    @FXML
    private TextField emergencyNumber;
    @FXML
    private Button exitButton;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private Button homeButton;
    @FXML
    private TextField lastName;
    @FXML
    private BorderPane leftPanel;
    @FXML
    private AnchorPane lineTop;
    @FXML
    private GridPane miGridPane;
    @FXML
    private ComboBox<String> month;
    @FXML
    private TextField name;
    @FXML
    private ComboBox<String> occupation;
    @FXML
    private TextField personalNumber;
    @FXML
    private BorderPane principalBorder;
    @FXML
    private Button recordsButton;
    @FXML
    private TextField searchBar;
    @FXML
    private BorderPane secondaryBorder;
    @FXML
    private ComboBox<String> shoolLevel;
    @FXML
    private Button signOutButton;
    @FXML
    private Tab tabAddUser;
    @FXML
    private Tab tabHome;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button userButton;
    @FXML
    private ComboBox<String> year;
    @FXML
    private Label titleTab;


    @FXML
    private TableView<Kid> tableList;

    @FXML
    private TableColumn<Kid,String> idC;
    @FXML
    private TableColumn<Kid,String> nombreC ;
    @FXML
    private TableColumn<Kid, String> apellidoC;
    @FXML
    private TableColumn<Kid, String> tipoVisitanteC;
    @FXML
    private TableColumn<Kid, String> discapacidadC;
    @FXML
    private TableColumn<Kid, String> escolaridadC;
    @FXML
    private TableColumn<Kid, String> fechaC;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoC.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tipoVisitanteC.setCellValueFactory(new PropertyValueFactory<>("tipoDeVisitante"));
        discapacidadC.setCellValueFactory(new PropertyValueFactory<>("discapacidad"));
        escolaridadC.setCellValueFactory(new PropertyValueFactory<>("escolaridad"));
        fechaC.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        try {
            tableList.setItems(kidCRUD.getAllResources());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        idC.setSortType(TableColumn.SortType.DESCENDING);
        tableList.getSortOrder().add(idC);
        tableList.sort();


        //Listener de ancho de ventana que se ejecuta 0.1 segundos despues de iniciar el stage
        new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
            dinamicWidthMenu();
        })).play();
        month.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int dayS = day.getSelectionModel().getSelectedIndex();

            day.getItems().clear();

            if(observable.getValue() != null && month.getItems().contains(observable.getValue())) {
                if ((observable.getValue().equals("January") ||
                        observable.getValue().equals("March") ||
                        observable.getValue().equals("May") ||
                        observable.getValue().equals("July") ||
                        observable.getValue().equals("August") ||
                        observable.getValue().equals("October") ||
                        observable.getValue().equals("December"))) {
                    day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
                    day.getSelectionModel().select(dayS);
                } else if (observable.getValue().equals(("February"))) {
                    if (!year.getSelectionModel().isEmpty()) {
                        if (Double.parseDouble(year.getSelectionModel().getSelectedItem()) % 4 == 0) {
                            day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29");
                            day.getSelectionModel().select(Math.min(dayS, 28));
                        } else {
                            day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28");
                            day.getSelectionModel().select(Math.min(dayS, 27));
                        }
                    } else {
                        day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28");
                        day.getSelectionModel().select(Math.min(dayS, 27));
                    }
                } else {
                    day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30");
                    day.getSelectionModel().select(Math.min(dayS, 29));
                }
                month.getSelectionModel().select(newValue);
            }
            else {

                //new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
                day.getSelectionModel().select(dayS);
                month.getSelectionModel().select(0);

                //})).play();
            }

        });
        year.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("observable: " + observable.getValue());
            if (year.getSelectionModel().getSelectedItem() != null && year.getItems().contains(observable.getValue())) {
                if (month.getSelectionModel().getSelectedItem().equals("February")) {
                    int dayS = day.getSelectionModel().getSelectedIndex();
                    day.getItems().clear();
                    if (Double.parseDouble(observable.getValue()) % 4 == 0) {
                        day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29");
                        day.getSelectionModel().select(dayS);
                    } else {
                        day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28");
                        day.getSelectionModel().select(Math.min(dayS, 27));
                    }
                }
            }
            else {
                //new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                year.getSelectionModel().select(100);

                //})).play();


            }

        });
        day.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("day: " + observable.getValue());
            String obs = observable.getValue();
            if (day.getSelectionModel().getSelectedItem() != null && day.getItems().contains(observable.getValue())){
                System.out.println("Index: " + day.getSelectionModel().getSelectedIndex());
            }else if (!day.getItems().isEmpty()){
                //new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                day.getSelectionModel().select(0);

                //})).play();
            }
        });
        gender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!gender.getItems().contains(observable.getValue()) && (gender.getSelectionModel().getSelectedItem() != null)){
                //new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                //gender.getEditor().setText("");
                gender.getSelectionModel().select(0);
                gender.getSelectionModel().clearSelection();
                //})).play();
            }
        });
        disability.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!disability.getItems().contains(observable.getValue()) && (disability.getSelectionModel().getSelectedItem() != null)){
                disability.getSelectionModel().select(0);
                disability.getSelectionModel().clearSelection();
            }
        });
        schoolLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!schoolLevel.getItems().contains(observable.getValue()) && (schoolLevel.getSelectionModel().getSelectedItem() != null)){
                //new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                //gender.getEditor().setText("");
                schoolLevel.getSelectionModel().select(0);
                schoolLevel.getSelectionModel().clearSelection();
                //})).play();
            }
        });
        occupation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (!occupation.getItems().contains(observable.getValue()) && (occupation.getSelectionModel().getSelectedItem() != null)){
                //new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
                //gender.getEditor().setText("");
                occupation.getSelectionModel().select(0);
                occupation.getSelectionModel().clearSelection();
                //})).play();
            }
        });
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
        KidSinglenton kidSinglenton= KidSinglenton.getInstance();//Voy a utilizar el singleton solo para guardar el nombre
        kidSinglenton.setNombre(searchBar.getText());//A pesar de que pedimos nombre completo del lado del servidor lo vamos a interpretar solo como texto
        //Aquí generamos la ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VentanasdeAsistencia/SelectKidInterface.fxml"));
        Parent root= loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Seleccionar Usuario Registrado");
        stage.setScene(scene);
        stage.showAndWait();
        //ListAdult.setItems(adultCRUD.getAllResources()); vamos a dejar lo de la tabla para despues
        //Y una vez que la ventana se cierra, ya sea por x o y, vamos a regresar el singleton a null
        kidSinglenton = null;//de esta manera en las demas partes de nuestro codigo donde lo usamos no se verá conflictuado
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

    public void submitAction(ActionEvent event) throws Exception {
//Primero vamos a crear el objeto en el que vamos a colocar toda la info de la interfaz
        Kid kid = new Kid();
        PersonalDataKid personalKid=  new PersonalDataKid();
        KidSinglenton singleton = KidSinglenton.getInstance();
        //Le asigno el valor de sus atributos con base a lo que obtenga de la interfaz
        //adulto.setId(Integer.parseInt(nRegistroRA.getText()));
        try {
            if (name.getText().isEmpty() ||
                    lastName.getText().isEmpty() ||
                    (day.getSelectionModel().isEmpty() && day.getEditor().getText().isEmpty()) ||
                    (month.getSelectionModel().isEmpty() && month.getEditor().getText().isEmpty()) ||
                    (year.getSelectionModel().isEmpty() && year.getEditor().getText().isEmpty()) ||
                    (gender.getSelectionModel().isEmpty() && gender.getEditor().getText().isEmpty()) ||
                    (disability.getSelectionModel().isEmpty() && disability.getEditor().getText().isEmpty()) ||
                    (schoolLevel.getSelectionModel().isEmpty() && schoolLevel.getEditor().getText().isEmpty()) ||
                    (occupation.getSelectionModel().isEmpty() && occupation.getEditor().getText().isEmpty()))
            {
                System.out.println("imprimir: " +day.getValue());
                throw new Exception("hay campos vacios");
            }

            String birth = year.getValue() + "-" + (month.getSelectionModel().getSelectedIndex()+1) + "-" + day.getValue();
            String[] parts = birth.split("-");
            String formattedDate = String.format("%s-%02d-%02d", parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));

            kid.setFechaNacimiento(formattedDate);
            singleton.setFechaNacimiento(kid.getFechaNacimiento());
            kid.setNombre(name.getText());
            singleton.setNombre(kid.getNombre());
            kid.setApellido(lastName.getText());
            singleton.setApellido(kid.getApellido());
            kid.setGenero(gender.getValue());
            singleton.setGenero(kid.getGenero());
            kid.setDiscapacidad(disability.getValue());
            singleton.setDiscapacidad(kid.getDiscapacidad());
            kid.setEscolaridad(schoolLevel.getValue());
            singleton.setEscolaridad(kid.getEscolaridad());
            kid.setOcupacion(occupation.getValue());
            singleton.setOcupacion(kid.getOcupacion());
            kid.setNVisitas(1);
            singleton.setNVisitas(kid.getNVisitas());
            kid.setTipoDeVisitante("No Frecuente");
            singleton.setTipoDeVisitante(kid.getTipoDeVisitante());
            System.out.println(new ObjectMapper().writeValueAsString(kid));


            personalKid.setDomicilio(address.getText());
            personalKid.setNumeroemergencia(emergencyNumber.getText());
            personalKid.setNumeropersonal(personalNumber.getText());


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Hubo un error al capturar los datos");
            alert.setContentText("Coloque los datos correctamente");
            alert.showAndWait();
            throw new Exception(e);
        }
        //ahora solo llamaremos la función del crud que se encarga de subir datos mediante el servidor a la base de datos
        try{
            //obtenemos el id del adulto que vamos a registrar
            //System.out.println("Fecha de nacimiento" + singleton.getFechaNacimiento().toString());
            int idKid = kidCRUD.postResourse(singleton);
            kid.setId(idKid);
            personalKid.setIdKid(kid);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Correcto");
            //alert.setHeaderText("El registro se ha realizado correctamente con el ID: " + idadulto);
            alert.showAndWait();
            if (!(personalKid.getDomicilio().isEmpty() && personalKid.getNumeroemergencia().isEmpty() && personalKid.getNumeropersonal().isEmpty())){
                int idPersonal = personalCRUD.postResourse(personalKid);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Registro Correcto");
                //alert2.setHeaderText("Se ha registrado la información personal con exito con el ID: "+ idPersonal);
                alert2.showAndWait();
            }
            //Debemos actualizar la tabla tambien
            //ListAdult.setItems(adultCRUD.getAllResources());

            name.setText("");
            lastName.setText("");
            gender.getSelectionModel().clearSelection();
            gender.getEditor().setText("");
            disability.getSelectionModel().clearSelection();
            disability.getEditor().setText("");
            schoolLevel.getSelectionModel().clearSelection();
            schoolLevel.getEditor().setText("");
            occupation.getSelectionModel().clearSelection();
            occupation.getEditor().setText("");
            day.getItems().clear();
            day.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
            day.getSelectionModel().clearSelection();
            day.getEditor().setText("");
            month.getSelectionModel().clearSelection();
            month.getEditor().setText("");
            year.getSelectionModel().clearSelection();
            year.getEditor().setText("");
            tableList.setItems(kidCRUD.getAllResources());


            //Usamos a singleton para usar ese id en otra parte

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error de conexión");
            alert.setContentText("Comunique a soporte : " + e);
            alert.showAndWait();
            throw new Exception(e);
        }
    }

    public void menuAction(ActionEvent event) {
        if (((Button) event.getSource()).getId().equals("homeButton")) {
            tabPane.getSelectionModel().select(0);
            titleTab.setText("Home");

        }
        if (((Button) event.getSource()).getId().equals("addButton")){
            tabPane.getSelectionModel().select(1);
            titleTab.setText("Register");
        }
        if (((Button) event.getSource()).getId().equals("recordsButton")){
            tabPane.getSelectionModel().select(2);
            titleTab.setText("Records");
        }
        if (((Button) event.getSource()).getId().equals("exportATabButton")){
            tabPane.getSelectionModel().select(3);
            titleTab.setText("Export");
        }
    }

    public void ExportAction(ActionEvent event) throws Exception {

        List<Kid> listaKid  = kidCRUD.getAllResources();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Adultos");

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            style.setWrapText(true);
            CellStyle styleH = workbook.createCellStyle();
            java.awt.Color color = new java.awt.Color(236, 239, 254);

            System.out.println("Short: " + (short) 3211276);
            styleH.setFillForegroundColor((short) 21310);
            styleH.setFillPattern(FillPatternType.SOLID_FOREGROUND);




            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Numero de Usuario");
            headerRow.createCell(1).setCellValue("Nombre");
            headerRow.createCell(2).setCellValue("Apellido");
            headerRow.createCell(3).setCellValue("Fecha de Nacimiento");
            headerRow.createCell(4).setCellValue("Genero");
            headerRow.createCell(5).setCellValue("Escolaridad");
            headerRow.createCell(6).setCellValue("Discapacidad");
            headerRow.createCell(7).setCellValue("Ocupacion");
            headerRow.createCell(8).setCellValue("Numero De Visitas");
            headerRow.createCell(9).setCellValue("Tipo De Visitante");
            int rowIndex = 1;
            for (Kid persona : listaKid) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(persona.getId());
                row.createCell(1).setCellValue(persona.getNombre());
                row.createCell(2).setCellValue(persona.getApellido());
                row.createCell(3).setCellValue(persona.getFechaNacimiento());

                if (persona.getEscolaridad().equals("Posgrado")){
                    row.createCell(10).setCellValue("1");
                }
                row.createCell(4).setCellValue(persona.getGenero());
                row.createCell(5).setCellValue(persona.getEscolaridad());
                row.createCell(6).setCellValue(persona.getDiscapacidad());
                row.createCell(7).setCellValue(persona.getOcupacion());
                row.createCell(8).setCellValue(persona.getNVisitas());
                row.createCell(9).setCellValue(persona.getTipoDeVisitante());

                // Aplicar estilo a las celdas
                for (int i = 0; i < 10; i++) {
                    row.getCell(i).setCellStyle(style);
                }
            }

            for (int i = 0; i < 10; i++) {
                headerRow.getCell(i).setCellStyle(styleH);
            }
            for (int i = 0; i < 10; i++) {
                sheet.autoSizeColumn(i);
            }
            FileOutputStream out = new FileOutputStream(new File("personas.xlsx"));
            workbook.write(out);
            String path = System.getProperty("user.dir");
            String fileName = "personas.xlsx";

            String filePath = path + File.separator + fileName;

            Desktop desktop = Desktop.getDesktop();
            File file = new File(filePath);
            desktop.open(file);

            out.close();


        }

    }


    public void signOutAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.setTitle("");
        stage.setScene(scene);
        stage.setMinWidth(1044);
        stage.setMinHeight(631);
        stage.centerOnScreen();
        stage.show();
    }

    public void exitAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
