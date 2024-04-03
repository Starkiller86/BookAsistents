package org.sbcm.Dao;

import javafx.collections.ObservableList;
import org.json.JSONObject;
import org.sbcm.Model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class UserDaoImp implements CRUD<User> {



    HttpURLConnection connection;
    URI uri;
    @Override
    public ObservableList<User> getAllResources() throws Exception {
        return null;
    }

    @Override
    public int postResourse(User user) throws Exception {
        return 0;
    }

    @Override
    public User getOneResourceById(int id) throws Exception {
        return null;
    }

    @Override
    public void deleteResource(int id) throws Exception {

    }

    @Override
    public void putResource(User user) throws Exception {

    }

    public int putConfirmResource(User user) throws Exception{
        uri = new URI(host +":4040/sbcm/users");
        connection = (HttpURLConnection) uri.toURL().openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "JAVAFX/1.0 SNAPSHOT (Windows 11; x64)");
        connection.setDoOutput(true);

        JSONObject jsonAdult = new JSONObject(mapper.writeValueAsString(user));
        OutputStream transmisionSalida = connection.getOutputStream();
        byte[] salidaBytes = jsonAdult.toString().getBytes(StandardCharsets.UTF_8);
        transmisionSalida.write(salidaBytes);
        if (connection.getResponseCode()!=200){
            throw new Exception();
        }
        InputStreamReader in = new InputStreamReader(connection.getInputStream());
        BufferedReader buf = new BufferedReader(in);
        String line = buf.readLine();
        StringBuilder build = new StringBuilder();
        while(line != null){
            build.append(line);
            line = buf.readLine();
        }
        buf.close();
        connection.disconnect();
        System.out.println(build);
        return Integer.parseInt(build.toString());
    }

    @Override
    public ObservableList<User> getAllResourcesByName(String name) throws Exception {
        return null;
    }

    @Override
    public void putAssistance(User user) throws Exception {

    }

    @Override
    public void setConnection(HttpURLConnection connection) {

    }

    @Override
    public URI getUri() {
        return null;
    }
}
