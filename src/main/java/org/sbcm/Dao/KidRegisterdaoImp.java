package org.sbcm.Dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.sbcm.Model.Adult;
import org.sbcm.Model.Kid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
     /**KidRegisterdaolmp es la implementación del CRUD, de tal manera que mediante las conexiones Http, es posible poder utilizar los registros o datos que se
      * encuentran en la base de datos, haciendo que se puedan Crear, Actualizar, Leer y Eliminar cualquier registro mediante las conexiones Http, estos método
      * son los que se implementan con el CRUD y que hacen que se realicen estas acciones con los registros**/
public class KidRegisterdaoImp implements CRUD<Kid>{
         @Override
         public void putAssistance(Kid kid) throws Exception {

         }

         //estas son las variables de conexión o de instancia que permiten que la base de datos se conecten con el código
    HttpURLConnection connection;
    URI uri;
    boolean usingTest = true;

     /***
      * Este método se encarga de mostrar todos los registros que se encuentran en la base de datos, es un metodo que devuelve JSONArray como resultado, este
      * metodo hace que se tengan los datos de la base de datos mediante las variables de conexión Http, las cuales hacen que estos datos se conecten con el código
      * @return ListKid es la variable que va a retornar, ya que, ahi se encuentran todos los registros de la base de datos
      * @throws Exception se va a devolver si la conexión Http es diferente a 200, de otra forma continua el código
      */

    @Override
    public ObservableList<Kid> getAllResources() throws Exception {
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/kids");

        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        System.out.println(connection.getRequestProperties());
        if(connection.getResponseCode()!=200){
            throw new Exception();
        }
        InputStreamReader lecturaVytesStram = new InputStreamReader(connection.getInputStream());
        BufferedReader lecturabuffer = new BufferedReader(lecturaVytesStram);
        String linea = lecturabuffer.readLine();
        StringBuilder lineas = new StringBuilder();
        while (linea != null){
            lineas.append(linea);
            linea = lecturabuffer.readLine();
        }
        lecturabuffer.close();
        JSONArray jsonListKid = new JSONArray(lineas.toString());
        ObservableList<Kid> ListKid = FXCollections.observableArrayList();

        for (Object o: jsonListKid){
            JSONObject jsonObject = (JSONObject) o;
            ObjectMapper objectMapper = new ObjectMapper();
            Kid kid = objectMapper.readValue(jsonObject.toString(), Kid.class);
            ListKid.add(kid);
        }
        System.out.println(connection.getHeaderFields());
        connection.disconnect();

        return ListKid;
    }

     /**
      * Este método se encarga de crear un nuevo registro en la base de datos, esto se realizara
      * mediante las variables de conexión Http, pues estas haran que estos registros se coloquen
      * en la base de datos.
      * @param kid es el objeto de Kid el cual sera la vable que se utiliza en este método al igual que con todos los datos
      * @throws Exception esto de devuelve cuando la conexión Http es difente a 200, de otra forma continua el programa
      */

    @Override
    public int postResourse(Kid kid) throws Exception {
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/kids");

        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent","JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonKid = new JSONObject(objectMapper.writeValueAsString(kid));
        OutputStream transmisionSalida = connection.getOutputStream();
        byte[] salidaBytes = jsonKid.toString().getBytes(StandardCharsets.UTF_8);
        transmisionSalida.write(salidaBytes);
        if(connection.getResponseCode()!=200){
            throw  new Exception();
        }
        connection.disconnect();
        return 0;
    }

