package org.sbcm.Dao;

import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.sbcm.Model.UserRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class UserRequestDao implements CRUD<UserRequest> {
    HttpURLConnection connection;
    URI uri;

    @Override
    public ObservableList<UserRequest> getAllResources() throws Exception {
        return null;
    }

    @Override
    public int postResourse(UserRequest userRequest) throws Exception {
        uri = new URI(this.host +"sbcm/user-request");
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type" , "application/json");
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent","JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        JSONObject jsonAdult = new JSONObject(mapper.writeValueAsString(userRequest));
        OutputStream transmisionSalida = connection.getOutputStream();
        byte[] salidaBytes = jsonAdult.toString().getBytes(StandardCharsets.UTF_8);
        transmisionSalida.write(salidaBytes);
        if(connection.getResponseCode()!=200){
            throw  new Exception();
        }//Se supone que cómo solo enviabamos aquí terminaba el metodo post, pero ahora regresa un entero, entonces vamos a recibir ese entero

        InputStreamReader lecturaTransmision = new InputStreamReader(connection.getInputStream());
        BufferedReader lecturaBuffer = new BufferedReader(lecturaTransmision);
        String line = lecturaBuffer.readLine();
        StringBuilder lines = new StringBuilder();
        while (line!=null){
            lines.append(line);
            line = lecturaBuffer.readLine();
        }
        lecturaBuffer.close();
        int idRegistrado = Integer.parseInt(lines.toString());


        connection.disconnect();
        return idRegistrado;

    }

    @Override
    public UserRequest getOneResourceById(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteResource(int id) throws Exception {

    }

    @Override
    public void putResource(UserRequest userRequest) throws Exception {


    }

    @Override
    public ObservableList<UserRequest> getAllResourcesByName(String name) throws Exception {
        return null;
    }

    @Override
    public void putAssistance(UserRequest userRequest) throws Exception {

    }

    @Override
    public void setConnection(HttpURLConnection connection) {

    }

    @Override
    public URI getUri() {
        return null;
    }
}