     /***
      * Este método se utliza para obtener un registro en especifico que se requiera, esto se realiza
      * mediante el id, la cual sera la variable de identificacion la cual mediante las variables de
      * coneccion se pueda encontar ese registro en la base de datos
      * @param id es la variable que se utiliza para poder obtener el registro en especifico que se requiera en este método
      * @return kid es el objeto de Kid el cual se retornar con los datos del registro que se pidio en el código
      * @throws Exception se devuelve en caso de que la conexión Http sea difente a 200, de otra forma continua el código
      */
    @Override
    public Kid getOneResourceById(int id) throws Exception {
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/adults"+id);
        if(!usingTest)
            connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent","JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        if(connection.getResponseCode()!=200){
            throw new Exception();
        }
        InputStreamReader lecturaTransmision = new InputStreamReader(connection.getInputStream());
        BufferedReader lecturabuffer = new BufferedReader(lecturaTransmision);
        String linea = lecturabuffer.readLine();
        StringBuilder lineas = new StringBuilder();
        while (linea != null){
            lineas.append(linea);
            linea = lecturabuffer.readLine();
        }
        lecturabuffer.close();
        JSONObject jsonKid = new JSONObject(lineas.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        Kid kid = objectMapper.readValue(jsonKid.toString(),Kid.class);
        connection.disconnect();


        return kid;
    }

     /**
      * Este método se encarga de eliminar el registro que se indique mediante su id,
      * este método se encarga de encontrar el registro que se requirio en la base de datos
      * mediante las variables de conexión Http, las cuales hacen que la base de datos y el código
      * se puedan conectar, haciendo que mediante el id, se pueda eliminar el registro mediante el id
      * @param id es la variable de identificación que se utilizara para poder buscar los registros
      * @throws Exception es lo que se devolvera si la conexión Http es diferente a 200, de otra manera continua el código
      */

    @Override
    public void deleteResource(int id) throws Exception {
        //Muchachos por eso les quité el copiar y pegar en clase
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/kids/"+id);//aquí debemos colocar al final al id que entra por parametro

            connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        if(connection.getResponseCode()!=200){
            throw new Exception();
        }
        connection.disconnect();
    }

     /***
      * Este método se encarga de actualizar los datos de un registro en la base de datos mediante las variable
      * de identificación, esto se realizara mediante las variables de conexión, las cuales se encargaran de
      * poder buscar el registro que se indico para actualizar los datos
      * @param kid es el objeto de Kid y se utilizara como variable de identificación la cual se usara para poder encontrar el registro
      * @throws Exception es lo que se devolvera si la conexión Http es distita a 200, de otra manera continua el código
      */

    @Override
    public void putResource(Kid kid) throws Exception {
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/kids");
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        connection.setDoOutput(true);
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonKid = new JSONObject(objectMapper.writeValueAsString(kid));
        OutputStream transmisionSalida = connection.getOutputStream();
        byte[] salidaBytes = jsonKid.toString().getBytes(StandardCharsets.UTF_8);
        transmisionSalida.write(salidaBytes);
        if (connection.getResponseCode()!=200){
            throw new Exception();
        }
        connection.disconnect();
    }

    @Override
    public ObservableList<Kid> getAllResourcesByName(String name) throws Exception {
        String nameReplace = name.replace(" ", "%20");
        uri = new URI("http://localhost:4040/sbcm/registrolibrerias/kids/search/"+nameReplace);
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", "JAVAFX 1.0 SNAPSHOT (Windows 10; x64)");
        if (connection.getResponseCode() != 200)
            throw new Exception();

        InputStreamReader lecturaTransmision = new InputStreamReader(connection.getInputStream());
        BufferedReader lecturaBufer = new BufferedReader(lecturaTransmision);
        String line = lecturaBufer.readLine();
        StringBuilder lines = new StringBuilder();
        while (line != null){
            lines.append(line);
            line = lecturaBufer.readLine();
        }
        lecturaBufer.close();

        JSONArray consultaArray = new JSONArray(lines.toString());
        ObservableList<Kid> consultaLista = FXCollections.observableArrayList();
        ObjectMapper mapper = new ObjectMapper();

        for (Object o: consultaArray){
            JSONObject json = (JSONObject) o;
            Kid kid = mapper.readValue(json.toString(), Kid.class);
            consultaLista.addAll(kid);
        }
        connection.disconnect();


        return consultaLista;
    }

         @Override
    public void setConnection(HttpURLConnection connection) {

    }

    @Override
    public URI getUri() {
        return null;
    }
}
